import java.util.Scanner;

public class Main {

    private final String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final String[] otherNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private boolean isArabic = true;

    public Main() throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String[] split = sc.nextLine().split(" ");
            if (split.length > 3) throw new Exception("Can't calculate more than 2 numbers, sorry (>_<)");

            castToArabicWithSomeChecks(split);
            int result = doMath(split);
            if (!isArabic) {
                if (result < 1) throw new Exception("Sorry, answer in this case less than 1 (!_!)");
                if (result == 100) System.out.println("C");
                else System.out.println(castToRome(result));
            } else System.out.println(result);
        }
    }

    private void castToArabicWithSomeChecks(String[] split) throws Exception {
        for (String number : numbers)
            if (split[0].equals(number))
                for (String number2 : numbers)
                    if (split[2].equals(number2)) {
                        isArabic = true;
                        return;
                    }
        for (int i = 0; i < otherNumbers.length; i++)
            if (split[0].equals(otherNumbers[i]))
                for (int j = 0; j < otherNumbers.length; j++)
                    if (split[2].equals(otherNumbers[j])) {
                        split[0] = numbers[i];
                        split[2] = numbers[j];
                        isArabic = false;
                        return;
                    }
        throw new Exception("What's that? I can't do math with this");
    }

    private int doMath(String[] split) throws Exception {
        int num1 = Integer.parseInt(split[0]);
        int num2 = Integer.parseInt(split[2]);
        String operation = split[1];
        switch (operation) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/": return num1 / num2;
            default: throw new Exception("Idk what I need to do with numbers (0_0)");
        }
    }

    private String castToRome(int result) {
        int fifties = result / 50;
        int tens = (result - (fifties * 50)) / 10;
        int fives = (result - ((tens * 10) + (fifties * 50))) / 5;
        int ones = (result - ((fives * 5) +(tens * 10) + (fifties * 50)));

        System.out.println(tens + " " + fives + " " + ones);

        String res = "L".repeat(fifties);
        if (tens == 4) res += "XL";
        else res += "X".repeat(tens);
        if (ones == 4) res += "IV";
        else {
            if (fives == 4) res += "IX";
            else res += "V".repeat(fives);
            res += "I".repeat(ones);
        }
        return res;
    }

    public static void main(String[] args) throws Exception {new Main();}
}
