public class Triangle implements Draw
{
	@Override
	public void painting() {
		System.out.println("painting triangle");
	}
	
	@Override
	public void erase() {
		System.out.println("erase triangle");
	}
}
