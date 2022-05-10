**1、nginx配置文件**

- 配置文件中的内容

  - 全局块：配置服务器整体运行的配置指令 比如 worker_processes 1;处理并发数的配置

    ​        从配置文件开始到 events 块之间的内容，主要会设置一些影响 nginx 服务器整体运行的配置指令，主要包括配 置运行 Nginx 服务器的用户（组）、允许生成的 worker process 数，进程 PID 存放路径、日志存放路径和类型以 及配置文件的引入等。 比如上面第一行配置的：

    ```
    worker_processes 1;
    ```

    ​        这是 Nginx 服务器并发处理服务的关键配置，worker_processes 值越大，可以支持的并发处理量也越多，但是 会受到硬件、软件等设备的制约

    

  - events 块：影响 Nginx 服务器与用户的网络连接 比如 worker_connections 1024; 支持的最大连接数为 1024

    ​        比如上面的配置：

    ![image-20210215174203430](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210215174203430.png)

    ​		events 块涉及的指令主要影响 Nginx 服务器与用户的网络连接，常用的设置包括是否开启对多 work process 下的网络连接进行序列化，是否允许同时接收多个网络连接，选取哪种事件驱动模型来处理连接请求，每个 word process 可以同时支持的最大连接数等。

    ​		上述例子就表示每个 work process 支持的最大连接数为 1024.

    ​		这部分的配置对 Nginx 的性能影响较大，在实际中应该灵活配置。

    

  - http 块 还包含两部分： http 全局块 server 块

    ​	![image-20210215174354578](C:\Users\蛋丁\AppData\Roaming\Typora\typora-user-images\image-20210215174354578.png)

    ​		这算是 Nginx 服务器配置中最频繁的部分，代理、缓存和日志定义等绝大多数功能和第三方模块的配置都在这里。 需要注意的是：http 块也可以包括 http 全局块、server 块。

    - http 全局块

      ​        http 全局块配置的指令包括文件引入、MIME-TYPE 定义、日志自定义、连接超时时间、单链接请求数上限等。

    - server 块

      ​		这块和虚拟主机有密切关系，虚拟主机从用户角度看，和一台独立的硬件主机是完全一样的，该技术的产生是为了 节省互联网服务器硬件成本。

      ​		每个 http 块可以包括多个 server 块，而每个 server 块就相当于一个虚拟主机。

      ​		而每个 server 块也分为全局 server 块，以及可以同时包含多个 locaton 块。

      1、全局server块

      ​		最常见的配置是本虚拟机主机的监听配置和本虚拟主机的名称或 IP 配置。

      2、localhost块

      ​		一个 server 块可以配置多个 location 块。

      ​		这块的主要作用是基于 Nginx 服务器接收到的请求字符串（例如 server_name/uri-string），对虚拟主机名称 （也可以是 IP 别名）之外的字符串（例如 前面的 /uri-string）进行匹配，对特定的请求进行处理。地址定向、数据缓 存和应答控制等功能，还有许多第三方模块的配置也在这里进行。