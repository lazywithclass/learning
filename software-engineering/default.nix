{ pkgs ? import <nixpkgs> {} }:

let
in pkgs.mkShell {
  packages = with pkgs; [
    pkgs.gradle
    pkgs.jdk
    pkgs.jetbrains.idea-community
  ];
}
