public class Director {
	private Builder builder;
	public Director(Builder builder) {
		this.builder = builder;
	}
	public void buildComputer() {
		builder.buildARM();
		builder.buildBoard();
		builder.buildCPU();
	}
}