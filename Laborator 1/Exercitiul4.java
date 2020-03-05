import java.util.Scanner;

public class Exercitiul4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();

        int factorial = 1;
        for (int i = 1; i <= n; i++)
            factorial = factorial * i;

        System.out.println("Factorial: " + factorial);
    }
}
