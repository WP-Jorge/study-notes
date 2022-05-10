**1、拉取**
``docker pull rabbitmq:3.8.12``
**2、运行**
``docker run --name rabbitmq -d -p 15672:15672 -p 5672:5672 rabbitmq:3.8.12``
**3、进入**
``docker exec -it rabbitmq bash``
**4、安装management**
``rabbitmq-plugins enable rabbitmq_management``