# to build, run this command in same directory
# 'docker build -t undecided/backend .'

#backend
FROM arm64v7/openjdk:11
ARG JAR_FILE=backend/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]