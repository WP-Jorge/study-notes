/**
 * Author Admin
 * Create 2021/6/5 16:55
 */
public class Originator {
	private String state;
	public Originator(String state) {
		this.state=state;
	} public void setState(String state) {
		this.state=state;
	} public String getState() {
		return state;
	} public Memento createMemento() {
		return new Memento(this);
	} public void restoreMemento(Memento m) {
		state=m.getState();
	} public void show() {
		System.out.println(state);
	}
}
