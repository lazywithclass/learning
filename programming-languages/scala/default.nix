{ pkgs ? import <nixpkgs> {} }:

let
in pkgs.mkShell {
  packages = with pkgs; [
    scala_2_13
    sbt
    metals
  ];
}
