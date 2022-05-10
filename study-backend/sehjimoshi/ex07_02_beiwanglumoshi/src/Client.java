public class Client {
	public static void main(String[] args) {
		Caretaker caretaker=new Caretaker();
		Originator originator=new Originator("Windows状态一");
		caretaker.setMemento(originator.createMemento());
		originator.show();
		originator.setState("Windows状态二");
		originator.show();
		System.out.println("-进行数据回滚-");
		originator.restoreMemento(caretaker.getMemento());
		originator.show();
	}
}