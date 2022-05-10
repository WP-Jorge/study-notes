**1、docker的镜像**

![image-20210213113302681](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210213113302681.png)

**2、镜像的拉取**

- 去docker hub去搜索需要的镜像

- 将对应拉取版本的代码复制到docker进行下载安装

  ````
  docker pull mysql:8.0.23
  ````

**3、使用配置阿里云镜像**

- 注册阿里云账号

- 登录账号

- 管理控制台

- 在产品服务中搜索容器服务 在搜索列表中找到容器镜像服务

  - 在打开的页面中找到镜像加速器

  - 切换使用系统

  - 复制以下命令

    ````
	https://fgifbda9.mirror.aliyuncs.com
	
    sudo mkdir -p /etc/docker
    sudo tee /etc/docker/daemon.json <<-'EOF'
    {
    	"registry-mirrors": ["https://fgifbda9.mirror.aliyuncs.com"]
    }
    EOF
    sudo systemctl daemon-reload
    sudo systemctl restart docker
    ````

  - 使用docker info 查看配置