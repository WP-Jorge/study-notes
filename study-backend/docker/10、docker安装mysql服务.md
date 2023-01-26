**1、docker安装mysql服务**

- 安装哪个服务就去docker hub搜索对应的镜像
  - 描述（一般需要去查看如何安装、启动）
  - 版本信息
- 确定使用的版本

- 如何使用镜像

  - 基本启动mysql服务

    ```
    // 启动mysql服务并初始化登录密码
    docker run -e MYSQL_ROOT_PASSWORD=111111 mysql:8.0.23
    -e MYSQL_ROOT_PASSWORD=111111 表示给mysql执行登录密码
    ```

  - 启动一个mysql服务后台运行，并指定111111为密码，指定容器名

    ```
        docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=111111 --name mysql mysql:8.0.23
    ```

  - 启动一个mysql服务，指定root用户，密码111111，指定容器名称，使用数据卷，将数据持久化到宿主机系统，指定名称

    注意：通过docker hub描述得知mysql存储数据文件目录放置在容器中的这个目录/var/lib/mysql

    ```
    docker run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=111111 --name mysql -v mysqldata:/var/lib/mysql mysql:8.0.23
    
    docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=111111 -v mysqldata:/var/lib/mysql -v mysqlconfig:/etc/mysql mysql:8.0.28 (使用这个)
    ```

  - 启动一个mysql服务，指定root用户，密码111111，指定容器名称，使用数据卷，将数据持久化到宿主机系统，指定名称，以修改之后的配置文件启动
  
    ```
    docker run -d -p 3308:3306 -e MYSQL_ROOT_PASSWORD=111111 --name mysql3308 -v mysqldata:/var/lib/mysql -v mysqlconfig:/etc/mysql mysql:8.0.23
    
    docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=111111 -v mysqldata:/data -v mysqlconfig:/etc/mysql mysql:8.0.25
    ```
    
    

