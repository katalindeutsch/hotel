# Introduction

## Prerequisites

Ensure Docker is installed and running on your machine before proceeding.

## How It Works

- **Dockerfile**: Defines the environment and instructions for building a Docker image of the application.

## Step-by-Step Instructions

0. **Create the jar file**

```bash
 mvn clean install
```

Note: This should create a target folder with an executable jar file in it called **Hotel-0.0.1-SNAPSHOT.jar**.

1. **Build the Docker Image**

   Navigate to the root (hotel) directory and run the following command:

```bash
 docker build -t hotel-app .
```
   
Note: This command creates a Docker image named hotel.

2. **Run the Docker Container**

   To create and start a container from the image, use the following command:

```bash
docker run -d -p 8080:8080 hotel-app
```

Note: This command creates and runs a container on port 8080.

