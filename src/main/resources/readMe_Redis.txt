--check if docker is up
docker --version

 --pull docker image
docker pull redis

--run redis
 docker run -d --name redis-container -p 6379:6379 redis

 --list images
 docker ps

 -- start docker client
  docker exec -it redis-container redis-cli

--  inside client
ping should return PONG

put a value
set key value
ex: set name "VAMSHI"

get value
get key
ex: get name

-- flush the cache
flushall

-- list of all keys
keys *

-- start redis
docker stop redis-container
docker start redis-container

https://redis.io/docs/latest/operate/rs/references/cli-utilities/redis-cli/