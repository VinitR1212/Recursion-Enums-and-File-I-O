import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SumCalculator {

    public enum SeriesCategory {
        ARITHMETIC,
        GEOMETRIC,
        FIBONACCI,
        CUSTOM;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a series category: ARITHMETIC, GEOMETRIC, FIBONACCI, CUSTOM");
        SeriesCategory category = SeriesCategory.valueOf(scanner.next().toUpperCase());

        System.out.println("Enter the number of terms:");
        int n = scanner.nextInt();

        int a = 0;
        int d = 0;
        double r = 0;

        switch (category) {
            case ARITHMETIC:
                System.out.println("Enter the first term (a):");
                a = scanner.nextInt();
                System.out.println("Enter the common difference (d):");
                d = scanner.nextInt();
                break;

            case GEOMETRIC:
                System.out.println("Enter the first term (a):");
                a = scanner.nextInt();
                System.out.println("Enter the common ratio (r):");
                r = scanner.nextDouble();
                break;

            case FIBONACCI:
                // No additional parameters needed for Fibonacci series
                break;

            case CUSTOM:
                // No additional parameters needed for custom series example
                break;
        }

        int result = 0;
        switch (category) {
            case ARITHMETIC:
                result = arithmeticSum(a, d, n);
                break;

            case GEOMETRIC:
                result = (int) geometricSum(a, r, n);
                break;

            case FIBONACCI:
                result = fibonacciSum(n);
                break;

            case CUSTOM:
                result = customSum(n);
                break;
        }

        System.out.println("The sum of the series is: " + result);

        // Save the result to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("series_sum_results.txt", true))) {
            writer.write("Series Category: " + category + "\n");
            writer.write("Number of terms: " + n + "\n");
            switch (category) {
                case ARITHMETIC:
                    writer.write("First term (a): " + a + "\n");
                    writer.write("Common difference (d): " + d + "\n");
                    break;

                case GEOMETRIC:
                    writer.write("First term (a): " + a + "\n");
                    writer.write("Common ratio (r): " + r + "\n");
                    break;
            }
            writer.write("Sum of the series: " + result + "\n");
            writer.write("------------------------------\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        scanner.close();
    }

    // Recursive method for arithmetic series sum
    public static int arithmeticSum(int a, int d, int n) {
        if (n == 1) {
            return a;
        }
        return a + arithmeticSum(a + d, d, n - 1);
    }

    // Recursive method for geometric series sum
    public static double geometricSum(double a, double r, int n) {
        if (n == 1) {
            return a;
        }
        return a + geometricSum(a * r, r, n - 1);
    }

    // Recursive method for Fibonacci series sum
    public static int fibonacciSum(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci(n) + fibonacciSum(n - 1);
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Custom series sum (example: factorial series sum)
    public static int customSum(int n) {
        if (n <= 1) {
            return 1;
        }
        return factorial(n) + customSum(n - 1);
    }

    private static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
