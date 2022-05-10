/**
 * Author Admin
 * Create 2021/5/24 11:33
 */
public class StopCommand implements Command {
	private AudioPlayer audioPlayer;
	public StopCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}
	@Override
	public void execute() {
		audioPlayer.stop();
	}
}
