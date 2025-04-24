## Python modules

The directory structure when working with modules should be

```
.
├── example
│   ├── example_group
│   │   ├── hello.py
│   │   └── __init__.py
│   └── example_user
│       ├── hello.py
│       └── __init__.py
├── __init__.py
```

As you can see also a message from `__init__.py` is printed when the module is imported.

```bash
$ cd example
$ python -m example_user.hello
hello from __init__.py
oh hai
```
