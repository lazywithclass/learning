#!/usr/bin/env sh

./clean.sh
ocamlc -c SequenceI.mli
ocamlc -c Sequence.ml
ocamlc -o main Sequence.cmo main.ml
