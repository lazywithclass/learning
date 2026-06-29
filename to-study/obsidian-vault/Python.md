---
cssclasses:
  - cornell-note
tags:
  - python
---

[Code examples](https://github.com/lazywithclass/learning/tree/master/programming-languages/python)

## Compile to C

```language-bash
python3 -m py_compile file.py
```

## uv

Useful commands:

* `uv venv --python 3.13`
* `uv venv` - creates the virtual environment
* `uv pip install -r pyproject.toml`
* `uv lock` - creates the lockfile