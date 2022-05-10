public class GameComputerBuilder implements Builder {
	private Computer computer = new Computer();
	@Override
	public void buildCPU() {
		System.out.println("开始创建GameCPU");
		computer.setCPU("GameCPU");
	}
	@Override
	public void buildARM() {
		System.out.println("开始创建GameARM");
		computer.setRAM("GameARM");
	}
	@Override
	public void buildBoard() {
		System.out.println("开始创建GameBoard");
		computer.setBoard("GameBoard");
	}
	@Override
	public Computer returnComputer() {
		return computer;
	}
}
