docker run --name my-redis -p 6379:6379 -d redis
docker exec -it my-redis sh
redis-cli
keys * #lista todas as chaves
#get user:1 # lista o usuário 1
