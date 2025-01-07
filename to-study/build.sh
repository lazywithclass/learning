#!/usr/bin/env bash

OBSIDIAN_VAULT="obsidian-vault"
NOTES_DIR="site"
OUTPUT_FILE="$NOTES_DIR/notes.html"


find $OBSIDIAN_VAULT/*md -maxdepth 1 -type f | while read -r file; do
    echo "$file"
    filename=$(echo "$file" | cut -d/ -f2)
    pandoc --quiet "$file" -s -o "$NOTES_DIR/$(echo $filename).html"
done

cat > "$OUTPUT_FILE" <<EOF
<div class="notes">
  <ul>
EOF

for file in "$NOTES_DIR"/*; do
  filename=$(basename "$file")
  cat >> "$OUTPUT_FILE" <<EOF
    <li><a href="$NOTES/$filename">"${filename%.html}"</a></li>
EOF
done

cat >> "$OUTPUT_FILE" <<EOF
  </ul>
</div>
EOF

