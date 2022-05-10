**1、nginx负载均衡**

- 实现效果

  浏览器地址栏输入地址 http://192.168.61.131/edu/a.html，负载均衡效果，平均 8080 和 8081 端口中

  ![](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210216124658015.png)

![image-20210216124710435](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210216124710435.png)

- 实现

  - 准备两台 tomcat 服务器，一台 8080，一台 8081

  - 在两台 tomcat 里面 webapps 目录中，创建名称是 edu 文件夹，在 edu 文件夹中创建 页面 index.html，用于测试

  - 在 nginx 的配置文件中进行负载均衡的配置

    ```
    	upstream myserver {
          server  192.168.61.131:8080;
          server  192.168.61.131:8081;
        }
        
        server {
          listen  80;
          server_name  192.168.61.131;
          location / {
            proxy_pass  http://myserver;
            root  html;
            index  index.html index.htm;
          }
        }
    ```

  - 启动nginx

    ```
    docker run --name nginx -p 80:80 -v /usr/local/nginx/conf/nginx.conf:/etc/nginx/nginx.conf:ro -v /usr/local/nginx/conf.d:/etc/nginx/conf.d:ro -v /usr/local/nginx/html:/usr/share/nginx/html:rw -d nginx:1.19.6
    ```

- nginx 分配服务器策略

  - 轮询（默认）

    每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器 down 掉，能自动剔除。

  - weight

    weight 代表权重默认为 1,权重越高被分配的客户端越多

    ```
    upstream server_pool{
    	server 192.168.5.21 weight=10;
    	server 192.168.5.22 weight=10;
    }
    
    ```

  -  ip_hash

    每个请求按访问 ip 的 hash 结果分配，这样每个访客固定访问一个后端服务器

    ```
    upstream server_pool{
        ip_hash;
        server 192.168.5.21:80;
        server 192.168.5.22:80;
    }
    ```

  -  fair（第三方）

    按后端服务器的响应时间来分配请求，响应时间短的优先分配。

    ```
    upstream server_pool{
        server 192.168.5.21:80;
        server 192.168.5.22:80;
        fair;
    }
    ```

    

  