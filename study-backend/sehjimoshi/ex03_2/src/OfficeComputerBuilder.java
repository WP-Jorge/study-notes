public class OfficeComputerBuilder implements Builder {
	
	private Computer computer = new Computer();
	
	@Override
	public void buildCPU() {
		System.out.println("开始创建OfficeCPU");
		computer.setCPU("OfficeCPU");
	}
	
	@Override
	public void buildARM() {
		System.out.println("开始创建OfficeARM");
		computer.setRAM("OfficeARM");
	}
	
	@Override
	public void buildBoard() {
		System.out.println("开始创建OfficeBoard");
		computer.setBoard("OfficeBoard");
	}
	
	@Override
	public Computer returnComputer() {
		return computer;
	}
}
