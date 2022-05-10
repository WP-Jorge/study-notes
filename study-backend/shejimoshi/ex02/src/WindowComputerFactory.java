public class WindowComputerFactory implements ComputerFactory {
	@Override
	public Computer getComputer() {
		return new Window();
	}
}
