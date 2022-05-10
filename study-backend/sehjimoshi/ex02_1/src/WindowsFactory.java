public class WindowsFactory implements AbstractFactory {
	@Override
	public ARM createARM() {
		return new WindowsARM();
	}
	
	@Override
	public CPU createCPU() {
		return new WindowsCPU();
	}
}
