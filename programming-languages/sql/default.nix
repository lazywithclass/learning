{ pkgs ? import <nixpkgs> {} }:

# TODO the service should start and stop when I enter and exit from this folder
# it should do it using systemd, not the way I do it now which is connecting
# through Emacs

pkgs.mkShell {
  buildInputs = [
    pkgs.postgresql
    pkgs.dbeaver
  ];
}
