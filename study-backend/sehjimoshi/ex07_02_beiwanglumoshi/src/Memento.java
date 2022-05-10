/**
 * Author Admin
 * Create 2021/6/5 16:55
 */
public class Memento {
	private String state; public Memento(Originator o) {
		state=o.getState();
	} public void setState(String state) {
		this.state=state;
	} public String getState() {
		return state;
	}
}
