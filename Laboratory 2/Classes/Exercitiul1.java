package Classes;

import java.util.Arrays;
import java.util.Scanner;

public class Exercitiul1 {
    public static void main(String[] args) {
        int[] grades = new int[20];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i <= 19; i++) {
            System.out.print("Introduce number: ");
            int grade = scanner.nextInt();

            if (grade == -1) {
                float avarage = 0F;
                for (int j = 0; j < i; j++)
                    avarage += grades[j];

                avarage = avarage / i;
                System.out.println("Avarage: " + avarage);
                System.out.println("Array: " + Arrays.toString(grades));

                break;
            } else grades[i] = grade;
        }
    }
}
