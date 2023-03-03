import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your expression = ");
        String input = scanner.nextLine();
        try {
            System.out.println(calc(input));
        } catch (IllegalArgumentException e) {
            System.out.println("Input correct expression");
        }
        scanner.close();
    }

    public static String calc(String input) throws IllegalArgumentException {
        if (input.isBlank()) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "String cannot be empty";
            }
        }

        String[] numbers = input.trim().split(" ");

        if (numbers.length != 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Input correct expression";
            }
        }

        if ((!isNumeric(numbers[0]) && isNumeric(numbers[2])) || (isNumeric(numbers[0]) && !isNumeric(numbers[2]))) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Input either Arabic or Roman numbers";
            }
        }

        int number1;
        int number2;

        if (isNumeric(numbers[0])) {
            number1 = Integer.parseInt(numbers[0]);
            number2 = Integer.parseInt(numbers[2]);
        } else {
            RomanNumber romNumber1 = RomanNumber.valueOf(numbers[0]);
            RomanNumber romNumber2 = RomanNumber.valueOf(numbers[2]);

            number1 = romNumber1.getArabic();
            number2 = romNumber2.getArabic();
        }

        if ((number1 < 1 || number1 > 10) || (number2 < 1 || number2 > 10)) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "Input a number between 1 and 10 only";
            }
        }

        int output = switch (numbers[1]) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "/" -> number1 / number2;
            case "*" -> number1 * number2;
            default -> 0;
        };

        if (isNumeric(numbers[0])) {
            return String.valueOf(output);
        } else if (output > 0) {
            StringBuilder sb = new StringBuilder();

            RomanNumber[] values = RomanNumber.values();
            for (int i = values.length - 1; i >= 0; i--) {
                while (output >= values[i].getArabic()) {
                    sb.append(values[i]);
                    output -= values[i].getArabic();
                }
            }
            return sb.toString();
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                return "The result of subtraction cannot be less or equal to zero";
            }
        }
    }

    public static boolean isNumeric(String input) {
        try {
            int intValue = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}