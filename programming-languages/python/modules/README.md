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

From inside the example folder by running the following command, you should see "oh hai" printed to the console

```bash
$ python -m example_user.hello
oh hai
```
