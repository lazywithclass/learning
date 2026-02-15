
## Docker container

Official Clojure images follow the schema `clojure:<distro-java>-<versione-java>-<tool-build>`

## project.clj

### Dependencies management

`lein deps :tree` helps in understanding if some other package is pulling in old or conflicting dependencies.

### `:managed-dependencies`

When you add `[io.netty/netty-buffer "2.0"]` to `:managed-dependencies`, you are setting a global rule:

> "I don't care what Spark asks for, and I don't care what AWS asks for. If anyone asks for `netty-buffer`, they get version `2.0`. 
> End of discussion."

## The `contains` situation


## `Persistent`

In Clojure, **persistent** has a precise meaning:

> A _persistent_ data structure preserves previous versions when modified, by sharing structure rather than copying everything.

## Why Clojure changes the underlying data structure

It's about the `Sequence Abstraction` 