/**
 * Author Admin
 * Create 2021/5/24 11:35
 */
public class CommandBar {
	private Command playCommand;
	private Command rewindCommand;
	private Command stopCommand;
	public void setPlayCommand(Command playCommand) {
		this.playCommand = playCommand;
	}
	public void setRewindCommand(Command rewindCommand) {
		this.rewindCommand = rewindCommand;
	}
	public void setStopCommand(Command stopCommand) {
		this.stopCommand = stopCommand;
	}
	public void play() {
		this.playCommand.execute();
	}
	public void rewind() {
		this.rewindCommand.execute();
	}
	public void stop() {
		this.stopCommand.execute();
	}
}
