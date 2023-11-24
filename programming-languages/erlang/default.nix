{ pkgs ? import <nixpkgs> {} }:

let
in pkgs.mkShell {
  packages = with pkgs; [
    erlang
    erlang-ls
    rebar3
  ];
}
