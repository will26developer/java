package kata_6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticPosition {
    public static String alphabetPosition(String text) {
        List<String> positions = new ArrayList<>();
        for (Character letter : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(letter)) {
                String numStr = String.valueOf(letter - 'a' + 1);
                positions.add(numStr);
            }
        }

        return String.join(" ", positions);
    }

    public static String alphabetPositionFunctional(String text) {
        return text.toLowerCase().chars().filter(Character::isLetter)
                .mapToObj(letter -> String.valueOf(letter - 'a' + 1)).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        System.out.println(alphabetPosition("The sunset sets at twelve o' clock."));
        System.out.println(alphabetPositionFunctional("The sunset sets at twelve o' clock."));
    }
}
