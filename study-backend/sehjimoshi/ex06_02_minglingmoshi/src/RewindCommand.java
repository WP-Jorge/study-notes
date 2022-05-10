/**
 * Author Admin
 * Create 2021/5/24 11:33
 */
public class RewindCommand implements Command {
	private AudioPlayer audioPlayer;
	public RewindCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}
	@Override
	public void execute() {
		audioPlayer.rewind();
	}
}
