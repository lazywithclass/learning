{ pkgs ? import <nixpkgs> {}}:

pkgs.mkShell {
  packages = [ pkgs.twelf];
}

