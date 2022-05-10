package spring;

/**
 * 1、I0C操作Bean管理(基于注解方式)。
 * 1）什么是注解。
 * (1)注解是代码特殊标记，格式: @注解名称(属性名称=属性值,属性名称=属性值..)
 * (2) 使用注解，注解作用在类上面，方法上面，属性上面。
 * (3)使用注解目的:简化xml配置的
 * 2）Spring针对Bean管理中创建对象提供注解
 * (1) @Component  适用于各种分不清的bean
 * (2) @Service    适用于service层
 * (3) @Controller 适用于控制器层
 * (4) @Repository 适用于dao层
 * 上面四个注解功能是一样的，都可以用来创建bean实例。
 * 3）基于注解方式实现对象创建
 * (1) 引入依赖
 * ① spring-aop.jar
 * (2) 开启组件扫描
 * 如bean1.xml
 * (3) 创建类，在类上面添加创建对象注解
 * (4) 开启组件扫描细节配置
 * 4）基于注解方式实现属性注入
 * (1) @Autowireds：根据属性类型进行自动装配注入
 * ① 把service和dao对象创建，在service和dao类添加创建对象注解
 * ② 在service注入dao对象，在service类添加dao类型属性，在属性上面使用注解
 * (2) @Qualifiers：根据属性名称进行注入
 * ① 这个@Qualifier注解的使用，和上面@Autowired一起使用
 * (3) @Resource：可以根据类型注入，也可以根据名称注入
 * <p>
 * (4) @Value：注入普通类型属性
 * 5）全注解开发
 * (1) 创建配置类，替代xml配置文件
 * (2) 编写测试类
 */
public class IOC {
}
