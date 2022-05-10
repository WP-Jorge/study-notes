**1、redis集群搭建**

​		判断一个是集群中的节点是否可用，是集群中的所用主节点选举过程，如果半数以上的节点认为当前节点挂掉,那么当前节点就是挂掉了,所以搭建redis集群时建议节点数最好为奇数，搭建集群至少需要三个主节点,三个从节点,至少需要6个节点。

- 复制redis.conf 到redisgems文件夹下

  ``cd``

  ``cp redis-6.0.10 redisgems``

- 编辑redis.conf

  ``bind 0.0.0.0``

  ``protected-mode no``

  ``port 7000``

  ``daemonize yes``

  ``pidfile /var/run/redis_7000.pid``

  ``dbfilename dump-7000.rdb``

  ``appendonly yes``

  ``appendfilename "appendonly-7000.aof"``

  ``cluster-enabled yes`` 开启集群

  ``cluster-config-file nodes-7000.conf``

  ``cluster-node-tomeout 5000``

  一个创建7个

- 进入redis/bin目录中

  ``cd /usr/redis/bin``

  依次启动服务

  ````
  ./redis-server /root/redisgems/7000/redis.conf
  
  ./redis-server /root/redisgems/7001/redis.conf
  
  ./redis-server /root/redisgems/7002/redis.conf
  
  ./redis-server /root/redisgems/7003/redis.conf
  
  ./redis-server /root/redisgems/7004/redis.conf
  
  ./redis-server /root/redisgems/7005/redis.conf
  ````

  启动集群

  ``redis-cli --cluster create ip1:7000 ip1:7001 ip1:7002 ip2:7003 ip2:7004 ip3:7005 --cluster-replicas 1``

- 操作集群

  新建窗口，进入到bin

  ``cd /usr/redis/bin``

  ``./redis-cli -p port(开启的端口号都可以) -c``

  ``cluster nodes`` 查看节点

- 节点操作

  ``添加主节点 redis-cli --cluster add-node ip3:7006 ip1:7001`` 将7006添加到7001中

  ``添加从节点 redis-cli --cluster add-node ip3:7006 ip1:7001 --cluster-slave`` 将7006添加到7001中

  ``开启重新分片(reshard)操作redis-cli -h ip1 -p 7001 --cluster reshard ip1:7001``当使用上述方式添加完主节点之后，添加进入的节点为空，并没有分配任何槽，此时我们需要执行reshard操作，为新添加的主节点分配插槽，已达到分担集群压力，提高并发能力的目的,需要指定需要重新reshard的slots的个数，需要指定接收这些slots的插槽的目的节点id，需要指定分配这些slots的插槽的源节点id，输入done为结束符

  ``删除节点redis-cli -h ip1 -p 7001 --cluster del-node ip1:7001 node-id``当系统负载压力比较小的时候，为了避免耗费资源，可以选择动态的删除节点。使用上述方式删除节点的时候，当删除节点为从节点的时候可以直接删除，当删除节点为主节点的时候，则必须主节点不为空，即需要把主节点的插槽及插槽中的数据分配给其他的主节点

  ``在相应的从节点上执行cluster replicate <master-node-id>``副本迁移在集群中的概念，即是在某些场景下，可能需要把一个主服务器的从节点迁移到另外一个主服务器上去，这种情况一般用在多从节点的情况下，为了提高系统的高可用性而进行的操作，例如当有三个主服务器的情况下，其中一个主服务器有三个从节点，一个主服务器有两个从节点，一个主服务器只有一个从节点，如果一主一从这一对实例同时发生故障，将导致集群不可用，这种情况下，可以把一主三从这一对的实例的一个从节点移动到一主一从这一对实例上，即可避免上述情况，提高了系统的可用性.

  ````
  # 查看集群相关信息
  cluster info
  # 列出集群当前已知的所有节点，并描述节点信息
  cluster nodes
  # 将当前节点设置为 node_id 指定的节点的从节点
  cluster replicate <node_id>
  # 计算键 key 应该被放置在哪个槽上
  cluster keyslot <key>
  ````

- **错误解决**

  ``[ERR] Node 192.168.43.88:9000 is not empty. Either the node already knows other nodes (check with CLUSTER NODES) or contains some key in database 0.``

  1.删除dump.rdb文件。
  2.删除自动生成的在redis.conf里面cluster-config-file配置的的文件

  ![image-20210210155519001](D:\developTools\allProject\JavaWeb\day35_redis_01_start\redis\8、redis集群搭建.assets\image-20210210155519001.png)