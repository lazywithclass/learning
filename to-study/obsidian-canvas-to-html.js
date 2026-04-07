#!/usr/bin/env node

/**
 * obsidian-canvas-to-html.js
 *
 * Converts an Obsidian .canvas file (JSON) into a self-contained,
 * interactive HTML page with pan, zoom, and clickable links.
 *
 * Usage:
 *   node obsidian-canvas-to-html.js input.canvas > output.html
 *   node obsidian-canvas-to-html.js input.canvas -o output.html
 *   node obsidian-canvas-to-html.js input.canvas --title "My Canvas"
 */

const fs = require("fs");
const path = require("path");

// --- CLI args ---
const args = process.argv.slice(2);
if (args.length === 0 || args.includes("--help") || args.includes("-h")) {
  console.error(`Usage: node obsidian-canvas-to-html.js <input.canvas> [-o output.html] [--title "Title"]`);
  process.exit(1);
}

const inputFile = args[0];
let outputFile = null;
let title = path.basename(inputFile, ".canvas");

for (let i = 1; i < args.length; i++) {
  if ((args[i] === "-o" || args[i] === "--output") && args[i + 1]) {
    outputFile = args[++i];
  } else if (args[i] === "--title" && args[i + 1]) {
    title = args[++i];
  }
}

// --- Parse canvas ---
let canvas;
try {
  const raw = fs.readFileSync(inputFile, "utf-8");
  canvas = JSON.parse(raw);
} catch (e) {
  console.error(`Error reading ${inputFile}: ${e.message}`);
  process.exit(1);
}

const nodes = canvas.nodes || [];
const edges = canvas.edges || [];

// --- Color map (Obsidian's 6 card colors) ---
const OBSIDIAN_COLORS = {
  "1": { bg: "#fb464c", fg: "#fff" },    // red
  "2": { bg: "#e9973f", fg: "#fff" },    // orange
  "3": { bg: "#e0de71", fg: "#1a1a1a" }, // yellow
  "4": { bg: "#44cf6e", fg: "#fff" },    // green
  "5": { bg: "#53dfdd", fg: "#1a1a1a" }, // cyan
  "6": { bg: "#a882ff", fg: "#fff" },    // purple
};

function escapeHtml(str) {
  return str
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
}

// Simple markdown-ish: **bold**, *italic*, `code`, newlines, bullet lists
function renderMarkdown(text) {
  let html = escapeHtml(text);
  html = html.replace(/\*\*(.+?)\*\*/g, "<strong>$1</strong>");
  html = html.replace(/\*(.+?)\*/g, "<em>$1</em>");
  html = html.replace(/`(.+?)`/g, "<code>$1</code>");
  // bullet lists
  html = html.replace(/^[\t ]*\* (.+)$/gm, "<li>$1</li>");
  html = html.replace(/(<li>.*<\/li>\n?)+/g, (match) => `<ul>${match}</ul>`);
  html = html.replace(/\n/g, "<br>");
  return html;
}

