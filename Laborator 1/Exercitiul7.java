import java.util.Scanner;

public class Exercitiul7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();

        int factor = n;
        while (factor >= 2) {
            boolean isPrime = true;
            for (int d = 2; d <= n / 2; d++)
                if (factor % d == 0) {
                    isPrime = false;
                    break;
                }

            if (isPrime) {
                System.out.println("Biggest prime factor to divide " + n + ": " + factor);
                break;
            }

            factor--;
        }

        System.out.println("There is no prime factor to divide " + n + ".");
    }
}
