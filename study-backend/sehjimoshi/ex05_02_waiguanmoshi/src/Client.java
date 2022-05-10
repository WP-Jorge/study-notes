/**
 * Author Admin
 * Create 2021/5/16 18:13
 */
public class Client {
	public static void main(String[] args) {
		ComputerFacade computerFacade = new ComputerFacade();
		computerFacade.open();
		System.out.println("------------------");
		computerFacade.close();
	}
}
