require 'webrick'

options = {
  DirectoryIndex: ['index.html', 'index.md'],
  FancyIndexing: false
}

run Rack::Handler::WEBrick.run Jekyll::Rack, options
