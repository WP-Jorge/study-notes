public class Computer {
	private String CPU;
	private String RAM;
	private String board;
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String CPU) {
		this.CPU = CPU;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String RAM) {
		this.RAM = RAM;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	@Override
	public String toString() {
		return "Computer{" +
				"CPU='" + CPU + '\'' +
				", RAM='" + RAM + '\'' +
				", board='" + board + '\'' +
				'}';
	}
}
