/**
 * Author Admin
 * Create 2021/5/24 11:36
 */
public class Client {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();
		PlayCommand playCommand = new PlayCommand(audioPlayer);
		RewindCommand rewindCommand = new RewindCommand(audioPlayer);
		StopCommand stopCommand = new StopCommand(audioPlayer);
		CommandBar commandBar = new CommandBar();
		commandBar.setPlayCommand(playCommand);
		commandBar.setRewindCommand(rewindCommand);
		commandBar.setStopCommand(stopCommand);
		
		commandBar.play();
		commandBar.rewind();
		commandBar.stop();
	}
}
