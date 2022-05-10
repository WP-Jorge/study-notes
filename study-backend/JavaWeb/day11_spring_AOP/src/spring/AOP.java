package spring;

/**
 * 1、AOP (概念)
 * 1）什么是AOP
 * (1)面向切面编程(方面)，利用AOP可以对业务逻辑的各个部分进行隔离，从而使得
 * 业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
 * (2)通俗描述:不通过修改源代码方式，在主干功能里面添加新功能。
 * (3)使用登录例子说明AOP
 * 2、AOP (底层原理)。
 * 1）AOP底层使用动态代理
 * (1)有两种情况动态代理
 * ① 第一种有接口情况，使用JDK动态代理。
 * 创建接口实现类代理对象，增强类的方法。
 * ② 第二种没有接口情况，使用CGLIB动态代理
 * 创建子类的代理对象，增强类的方法。
 * 3、AOP（JDK动态代理）
 * 1）调用newProxyInstance方法。
 * (1) 方法有三个参数:
 * 第一参数，类加载器。
 * 第二参数，增强方法所在的类，这个类实现的接口，支持多个接口。
 * 第三参数，实现这个接口InvocationHandler,创建代理对象，写增强的方法
 * 2）编写JDK动态代理代码
 * (1) 创建接口，定义方法。
 * (2) 创建接口实现类，实现方法.
 * (3) 使用Proxy类创建接口代理对象
 * <p>
 * 4、AOP专业术语
 * 1）连接点
 * (1) 类里面哪些方法可以被增强，这些方法称为连接点
 * 2）切入点
 * (1) 实际被真正增强的方法，称为切入点
 * 3）通知(增强)
 * (1) 实际增强的逻辑部分称为通知(增强)
 * (2) 通知有多钟类型
 * ① 前置通知
 * ② 后置通知
 * ③ 环绕通知
 * ④ 异常通知
 * ⑤ 最终通知
 * 4）切面
 * (1) 把通知应用到切入点过程
 * <p>
 * 5、AOP操作(准备)。
 * 1) Spring 框架一般都是基于AspectJ实现AOP操作
 * (1)什么是AspectJ:AspectJ不是Spring组成部分，独立于AOP框架，一般把AspectJ和Spimg框架一起使用，进行AOP操作
 * 2) 基于AspectJ实现AOP操作。
 * (1) 基于xml配置文件实现
 * (2) 基于注解方式实现(使用)
 * 3) 在项目工程里面引入AOP相关依赖(在原有的基础上)
 * (1) spring-aspects-5.3.1.jar
 * (2) aopalliance-1.0.jar
 * (3) aspectjweaver-1.9.6.jar
 * (4) cglib-3.3.0.jar
 * 4) 切入点表达式
 * (1) 语法结构: execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表])).
 * (2) 举例1:对com.atguigu.dao. BookDao类里面的add进行增强。
 * execution(* com.atguigu.dao.BookDao.add(..))
 * 举例2:对com.atguigu.dao.BookDao类里面的所有的方法进行增强
 * execution(* com.atguigu.dao.BookDao.*(..))
 * 举例3:对com.atguigu.dao包里面所有类，类里面所有方法进行增强
 * execution(* com.atguigu.dao.*.* (..))
 * 如bean1.xml
 * <p>
 * 6、AOP操作(AspectJ 注解)
 * 1) 创建类，在类里面定义方法
 * 2) 创建增强类(编写增强逻辑)
 * (1) 在增强类里面，创建方法，让不同方法代表不同通知类型
 * 3) 进行通知的配置，
 * (1) 在spring配置文件中，开启注解扫描(命名空间二个：context，aop)
 * (2) 使用注解创建User和UserProxy对象
 * (3) 在增强类上面添加注解@Aspect
 * (4) 在spring配置文件中开启生成代理对象
 * 4) 配置不同类型的通知
 * (1) 在增强类的里面，在作为通知方法，上面添加通知类型注解，使用切入点表达式配置
 * 5) 相同的切入点抽取
 * (1) 使用@Pointcut注解
 * (2) 有多个增强类多同一个方法进行增强，设置增强类优先级
 * ① 在增强类上面添加注解@Order(数字类型的值)，值越小优先级越高
 * 如bean1.xml、UserProxy、PersonProxy
 * <p>
 * 7、AOP操作(AspectJ 配置文件)
 * 1) 创建两个类，增强类和被增强类，创建方法
 * 2) 在spring配置文件中创建两个类对象。
 * 3) 在spring配置文件中配置切入点
 * 如bean2.xml
 * <p>
 * 8、完全注解开发
 * （1) 创建配置类，不需要创建xml配置文件
 *
 * @Configuration // 申明配置类
 * @ComponentScan(basePackages = "aopano") // 开启包扫描
 * @EnableAspectJAutoProxy(proxyTargetClass = true) // 开启AspectJ注解扫描，默认false
 * public class ConfigAop {}
 */
public class AOP {
}
