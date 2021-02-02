# Getting Started with Docker containers

## Build

### Building application in docker image. 
####Be sure that no mysql instance is running

```bash
cd ..
docker build -t internship .
```

## Run

### Run mysql and internship service with docker-compose

```bash
cd docker
docker-compose up
```

## Learn More
[Docker Getting Started Guide](https://docs.docker.com/get-started/)

[Get started with Docker Compose](https://docs.docker.com/compose/gettingstarted/)