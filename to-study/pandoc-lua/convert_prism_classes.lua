function CodeBlock(el)
  -- Convert all code block classes to Prism format by adding "language-" prefix
  local new_classes = {}
  
  for i, class in ipairs(el.classes) do
    -- If class already starts with "language-", keep it as is
    if class:match("^language-") then
      table.insert(new_classes, class)
    -- If class is "sourceCode", skip it (it's Pandoc's wrapper class)
    elseif class == "sourceCode" then
      -- skip
    -- Otherwise, add "language-" prefix
    else
      table.insert(new_classes, "language-" .. class)
    end
  end
  
  -- If no classes were found, but there's a language attribute, use it
  if #new_classes == 0 and el.attr and el.attr.classes and #el.attr.classes > 0 then
    for i, class in ipairs(el.attr.classes) do
      if class ~= "sourceCode" and not class:match("^language-") then
        table.insert(new_classes, "language-" .. class)
      elseif class:match("^language-") then
        table.insert(new_classes, class)
      end
    end
  end
  
  el.classes = new_classes
  return el
end
