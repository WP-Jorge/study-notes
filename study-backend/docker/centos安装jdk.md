**1、先查看centos中自带的jdk并卸载**

```shell
[root@root ~]# rpm -qa | grep jkd    //查看
[root@root ~]rpm -e | grep java     //删除
# 卸载 -e --nodeps 强制删除
[root@kuangshen ~]# rpm -e --nodeps jdk1.8.0_121-1.8.0_121-fcs.x86_64
```

**2、yum 命令查找jdk所有版本**

```shell
//第一种：
[root@root ~]# yum -y list java*
//第二种：
[root@root ~]# yum search jdk
```

**3、安装jdk**

```shell
[root@root ~]# yum install java-1.8.0-openjdk.x86_64
一直y确定

检验安装
[root@root ~]# java -version
```

**4、设置jdk环境变量**

```shell
在文件最后加入如下配置：
# set java environment
JAVA_HOME= /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.292.b10-1.el7_9.x86_64/jre
PATH=$PATH:$JAVA_HOME/bin
CLASSPATH=.:$JAVA_HOME/lib
export JAVA_HOME CLASSPATH PATH
```

**5、使profile文件立马生效**

```shell
[root@root alternatives]#. /etc/profile
```

**6、使用nohup java -jar sysrd-test.jar >log.log & 启动java项目**

```shell
# nohup表示不挂断运行命令，即使shell关闭了，程序依然会在后台运行。其中的>log.log表示将程序的输出重定向到log.log文件中，默认的情况下，是输出到当前目录下的nohup.out文件中
nohup java -jar sysrd-test.jar >log.log &
```

**7、结束命令**

```shell
ps aux // 查看所有运行的程序的进程
kill 进程号// 选中程序对应的进程，并kill掉
```



