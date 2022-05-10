**1、安装docker**

- 在linux安装

  - 通用方式 bash脚本安装

    ````
    curl -fsSL get.docker.com -o get-docker.sh
    sudo sh get-docker.sh --mirror Aliyun
    ````
  
  - 查看docker版本
  
    ````
    docker -v
    ````
  
  - docker服务操作
  
    ````
    systemctl status docker // 查看docker服务的状态
    systemctl start docker // 启动docker
    systemctl stop docker // 关闭docker
    systemctl restart docker // 重启docker
    ````
  
  - 查看docker是否启动成功
  
    ````
    docker info
    ````
  
  - 设置docker开启自启动
  
    ````
    systemctl enable docker
    ````
  
  - 建立docker组 并使用root用户
  
    ````
    sudo groupadd docker
    sudo usermod -aG docker $USER
    ````
  
  - 重启docker服务
  
    ````
    systemctl restart docker
    ````