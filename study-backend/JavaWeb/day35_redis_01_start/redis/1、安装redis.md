**1、安装vm虚拟机**

**2、安装CentOS7虚拟机**

**3、安装redis6.0+**

 - 进入root目录，解压命令

   ``tar -zxvf redis-6.0.10.tavr.gz``

 - 如果没安装gcc9.0+则使用命令进行安装

   安装scl源

   ``yum -y install centos-release-scl``

   升级

   ``yum -y install devtoolset-9-gcc devtoolset-9-gcc-c++ devtoolset-9-binutils``

   ``scl enable devtoolset-9 bash``

   长久使用gcc升级后的版本

   ``echo "source /opt/rh/devtoolset-9/enable" >> /etc/profile``

   ``gcc -v``

   执行完此命令后，其它的shell窗口需要关闭重新打开才生效。

 - 进入解压后的redis目录输入命令进行编译

   ``make MALLOC=libc`` 

 - 输入命令，编译到usr目录下

   ``make install PREFIX=/usr/redis``

 - 进入redis目录

   ``cd``

   ``cd /usr/redis/bin``

**4、启动redis**

 - 第一种方式 直接打开

    - 执行命令

      ``./redis-server``

   - 直接运行redis

     ``./redis-server``

 - 第二种方式 客户端访问（指定ip、端口号）

    - 在运行``./redis-server``的基础上再开一个窗口进入bin目录输入

      ``./redis-cli -h localhost(ip) -p 6379(端口号)``

   - 如果ip是localhost，端口号是6379，则可以直接写

     ``./redis-cli``

**5、启动细节**

- 直接使用./redi-server方式启动使用的是redis-server这个shell脚本中默认的配置

- 如何在启动redis时指定配置文件启动

  - 注意：默认在redis安装完成之后在安装目录没有任何配置文件，需要自己在源码目录中赋值redis.conf配置文件到安装目录中

    - 进入源码目录

      ``cd``

      ``cd redis-6.0.10``

    - 拷贝文件

      ``cp redis.conf /usr/redis``

    - 进入redis的安装目录查看

      ``cd /usr/redis``

      ``ls``

    - 进入bin目录加载配置启动

      ``./redis-server ../redis.conf``

  - 修改redis默认端口号

    - ``vim redis.conf``

      修改里面的port保存退出

  - 如果需要可以显示中文，使用

    ``./redis-cli --raw``启动

