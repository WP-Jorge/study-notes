public class Client {
	public static void main(String[] args) {
		// 游戏电脑
		GameComputerBuilder gameComputerBuilder = new GameComputerBuilder();
		Director director = new Director(gameComputerBuilder);
		director.buildComputer();
		Computer computer = gameComputerBuilder.returnComputer();
		System.out.println("游戏电脑创建完成：" + computer);
		// 办公电脑
		System.out.println("=================================================================");
		OfficeComputerBuilder officeComputerBuilder = new OfficeComputerBuilder();
		Director director1 = new Director(officeComputerBuilder);
		director1.buildComputer();
		Computer computer1 = officeComputerBuilder.returnComputer();
		System.out.println("办公电脑创建完成：" + computer1);
	}
}
