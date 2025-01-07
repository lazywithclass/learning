{ pkgs ? import <nixpkgs> {} }:

let
in pkgs.mkShell {
  packages = with pkgs; [
    pandoc
    nodePackages.vercel
  ];
}
