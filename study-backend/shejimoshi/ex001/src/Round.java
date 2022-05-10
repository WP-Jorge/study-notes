public class Round implements Draw {
	public Round() {
	}
	
	@Override
	public void painting() {
		System.out.println("painting round");
	}
	
	@Override
	public void erase() {
		System.out.println("erase round");
	}
}
