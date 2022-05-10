/**
 * Author Admin
 * Create 2021/5/16 18:06
 */
public class ComputerFacade {
	private CPU cpu = new CPU();
	private Disk disk = new Disk();
	private NeiCun neiCun = new NeiCun();
	public void close() {
		disk.close();
		neiCun.close();
		cpu.close();
	}
	public void open() {
		cpu.open();
		neiCun.open();
		disk.open();
	}
}
