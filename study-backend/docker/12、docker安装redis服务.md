**1、docker安装redis服务**

- 下载redis 

  ```
  docker pull redis:6.0.10
  ```

- redis的启动

  - 简单的启动

    ```
    docker run -d -p 6379:6379 --name redis redis:6.0.10
    ```

  - 开启redis持久化启动

    ```
    docker run -d -p 6379:6379 --name redis -v redisdata:/data redis:6.0.10 redis-server --appendonly yes
    ```

    注意：一旦开启持久化之后，持久化生成aof文件会被放入容器中的/data目录中

- 修改redis配置文件，以配置文件方式启动

  注意：在当前/root/redisconf目录中存在redis.conf配置文件

  ```
  docker run --name redis -p 6379:6379 -v /root/redisconf:/usr/local/etc/redis -d redis:6.0.10 redis-server /usr/local/etc/redis/redis.conf
  
  ```



  ```
  // 每次启动都会创建新的数据卷，所以内容就没了
  docker run -p 6379:6379 -v /root/all/redis/redisconf:/usr/local/etc/redis --name redis -d redis:6.0.10 redis-server /usr/local/etc/redis/redis.conf
  
  // 使用具名数据卷，每次启动时都会区找之前创建的数据卷，如果没有就重新创建
  将redis.config放到 /var/lib/docker/volumes/redisdata/_data/目录下
  
  docker run -d --name redis -p 6379:6379 -v redisdata:/data redis:6.0.10 redis-server /data/redis.conf（使用这个）
  
  redisdata：数据卷名
  /data：数据卷与容器中的相对映射地址
  /data/redis.conf：容器中的配置文件，需要在数据卷在本地映射的文件夹中放入一个redis.conf配置文件，如 redisdata/_data/redis.conf
  
  ```