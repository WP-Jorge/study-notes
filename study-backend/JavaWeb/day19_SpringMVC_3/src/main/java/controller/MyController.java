package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vo.Student;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MyController {
	/**
	 * 逐个接收请求的参数
	 * 要求:处理器方法的形参名和请求中参数名必须一致。
	 * 同名的请求参数赋值给同名的形参
	 * 框架接收请求参数
	 * 1.使用request对象 接收请求参数
	 * String strName = request. getParameter( "name ");
	 * string strAge = request. getParameter( "age' ) ;
	 * 2. springmvc 框架通过DispatcherServlet 调用MyController的doSome()方法
	 * 调用方法时,按名称对应,把接收的参数赋值给形参
	 * doSome ( strName , Integer. value0f(strAge) )
	 * 框架会提供类型转换的功能，能把string转为 int,long,float，double 等类型。
	 * 400状态码是客户端错误，表示提交请求参数过程中 发生了问题。
	 */
	
	@RequestMapping(value = "/receiveproperty.do")
	public ModelAndView doFirst(String name, int age) {
		// 可以在方法中直接使用name,age
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		mv.setViewName("show");
		return mv;
	}
	
	/**
	 * 请求中参数名和处理器方法的形参名不一样
	 * QRequestParam: 解决请求中参数名形参名不一样的问题
	 * 属性: 1.value请求中的参数名称
	 * 2.required是一个boolean ,默认是true,false：参数有无都可，没有不会报错
	 * true :表示请求中必须包含此参数。
	 * 位置:在处理器方法的形参定义的前面
	 */
	@RequestMapping(value = "/receiveparam.do")
	public ModelAndView receiveparam(@RequestParam(value = "rname", required = false) String name, @RequestParam(value = "rage", required = false) Integer age) {
		// 可以在方法中直接使用name,age
		System.out.println(name);
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", name);
		mv.addObject("myage", age);
		mv.setViewName("show");
		return mv;
	}
	
	/**
	 * 处理器方法形参是java对象，这个对象的属性名和请求中参数名样的
	 * 框架会创建形参的java对象,给属性赋值。请求中的参数是name ,框架会调用setName()
	 */
	@RequestMapping(value = "/receiveObject.do")
	public ModelAndView receiveObject(Student myStudent) {
		// 可以在方法中直接使用name,age
		System.out.println(myStudent.getName());
		ModelAndView mv = new ModelAndView();
		mv.addObject("myname", myStudent.getName());
		mv.addObject("myage", myStudent.getAge());
		mv.addObject("myStudent", myStudent);
		mv.setViewName("show");
		return mv;
	}
}
