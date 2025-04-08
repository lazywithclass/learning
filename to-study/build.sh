#!/usr/bin/env bash

OBSIDIAN_VAULT="obsidian-vault"
NOTES_DIR="site"
OUTPUT_FILE="$NOTES_DIR/notes.html"

normalize_filename() {
    local filename="$1"
    echo "$filename" | tr '[:upper:]' '[:lower:]' | tr ' ' '-'
}

find $OBSIDIAN_VAULT/*md -maxdepth 1 -type f | while read -r file; do
    filename=$(echo "$file" | cut -d/ -f2)
    title=$(echo "$filename" | cut -d. -f1)
    filename=$(normalize_filename "$filename")
    if ! grep -q "$filename" whitelist; then
      continue
    fi
    echo "$file"
    pandoc --quiet "$file" --metadata title="$title" --template=custom.html5 -o "$NOTES_DIR/$(echo $filename).html" --lua-filter=pandoc-lua/add_links.lua

done

cat > "$OUTPUT_FILE" <<EOF
<div class="notes">
  <ul>
EOF

for file in "$NOTES_DIR"/*; do
  filename=$(basename "$file")
  if ! grep -q "$filename" whitelist; then
    continue
  fi
  cat >> "$OUTPUT_FILE" <<EOF
    <li><a href="$NOTES/$filename">"${filename%.html}"</a></li>
EOF
done

cat >> "$OUTPUT_FILE" <<EOF
  </ul>
</div>
EOF

mkdir -p "$NOTES_DIR/attachments"
cp -r "$OBSIDIAN_VAULT/attachments" "$NOTES_DIR/"

mkdir -p "$NOTES_DIR/iframes"
cp -r "iframes" "$NOTES_DIR/"
