**1、nginx安装**

- 拉取镜像

  ```
  docker pull nginx:1.20.1
  ```

- 运行nginx

  ```
  docker run -d -p 8080:80 --name nginx-test nginx:1.20.1
  ```

- 进入/usr/all/allTools/nginx目录创建文件夹

  ```
  mkdir conf conf.d html
  ```

- 拷贝文件

  ```
  docker cp nginx-test:/etc/nginx/nginx.conf /usr/all/allTools/nginx/conf/nginx.conf
  
  docker cp nginx-test:/etc/nginx/conf.d/default.conf /usr/all/allTools/nginx/conf.d
  
  docker cp nginx-test:/usr/share/nginx/html /usr/all/allTools/nginx
  ```

- 停掉刚刚的服务

  ```
  docker ps -qa
  docker rm -f d4
  ```

- 启动服务

  ```
  docker run --name nginx -p 80:80 -v /usr/all/allTools/nginx/conf/nginx.conf:/etc/nginx/nginx.conf:ro -v /usr/all/allTools/nginx/conf.d:/etc/nginx/conf.d:ro -v /usr/all/allTools/nginx/html:/usr/share/nginx/html:rw -d nginx:1.21.0 （使用这个）
  ```

  

