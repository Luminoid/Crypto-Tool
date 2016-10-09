package operation;

import convertion.Ascii_Hex;
import util.StringFormat;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Ethan on 16/9/27.
 */
public class Operation {
    public static String[] shift(String cipher) {
        String[] sol = new String[26];
        for (int i = 0; i < 26; i++) {
            final int shift = i;
            sol[i] = cipher.chars().map(c -> (char) (c + shift)).
                    collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append).toString();
        }
        return sol;
    }

    public static String xorHex(String str1, String str2) {
        try {
            BigInteger i1 = new BigInteger(str1, 16);
            BigInteger i2 = new BigInteger(str2, 16);
            BigInteger i3 = i1.xor(i2);
            return i3.toString(16);
        } catch (Exception e) {
            return "Format Error!";
        }
    }

    public static String[] cribDrag(String xor, String guess) {
        try {
            String guessHex = Ascii_Hex.asciiToHex(guess);
            int len = xor.length() - guessHex.length() + 2;
            ArrayList<String> ret = new ArrayList<>();

            for (int i = 0; i < len; i += 2) {
                String xorHex = Operation.xorHex(guessHex, xor.substring(i, i + guessHex.length()));
                String xorStr = Ascii_Hex.hexToAscii(xorHex);
                if (xorStr.matches("[a-zA-Z .,{}]+")) {
                    ret.add(xorStr);
                }
            }

            return ret.toArray(new String[ret.size()]);
        } catch (Exception ex) {
            String[] ret = new String[1];
            ret[0] = "The guess word is too long!";
            return ret;
        }
    }

    public static void main(String[] args) {
        System.out.println(StringFormat.formatArray(shift("U8Y]:8KdJHTXRI>XU#?!K_ecJH]kJG*bRH7YJH7YSH]*=93dVZ3^S8*$:8\"&:9U]RH;" +
                "g=8Y!U92'=j*$KH]ZSj&[S#!gU#*dK9\\.")));

        System.out.println(xorHex("11", "23")); // 32

        // message: "Hello World" and "the program"; key: "supersecret"; guess: "the "
        System.out.println(StringFormat.formatArray(cribDrag(xorHex("3b101c091d53320c000910", "071d154502010a04000419"), "the ")));

    }
}
