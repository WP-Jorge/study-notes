/**
 * Author Admin
 * Create 2021/4/26 18:14
 */
public class Client {
	public static void main(String[] args) {
		// 创建大学
		University cslg = new University("常熟理工学院");
		
		// 创建学院以及院内专业
		College jsj = new College("计算机科学与工程学院");
		College wgy = new College("外国语学院");
		Department rjgc = new Department("软件工程");
		Department jsjkxyjs = new Department("计算机科学与技术");
		
		// 创建学院以及院内专业
		Department xyz = new Department("小语种");
		Department yy = new Department("英语");
		
		cslg.add(jsj);
		cslg.add(wgy);
		
		jsj.add(rjgc);
		jsj.add(jsjkxyjs);
		
		wgy.add(xyz);
		wgy.add(yy);
		
		// 显示全部
		cslg.display();
		
		// 发送消息
		jsj.sendMessage("软甲工程专业的信息", rjgc);
	}
}
