package controller;

import domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	// 自动注入StudentService
	@Autowired
	private StudentService studentService;
	
	// 注册学生
	@RequestMapping("/addStudnet.do")
	public ModelAndView addStudnet(Student student) {
		ModelAndView mv = new ModelAndView();
		String tips = "注册失败";
		int i = studentService.addStudent(student);
		
		if (i > 0) {
			// 注册成功
			tips = "学生【" + student.getName() + "】注册成功";
		}
		// 添加数据
		mv.addObject("tips", tips);
		// 指定结果页面
		mv.setViewName("result");
		return mv;
	}
	
	// 处理查询，响应ajax
	@ResponseBody
	@RequestMapping("/queryStudent.do")
	public List<Student> queryStudents() {
		log.info("访问queryStudent.do");
		
		// 参数检查，简单的数据处理
		List<Student> students = studentService.findStudents();
		return students;
	}
}
