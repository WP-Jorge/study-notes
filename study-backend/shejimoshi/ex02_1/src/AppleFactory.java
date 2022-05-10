public class AppleFactory implements AbstractFactory {
	@Override
	public ARM createARM() {
		return new AppleARM();
	}
	
	@Override
	public CPU createCPU() {
		return new AppleCPU();
	}
}
