**1、开启redis远程连接**

注意：默认redis服务器时没有开启远程连接，默认拒绝所有的远程了护短连接

- 修改配置开启远程连接

  ``vim redis.conf`` 	修改如下配置

  ``bind 0.0.0.0``		允许一切客户端访问 或者把这个注释掉

  ``protected-mode no``	关闭防火墙

  ``ctrl :``

  ``wq``

  重启redis

