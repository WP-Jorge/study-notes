**1、nginx方向代理二**

- 实现效果

  访问 http://192.168.61.131/8080/ 直接跳转到 127.0.0.1:8080 

  访问 http://192.168.61.131/8081/ 直接跳转到 127.0.0.1:8081

- 修改配置文件

  ![image-20210215193411705](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210215193411705.png)

- 创建两个tomcat服务，端口分别为8080，8081

  ```
  docker run -d -p 8081:8080 --name tomcat1 -v tomcat:/usr/local/tomcat/webapps tomcat:7-jdk8
  docker run -d -p 8081:8080 --name tomcat2 -v tomcat:/usr/local/tomcat/webapps tomcat:7-jdk8
  ```

- 去容器内的webapps创建两个目录

  ```
  cd ./webapps
  mkdir 8080
  cd 8080
  touch a.html
  
  cd ..
  mkdir 8081
  cd 8081
  touch a.html
  ```

- 重启nginx

  ```
  docker restart nginx
  ```

- 测试

  ![image-20210215193909531](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210215193909531.png)![image-20210215193917191](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210215193917191.png)