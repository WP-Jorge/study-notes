/**
 * Author Admin
 * Create 2021/5/24 10:26
 */
public class HeadTeacher extends Handler {
	public HeadTeacher(String name) {
		super(name);
	}
	@Override
	public void handleRequest(int day) {
		if (day == 1) {
			System.out.println(name + "：处理1天的请假完成！");
		} else {
			nextHandler.handleRequest(day);
		}
	}
}
