function Header(el)
  local text = pandoc.utils.stringify(el.content)
  local slug = text:lower():gsub("[^%w%s-]", ""):gsub("%s+", "-")
  local link = pandoc.Link(text, "#" .. slug)
  el.content = {link}
  return el
end

