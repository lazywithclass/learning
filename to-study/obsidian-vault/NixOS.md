---
cssclasses:
  - cornell-note
tags:
  - nixos
---

# System wide executables

Under `~/.nix-profile/bin/` you could find the symlinks to the installed executables


# Overlay

Real world example, I needed pyparsing at the latest version.
This example assumes Nixos, home-manager, and direnv

This is `default.nix` which gets invoked every time I enter in a folder

```nix
{ pkgs ? import <nixpkgs> { overlays = [ (import ./overlay.nix) ]; } }:  
  
let  
in pkgs.mkShell {  
  buildInputs = with pkgs; [  
    pdfgrep  
    python3  
    python311Packages.python-lsp-server  
    python311Packages.flake8  
    python311Packages.python-dotenv  
    python311Packages.pymupdf  
    pyparsing  
  ];  
}
```

This is the overlay that allows for pyparsing version

```nix
self: super: {  
  pyparsing = super.python311Packages.pyparsing.overrideAttrs (oldAttrs: {  
    src = super.fetchurl {  
      url = "https://github.com/pyparsing/pyparsing/releases/download/3.2.0/pyparsing-3.2.0.tar.gz";  
      sha256 = "cbf74e27246d595d9a74b186b810f6fbb86726dbf3b9532efb343f6d7294fe9c";  
    };  
  });  
}
```