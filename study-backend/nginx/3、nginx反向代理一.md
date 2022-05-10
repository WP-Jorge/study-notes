**1、反向代理**

- 实现效果

  原本tomcat端口8080，使用nginx反向代理后使用80端口可以访问tomcat

- 安装tomcat

  ```
  docker pull tomcat:9.0.43-jdk8
  ```

- 启动tomcat

  ```
  docker run -d -p 8080:8080 --name tomcat -v tomcat:/usr/local/tomcat/webapps tomcat:9.0.43-jdk8
  ```

- 编辑nginx配置文件

  ![image-20210215183945433](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210215183945433.png)

