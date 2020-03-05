import java.util.Scanner;

public class Exercitiul2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input first integer:");
        int x = scanner.nextInt();

        System.out.println("Input first integer:");
        int y = scanner.nextInt();

        if (x == y)
            System.out.println(x + " = " + y);
        else if (x < y)
            System.out.println(x + " < " + y);
        else
            System.out.println(x + " > " + y);
    }
}
