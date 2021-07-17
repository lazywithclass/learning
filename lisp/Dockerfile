FROM ubuntu:20.04

WORKDIR /workspace

ENV PATH /workspace:$PATH

RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -y openjdk-11-jdk && \
  apt-get install -y nodejs && \
  apt-get install -y wget curl vim tree