// --- Build node HTML ---
function buildNodeHtml(node) {
  const colorInfo = node.color && OBSIDIAN_COLORS[node.color];

  switch (node.type) {
    case "text": {
      const bg = colorInfo ? colorInfo.bg : "var(--card-bg)";
      const fg = colorInfo ? colorInfo.fg : "var(--card-fg)";
      return `<div class="canvas-node canvas-node--text" 
        data-id="${node.id}"
        style="left:${node.x}px; top:${node.y}px; width:${node.width}px; height:${node.height}px; --node-bg:${bg}; --node-fg:${fg};">
        <div class="node-content">${renderMarkdown(node.text)}</div>
      </div>`;
    }

    case "file": {
      const label = path.basename(node.file, ".md");
      const bg = colorInfo ? colorInfo.bg : "var(--card-file-bg)";
      const fg = colorInfo ? colorInfo.fg : "var(--card-fg)";
      // Link to the markdown file from webserver root
      const href = "/" + node.file;
      return `<a class="canvas-node canvas-node--file"
        data-id="${node.id}"
        href="${escapeHtml(href)}"
        style="left:${node.x}px; top:${node.y}px; width:${node.width}px; height:${node.height}px; --node-bg:${bg}; --node-fg:${fg};">
        <div class="node-title">${escapeHtml(label)}</div>
      </a>`;
    }

    case "link": {
      const displayUrl = node.url.length > 60 ? node.url.substring(0, 57) + "..." : node.url;
      const isYoutube = /youtube\.com|youtu\.be/.test(node.url);
      const icon = isYoutube ? "▶️" : "🔗";
      return `<div class="canvas-node canvas-node--link"
        data-id="${node.id}"
        style="left:${node.x}px; top:${node.y}px; width:${node.width}px; height:${node.height}px;">
        <a href="${escapeHtml(node.url)}" target="_blank" rel="noopener" class="node-link">
          <span class="link-icon">${icon}</span>
          <span class="link-url">${escapeHtml(displayUrl)}</span>
        </a>
      </div>`;
    }

    case "group": {
      const bg = colorInfo ? colorInfo.bg + "18" : "var(--group-bg)";
      const border = colorInfo ? colorInfo.bg : "var(--group-border)";
      return `<div class="canvas-node canvas-node--group"
        data-id="${node.id}"
        style="left:${node.x}px; top:${node.y}px; width:${node.width}px; height:${node.height}px; --group-fill:${bg}; --group-stroke:${border};">
        ${node.label ? `<div class="group-label">${escapeHtml(node.label)}</div>` : ""}
      </div>`;
    }

    default:
      return "";
  }
}

// --- Edge path calculation ---
// fromSide/toSide: "top" | "bottom" | "left" | "right"
function getAnchor(node, side) {
  const cx = node.x + node.width / 2;
  const cy = node.y + node.height / 2;
  switch (side) {
    case "top":    return { x: cx, y: node.y };
    case "bottom": return { x: cx, y: node.y + node.height };
    case "left":   return { x: node.x, y: cy };
    case "right":  return { x: node.x + node.width, y: cy };
    default:       return { x: cx, y: cy };
  }
}

function buildEdgeSvg(edge, nodeMap) {
  const fromNode = nodeMap[edge.fromNode];
  const toNode = nodeMap[edge.toNode];
  if (!fromNode || !toNode) return "";

  const from = getAnchor(fromNode, edge.fromSide || "bottom");
  const to = getAnchor(toNode, edge.toSide || "top");

  // Cubic bezier with control points extending in the direction of the side
  const dist = Math.max(Math.abs(to.x - from.x), Math.abs(to.y - from.y)) * 0.4;
  const cp1 = offsetControlPoint(from, edge.fromSide || "bottom", dist);
  const cp2 = offsetControlPoint(to, edge.toSide || "top", dist);

  const hasFromArrow = edge.fromEnd === "arrow";
  const hasToArrow = edge.toEnd !== "none"; // default is arrow on toEnd

  return `<path class="edge-path" 
    d="M ${from.x} ${from.y} C ${cp1.x} ${cp1.y}, ${cp2.x} ${cp2.y}, ${to.x} ${to.y}"
    ${hasToArrow ? 'marker-end="url(#arrowhead)"' : ""}
    ${hasFromArrow ? 'marker-start="url(#arrowhead-start)"' : ""}
  />`;
}

function offsetControlPoint(pt, side, dist) {
  switch (side) {
    case "top":    return { x: pt.x, y: pt.y - dist };
    case "bottom": return { x: pt.x, y: pt.y + dist };
    case "left":   return { x: pt.x - dist, y: pt.y };
    case "right":  return { x: pt.x + dist, y: pt.y };
    default:       return pt;
  }
}

// --- Compute canvas bounds for SVG viewbox ---
const nodeMap = {};
nodes.forEach((n) => (nodeMap[n.id] = n));

let minX = Infinity, minY = Infinity, maxX = -Infinity, maxY = -Infinity;
nodes.forEach((n) => {
  minX = Math.min(minX, n.x);
  minY = Math.min(minY, n.y);
  maxX = Math.max(maxX, n.x + (n.width || 0));
  maxY = Math.max(maxY, n.y + (n.height || 0));
});
const PAD = 100;
minX -= PAD; minY -= PAD; maxX += PAD; maxY += PAD;
const svgWidth = maxX - minX;
const svgHeight = maxY - minY;

// --- Build edge SVG layer ---
const edgeSvgPaths = edges.map((e) => buildEdgeSvg(e, nodeMap)).join("\n");

