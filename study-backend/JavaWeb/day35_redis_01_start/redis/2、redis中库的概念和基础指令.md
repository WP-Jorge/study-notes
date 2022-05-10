**1、redis中库的概念**

- 库：database 用来存放数据的一个基本单元，一个库可以存放key-value键值对，在redis中每个库都有一个唯一的名称（编号），从0开始，默认有16个库，库的编号从0-15，默认使用0号库，每个库的内容都是独立的互不影响

  - 切换库的命令

    ``select dbid(库编号)``

  - 清除库的指令

    ``flushDB``清空当前库

    ``flushAll``清空所有库

**2、redis的相应指令**

- del

  ``del key [key ...]``

  删除给定的一个或多个key，不存在的key会被忽略

  返回值：被删除key的数量

- exists

  ``exists key [key ...]`` 

  检查给定key是否存在

  返回值： 存在1，不存在0，如果有多个key，只要有一个存在就返回1

- expire

  ``expire key seconds``

  为给定key设置生成时间，当key过期时（生存时间为0），他就会自动删除

  返回值：成功返回1

- keys

  ``keys pattern``

  查询所有符合给定模式pattern的key

  ``keys * 匹配数据库中所有key``

  ``keys h?llo``匹配h(任意单个字符)llo

  ``keys h*llo``匹配h(任意多个字符)llo

  ``keys h[ae]llo``匹配hallo 和hello

  返回值：给定模式的key列表

- move

  ``move key db``

  将当前数据库中的key移动到给定的数据库db中

  返回值：成功返回1，失败0

- pexpire

  ``pexpire key milliseconds``

  与expire类似，但是是以毫秒做单位

  返回值：成功1，key不存在或设置失败0

- pexpireat

  ``pexpireat key milliseconds-timestamp``

  与expire类似，单位为时间戳

  返回值：成功1，key不存在或设置失败0

- ttl

  ``ttl key``

  返回给定key的剩余过期时间

  返回值：key不存在，返回-2

  ​				key存在但是没有设置时间戳，但会-1

  ​				以秒为单位，返回key 的剩余生存时间

- pttl

  ``pttl key``

  与ttl类似，返回毫秒

  返回值：key不存在，返回-2

  ​				key存在但是没有设置时间戳，但会-1

  ​				以毫秒为单位，返回key 的剩余生存时间

- randomkey

  ``randomkey``

  从当前数据库中随机返回一个key（不删除）

  返回值：当数据库不为空时，返回一个key，当数据库为空时返回nil

- rename

  ``rename key newname``

  将key改成给定名称，当key与newname相同时或key不存在时返回一个错误，当newname已存在时rename命令会覆盖旧值

- type

  ``type key``

  返回key所存储的类型

  返回值：none（key不存在）

  ​				string（字符串）

  ​				list（列表）

  ​				set（集合）

  ​				zset（有序集合）

  ​				hash（哈希表）