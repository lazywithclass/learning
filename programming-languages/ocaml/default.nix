{ pkgs ? import <nixpkgs> {} }:

let
  lib = import <nixpkgs/lib>;
  buildNodeJs = pkgs.callPackage <nixpkgs/pkgs/development/web/nodejs/nodejs.nix> {
    python = pkgs.python3;
  };

  nodejsVersion = lib.fileContents ~/.nvmrc;

  nodejs = buildNodeJs {
    enableNpm = true;
    version = "18.17.1";
    sha256 = "8hXPA9DwDwesC2dMaBn4BMFULhbxUtoEmAAirsz15lo=";
  };

in pkgs.mkShell {
  packages = with pkgs; [
    ocaml
    ocamlPackages.ocaml-lsp
    ocamlPackages.ocp-indent
    python3
    deno
    nodejs
    nodePackages.npm
  ];

  # inherit NPM_CONFIG_PREFIX;

  # shellHook = ''
    # export PATH="${NPM_CONFIG_PREFIX}/bin:$PATH"
  # '';
}
