package encoding;

import java.util.Base64;

/**
 * Created by Ethan on 16/9/28.
 */
public class Base64Encoding {
    public static String encode(String str) {
        try {
            return Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
        } catch (Exception ex) {
            return "Encoding error!";
        }
    }

    public static String decode(String str) {
        try {
            return new String(Base64.getDecoder().decode(str), "utf-8");
        } catch (Exception ex) {
            return "Encoding error!";
        }
    }

    public static void main(String[] args) {
        System.out.println(encode("String1234"));
        System.out.println(decode("U3RyaW5nMTIzNA=="));
    }
}
