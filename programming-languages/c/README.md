# C

Following antirez's Youtube course: https://www.youtube.com/watch?v=HjXBXBgfKyk

## Commands 

```
# compile
gcc -o program program.c
```

```
# show assembly
gcc -S program.c
```

## Language Features

Directives start with `#`. `.h` files are header files.

Prototypes are declarations of functions, we could omit the `#include <stdio.h>` because programs by default are linked with libc, so at runtime it gets loaded as a shared library.

```c
int printf(const char * restrict format, ...);
```


