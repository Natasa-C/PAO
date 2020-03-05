import java.util.Scanner;

public class Exercitiul5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();

        boolean isPrime = true;
        for (int d = 2; d <= n / 2; d++)
            if (n % d == 0) {
                isPrime = false;
                break;
            }

        if (isPrime)
            System.out.println("The number is prime.");
        else
            System.out.println("The number is not prime.");
    }
}
