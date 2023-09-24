FROM ubuntu:20.04

WORKDIR /workspace

RUN adduser lazywithclass

RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -q -y openjdk-11-jdk wget
