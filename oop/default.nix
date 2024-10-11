{ pkgs ? import <nixpkgs> {} }:

let
in pkgs.mkShell {
  packages = with pkgs; [
    pkgs.jdk20
    pkgs.jetbrains.idea-community
  ];
}
