**1、nginx配置高可用主备模式**

- 准备
  - 需要两台 nginx 服务器
  - 需要 keepalived
  - 需要虚拟 ip

- 配置高可用的准备工作

  - 需要两台服务器 192.168.17.129 和 192.168.17.131
  - 在两台服务器安装 nginx
  - 在两台服务器安装 keepalived

- 在两台服务器安装 keepalived

- 安装keepalived

  ```
  docker pull arcts/keepalived:1.2.2
  ```

- 完成高可用配置（主从配置）

  - 进入keepalived修改/etc/keepalived/keepalivec.conf 配置文件

    ```
    global_defs {
        notification_email {
            acassen@firewall.loc
            failover@firewall.loc
            sysadmin@firewall.loc
    	}
        notification_email_from Alexandre.Cassen@firewall.loc
        smtp_server 192.168.17.129
        smtp_connect_timeout 30
        router_id LVS_DEVEL
    }
    vrrp_script chk_http_port {
        script "/usr/local/src/nginx_check.sh"
        interval 2 #（检测脚本执行的间隔）
        weight 2
    }
    vrrp_instance VI_1 {
        state BACKUP # 备份服务器上将 MASTER 改为 BACKUP
        interface ens33 //网卡
        virtual_router_id 51 # 主、备机的 virtual_router_id 必须相同
        priority 90 # 主、备机取不同的优先级，主机值较大，备份机值较小
        advert_int 1
        authentication {
            auth_type PASS
            auth_pass 1111
        }
        virtual_ipaddress {
        192.168.17.50 // VRRP H 虚拟地址
        }
    }
    ```

  - 在/etc/keepalived目录下创建check.sh

    ```
    Nginx=`ps -ef | grep nginx | grep -v grep | wc -l`
    echo 'nginx status ' $Nginx
    if [ $Nginx -eq 0 ]
    then
        echo 'nginx shutdown...'
        nginx
        echo 'start nginx ...'
        sleep 2
        if [ `ps -ef | grep nginx | grep -v grep | wc -l` -eq 0 ]
        then
    				ps -ef | grep keepalived | grep -v grep | awk '{print $2}' | xargs kill -9
    				echo 'kill keepalived...'
        else
    	echo 'nginx started...'
        fi
    fi
    echo 'script end...'
    ```

    **脚本作用：当 nginx 进程不存在时，会自动重启 nginx 服务 ，休眠 2 秒再次检查 nginx 进程，如果不存在就停止 keepalived 服务**

  - 给脚本执行权限

    ```
    chmod +x check.sh
    ```

  - 设置开机自启

    ```
    systemctl enable keepalived.service
    
    #开启keepalived
    systemctl start keepalived.servic
    ```

- 把两台服务器上 nginx 和 keepalived 启动，启动nginx 和 keepalived

