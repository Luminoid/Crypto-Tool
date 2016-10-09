package encryption;

import util.StringFormat;

/**
 * Created by Ethan on 16/9/14.
 */
public class CaesarCipher {
    public static String encrypt(String plain, int shift) {
        return plain.toLowerCase().chars().map(c -> (char) ((c >= 97 && c <= 122) ? c + shift : c)).
                collect(StringBuilder::new, (sb, c) -> sb.append((char) (c > 122 ? c - 26 : c)), StringBuilder::append).toString();
    }

    public static String[] decrypt(String cipher) {
        String[] sol = new String[26];
        for (int i = 0; i < 26; i++) {
            final int shift = i;
            sol[i] = cipher.toLowerCase().chars().map(c -> (char) ((c >= 97 && c <= 122) ? c + shift : c)).
                    collect(StringBuilder::new, (sb, c) -> sb.append((char) (c > 122 ? c - 26 : c)), StringBuilder::append).toString();
        }
        return sol;
    }

    public static void main(String[] args) {
        System.out.println(StringFormat.formatArray(decrypt("yvccf nficu"))); // "hello world"
    }
}
