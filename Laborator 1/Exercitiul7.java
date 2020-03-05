import java.util.Scanner;

public class Exercitiul7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();
        boolean primeExists = false;

        int factor = n;
        while (factor >= 2) {
            if (n % factor == 0) {
                boolean isPrime = true;
                for (int d = 2; d <= factor / 2; d++)
                    if (factor % d == 0) {
                        isPrime = false;
                        break;
                    }

                if (isPrime) {
                    System.out.println("Biggest prime factor to divide " + n + ": " + factor);
                    primeExists = true;
                    break;
                }
            }

            factor--;
        }

        if (primeExists == false)
            System.out.println("There is no prime factor to divide " + n + ".");
    }
}
