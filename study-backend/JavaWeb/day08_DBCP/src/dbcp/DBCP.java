package dbcp;

/**
 * 数据库连接池
 * 一、概念：一个容器（集合），存放数据库连接的容器
 * 1.当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户来访问时，从数据库中获取连接对象，用户使用完后会将连接对象归还给容器
 * 二、好处：
 * 1.节约资源
 * 2，用户访问高效
 * 三、实现
 * 1.接口标准：DataSource javax.sql包下的
 * 1) 方法：
 * ① 获取连接：getConnect()
 * ② 归还连接：Connection.close()。如果连接对象Connection是从连接池中获取的，那么调用Connection.close()方法，则不会在关闭连接，而是归还连接
 * 2.一般不用我们去实现它，由数据库产商去实现它
 * 2) C3P0：数据库连接池技术（比较老）
 * 2) Druid：数据库连接池实现技术，由阿里巴巴提供的（很棒）
 * 四、C3P0：数据库连接池技术
 * 1.步骤：
 * ① 导入jar包：
 * c3p0-0.9.5.5-sources  （依赖于）  mchange-commons-java-0.2.19-sources   数据库驱动jar包
 * mchange-commons-java-0.2.19-sources
 * ② 定义配置文件
 * 名称：c3p0.properties 或者 c3p0-config.xml
 * 路径：直接将文件放在src目录下即可
 * ③ 创建核心对象
 * 数据库连接池对象：CombopooledDataSource
 * ④ 获取连接：getConnection
 * 五、Druid：数据库连接池，由阿里巴巴提供
 * 1.步骤：
 * ① 导入jar包：
 * druid-1.2.1.jar
 * ② 定义配置文件：
 * 类型是properties
 * 可以叫任意名称，可以放在任意目录下
 * ③ 加载配置文件：
 * Properties
 * ④ 获取数据库连接池对象：
 * 通过工厂类来获取DruidDataSourceFactory
 * ⑤ 获取连接：
 * getConnection()
 * 2.定义工具类
 * 1) 定义一个工具类JDBCUtils
 * 2) 创建静态代码块加载配置文件，并初始化连接池对象
 * 3) 提供方法
 * ① 获取连接方法：
 * Ⅰ 通过数据库连接池连接
 * ② 释放资源
 * ③ 获取连接池的方法
 * 六、Spring JDBC
 * 1.Spring框架对JDBC的简单封装，提供了一个JDBCTemplate对象简化JDBC的开发
 * 2.步骤
 * 1) 导入jar包：
 * 2) 创建JdbcTemplate对象。依赖于数据源DataSource：
 * ① JdbcTemplate template = new JdbcTemplate(dataSource);
 * 3) 调用jdbcTemplate方法来完成CRUD的操作：
 * ① update()：执行DML语句。增删改
 * ② queryForMap()：查询结果，将结果集封装为map集合
 * ③ queryForList()：查询结果，将结果封装为List集合
 * ④ query()：查询结果，将结果封装为JavaBean对象
 * ⑤ queryForObject()：查询结果，将结果封装为Object对象（基础类型的对象）
 * 4) 练习：
 * ① 修改12号学生name为王铁蛋
 * ② 添加一条数据
 * ③ 删除刚刚添加的数据
 * ④ 查询id为1的数据，
 * 将其封装为Map集合。将列名作为key，将值作为value将这条数据封装为一个map集合
 * ⑤ 查询所有记录，将其封装为List
 * 将每一条记录封装成一个map集合，然后将map集合装载到List集合中
 * ⑥ 查询所有记录，将其封装为Student的对象的List集合
 * query的参数：RowMapper
 * 一般我们使用BeanPropertyRowMapper实现类，可以完成数据到JavaBean的自动封装
 * new BeanPropertyRowMapper<类型>(类型.class)
 * ⑦ 查询总的记录数
 * 一般用于聚合函数的查询
 */
public class DBCP {
	
}
