**1、compose的使用**

- 下载docker-compose

  ```
  // 下载
  sudo curl -L "https://github.com/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  
  // 授权
  sudo chmod +x /usr/local/bin/docker-compose
  ```

- 检查安装是否成功

  ```
  docker-compose -v
  ```

  