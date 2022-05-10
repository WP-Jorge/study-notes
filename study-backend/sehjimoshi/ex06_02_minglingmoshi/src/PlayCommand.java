/**
 * Author Admin
 * Create 2021/5/24 11:33
 */
public class PlayCommand implements Command {
	private AudioPlayer audioPlayer;
	public PlayCommand(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}
	@Override
	public void execute() {
		audioPlayer.play();
	}
}
