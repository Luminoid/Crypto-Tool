package encoding;

import java.nio.charset.Charset;

/**
 * Created by Ethan on 16/9/28.
 */
public class UnicodeEncoding {
    public static String encode(String str) {
        return new String(str.getBytes(), Charset.forName("utf-8"));
    }

    public static void main(String[] args) {
        System.out.println(encode("aaa"));
    }
}
