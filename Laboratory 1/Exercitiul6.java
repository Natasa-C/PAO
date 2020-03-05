import java.util.Scanner;

public class Exercitiul6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();

        if (n == 1 || n == 2)
            System.out.println(n - 1);
        else {
            int a = 0, b = 1, c = 0;
            for (int i = 3; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            System.out.println(c);
        }
    }
}
