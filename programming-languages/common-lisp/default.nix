{ pkgs ? import <nixpkgs> {} }:

let
in pkgs.mkShell {
  buildInputs = with pkgs; [
    sbcl
    sbclPackages.cl-cont
  ];

  shellHook = ''
  '';
}
