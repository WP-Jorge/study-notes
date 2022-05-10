

public class Main {
	public static void main(String[] args) {
		Comptete comptete = Comptete.getInstance();
		String competes = comptete.getCompete();
		System.out.println(competes);
	}
}
