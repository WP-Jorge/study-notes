/**
 * Author Admin
 * Create 2021/5/24 10:26
 */
public class Dean extends Handler {
	public Dean(String name) {
		super(name);
	}
	@Override
	public void handleRequest(int day) {
		if (day >= 7) {
			System.out.println(name + "：处理 " + day +"天的请假完成！");
		}
	}
}
