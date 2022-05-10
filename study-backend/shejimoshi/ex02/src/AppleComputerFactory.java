public class AppleComputerFactory implements ComputerFactory {
	@Override
	public Computer getComputer() {
		return new Apple();
	}
}
