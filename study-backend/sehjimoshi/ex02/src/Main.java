public class Main {
	public static void main(String[] args) {
		ComputerFactory appleComputerFactory = new AppleComputerFactory();
		Computer appleComputer = appleComputerFactory.getComputer();
		appleComputer.production();
		
		ComputerFactory windowComputerFactory = new WindowComputerFactory();
		Computer windowComputer = windowComputerFactory.getComputer();
		windowComputer.production();
	}
}
