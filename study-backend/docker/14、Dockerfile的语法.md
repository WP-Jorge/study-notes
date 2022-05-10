**1、Dockerfile的语法**

- FROM命令

  基于哪个镜像进行构建新的镜像，在构建时会自动从docker hub拉取base镜像，必须作为Dockerfile的第一个指令出现

  ```
  FROM <image>
  FROM <image>[:<tag>] // 使用版本不写为latest
  FROM <image>[@<digest>] // 使用摘要
  ```

- EXPOSE命令

  向外暴露端口

- WORKDIR命令

  用来为Dockerfile中的任何EUN、CMD、ENTRYPOINT、COPY和ADD指令设置工作目录。如果WORKDIR不存在，即使它没有任何后续Dockerfile指令中使用，它也将被创建

  ```
  WORKDIR /path/to/workdir
  WORKDIR /a
  WORKDIR b
  WORKDIR c
  注意：WORKDIR指令可以在Dockerfile中多次使用，如果提供了相对路径，则该路径将与先前WORKDIR指令的路径相对
  ```

- ADD命令

  用来从context上下文复制新文件、目录或远程文件url，并将他们添加到位于指定路径的映像文件系统中

  ```
  ADD hom* /mydir/ // 通配符添加多个文件
  ADD home?.txt /mydir/ // 通配符添加
  ADD test.txt relativeDir/ // 可指定相对路径
  ADD test.txt /absoluteDir/ // 可以指定绝对路径
  ADD url // 指定url下载后不解压，指定文件时解压
  ```

- COPY命令

  用来将context目录中的指定文件复制到镜像的指定目录中

  ```
  COPY src dest
  COPY ["<src>", ... "<dist>"]
  ```

- VOLUME命令

  用来定义容器运行时可以挂载到主机的目录

  ```
  VOLUME ["/data"]
  ```

- ENV命令

  用来为构建镜像环境变量，这个值将出现在构建阶段中所有后续指令的环境中

  ```
  ENV <key> <value>
  ENV <key>=<value> ...
  ```

- ENTRYPOINT命令

  用来指定容器启动时执行的命令和CMD类似

  ENTRYPOINT指令往往用于设置容器启动后的第一个命令，这对一个容器来说往往是固定的

  CMD指令往往用于设置容器启动的第一个命令的默认参数，这对一个容器来说是可以变化的

  ```
  ["executable", "param1", "param2"]
  ENTRYPOINT command param1 param2
  ```

- 构建

  将带有Dockerfile文件的当前目录构建，名字为mycentos7，版本号为01

  ```
  docker build -t mycentos7:01 .
  ```

  