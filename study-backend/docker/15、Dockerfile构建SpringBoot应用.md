**1、Dockerfile构建SpringBoot应用**

- 开发springboot应用
- 对springboot应用程序进行打包

- 打包项目

- 在服务器中创建dockerfile上下目录context

  - mkdir demo 这个目录作为context 目录

  - 在demo目录中创建Dockerfile文件

    ```
    touch Dockerfile
    ```

  - 上传应用jar包到context目录

  - 编写Dockerfile

    ```
    FROM openjdk:8-jdk
    WORKDIR /app
    ADD docker-0.0.1-SNAPSHOT.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java","-jar"]
    CMD ["app.jar"]
    
    ```

- 执行构建

  ```
  docker build -t demo:01 .
  ```

- 运行容器

  ```
  docker run -d -p 8080:8080 --name demo demo:01
  ```

  

