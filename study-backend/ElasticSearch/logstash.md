- 安装 logshash

```shell
docker pull logstash:7.14.0
```

- 运行 logstash 并进入内部

```shell
docker run -d --name logstash --privileged=true logstash:7.14.0
docker exec -it logstash /bin/bash
```

- 在容器内部安装 elasticsearch 插件

```shell
logstash-plugin install logstash-output-elasticsearch
```

- 退出容器并关闭容器

```shell
exit
docker rm -f logstash
```

- 在 /var/lib/docker/volumes/ 文件夹下创建 用于映射 logstash config 和 pipeline 的文件夹

```shell
cd /var/lib/docker/volumes/
mkdir logstash_pipeline
mkdir logstash_config
```

- 下载 mysql 连接 jar 包，并上传到 /var/lib/docker/volumes/logstash_pipeline 下

```shell
https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.28
```

- 在 logstash_config 中创建或修改 pipelines.yml 文件

```shell
touch pipelines.yml
vim pipelines.yml
```

```yml
- pipeline.id: main
  path.config: "/usr/share/logstash/pipeline/jdbc.conf"
```

- 修改或创建 logstash.yml 文件

```shell
touch logstash.yml
vim logstash.yml
```

```yml
http.host: "0.0.0.0"
xpack.monitoring.elasticsearch.hosts: [ "http://192.168.94.128:9200" ] # 注意：只要是 logstash 中，都只能使用具体的地址，不能使用 localhost 等
```

- 进入 logstash_pipeline 文件夹并创建 jdbc.conf 文件，编辑内容

```shell
cd ../logstash_pipeline
touch jdbc.conf
vim jdbc.conf
```

```conf
input {
	jdbc {
		type => "musicInfo"
		jdbc_connection_string => "jdbc:mysql://106.15.184.155:3306/box_music?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8"
		jdbc_default_timezone => "Asia/Shanghai"
		jdbc_user => "root"
		jdbc_password => "111111"
		jdbc_driver_library => "/usr/share/logstash/pipeline/mysql-connector-java-8.0.28.jar"
		jdbc_driver_class => "com.mysql.cj.jdbc.Driver"
		schedule => "* * * * *"
		# use_column_value => false
		# tracking_column => "update_time"
		# tracking_column_type => "numeric"
		# record_last_run => true
		# last_run_metadata_path => "/usr/share/logstash/pipeline/logs/logstash_default_last_time.log"
		# clean_run => false
		# lowercase_column_names => true
		statement => "select music.music_id, singer.singer_id, music_singer_id, music_title, music_pic, lyrics, duration, album, genre, size, `level`, bitrate, singer_name, music_format, music_url, music.update_time from music, music_singer, singer where music.music_id = music_singer.music_id and music_singer.singer_id = singer.singer_id"
	}
}

filter {
	mutate {
		remove_field => ["@version", "@timestamp"]
	}
}

output {
	if[type] == "musicInfo" {
		elasticsearch {
			hosts => ["192.168.94.128:9200"]
			# 不能使用大写字母
			index => "music_info"
			document_id => "%{music_singer_id}"
			template_overwrite => true
			manage_template => false
			template_name => "iktemplate"
			template => "/usr/share/logstash/pipeline/logstash-ik.json"
		}
	}
	stdout {
		codec => json_lines
	}
}
```

- 在目录下创建 logs 文件夹 并在 logs 文件夹中创建文件 logstash_default_last_time.log，并给 logs 文件夹赋予读写权限（如果不实现增量更新，可以不进行这不操作）

```shell
mkdir logs
cd logs
touch logstash_default_last_time.log
cd ../
chmod 777 -R logs
```
- 在目录下创建 logstash.json 文件，用来替换默认的创建模板，使用 ik 分词器
```shell
mkdir logstash.json
vim logstash.json
```
```json
{
	"order": 0,
	"version": 1,
	"index_patterns": ["*"],
	"settings": {
		"index": {
			"refresh_interval": "5s"
		}
	},
	"mappings": {
		"_default_": {
			"dynamic_templates": [
				{
					"message_field": {
						"path_match": "message",
						"match_mapping_type": "string",
						"mapping": {
							"type": "text",
							"norms": false
						}
					}
				},
				{
					"string_fields": {
						"match": "*",
						"match_mapping_type": "string",
						"mapping": {
							"type": "text",
							"norms": false,
							"analyzer": "ik_max_word",
							"fields": {
								"keyword": {
									"type": "keyword",
									"ignore_above": 256
								}
							}
						}
					}
				}
			],
			"properties": {
				"@timestamp": {
					"type": "date"
				},
				"@version": {
					"type": "keyword"
				},
				"geoip": {
					"dynamic": true,
					"properties": {
						"ip": {
							"type": "ip"
						},
						"location": {
							"type": "geo_point"
						},
						"latitude": {
							"type": "half_float"
						},
						"longitude": {
							"type": "half_float"
						}
					}
				}
			}
		}
	},
	"aliases": {}
}
```

- 启动 logstash

```shell
docker run -d --name logstash -v /var/lib/docker/volumes/logstash_config:/usr/share/logstash/config -v /var/lib/docker/volumes/logstash_pipeline:/usr/share/logstash/pipeline --privileged=true logstash:7.14.0
```
