/**
 * Author Admin
 * Create 2021/5/24 10:20
 */
public abstract class Handler {
	protected String name;
	protected Handler nextHandler;
	public Handler(String name) {
		this.name = name;
	}
	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;
	}
	public abstract void handleRequest(int day);
}
