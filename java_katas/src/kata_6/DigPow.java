package kata_6;

import java.util.stream.IntStream;

public class DigPow {

    public static long digPowFunctional(int n, int p) {
        String number = String.valueOf(n);
        long num = IntStream.range(0, number.length())
                .map(i -> (int) Math.pow(number.charAt(i) - '0', p + i)).sum();
        return (num % n == 0) ? num / n : -1;
    }

    public static long digPow(int n, int p) {
        long sum = 0;
        for (char numChar : String.valueOf(n).toCharArray()) {
            sum += (long) Math.pow(numChar - '0', p++);
        }
        return (sum % n == 0) ? sum / n : -1;
    }

    public static void main(String[] args) {
        System.out.println(digPowFunctional(46288, 3));
        System.out.println(digPow(46288, 3));
    }
}
