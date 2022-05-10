**1、docker中容器之网络配置**

- 为什么允许通过外部访问容器互联的方式来提供网络服务

- docker容器与操作系统间通讯机制

  - 注意：一般在使用docker网桥实现容器与容器通讯时，都是站在一个应用角度进行容器通讯

    ```
    // 查看docker 网桥配置
    docker network ls
    
    // 创建自定义网桥
    docker create ems(网桥名)
    
    // 运行时指定网桥
    docker run -d -p 8081:8080 --network ems(刚刚创建的网桥名) --name mytomcat01 tomcat:9.0.43-jdk8 
    注意：一旦在启动容器时指定网桥之后，日后可以在任意这个网桥关联的容器中使用容器名进行与其他容器的通讯
    	使用docker run 指定 --network 运行网桥时网桥必须存在
    ```

- 删除网桥

  ```
  docker network rm 网桥名称
  ```

- 查看网桥信息

  ```
  docker inspect 网桥名
  ```

  