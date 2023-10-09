{ pkgs ? import <nixpkgs> {} }:

let
  lib = import <nixpkgs/lib>;
  buildNodeJs = pkgs.callPackage <nixpkgs/pkgs/development/web/nodejs/nodejs.nix> {
    python = pkgs.python3;
  };

  nodejsVersion = lib.fileContents ~/.nvmrc;

  nodejs = buildNodeJs {
    enableNpm = true;
    version = nodejsVersion;
    sha256 = "g+AzgeJx8aVhkYjnrqnYXZt+EvW+KijOt41ySe0it/E=";
  };

  # NPM_CONFIG_PREFIX = toString ./npm_config_prefix;

in pkgs.mkShell {
  packages = with pkgs; [
    ocaml
    ocamlPackages.ocaml-lsp
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
