# to build, run this command in same directory
# 'docker build -t undecided/backend .'

#backend
FROM openjdk:11
ARG version
WORKDIR /usr/src/undecided
COPY /srv/docker/spring/UNDECIDED/target/UNDECIDED-$version.jar /usr/src/undecided/undecided.jar
RUN java -jar undecided.jar