#!/usr/bin/env bash

# Obsidian Canvas to HTML Converter
# Converts .canvas files to standalone HTML files

set -e

CANVAS_DIR="/home/lazywithclass/workspace/learning/to-study/obsidian-vault"
OUTPUT_DIR="$CANVAS_DIR/html_output"

# Create output directory
mkdir -p "$OUTPUT_DIR"

# Function to convert canvas file to HTML
convert_canvas_to_html() {
    local canvas_file="$1"
    local base_name=$(basename "$canvas_file" .canvas)
    local html_file="$OUTPUT_DIR/${base_name}.html"
    local css_file="$OUTPUT_DIR/canvas_styles.css"
    
    echo "Converting $canvas_file to $html_file"
    
    # Create the canvas_styles.css if it doesn't exist
    if [[ ! -f "$css_file" ]]; then
        cat > "$css_file" << 'EOF'
.canvas-container {
    position: relative;
    width: 100vw;
    height: 100vh;
    background-color: #f9f9f9;
    overflow: hidden;
}

.node {
    position: absolute;
    border: 1px solid #ccc;
    padding: 10px;
    background: #fff;
    box-shadow: 2px 2px 5px rgba(0,0,0,0.1);
    border-radius: 4px;
    font-family: Arial, sans-serif;
    font-size: 14px;
    overflow: hidden;
}

.node.file {
    background-color: #e8f4fd;
    border-color: #b3d9ff;
}

.node.text {
    background-color: #fff;
    color: #333;
}

.node.link {
    background-color: #f0f8ff;
    border-color: #87ceeb;
}

.node.group {
    background-color: #f5f5f5;
    border: 2px dashed #ccc;
    font-weight: bold;
}

.node-text-content {
    margin: 0;
    line-height: 1.4;
}

.node-link-content {
    display: block;
    color: #0066cc;
    text-decoration: none;
    font-weight: bold;
}

.node-file-content {
    margin: 0;
}

.edges-layer {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    pointer-events: none;
    z-index: -1;
}

.edge-line {
    stroke: #666;
    stroke-width: 2;
    stroke-dasharray: 5,5;
}
EOF
    fi
    
    # Parse JSON and generate HTML
    python3 - <<EOF
import json
import os
import re

def sanitize_filename(filename):
    """Remove unsafe characters from filename"""
    return re.sub(r'[^a-zA-Z0-9_.-]', '_', filename)

def read_markdown_file(md_filepath):
    """Read markdown file and convert basic markdown to HTML"""
    try:
        with open(md_filepath, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Simple markdown to HTML conversion
        # Convert headers
        content = re.sub(r'^# (.+)$', r'<h1>\1</h1>', content, flags=re.MULTILINE)
        content = re.sub(r'^## (.+)$', r'<h2>\1</h2>', content, flags=re.MULTILINE)
        
        # Convert bold and italic
        content = re.sub(r'\*\*(.+?)\*\*', r'<strong>\1</strong>', content)
        content = re.sub(r'\*(.+?)\*', r'<em>\1</em>', content)
        
        # Convert lists
        content = re.sub(r'^- (.+)$', r'<li>\1</li>', content, flags=re.MULTILINE)
        content = re.sub(r'(<li>.+</li>)', r'<ul>\1</ul>', content, flags=re.DOTALL)
        
        # Convert links
        content = re.sub(r'\[(.+?)\]\((.+?)\)', r'<a href="\2">\1</a>', content)
        
        return content
    except:
        return f"<p>Error reading {os.path.basename(md_filepath)}</p>"

with open("$canvas_file", 'r') as f:
    canvas_data = json.load(f)

html_content = f'''<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{base_name}</title>
    <link rel="stylesheet" href="canvas_styles.css">
</head>
<body>
    <div class="canvas-container">
        <svg class="edges-layer" xmlns="http://www.w3.org/2000/svg">
'''

# Add edges first (low z-index)
node_positions = {}
for node in canvas_data.get('nodes', []):
    node_id = node.get('id')
    if node_id:
        node_positions[node_id] = {
            'x': node.get('x', 0),
            'y': node.get('y', 0),
            'width': node.get('width', 200),
            'height': node.get('height', 100)
        }

for edge in canvas_data.get('edges', []):
    from_node = edge.get('fromNode')
    to_node = edge.get('toNode')
    if from_node in node_positions and to_node in node_positions:
        from_pos = node_positions[from_node]
        to_pos = node_positions[to_node]
        
        # Calculate center positions
        from_x = from_pos['x'] + from_pos['width'] / 2
        from_y = from_pos['y'] + from_pos['height'] / 2
        to_x = to_pos['x'] + to_pos['width'] / 2
        to_y = to_pos['y'] + to_pos['height'] / 2
        
        html_content += f'            <line class="edge-line" x1="{from_x}" y1="{from_y}" x2="{to_x}" y2="{to_y}" />\n'

html_content += '        </svg>\n'

# Add nodes
for node in canvas_data.get('nodes', []):
    node_type = node.get('type', 'text')
    x = node.get('x', 0)
    y = node.get('y', 0)
    width = node.get('width', 200)
    height = node.get('height', 100)
    
    node_content = ""
    
    if node_type == 'text':
        text_content = node.get('text', '')
        # Handle multiline text
        text_content = text_content.replace('\n', '<br>')
        node_content = f'<div class="node-text-content">{text_content}</div>'
        
    elif node_type == 'file':
        file_path = node.get('file', '')
        full_path = os.path.join("$CANVAS_DIR", file_path)
        if os.path.exists(full_path):
            md_content = read_markdown_file(full_path)
            node_content = f'<div class="node-file-content">{md_content}</div>'
        else:
            node_content = f'<div class="node-file-content">File not found: {file_path}</div>'
        
    elif node_type == 'link':
        url = node.get('url', '#')
        node_content = f'<a href="{url}" target="_blank" class="node-link-content">External Link: {url[:50]}...</a>'
        
    elif node_type == 'group':
        label = node.get('label', 'Group')
        node_content = f'<div class="node-text-content">{label}</div>'
    
    html_content += f'        <div class="node {node_type}" style="left:{x}px; top:{y}px; width:{width}px; height:{height}px;">{node_content}</div>\n'

html_content += '''    </div>
</body>
</html>'''

with open("$html_file", "w", encoding="utf-8") as f:
    f.write(html_content)

print(f"Successfully converted {os.path.basename('$canvas_file')} to HTML")
EOF
}

# Convert all canvas files
for canvas_file in "$CANVAS_DIR"/*.canvas; do
    if [[ -f "$canvas_file" ]]; then
        convert_canvas_to_html "$canvas_file"
    fi
done

echo "Conversion complete! HTML files saved to: $OUTPUT_DIR"
echo "Files created:"
ls -la "$OUTPUT_DIR"
