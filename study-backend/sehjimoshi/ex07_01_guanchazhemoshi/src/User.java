/**
 * Author Admin
 * Create 2021/6/5 15:34
 */
class User implements Observer {
	private String username;
	public void setUsername() {
		this.username = username;
	};
	public User() {}
	public User(String username) {
		this.username = username;
	}
	@Override
	public void updateMsg(String msg) {
		System.out.println(this.username + "接收到信息：" + msg);
	}
}
