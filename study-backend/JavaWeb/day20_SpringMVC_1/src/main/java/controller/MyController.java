package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vo.Student;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {
	/**
	 * 处理器方法返回string--表示逻辑视图名称，需要配置视图解析器
	 */
	@RequestMapping(value = "/returnString-view.do")
	
	public String doReturnView(HttpServletRequest request, String name, Integer age) {
		// 可以自己手工添加数据到request作用域
		request.setAttribute("myname", name);
		request.setAttribute("myage", age);
		// show是逻辑视图名称，项目中配置了视图解析器
		// 框架对视图执行forward转发操作
		return "show";
	}
	
	// 在没有视图解析器的情况下，吧视图解析器注释掉
	// 处理器方法返回string,表示完整视图路径，此时不能配置视图解析器
	@RequestMapping(value = "/returnString-view2.do")
	public String doReturnView2(HttpServletRequest request, String name, Integer age) {
		// 可以自己手工添加数据到request作用域
		request.setAttribute("myname", name);
		request.setAttribute("myage", age);
		// 完整视图路径，项目中不能配置视图解析器
		// 框架对视图执行forward转发操作
		return "/WEB-INF/view/show.jsp";
	}
	
	
	// 处理器方法返回void，响应ajax请求
	// 手工实现ajax，json数据:代码有重复的1. java对象转为json ; 2.通过HttpServletResponse输出json数据
	@RequestMapping(value = "/returnVoid-ajax.do")
	public void doReturnVoidAjax(HttpServletResponse response, String name, Integer age) throws IOException {
		// 处理ajax，使用json做数据的格式
		// service调用完成了使用Student表示处理结果
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		
		String json = "";
		// 把结果的对象转为json格式的数据
		if (student != null) {
			ObjectMapper om = new ObjectMapper();
			json = om.writeValueAsString(student);
			System.out.println("student转换的json=====" + json);
		}
		
		// 输出数据，响应ajax请求
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	/**
	 * @ResponseBody: 作用:把处理器方法返回对象转为json后,通过HttpServletResponse输出给浏览器。
	 * 位置:方法的定义上面。和其它注解没有顺序的关系。
	 * 返回对象框架的处理流程:
	 * 1.框架会把返回student类型,调用框架的中ArrayL ist<Ht tpMessageConverter>中每个类的canwrite()方法
	 * 检查那个HttpMessageConverter:接口的实现类能处理s tudent类型的数据。--MappingJackson2HttpMessageConverter
	 * 2.框架会调用实现类的write ( )， MappingJackson2HttpMessageConverter的write()方法
	 * 把李四同学的s tudent对象转为json ,调用Jackson的objec tMapper实现转为json
	 * 3.框架会调用@ResponseBody把2的结果数据输出到浏览器，ajax 请求处理完成
	 */
	@RequestMapping(value = "/returnStudentJson.do")
	@ResponseBody
	public Student doStudentJsonObject(String name, Integer age) {
		// 调用service，获取请求结 果数据，student对象表示结果数据
		Student student = new Student();
		student.setName("李四");
		student.setAge(20);
		// 会被框架转为json
		return student;
	}
	
	@RequestMapping(value = "/returnStudentJsonArray.do")
	@ResponseBody
	public List<Student> doStudentJsonObjectArray(String name, Integer age) {
		List<Student> list = new ArrayList<Student>();
		Student student1 = new Student();
		student1.setName("李四");
		student1.setAge(20);
		list.add(student1);
		
		Student student2 = new Student();
		student2.setName("张三");
		student2.setAge(22);
		list.add(student2);
		return list;
	}
	
	/**
	 * 处理器方法返回的是string，String表示数据的 ,不是视图。
	 * 区分返回值string是数据,还是视图,看有没有@ResponseBody注解
	 * 如果有@ResponseBody注解,返@string就是数据,反之就是视图
	 * <p>
	 * 默认使用“text/plain;charset=ISO-8859-1”作为contentType,导致中文有乱码，
	 * 解决方案:给Reques tMapping增加个属性 produces, 使用这个属性指定新的contentType .
	 */
	@RequestMapping(value = "/returnStringData.do", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String doStringToData(String name, Integer age) {
		return "Hello SpringMVC 返回对象，表示数据";
	}
}
