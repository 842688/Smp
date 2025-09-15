{ pkgs, ... }: {
  # Which nixpkgs channel to use.
  channel = "stable-24.05"; # or "unstable"

  # Use https://search.nixos.org/packages to find packages
  packages = [
    pkgs.go
    pkgs.python311
    pkgs.python311Packages.pip
    pkgs.nodejs_20
    pkgs.nodePackages.nodemon
    pkgs.jdk
  ];

  # Sets environment variables in the workspace.
  env = {
    JAVA_HOME = "${pkgs.jdk}";
  };

  # Search for extensions in the VS Code Marketplace.
  extensions = [];

  # Manages ports forwarded from the workspace.
  ports = {};

  # Pre-build steps that run before the workspace starts.
  prebuilds = {};
}
