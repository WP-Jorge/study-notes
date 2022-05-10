package maven;

/**
 * 1、需要改进项目的开发和管理，需要maven
 * 1) maven可以管理jar文件
 * 2)自动下载jar和他的文档，源代码
 * 3)管理jar直接的依赖， a. jar需要b.jar，maven会 自动下载b. jar
 * 4)管理你需要的jar版本
 * 5)帮你编译程序，把java编 译为class
 * 6)帮你测试你的代码是否正确。
 * 7)帮你打包文件，形成jar文件，或者war文件
 * 8)帮你部署项目
 * 2、构建:项目的构建。
 * 构建是面向过程的，就是一些步骤，完成项目代码的编译，测试，运行，打包，部署等等。
 * maven支持的构建包括有:
 * 1) 清理，把之前项目编译的东西删除掉，我新的编译代码做准备。
 * 2) 编译,把程序源代码编译为执行代码，java-class文件批量的，maven可以同时把成千，上百的文件编译为class.javac不一样，javac一次编译一个文件。
 * 3) 测试,maven可以执行测试程序代码，验证你的功能是否正确。批量的，maven同时执行多个测试代码，同时测试很多功能。
 * 4) 报告,生成测试结果的文件，测试通过没有。
 * 5) 打包，把你的项目中所有的class文件，配置文件等所有资源放到-一个压缩文件中。这个压缩文件就是项目的结果文件，遁 常java程序，压缩文件是jar
 * 扩展名的。对于web应用，压缩文件扩展名是. war
 * 6) 安装,把5中生成的文件jar, war安装到本机仓库
 * 7) 部署,把程序安装好可以执行。
 * 3、Maven核心概念
 * Maven能够实现自动化构建是和它的内部原理分不开的，这里我们从Maven的九个核心概念入手，
 * 看看Maven是如何实现自动化构建的
 * 1) POM
 * ① 一个文件名称是pom.xml，pom翻译过来叫做项目对象模型.
 * ② maven把一个项目当做一个模型使用。控制maven构建项目的过程，管理jar依赖。
 * 2) 约定的目录结构
 * ① maven项目的目录和文件的位置都是规定的。
 * 3) 坐标
 * ① 是一一个唯一的字符串，用来表示资源的。
 * 4) 依赖管理
 * ① 管理你的项目可以使用jar文件
 * 5) 仓库管理（了）
 * ① 你的资源存放的位置
 * 6) 生命周期（了解）
 * ① maven工具构建项目的过程
 * 7) 插件和目标（了解）
 * ① 执行maven构建的时候用的工具是插件
 * 8) 继承
 * 9) 聚合
 * 4、maven工具的安装和配置。
 * 1) 需要从maven的官网下载maven的安装包apache-maven-3.3.9-bin.zip
 * 2) 解压安装包，解压到一个目录，非中文目录。
 * 子目录bin :执行程序，主要是mvn.cmd
 * conf:maven 工具本身的配置文件settings.xml
 * 3) 配置环境变量
 * 在系统的环境变量中，指定一一个M2_HOME的名称，指定它的值是maven工具安装目录，bin之前的目录
 * M2_HOME=D:\work\maven work\apache-maven-3.3.9
 * 再把M2_HOME加入到path之中，在所有路径之前加入%M2_HOME&\bin;
 * 4) 验证，新的命令行中，执行mvn -v
 * 5、maven约定的目录结构，约定是大家都遵循的一一个规则。
 * 每一个maven项目在磁盘中都是一个文件夹(项目-Hel1o)
 * Hel1o/
 * ---/src
 * ------/main                 #放你主程序java代码和配置文件
 * ----------/java             #你的程序包和包中的java文件
 * ----------/resources        #你的java程序中要使用的配置文件
 * ------/test                 #放测试程序代码和文件的( 可以没有)
 * ----------/java             #测试程序包和包中的java文件
 * ----------/resources        #测试java程序中要使用的配置文件
 * ---/pom.xml                 #maven的核心文件(maven项目必须有)
 * 6、疑问:mvncompile编译src/main目录下的所有java文件的。
 * 1)为什么要下载
 * maven工具执行的操作需要很多插件(java类--jar文件)完成的
 * 2)下载什么东西了
 * jar文件--叫做插件--插件是完成某些功能
 * 3)下载的东西存放到哪里了。
 * 默认仓库(本机仓库) :
 * C: \Users\ (登录操作系统的用户名) Administrator\.m2\repository
 * 4) 执行mvn compile, 结 果是在项目的根目录下生成target目录(结果目录) ,
 * maven编译的java程序，最后的class文件都放在target目录中
 * 5) 设置本机存放资源的目录位置:
 * ① 修改maven的配置文件，maven
 * ② 安装目录/conf/settings.xml
 * ③ 先备份settings.xml
 * ④ 修改<localRepository> 指定你的目录(不要使用中文目录)
 * D:/developTools/apache-maven-3.3.9/maven_repository
 * 7、仓库
 * 1)仓库是什么:仓库是存放东西的，存放maven使用的jar和我们项目使用的jar
 * > maven使用的插件( 各种jar )
 * >我项目使用的jar (第三方的工具)
 * 2)仓库的分类
 * >本地仓库,就是你的个人计算机上的文件夹，存放各种jar
 * >远程仓库，在互联网上的，使用网络才能使用的仓库
 * ①:中央仓库，最权威的，所有的开发人员都共享使用的一个集中的仓库,https://repo.maven.apache.org:中央仓库的地址
 * ②:中央仓库的镜像:就是中央仓库的备份，在各大洲， 重要的城市都是镜像。
 * ③:私服，在公司内部，在局域网中使用的,不是对外使用的。
 * 3)仓库的使用，maven仓库的使用不需要人为参与。
 * 开发人员需要使用mysq1驱动--->maven首先查本地仓库--->私服--->镜像--->中央仓库
 * 8、pom.xml
 * 1) 基本信息
 * (1) modelVersion Maven 模型的版本，对于Maven2和Maven3来说，它只能是4.0.0
 * (2) groupId:组织id，一般是公 司域名的倒写。格式可以为:
 * ① 域名倒写。例如com.baidu
 * ② 域名倒写+项目名。例如com. baidu.appolo
 * (3) artifactId:项目名称，也是模块名称，对应groupId中项目中的子项目。
 * 2) packaging:打包后 压缩文件的扩展名，默认是jar，web 应用是war
 * packaging可以不写，默认 是jar
 * 3) 依赖: dependencies和dependency ，相当于是java代码 中import你的项目中要使用的各种资源说明，比我的项目要使用mysql驱动
 * <dependencies>
 * <dependency>
 * <groupId>mysql</groupId>
 * <artifactId>mysql-connector-java</arti factId>
 * <version>5.1.9</version>
 * </dependency>
 * </dependencies>
 * 4) properties:设置属性
 * 5) build:maven在进行项目的构建时，配置信息，例如指定编译java代码使用的jdk的版本
 * 9、生命周期
 * 1) maven生命周期，maven的命令, maven 的插件
 * ① maven的生命周期:就是maven构建项目的过程，清理，编译，测试，报告，打包，安装，部署
 * ② maven的命令: maven独 立使用，通过命令，完成maven的生命周期的执行。maven可以使用命令，完成项目的清理，编译，测试等等
 * ④ maven的插件: .
 * ⑤ maven命令执行时，真正完成功能的是插件，插件就是一些jar文件，一 些类
 * 2) 单元测试(测试方法) :用的是junit, junit是一 个专门测试的框架(工具)。
 * junit测试的内容:测试的是类中的方法，每一个方法都是独立测试的。
 * 方法是测试的基本单位(单元)。
 * maven借助单元测试，批量的测试你类中的大量方法是否符合预期的。
 * (1) 使用步骤
 * ① 加入依赖，在pom.xml加入单元测试依赖
 * <dependency>
 * <groupId>junit</groupId>
 * <artifactId>junit</artifactId>
 * <version>4.11</version>
 * <scope>test</scope>
 * </dependency>
 * (2) 在maven项目中的src/test/java目录下，创建测试程序。(测试结束后会生成一个测试报告，放在target/surefire-reports里面的Test.txt中)
 * 推荐的创建类和方法的提示:
 * 1.测试类的名称是Test+你要测试的类名
 * 2.测试的方法名称是:Test+方法名称
 * 例如你要测试HelloMaven,创建测试类TestHe11oMaven
 *
 * @Test public void testAdd() {}
 * 测试HelloMaven的add方法是否正确,其中testAdd叫做测试方法，它的定义规则
 * 1.方法是public的，必须的
 * 2.方法没有返回值，必须的
 * 3.方法名称是自定义的，推荐是Test+方法名称
 * 4.在方法的上面加入@Test
 * (3) 命令(使用命令时如果使用越下面的命令，则在它上面的命令会先执行)
 * mvn clean：清理(会删除原来编译和测试的目录，即target目录，但是已经install到仓库里的包不会删除)
 * mvn compile：编译主程序(会在当前目录下生成- -个target,里边存放编译主程序之后生成的字节码文件)
 * mvn test-compile：编译测试程序(会在当前目录下生成-一个target,里边存放编译测试程序之后生成的字节码文件)
 * mvn test：测试( 会生成一一个目录surefire-reports,保存测试结果)
 * mvn package：打包主程序(会编译、编译测试、测试、并且按照pom.xml配置把主程序打包生成jar包或者war包)
 * mvn install：安装主程序(会把本工程打包，并且按照本工程的坐标保存到本地仓库中)
 * mvn deploy：部署主程序(会把本工程打包，按照本工程的坐标保存到本地库中，并且还会保存到私服仓库中。还会自动把项目部署到web容器中)。
 * 10、控制配置maven构建项目的参数设置，设置jdk的版本
 * <build>
 * // 配置插件
 * <p1ugins>
 * // 配置具体的插件
 * <plugin>
 * // 插件ID
 * <groupId>org.apache.maven.plugins</groupId>
 * // 插件名称
 * <artifactId>maven-compiler-plugin</artifactId>
 * // 插件版本
 * <version>3.8.1</version>
 * // 配置插件的信息
 * <configuration>
 * // 告诉maven代码在jdk1.8上面编译
 * <source>1.8</source>
 * // 运行在jdk1.8上
 * <target>1.8</target>
 * </configuration>
 * </p1ugin>
 * </plugins>
 * </build>
 * 11、在idea中设置maven，让idea和maven结 合使用。
 * 1) idea中内置了maven，一般不使用内置的，因为用内置修改maven的设置不方便。使用自己安装的maven,
 * 2) 需要覆盖idea中的默认的设置。让idea知道安装位置的配置信息
 * 3) 配置的入口
 * (1) 配置当前工程的设置，file--settings---Build ,Excution , Deployment-- Build Tools
 * --Maven
 * Maven Home directory: maven 的安装目录
 * User Settings File :就是maven安装目录conf/setting . xnl配置文件
 * Local Repository : 本机仓库的目录位置
 * file--other settings--Build Tools--Maven--Runner
 * VM Options : -DarchetypeCatalog=internal
 * JRE:你项目的jdk
 * archetypeCatalog=internal，maven项目创建时，会联网下载模版文件,比较大,使用archetypeCatalog=internal,
 * 不用下载，创建maven项目速度快
 * (2) 配置以后新建工程的设置同上
 * 12、创建项目：
 * 1) 新建普通java项目，然后添加模块框架，选择maven，跟web工程新建相似
 * 或者新建maven项目
 * 使用模版创建项目
 * (1) maven-archetype-quickstart :普通的java项目
 * (2) maven- arche type - webapp : web. 工程
 * 2) 在pom.xml中添加以下语句
 * <properties>
 * // 设置编码格式，防止Maven报编码警告
 * <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 * <maven.compiler.source>1.8</maven.compiler.source>
 * <maven.compiler.target>1.8</maven.compiler.target>
 * </properties>
 * 13、依赖范围
 * 1) 依赖范围，使用scope表示的。
 * scope的值有compile,test,provided(打包前用到依赖，打包后不用到) (默认compile，表示全部依赖都用到)
 * scope:表示依赖使用的范围，也就是在maven构建项目的那些阶段中起作用。
 * maven构建项目编译，测试，打包，安装，部署过程(阶段)
 * 2) 在下面添加<scope></scope>
 * <dependency>
 * <scope>test</scope>
 * </dependency>
 * 14、maven常用操作
 * 1) maven的属性设置
 * <properties>设置maven的常用属性
 * 2) maven的全局变量
 * 自定义的属性：
 * (1) 在<properties>通过自定义标签声明变量(标签名就是变量名)
 * (2) 在pom. xml文件中的其它位置，使用${标签名}使用变量的值
 * 自定义全局变量一般是定义依赖的版本号，当你的项目中要使用多个相同的版本号,
 * 先使用全局变量定义，在使用${变量名}
 * 3) 资源插件
 * <build>
 * <resources>
 * <resource>
 * <directory>src/main/java</directory>    //所在的目录
 * <includes>                              //包括目录下的. properties, . xml文件都会扫描到
 * <include>**\/*.properties</include>
 * <include>**\/*.xml</include>
 * </includes>
 * // filtering选项false不启用过滤器, *.property已经起到过滤的作用了
 * <filtering>false</filtering>
 * </resource>
 * </resources>
 * </bui1d>
 * (1) 默认没有使用resources的时,会把src/main/ resources目录中的文件拷贝到target/classes目录中。
 * 对于sTC/main/java 目录下的非java文件不处理，不拷贝到target/classes目录中
 * (2) 我们的程序有需要把一些文件放在 src/main/java目 录中，当 我在执行java程序时，需要用到src/main/java目录中的文件。
 * 需要告诉maven在mvn compile src/main/java 目录下的程序时，需要把文件一同拷贝到target/classes目录中。
 * 此时就需要在<build>中 加入<resources> .
 */
public class Maven {
	public static void main(String[] args) {
		System.out.println("Hello Maven!");
	}
}
