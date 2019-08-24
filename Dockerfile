FROM maven:3.6-jdk-11

WORKDIR /tmp/qarunner/

COPY pom.xml /tmp/qarunner/

#COPY build/environment/environment.properties /tmp/qarunner/build/environment/
COPY build/environment/testEnvironment.json.example /tmp/qarunner/build/environment/testEnvironment.json
