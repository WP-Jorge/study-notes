package spring5;

/**
 * 1、整个Spring5框架的代码基于Java8， 运行时兼容JDK9，许多不建议使用的类和方法在代码库中删除
 * 2、Spring5.0 框架自带了通用的日志封装。
 * (1) Spring5已经移除Log4jConfigListener,官方建议使用Log4j2
 * (2) Spring5框架整合Log4j2
 * ① 第一步引入jar包。
 * log4j-api-2.11.2.jar
 * log4j-core- 2.112jar
 * log4j-slf4j-impl-2.11.2.jar
 * slf4j-api-1.7.30.jar
 * ② 第二步创建log4j2.xml
 * 3、Spring5 支持整合JUnit5
 * (1)整合JUnit5
 * ① 第一步引入Spring相关针对测试依赖
 * spring-test-5.3.1.jar
 * ② 第二步创建测试类，使用注解方式完成
 * 第一种：@ExtendWith (SpringExtension.class).
 *
 * @ContextConfiguration (" classpath : bean1.xml ")
 * 第二种：@SpringJUnitConfig(locations = "classpath:bean1.xml")(复合注解，一个顶两个)
 * 如JTest5
 */
public class Spring5Log {
}
