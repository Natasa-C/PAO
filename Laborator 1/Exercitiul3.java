import java.util.Scanner;

public class Exercitiul3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input n: ");
        int n = scanner.nextInt();

        int sum = 0;
        for (int i = 1; i <= n; i++)
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;

        System.out.println("Sum: " + sum);
    }
}
