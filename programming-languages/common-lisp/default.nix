{ nixpkgs ? import <nixpkgs> {} }:

let
  pkgs = [
    nixpkgs.sbcl 
  ];
in
  nixpkgs.stdenv.mkDerivation {
    name = "env";
    buildInputs = pkgs;
  }
