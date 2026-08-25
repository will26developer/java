package kata_6;

public class Order {
    public static String order(String words) {
        if (words.isEmpty()) {
            return "";
        }
        String[] wordArray = words.split(" ");
        String[] newWordArray = new String[wordArray.length];
        for (String word: wordArray) {
            for (char letter: word.toCharArray()) {
                if (Character.isDigit(letter)) {
                    newWordArray[(letter - '0') - 1] = word;
                }
            }
        }

        return String.join(" ", newWordArray);
    }

    public static void main(String[] args) {
        System.out.println(order("4of Fo1r pe6ople g3ood th5e the2"));
    }
}
