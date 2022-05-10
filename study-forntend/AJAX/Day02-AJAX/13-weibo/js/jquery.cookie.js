;(function ($, window) {
    $.extend({
        addCookie: function (key, value, day, path, domain) {
            // 1.处理默认保存的路径
            var index = window.location.pathname.lastIndexOf("/")
            var currentPath = window.location.pathname.slice(0, index);
            path = path || currentPath;
            // 2.处理默认保存的domain
            domain = domain || document.domain;
            // 3.处理默认的过期时间
            if(!day){
                document.cookie = key+"="+value+";path="+path+";domain="+domain+";";
            }else{
                var date = new Date();
                date.setDate(date.getDate() + day);
                document.cookie = key+"="+value+";expires="+date.toGMTString()+";path="+path+";domain="+domain+";";
            }
        },
        getCookie:function (key) {
            // console.log(document.cookie);
            var res = document.cookie.split(";");
            // console.log(res);
            for(var i = 0; i < res.length; i++){
                // console.log(res[i]);
                var temp = res[i].split("=");
                // console.log(temp);
                if(temp[0].trim() === key){
                    return temp[1];
                }
            }
        },
        delCookie:function (key, path) {
            addCookie(key, getCookie(key), -1, path);
        }
    });
})(jQuery, window);

/*
            cookie: 会话跟踪技术 客户端
            session:  会话跟踪技术  服务端

            cookie作用:
            将网页中的数据保存到浏览器中

            cookie生命周期:
            默认情况下生命周期是一次会话(浏览器被关闭)
            如果通过expires=设置了过期时间, 并且过期时间没有过期, 那么下次打开浏览器还是存在
            如果通过expires=设置了过期时间, 并且过期时间已经过期了,那么会立即删除保存的数据

            cookie注意点:
            cookie默认不会保存任何的数据
            cookie不能一次性保存多条数据, 要想保存多条数据,只能一条一条的设置???????现在好像可以了
            cookie有大小和个数的限制
            个数限制: 20~50
            大小限制: 4KB左右

            cookie作用范围:
            同一个浏览器的同一个路径下访问
            如果在同一个浏览器中, 默认情况下下一级路径就可以访问
            如果在同一个浏览器中, 想让上一级目录也能访问保存的cookie, 那么需要添加一个path属性才可以;
            document.cookie = "name=zs;path=/;";

            例如:
            保存到了www.it666.com/jQuery/Ajax/路径下,
            我们想在 www.it666.com/jQuery/Ajax/13-weibo/,
            和 www.it666.com/jQuery/ 路径下也能访问

            例如:
            我们在www.it666.com下面保存了一个cookie,
            那么我们在edu.it666.com中是无法访问的
            如果想在edu.it666.com中也能访问, 那么我们需要再添加一个domain属性才可以;
            document.cookie = "name=zs;path=/;domain=it666.com;";
            */
            // alert(document.cookie);
            // var date = new Date();
            // date.setDate(date.getDate() - 1);
            // document.cookie = "age=33;expires="+date.toGMTString()+";";
            // alert(document.cookie);

            // document.cookie = "name=lnj;";
            // document.cookie = "age=33;";
            // alert(document.cookie);
            // document.cookie = "name=lnj;age=33;gender=male;";