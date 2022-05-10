**1、查看docker引擎以及docker帮助命令**

- 展示docker信息（版本 等等）

  ````
  docker info
  ````

- 展示docker的版本信息

  ````
  docker -v
  ````

- 查看帮助命令

  ````
  docker -h || dokcer
  ````

- docker 中的命令格式

  ````
  docker [options] command(具体命令)
  ````

**2、操作镜像images的相关命令**

- 查看本地仓库中存在那些镜像

  ````
  docker images
  ````

  ````
  REPOSITORY(镜像名称)  TAG(当前版本)  IMAGE ID(ID)   CREATED(image创建时间)   SIZE(大小)
  hello-world   		 latest       bf756fb1ae65   13 months ago   	13.3kB
  
  ````

- 下载新的镜像

  - 第一种方式（推荐）

    ````
    docker pull <镜像名称>[:版本号]
    docker pull mysql:8.0.23
    ````

  - 第二种方式

    ````
    docker pull <镜像名:@DIGEST(摘要)>
    docker pull mysql:DIGEST:sha256:870892ea5cc8c623b389717c2eedd58248c82a8569d7601ede62a63d527641bd
    ````

- 不通过dockerhub 通过命令行搜索需要的镜像

  ````
  docker search mysql[:版本号]
  ````

- 删除镜像

  第一种方式

  ````
  docker image rm 镜像名<:tag | 镜像id>
  docker image rm -f 镜像名<:tag | 镜像id> // 强制删除，会把镜像和容器都删掉
  docker rmi // docker image rm的简写
  docker rmi -f $(docker images -q) // 选择删除
  ````

- 查看镜像

  ````
  docker images mysql // 查看mysql多个版本的镜像
  docker images -q // 只显示镜像id
  ````

- location指令说明

  ![image-20210215194345913](D:\developTools\allProject\docker\3、docker引擎的相关操作.assets\image-20210215194345913.png)



