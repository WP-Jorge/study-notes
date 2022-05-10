/**
 * Author Admin
 * Create 2021/4/26 10:55
 */
public class Client {
	public static void main(String[] args) {
		Circle circle = new Circle();
		float squ = circle.squ((float) Math.sqrt(2));
		System.out.println("圆的半径为 根号2，圆内切正方形面积为：" + squ);
	}
}
