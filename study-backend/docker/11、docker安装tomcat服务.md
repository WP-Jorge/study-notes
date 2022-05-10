**1、docker安装tomcat服务**

- 下载tomcat

  ```
  docker pull tomcat:9.0.43-jdk8
  ```

- 启动tomcat服务

  - 启动基本的tomcat服务

    ```
    docker run -d -p 8080:8080 --name tomcat tomcat:9.0.43-jdk8
    ```

  - 将部署应用目录通过数据卷挂载到宿主机系统

    注意：部署web应用在容器中的目录为/usr/local/tomcat/webapps 配置文件/usr/local/tomcat/conf

    ```
    docker run -d -p 8080:8080 --name tomcat -v apps:/usr/local/tomcat/webapps tomcat:9.0.43-jdk8
    ```

    注意：在tomcat:9.0.43-jdk8这个版本中 需要将webapps.dist目录中的文件全部复制到webapps中，然后将webapps.dist目录删除，只有这样tomcat才能正常运行

    ```
    docker exec -it id bash
    cp -r webapps.dist/* ./webapps
    rm -rf webapps.dist
    ```

  - 修改配置文件，并应用目录通过数据卷挂载到宿主机系统

    ```
    docker run -d -p 8080:8080 -name tomcat -v apps:/usr/local/tomcat/webapps -v confs:confs/usr/local/tomcat/conf tomcat:9.0.43-jdk8
    ```

    

  

