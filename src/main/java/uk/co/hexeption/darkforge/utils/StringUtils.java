package uk.co.hexeption.darkforge.utils;

/**
 * Created by Hexeption on 15/01/2017.
 */
public class StringUtils {

    private static final char[] ALPHANUM = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0'

    };

    private static final char[] CONSONANTS = new char[]{
            'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x',
            'z', 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W',
            'X', 'Z'

    };

    public static String addED(final String in) {

        final char end = in.charAt(in.length() - 1);

        String out = in;

        if (charArrayContains(end, CONSONANTS)) {
            out += ((new Character(end)).toString());
            out += "ed";
        } else if (new Character(end).equals('y')) {
            out = in.substring(0, in.length() - 1) + "ied";
        } else if (new Character(end).equals('e')) {
            out += "d";
        } else {
            out += "ed";
        }

        return out;
    }

    private static boolean charArrayContains(final char e, final char[] f) {

        for (final char z : f) {
            if (e == z) {
                return true;
            }
        }
        return false;
    }


}
