package encryption;

import operation.Operation;

/**
 * Created by Ethan on 16/10/4.
 * <p>
 * Hexadecimal ASCII character sets
 * <p>
 * Bit    4:      0000000000000000 1111111111111111 |
 * Bits 0-3:      0123456789ABCDEF 0123456789ABCDEF | Block:
 * -------------+-----------------------------------+---------------------------
 * Bits 5-6: 00 | ................ ................ | Control characters
 * 01 |  !"#$%&'()*+,-./ 0123456789:;<=>? | Numbers and punctuation
 * 10 | @ABCDEFGHIJKLMNO PQRSTUVWXYZ[\]^_ | Uppercase letters (mostly)
 * 11 | `abcdefghijklmno pqrstuvwxyz{|}~. | Lowercase letters (mostly)
 */

public class OneTimePadCipher {
    public static void decrypt(String cipher, String target) {
        String plain = "";
        String[] ciphers = cipher.split("\n");
        for (String s : ciphers) {
            xorString(s, target);
        }
    }

    public static String xorString(String str1, String str2) {
        int len = str1.length() >= str2.length() ? str2.length() : str1.length();
        return Operation.xorHex(str1, str2).substring(0, len);
    }

    public static void parse(String str) {

    }

    public static void main(String[] args) {

    }

    public class token {
        int hex;
        String val;

        public token(String hex) {
            this.hex = Integer.parseInt(hex, 16);
        }
    }

}
