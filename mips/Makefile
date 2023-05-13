BUILD_NAME = mars-build
CONTAINER_NAME = mars-container

build:
	docker build -t ${BUILD_NAME} .
	docker volume create ${VOLUME_NAME}

connect:
	docker run -u ${USER} -v /home/lazywithclass/workspace/learning/mips:/workspace -it ${BUILD_NAME} /bin/bash

mars:
	docker run -u ${USER} -v /home/lazywithclass/workspace/learning/mips:/workspace -e DISPLAY --name mars --rm -it ${BUILD_NAME} java -jar /workspace/Mars4_5.jar

