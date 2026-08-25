package kata_6;

import java.util.Arrays;

public class SpinWords {
    public String spinWords(String sentence) {
        StringBuilder strBuilder = new StringBuilder();
        for (String word : sentence.split(" ")) {
            String str = (word.length() >= 5) ? new StringBuilder(word).reverse().toString() : word;
            strBuilder.append(str);
        }
        return String.join(" ", strBuilder);
    }

    public String spinWordsFunctional(String sentence) {
        return String.join(" ", Arrays.stream(sentence.split(" "))
                .map(word -> (word.length() >= 5) ? new StringBuilder(word).reverse().toString() : word)
                .toList());
    }

    public static void main(String[] args) {
        SpinWords spinWords = new SpinWords();
        System.out.println(spinWords.spinWords("Hey fellow warriors"));
        System.out.println(spinWords.spinWordsFunctional("Hey Williams Martinez"));
    }

}
