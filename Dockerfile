# to build, run this command in same directory
# 'docker build -t undecided/backend .'

#backend
FROM openjdk:11
ARG version
WORKDIR /usr/src/undecided
COPY target/UNDECIDED-0.1.jar /undecided.jar
ENTRYPOINT ["java","-jar","/undecided.jar"]
