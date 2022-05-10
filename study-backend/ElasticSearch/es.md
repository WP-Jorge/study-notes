1、拉取镜像

```shell
docker pull elasticsearch:7.14.0
docker pull kibana:7.14.0
```

2、修改 jvm 内存
```shell
find / -name jvm.options
vim 这个文件 ，把 -Xms、-Xmx 大小修改成 512m
```

3、在 /var/lib/docker/docker-compose/es-kibana 目录下创建 kibana.yml 配置文件

```shell
touch kibana.yml
```

4、修改 kibana.yml 中的配置

```yaml
# Default Kibana configuration for docker target
server.host: "0"
server.shutdownTimeout: "5s"
elasticsearch.hosts: [ "http://elasticsearch:9200" ]
monitoring.ui.container.elasticsearch.enabled: true
```

5、进入 es-kibana 修改并创建 docker-compose.yml，内容：

```yaml
version: "3.8"
volumes:
  data:
  config:
  plugin:
networks:
  es:
services:
  elasticsearch:
    image: elasticsearch:7.14.0
    ports:
      - "9200:9200"
      - "9300:9300"
    networks:
      - "es"
    environment:
      - "discovery.type=single-node"
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    volumes:
      - data:/usr/share/elasticsearch/data
      - config:/usr/share/elasticsearch/config
      - plugin:/usr/share/elasticsearch/plugins
  kibana:
    image: kibana:7.14.0
    ports:
      - "5601:5601"
    networks:
      - "es"
    volumes:
      - ./kibana.yml:/usr/share/kibana/config/kibana.yml
```

6、安装 docker-compose
```shell
curl -L https://get.daocloud.io/docker/compose/releases/download/1.29.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

7、进入 /var/lib/docker/docker-compose/es-kibana 目录，启动
```shell
docker-compose up -d
```

8、单独运行 elasticsearch
```shell
docker run -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.14.0
```

9、单独运行 kibana
```shell
docker run -d -v /var/lib/docker/docker-compose/es-kibana/kibana.yml:/var/share/kibana/config/kibana.yml --name kibana -p 5601:5601 kibana:7.14.0
```

10、安装 ik 分词器
```text
1、下载对应版本的 ik 分词器
2、放到 /var/lib/docker/docker-compose/es-kibana 目录下解压，取名为 ik-7.14.0
```shell
unzip ik 分词器 -d ik-7.14.0
```
3、修改 docker-compose 中的 elasticsearch 插件映射地址为 ./ik-7.14.0:/usr/share/elasticsearch/plugins/ik-7.14.0
4、重启
```

11、自定义分词、停用词
```text
1、在 ik-7.14.0/config 下有个 IKAnalizer.cfg.xml 文件，可在 <entry key="ext_dict">music.dic</entry> 中加入自己的分词库，在 <entry key="ext_stopwords"></entry> 加入停用词

```

```json
# 查看所有索引
GET /_cat/indices

# 删除索引
DELETE /music_info

# 多字段查询
GET /music_info/_search
{
  "query": {
    "multi_match": {
      "query": "漫长的告白",
      "fields": ["music_title", "singer_name", "lyric", "category_name"]
    }
  }
}
```