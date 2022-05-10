**1、**docker-compose入门

- 两个重要概念

  - 项目（project）：有多个服务共同组成一个完整业务单元，定义docker-compose.yml文件中

  - 服务（service）：一个服务对应一个应用容器 在一个项目中可以存在多个服务

- 第一个docker-compose程序

  - 创建一个docker-compose.yml文件

  - 在配置文件中定义一个项目存在哪些服务

    ```
    version: "3.0"
    services:
      tomcat: # 服务名，唯一
        image: tomcat:9.0.43-jdk8 # 创建当前这个服务使用的镜像是谁
        ports:
         - 8080:8080
    
    ```

  - 运行docker-compose

    ```
    // 启动这个项目的所有服务，必须保证运行命令的目录存在docker-compose.yml 
    docker-compose up
    ```

    

