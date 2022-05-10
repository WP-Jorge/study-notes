/**
 * Author Admin
 * Create 2021/5/24 10:26
 */
public class DepartmentHead extends Handler {
	public DepartmentHead(String name) {
		super(name);
	}
	@Override
	public void handleRequest(int day) {
		if (day <= 3) {
			System.out.println(name + "：处理 " + day +"天的请假完成！");
		} else {
			nextHandler.handleRequest(day);
		}
	}
}
