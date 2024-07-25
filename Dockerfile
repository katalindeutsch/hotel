# Use the Eclipse Temurin JDK 17 base image
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /hotel
COPY target/Hotel-0.0.1-SNAPSHOT.jar /hotel/target/Hotel-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/hotel/target/Hotel-0.0.1-SNAPSHOT.jar"]

################################## TODO: would be a nicer solution :) #############################
## Install Maven
#RUN apt-get update && \
#    apt-get install -y maven
#
## Set the working directory in the container
#WORKDIR /hotel
#
## Copy the source code and pom.xml into the container
#COPY src /hotel
#COPY pom.xml /hotel
#
## Copy the run.sh script into the container
#COPY run.sh /hotel/run.sh
#
## Make the run.sh script executable
#RUN chmod +x /hotel/run.sh
#
## Run the run.sh script
#CMD ["./run.sh"]


#WORKDIR /hotel
#COPY hotel.jar /target/Hotel-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "Hotel-0.0.1-SNAPSHOT.jar"]