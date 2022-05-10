package spring;

/**
 * 1、事务概念。
 * 1) 什么事务
 * (1) 事务是数据库操作最基本单元，逻辑上一组操作，要么都成功，如果有一个失败所有操作都失败。
 * (2) 典型场景:银行转账。
 * ① lucy转账100元给mary.
 * ② lucy少100，mary 多100元
 * 2) 事务四个特性
 * (1) 原子性
 * (2) 一致性
 * (3) 隔离性
 * (4) 持久性
 * <p>
 * 2、事务操作(搭建事务操作环境)
 * 1) 创建数据库表，添加记录
 * 2) 创建service, 搭建dao, 完成对象创建和注入关系
 * (1) service注入dao,在dao注入JdbcTemplate, 在JdbcTemplate注入DataSource
 * (2) 在dao创建两个方法:多钱和少钱的方法，在service创建方法(转账的方法)
 * (3) 创建测试类进行测试
 * (4) 测试中如果正常运行是没有问题的，但是如果代码在运行过程中产生了异常，那就有问题了
 * ① 使用事务进行解决
 * <p>
 * 3、事务操作(Spring 事务管理介绍)。
 * 1) 事务添加到JavaEE三层结构里面Service层(业务逻辑层)
 * 2) 在Spring进行事务管理操作。
 * (1)有两种方式:编程式事务管理和声明式事务管理(使用)
 * 3) 声明式事务管理,
 * (1) 基于注解方式
 * (2)基于xml配置文件方式
 * 4) 在Spring进行声明式事务管理，底层使用AOP
 * 5) Spring事务管理API
 * (1)提供一个接口，代表事务管理器，这个接口针对不同的框架提供不同的实现类
 * <p>
 * 4、事务操作(注解声明式事务管理)
 * 1) 在spring配置文件配置事务管理器
 * 2) 在spring配置文件，开启事务注解
 * (1) 在spring配置文件引入名称空间tx
 * (2) 开启事务的注解
 * 3) 在service类上面(或者在service类里面方法上面)添加事务注解
 * (1) @Transactional 可以加到类上方也可以加到方法上方
 * (2) 如果把这个注解添加类上面，这个类里面所有的方法都添加事务.
 * (3) 如果把这个注解添加方法上面，为这个方法添加事务。
 * <p>
 * 5、事务管理（申明式事务管理参数配置）
 * 1) 在service类上面添加注解@Transactional,在这个注解里面可以配置事务相关参数
 * (1) propagation: 事务的传播行为（add方法调用update方法）
 * ① 多事务方法直接进行调用，这个过程中事务是如何进行管理的，事务方法:对数据库表数据进行变化的操作
 * Ⅰ REQUIRE (默认) 如果add方法本身有事务，调用update方法之后，update 使用当前add方法里面事务
 * 如果add方法本身没有事务，调用update方法之后，创建新事务
 * Ⅱ REQUIRE_NEW   使用add方法调用update方法，如果add无论是否有事务，都创建新的事务
 * (2) ioslation: 事务隔离级别
 * ① 事务有特性成为隔离性，多事务操作之间不会产生影响。不考虑隔离性产生很多问题
 * ② 有三个读问题:脏读、不可重复读、虚(幻)读。
 * ③ 解决:通过设置事务隔离级别，解决读问题.
 * READ_UNCOMMITTED 1 1 1
 * READ_COMMITTED   0 1 1
 * REPEATABLE_READ  0 0 1 (默认)
 * SERIALIZABLE     0 0 0
 * (3) timeout: 超时时间
 * ① 事务需要在一定时间内进行提交，如果不提交进行回滚
 * ② 默认值是-1，设置时间以秒单位进行计算
 * (4) readOnly: 是否只读
 * ① 读:查询操作，写:添加修改删除操作
 * ② readOnly默认值false,表示可以查询，可以添加修改删除操作
 * ③ 设置readOnly值是true,设置成true之后，只能查询
 * (5) rollbackFor: 回滚
 * ① 设置出现哪些异常进行事务回滚
 * (6) noRollBackFor: 不回滚
 * ① 设置出现哪些异常进行事务不进行回滚
 * 如bean1.xml、UserService、TestUser
 * <p>
 * 6、事务操作（XML声明式事务管理）
 * 1) 在spring配置文件中进行配置
 * (1) 第一步配置事务管理器。
 * (2) 第二步配置通知，
 * (3) 第三步配置切入点和切面
 * 如bean2.xml
 * <p>
 * 7、事务操作（完全注解申明式管理）
 * 1) 创建配置类,使用配置类替代xml配置文件
 * 如TxConfig
 */
public class Transaction {
}
