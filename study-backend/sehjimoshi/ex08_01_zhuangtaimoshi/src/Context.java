/**
 * Author Admin
 * Create 2021/6/7 10:23
 */
public class Context {
	private State state;
	public void setState(String flag) {
		if (flag.equals("login")) {
			this.state = new Login();
		} else {
			this.state = new Logout();
		}
	}
	public void request() {
		state.operation();
	}
}
