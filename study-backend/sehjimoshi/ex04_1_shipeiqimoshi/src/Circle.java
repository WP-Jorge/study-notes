/**
 * Author Admin
 * Create 2021/4/26 10:50
 */
public class Circle implements Target {
	
	private Square square = new Square();
	
	@Override
	public float squ(float r) {
		r = (float) (r * Math.sqrt(2));
		float squ = square.squ(r);
		return squ;
	}
}
