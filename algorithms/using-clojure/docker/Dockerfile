FROM clojure:lein

RUN apt update
RUN apt install -y curl tmux neovim lsof ssh

WORKDIR /app

# Multi-stage build to avoid getting deps everytime
COPY project.clj .
RUN lein deps

COPY . .
