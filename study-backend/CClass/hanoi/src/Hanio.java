import java.util.Scanner;

public class Hanio {
	static int c = 1;
	public static void main() {
		Scanner sc = new Scanner(System.in);
		
	}
	public static void hanio(int n, char a, char b, char c) {
		if (n == 1) {
			move(1, a, b);
		} else {
			hanio(n - 1, a, c, b);
			move(n - 1, a, c);
			hanio(n - 1, b, a, c);
		}
	}
	public static void move(int n, char A, char C) {
		System.out.print(c);
		System.out.print(".Move ");
		System.out.print(n);
		System.out.print(" form ");
		System.out.print(A);
		System.out.print(" to ");
		System.out.println(C);
		c++;
	}
}
