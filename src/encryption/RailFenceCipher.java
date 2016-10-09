package encryption;

import util.StringFormat;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/9/20.
 */
public class RailFenceCipher {
    public static ArrayList<String> decrypt(String cipher) {
        ArrayList<String> sol = new ArrayList<>(10);
        for (int rail = 1; rail < 10; rail++) {
            int cipherLen = cipher.length();
            if (cipherLen % rail == 0) {
                int len = cipherLen / rail;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < len; i++)
                    for (int j = 0; j < rail; j++)
                        sb.append(cipher.charAt(j * len + i));
                sol.add(sb.toString());
            }
        }
        return sol;
    }

    public static void main(String[] args) {
        System.out.println(StringFormat.formatList(decrypt("tn c0afsiwal kes,hwit1r  g,npt  ttessfu}ua u  hmqik e {m,  n huiouosarwCniibecesnren.")));
    }
}
