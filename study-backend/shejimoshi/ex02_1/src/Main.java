public class Main {
	public static void main(String[] args) {
		AppleFactory appleFactory = new AppleFactory();
		ARM appleArm = appleFactory.createARM();
		CPU appleCpu = appleFactory.createCPU();
		appleArm.production();
		appleCpu.production();
		
		WindowsFactory windowsFactory = new WindowsFactory();
		ARM windowsArm = windowsFactory.createARM();
		CPU windowsCpu = windowsFactory.createCPU();
		windowsArm.production();
		windowsCpu.production();
	}
}
