---
cssclasses:
  - cornell-note
tags:
  - git
  - git-flow
  - git-exercises
---

# Git

Exercises, sorted by descending quality: 
* https://gitexercises.fracz.com/
* https://github.com/eficode-academy/git-katas
* https://training-course-material.com/training/Git_exercises



`cat -A $filename` shows line endings

https://www.aleksandrhovhannisyan.com/blog/crlf-vs-lf-normalizing-line-endings-in-git/#the-eol-attribute-controlling-line-endings-in-gits-working-tree

```
git config --global core.autocrlf input
git config --global core.safecrlf warn
```

## Useful commands

git branch -m master main


TODO extract commands and list them here

https://about.gitlab.com/images/press/git-cheat-sheet.pdf
https://education.github.com/git-cheat-sheet-education.pdfs

# Git-flow

https://nvie.com/posts/a-successful-git-branching-model/

![gitflow|400](gitflow.png)

# --no-ff

![no-ff|400](no-ff.png)

| name             | branch off from | merge back into    |
| ---------------- | --------------- | ------------------ |
| feature branches | develop         | develop            |
| release branches | develop         | develop and master |
| hotfix branches  | master          | develop and master |


