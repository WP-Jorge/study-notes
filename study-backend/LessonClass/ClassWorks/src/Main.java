import java.util.Scanner;

public class Main {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        if (m % n == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
