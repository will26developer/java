package kata_7;

public class VowelCount {
    public static int getCountFunctional(String str) {
        return (int) str.chars().filter(letter -> "aeiouAEIOU".indexOf(letter) >= 0).count();
    }

    public static int getCount(String str) {
        int count = 0;
        for (char letter : str.toCharArray()) {
            if ("aeiouAEIOU".indexOf(letter) >= 0) {
                count += 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(getCount("abracadabra"));
        System.out.println(getCountFunctional("abracadabra"));
    }
}
