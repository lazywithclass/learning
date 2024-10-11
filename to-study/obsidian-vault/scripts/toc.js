function createTOC(tp, minDepth = 1, maxDepth = Number.MAX_SAFE_INTEGER) { 
  let filename = tp.config.target_file.basename
  let singleIndent = app.vault.getConfig('useTab') 
    ? '\t' 
    : ' '.repeat(app.vault.getConfig('tabSize'))
    
  let countHash = (str) => (str.match(/#/g) || []).length
  let headerText = (str) => (str.substr(str.indexOf("# ")))

  return tp.file.content
    .split('\n')
    .filter((line) => line.startsWith('#'))
    .map((heading) => { 
      return { 
        heading, 
        "depth": countHash(heading)
      }
    })
    .filter((obj) => obj.depth >= minDepth && obj.depth <= maxDepth)
    .map((obj) => {
      let indent = singleIndent.repeat(obj.depth - minDepth)
      let text = headerText(obj.heading)
      return `${indent}- [[${filename}#${text}|${text}]]`
    })
    .join('\n')
    .concat('\n')
}

module.exports = createTOC;