# default.nix
{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  name = "pg-shell";

  buildInputs = [
    pkgs.postgresql
    pkgs.dbeaver-bin
  ];
}
