import java.util.Scanner;

public class TemperatureConvertor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Choose an option from below: \n\t " +
                    "1. Convert celsius to fahrenheit \n\t " +
                    "2. Convert fahrenheit to celsius \n\t " +
                    "3. Exit");

            int option = scanner.nextInt();

            if (option == 1) {

                System.out.print("Enter temperature in celsius: ");
                float temperatureCelsius = scanner.nextFloat(); // camel-case
                float fahrenheit = temperatureCelsius * 9 / 5 + 32;
                System.out.println(fahrenheit + " degrees F");

            } else if (option == 2) {

                System.out.print("Enter temperature in fahrenheit: ");
                float temperatureFahrenheit = scanner.nextFloat(); // camel-case
                float celsius = (temperatureFahrenheit - 32) * 5 / 9;
                System.out.println(celsius + " degrees C");

            } else if (option == 3) {

                break; // stops a loop

            } else {

                System.out.println("Try again.");

            }

        }

    }
}