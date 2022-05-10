package spring;

/**
 * 一、IOC（概念和原理）
 * 1、什么是IOC
 * (1)控制反转，把对象创建和对象之间的调用过程，交给Spring进行管理。
 * (2)使用IOC目的:为了耦合度降低
 * (3)做入门案例就是IOC实现。
 * <p>
 * 2、IOC底层原理。
 * (1) xml解析、工厂模式、反射。
 * <p>
 * 3、IOC (接口)
 * 1）IOC思想基于IOC容器完成，IOC容器底层就是对象工厂
 * 2）Spring 提供IOC容器实现两种方式: (两个接口)。
 * (1) BeanFactory: IOC容器基本实现，是Spring内部的使用接口，不提供开发人员进行使用
 * ① 加载配置文件时候不会创建对象，在获取对象(使用)才去创建对象
 * (2) ApplicationContext: BeanFactory 接口的子接口，提供更多更强大的功能，一般由开发人
 * 员进行使用。
 * ① 加载配置文件时候就会把在配置文件对象进行创建
 * <p>
 * 4、I0C操作Bean管理
 * 1）什么是Bean管理
 * (0) Bean管理指的是两个操作
 * (1) Spring 创建对象
 * (2) Spring 注入属性
 * 2）Bean管理操作有两种方式
 * (1)基于xml配置文件方式实现
 * 3）IOC操作Bean管理(基于xml方式)
 * (1)基于xml方式创建对象。
 * <bean id="user" class="spring.User"></bean>
 * ① 在spring配置文件中，使用bean标签，标签里面添加对应属性，就可以实现对象创建.
 * ② 在bean标签有很多属性，介绍常用的属性
 * id属性:唯-标识
 * class属性:类全路径(包类路径)
 * ③ 创建对象时候，默认也是执行无参数构造方法完成对象创建.
 * (2)基于注解方式实现。
 * ① DI:依赖注入，就是注入属性。
 * 第一种注入方式:使用set方法进行注入
 * 创建类，定义属性和对应的set方法。如bean1.xml的book配置
 * 第二种注入方式:使用有参数构造进行注入
 * 如bean1.xml的order配置
 * 第三种注入方式：p名称注入
 * 如bean1.xml的student
 * 5、IOC操作Bean管理(xml注入其他类型属性)。
 * (1) null值
 * <property name ="address">
 * <null/>
 * </property>
 * (2)属性值包含特殊符号。
 * <property name="sname">
 * <value><![CDATA[<<狂人>>]]></value>
 * </property>
 * 如bean1.xml的student2
 * （3）注入外部bean
 * ① 创建两个类service类和dao类，
 * ② 在service调用dao里面的方法。
 * ③ 在spring配置文件中进行配置,如bean2.xml
 * （4）注入属性-内部bean和级联赋值
 * 1）一对多关系:部门和员工
 * 一个部门有多个员工，一个员工属于一个部门。
 * 部门是一，员工是多。
 * 如bean3.xml
 * 2）级联赋值
 * （5）注入数组类型的属性
 * 如bean4.xml
 * （6）注入List集合类型的属性
 * 如bean4.xml
 * （7）注入Map集合类型的属性
 * 如bean4.xml
 * （8）在集合中设置对象类型属性
 * 如bean4.xml
 * （9）把集合注入部分提取出来
 * 在spring配置中引入名称空间util
 * 如bean5.xml
 * <p>
 * 6、IOC操作Bean管理（FactoryBean）
 * 1）Spring有两种类型的bean，一种普通bean,另外一种工厂bean ( FactoryBean)
 * ① 普通bean:在配置文件中定义bean类型就是返回类型。
 * ② bean:在配置文件定义bean类型可以和返回类型不一样
 * 第一步创建类，让这个类作为工厂bean, 实现接口FactoryBeant
 * 第二步实现接口里面的方法，在实现的方法中定义返回的bean类型。
 * 7、IOC操作Bean管理（Bean的作用域）
 * 1）在Spring里面，设置创建bean实例是单实例还是多实例
 * 2）在Spring里面，默认情况下，bean是单实例对象
 * 在spring配置文件bean标签里面有属性(scope) 用于设置单实例还是多实例
 * 第一个值：singleton，单实例对象（默认），加载spring文件时就会创建单实例对象
 * 第二个值：prototype，多实例对象，在调用getBean方法时候创建多实例对象
 * 8、IOC操作Bean管理（Bean的生命周期）大体有5步，细分有7步
 * 1)生命周期：
 * (1)通过构造器创建bean实例(无参数构造)
 * (2)为bean的属性设置值和对其他bean引用(调用set方法)
 * (3)把bean实例传递到bean后置处理器的方法 postProcessBeforeInitialization
 * (4)调用bean的初始化的方法(需要进行配置初始化的方法)
 * (5)把bean实例传递到bean后置处理器的方法 postProcessAfterInitialization
 * (6)bean可以使用了(对象获取到了)
 * (7)当容器关闭时候，调用bean的销毁的方法(需要进行配置销毁的方法)
 * 如bean7.xml
 * 2)添加后置处理器
 * （1）创建一个类，实现接口BeanPostProcessor，创建后置处理器
 * <p>
 * 9、IOC操作Bean管理（xml自动装配）
 * 1）什么是自动装配
 * (1)根据指定装配规则(属性名称或者属性类型)，Spring 自动将匹配的属性值进行注入。
 * 2）自动装配过程
 * (1)根据属性名称自动注入
 * (2)根据属性类型自动注入
 * 如bean8.xml
 * 10、IOC操作Bean管理（外部属性文件）
 * 1）直接配置数据库信息
 * (1)配置德鲁伊连接池
 * (2)进入Druid连接池jar包
 * 2）引入外部属性文件配置数据库连接池
 * (1)创建外部属性文件，properties格式文件，写数据库信息
 * (2)把外部properties属性文件导入spring配置文件中
 * ① 引入context名称空间
 * ② 在spring配置文件中使用标签引入外部属性文件
 * 如bean9.xml
 */

public class IOC {
}
