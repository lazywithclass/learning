.PHONY: test

CWD = $(shell pwd)
NAME = learning-lisp
BUILD_NAME = ${NAME}-build
CONTAINER_NAME = ${NAME}-container

build:
	docker build -t ${BUILD_NAME} .

create:
	docker run -it -d --name ${CONTAINER_NAME} ${BUILD_NAME} 2>/dev/null; true

connect:
	docker run  -v ${CWD}:/workspace --rm -it ${BUILD_NAME} /bin/bash

repl:
	docker run -v ${CWD}:/workspace --rm -it ${BUILD_NAME} lein repl