// --- Build all node HTML ---
// Render groups first (behind), then other nodes
const groups = nodes.filter((n) => n.type === "group");
const nonGroups = nodes.filter((n) => n.type !== "group");
const allNodeHtml = [...groups, ...nonGroups].map(buildNodeHtml).join("\n");

// --- Assemble HTML ---
const html = `<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${escapeHtml(title)}</title>
<style>
  :root {
    --bg: #1e1e2e;
    --card-bg: #313244;
    --card-file-bg: #45475a;
    --card-fg: #cdd6f4;
    --card-border: #585b70;
    --group-bg: rgba(88, 91, 112, 0.12);
    --group-border: #585b70;
    --edge-color: #7f849c;
    --edge-width: 2;
    --font-body: "IBM Plex Sans", -apple-system, BlinkMacSystemFont, sans-serif;
    --font-mono: "IBM Plex Mono", "Fira Code", monospace;
    --link-color: #89b4fa;
  }

  @media (prefers-color-scheme: light) {
    :root {
      --bg: #eff1f5;
      --card-bg: #ffffff;
      --card-file-bg: #e6e9ef;
      --card-fg: #4c4f69;
      --card-border: #bcc0cc;
      --group-bg: rgba(108, 112, 134, 0.08);
      --group-border: #bcc0cc;
      --edge-color: #8c8fa1;
      --link-color: #1e66f5;
    }
  }

  @import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@400;500;600&family=IBM+Plex+Mono:wght@400;500&display=swap');

  *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

  html, body {
    width: 100%; height: 100%;
    overflow: hidden;
    background: var(--bg);
    font-family: var(--font-body);
    color: var(--card-fg);
  }

  #toolbar {
    position: fixed; top: 16px; left: 50%; transform: translateX(-50%);
    z-index: 1000;
    display: flex; gap: 6px; align-items: center;
    background: var(--card-bg);
    border: 1px solid var(--card-border);
    border-radius: 10px;
    padding: 6px 12px;
    box-shadow: 0 4px 24px rgba(0,0,0,0.25);
    font-size: 13px;
    user-select: none;
  }
  #toolbar button {
    background: none; border: 1px solid var(--card-border);
    color: var(--card-fg); border-radius: 6px;
    padding: 4px 10px; cursor: pointer; font-size: 14px;
    transition: background 0.15s;
  }
  #toolbar button:hover { background: var(--group-bg); }
  #zoom-display { min-width: 48px; text-align: center; font-variant-numeric: tabular-nums; }

  #canvas-viewport {
    width: 100%; height: 100%;
    overflow: hidden;
    cursor: grab;
  }
  #canvas-viewport.grabbing { cursor: grabbing; }

  #canvas-world {
    position: relative;
    transform-origin: 0 0;
    will-change: transform;
  }

  /* --- Edges SVG --- */
  #edge-layer {
    position: absolute;
    top: 0; left: 0;
    pointer-events: none;
    overflow: visible;
  }
  .edge-path {
    fill: none;
    stroke: var(--edge-color);
    stroke-width: var(--edge-width);
    stroke-linecap: round;
  }

  /* --- Nodes --- */
  .canvas-node {
    position: absolute;
    border-radius: 8px;
    overflow: hidden;
    transition: box-shadow 0.2s;
  }
  .canvas-node:hover {
    box-shadow: 0 0 0 2px var(--link-color), 0 8px 32px rgba(0,0,0,0.2);
    z-index: 10;
  }

  .canvas-node--text {
    background: var(--node-bg, var(--card-bg));
    color: var(--node-fg, var(--card-fg));
    border: 1px solid var(--card-border);
    display: flex; align-items: flex-start; justify-content: flex-start;
  }
  .canvas-node--text .node-content {
    padding: 12px 14px;
    font-size: 13px;
    line-height: 1.55;
    overflow-y: auto;
    max-height: 100%;
    word-wrap: break-word;
  }
  .canvas-node--text .node-content code {
    font-family: var(--font-mono);
    background: rgba(0,0,0,0.15);
    padding: 1px 4px;
    border-radius: 3px;
    font-size: 12px;
  }
  .canvas-node--text .node-content ul {
    padding-left: 18px;
    margin: 4px 0;
  }

  .canvas-node--file {
    background: var(--node-bg, var(--card-file-bg));
    color: var(--node-fg, var(--card-fg));
    border: 1px solid var(--card-border);
    display: flex; align-items: center; justify-content: center;
    text-decoration: none;
    cursor: pointer;
    transition: box-shadow 0.2s, transform 0.15s;
  }
  .canvas-node--file:hover {
    transform: scale(1.02);
  }
  .node-title {
    padding: 14px;
    font-weight: 600;
    font-size: 15px;
    text-align: center;
    line-height: 1.4;
    word-wrap: break-word;
  }

  .canvas-node--link {
    background: var(--card-bg);
    border: 1px solid var(--card-border);
    display: flex; align-items: center; justify-content: center;
  }
  .node-link {
    display: flex; flex-direction: column; align-items: center;
    gap: 10px; padding: 20px;
    text-decoration: none; color: var(--link-color);
    transition: opacity 0.15s;
    text-align: center;
  }
  .node-link:hover { opacity: 0.8; }
  .link-icon { font-size: 32px; }
  .link-url { font-size: 11px; font-family: var(--font-mono); word-break: break-all; opacity: 0.7; }

  .canvas-node--group {
    background: var(--group-fill, var(--group-bg));
    border: 2px dashed var(--group-stroke, var(--group-border));
    border-radius: 12px;
    pointer-events: none;
  }
  .group-label {
    position: absolute;
    top: -28px; left: 12px;
    font-size: 13px;
    font-weight: 600;
    color: var(--card-fg);
    opacity: 0.7;
    letter-spacing: 0.03em;
    text-transform: uppercase;
    pointer-events: none;
  }

  /* --- Minimap --- */
  #minimap {
    position: fixed; bottom: 16px; right: 16px;
    width: 200px; height: 140px;
    background: var(--card-bg);
    border: 1px solid var(--card-border);
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 4px 16px rgba(0,0,0,0.2);
    z-index: 1000;
    cursor: pointer;
  }
  #minimap canvas { width: 100%; height: 100%; }
  #minimap-viewport {
    position: absolute;
    border: 2px solid var(--link-color);
    background: rgba(137, 180, 250, 0.08);
    pointer-events: none;
  }
</style>
</head>
<body>

<div id="toolbar">
  <button id="btn-zoom-out" title="Zoom out">−</button>
  <span id="zoom-display">100%</span>
  <button id="btn-zoom-in" title="Zoom in">+</button>
  <button id="btn-fit" title="Fit to screen">⊞ Fit</button>
</div>

<div id="canvas-viewport">
  <div id="canvas-world">
    <svg id="edge-layer"
         viewBox="${minX} ${minY} ${svgWidth} ${svgHeight}"
         width="${svgWidth}" height="${svgHeight}"
         style="left:${minX}px; top:${minY}px; width:${svgWidth}px; height:${svgHeight}px;"
         xmlns="http://www.w3.org/2000/svg">
      <defs>
        <marker id="arrowhead" viewBox="0 0 10 10" refX="10" refY="5"
                markerWidth="8" markerHeight="8" orient="auto-start-reverse"
                fill="var(--edge-color)">
          <path d="M 0 0 L 10 5 L 0 10 Z"/>
        </marker>
        <marker id="arrowhead-start" viewBox="0 0 10 10" refX="0" refY="5"
                markerWidth="8" markerHeight="8" orient="auto-start-reverse"
                fill="var(--edge-color)">
          <path d="M 10 0 L 0 5 L 10 10 Z"/>
        </marker>
      </defs>
      ${edgeSvgPaths}
    </svg>
    ${allNodeHtml}
  </div>
</div>

<div id="minimap">
  <canvas id="minimap-canvas"></canvas>
  <div id="minimap-viewport"></div>
</div>

<script>
(() => {
  const vp = document.getElementById("canvas-viewport");
  const world = document.getElementById("canvas-world");
  const zoomDisplay = document.getElementById("zoom-display");

  let scale = 1, tx = 0, ty = 0;
  let isPanning = false, startX = 0, startY = 0;

  // --- Canvas bounds ---
  const bounds = { minX: ${minX}, minY: ${minY}, maxX: ${maxX}, maxY: ${maxY},
                   w: ${svgWidth}, h: ${svgHeight} };

  function applyTransform() {
    world.style.transform = \`translate(\${tx}px, \${ty}px) scale(\${scale})\`;
    zoomDisplay.textContent = Math.round(scale * 100) + "%";
    updateMinimap();
  }

  function fitToScreen() {
    const vpW = vp.clientWidth, vpH = vp.clientHeight;
    const scaleX = vpW / bounds.w, scaleY = vpH / bounds.h;
    scale = Math.min(scaleX, scaleY) * 0.9;
    tx = (vpW - bounds.w * scale) / 2 - bounds.minX * scale;
    ty = (vpH - bounds.h * scale) / 2 - bounds.minY * scale;
    applyTransform();
  }

  // --- Pan (with click-vs-drag detection for file card links) ---
  let dragStartClientX = 0, dragStartClientY = 0;
  let clickedFileLink = null;
  const DRAG_THRESHOLD = 5; // px before it counts as a drag

  vp.addEventListener("pointerdown", (e) => {
    const fileLink = e.target.closest("a.canvas-node--file");
    dragStartClientX = e.clientX;
    dragStartClientY = e.clientY;
    
    if (fileLink) {
      // Don't navigate yet — wait to see if it's a drag
      clickedFileLink = fileLink;
      e.preventDefault();
    }
    if (e.target.closest("a.node-link")) return; // external links: let browser handle

    isPanning = true;
    startX = e.clientX - tx;
    startY = e.clientY - ty;
    vp.classList.add("grabbing");
    vp.setPointerCapture(e.pointerId);
  });
  vp.addEventListener("pointermove", (e) => {
    if (!isPanning) return;
    const dx = e.clientX - dragStartClientX;
    const dy = e.clientY - dragStartClientY;
    if (Math.abs(dx) > DRAG_THRESHOLD || Math.abs(dy) > DRAG_THRESHOLD) {
      clickedFileLink = null; // moved too far — this is a drag, not a click
    }
    tx = e.clientX - startX;
    ty = e.clientY - startY;
    applyTransform();
  });
  vp.addEventListener("pointerup", () => {
    isPanning = false;
    vp.classList.remove("grabbing");
    if (clickedFileLink) {
      // Was a genuine click (no significant drag) — navigate
      window.location.href = clickedFileLink.getAttribute("href");
      clickedFileLink = null;
    }
  });

  // --- Zoom (wheel) ---
  vp.addEventListener("wheel", (e) => {
    e.preventDefault();
    const delta = e.deltaY > 0 ? 0.9 : 1.1;
    const rect = vp.getBoundingClientRect();
    const mx = e.clientX - rect.left;
    const my = e.clientY - rect.top;
    tx = mx - (mx - tx) * delta;
    ty = my - (my - ty) * delta;
    scale *= delta;
    scale = Math.max(0.02, Math.min(5, scale));
    applyTransform();
  }, { passive: false });

  // --- Zoom (touch pinch) ---
  let lastPinchDist = 0;
  vp.addEventListener("touchstart", (e) => {
    if (e.touches.length === 2) {
      const dx = e.touches[0].clientX - e.touches[1].clientX;
      const dy = e.touches[0].clientY - e.touches[1].clientY;
      lastPinchDist = Math.sqrt(dx * dx + dy * dy);
    }
  }, { passive: true });
  vp.addEventListener("touchmove", (e) => {
    if (e.touches.length === 2) {
      const dx = e.touches[0].clientX - e.touches[1].clientX;
      const dy = e.touches[0].clientY - e.touches[1].clientY;
      const dist = Math.sqrt(dx * dx + dy * dy);
      if (lastPinchDist > 0) {
        const delta = dist / lastPinchDist;
        const cx = (e.touches[0].clientX + e.touches[1].clientX) / 2;
        const cy = (e.touches[0].clientY + e.touches[1].clientY) / 2;
        const rect = vp.getBoundingClientRect();
        const mx = cx - rect.left, my = cy - rect.top;
        tx = mx - (mx - tx) * delta;
        ty = my - (my - ty) * delta;
        scale = Math.max(0.02, Math.min(5, scale * delta));
        applyTransform();
      }
      lastPinchDist = dist;
    }
  }, { passive: true });

  // --- Buttons ---
  document.getElementById("btn-zoom-in").addEventListener("click", () => {
    const cx = vp.clientWidth / 2, cy = vp.clientHeight / 2;
    tx = cx - (cx - tx) * 1.2; ty = cy - (cy - ty) * 1.2;
    scale = Math.min(5, scale * 1.2);
    applyTransform();
  });
  document.getElementById("btn-zoom-out").addEventListener("click", () => {
    const cx = vp.clientWidth / 2, cy = vp.clientHeight / 2;
    tx = cx - (cx - tx) * 0.8; ty = cy - (cy - ty) * 0.8;
    scale = Math.max(0.02, scale * 0.8);
    applyTransform();
  });
  document.getElementById("btn-fit").addEventListener("click", fitToScreen);

  // --- Minimap ---
  const mmCanvas = document.getElementById("minimap-canvas");
  const mmVp = document.getElementById("minimap-viewport");
  const mmCtx = mmCanvas.getContext("2d");

  function drawMinimapBackground() {
    const dpr = window.devicePixelRatio || 1;
    const mmW = mmCanvas.parentElement.clientWidth;
    const mmH = mmCanvas.parentElement.clientHeight;
    mmCanvas.width = mmW * dpr; mmCanvas.height = mmH * dpr;
    mmCtx.scale(dpr, dpr);

    const scaleX = mmW / bounds.w, scaleY = mmH / bounds.h;
    const s = Math.min(scaleX, scaleY) * 0.9;
    const ox = (mmW - bounds.w * s) / 2 - bounds.minX * s;
    const oy = (mmH - bounds.h * s) / 2 - bounds.minY * s;

    mmCtx.clearRect(0, 0, mmW, mmH);
    // Draw nodes as small colored rectangles
    const nodes = document.querySelectorAll(".canvas-node");
    nodes.forEach((n) => {
      const left = parseFloat(n.style.left);
      const top = parseFloat(n.style.top);
      const w = parseFloat(n.style.width);
      const h = parseFloat(n.style.height);
      const isGroup = n.classList.contains("canvas-node--group");
      mmCtx.fillStyle = isGroup ? "rgba(137,180,250,0.08)" : "rgba(137,180,250,0.4)";
      mmCtx.fillRect(left * s + ox, top * s + oy, w * s, h * s);
    });

    mmCanvas._mmScale = s;
    mmCanvas._mmOx = ox;
    mmCanvas._mmOy = oy;
  }

  function updateMinimap() {
    const mmW = mmCanvas.parentElement.clientWidth;
    const mmH = mmCanvas.parentElement.clientHeight;
    const s = mmCanvas._mmScale || 1;
    const ox = mmCanvas._mmOx || 0;
    const oy = mmCanvas._mmOy || 0;

    // Viewport rect in canvas-world coords
    const vpW = vp.clientWidth / scale;
    const vpH = vp.clientHeight / scale;
    const vpX = -tx / scale;
    const vpY = -ty / scale;

    mmVp.style.left = (vpX * s + ox) + "px";
    mmVp.style.top = (vpY * s + oy) + "px";
    mmVp.style.width = (vpW * s) + "px";
    mmVp.style.height = (vpH * s) + "px";
  }

  // --- Init ---
  fitToScreen();
  drawMinimapBackground();
  window.addEventListener("resize", () => { drawMinimapBackground(); updateMinimap(); });

  // --- Minimap click-to-navigate ---
  const mm = document.getElementById("minimap");
  mm.addEventListener("click", (e) => {
    const rect = mm.getBoundingClientRect();
    const mx = e.clientX - rect.left, my = e.clientY - rect.top;
    const s = mmCanvas._mmScale || 1;
    const ox = mmCanvas._mmOx || 0, oy = mmCanvas._mmOy || 0;
    // World coordinate clicked
    const wx = (mx - ox) / s;
    const wy = (my - oy) / s;
    // Center viewport on that world point
    tx = vp.clientWidth / 2 - wx * scale;
    ty = vp.clientHeight / 2 - wy * scale;
    applyTransform();
  });
})();
</script>
</body>
</html>`;

// --- Output ---
if (outputFile) {
  fs.writeFileSync(outputFile, html);
  console.error(`Written to ${outputFile}`);
} else {
  process.stdout.write(html);
}
