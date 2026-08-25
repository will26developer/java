package kata_6;

public class Narcissistic {
    public static boolean isNarcissistic(int number) {
        int num = 0;
        String strNum = String.valueOf(number);
        int len = strNum.length();

        for (char digit : strNum.toCharArray()) {
            num += (int) Math.pow((digit - '0'), len);
        }

        return num == number;

    }

    public static boolean isNarcissisticFunctional(int number) {
        String strNum = String.valueOf(number);
        int num = strNum.chars().map(digit -> (int) Math.pow((digit - '0'), strNum.length())).sum();
        return num == number;
    }

    public static void main(String[] args) {
        System.out.println(isNarcissistic(153));
        System.out.println(isNarcissisticFunctional(153));
    }
}
