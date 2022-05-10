**1、nginx动静分离**

- nginx default.conf配置

  ```
  location ~ /www/ {
      root /usr/share/nginx/html/data;
      index index.html index.htm;
  }
  
  location ~ /image/ {
      root /usr/share/nginx/html/data;
      autoindex on;
  }
  ```

- 文件配置

  - 在/usr/local/nginx/html目录下创建文件夹data
  - 在data中创建www文件夹
    - 在www文件夹下面创建index.html文件，内容为www
  - 在data中创建image文件夹
    - 在image文件夹中放入野猪乔治.jpg

  - 开启nginx

- 效果

  ![image-20210216151630329](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210216151630329.png)

  ![image-20210216151658169](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210216151658169.png)

