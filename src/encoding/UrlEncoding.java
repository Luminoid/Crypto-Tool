package encoding;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Ethan on 16/9/28.
 */
public class UrlEncoding {
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception ex) {
            return "Encoding error!";
        }
    }

    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (Exception ex) {
            return "Encoding error!";
        }
    }

    public static void main(String[] args) {
        System.out.println(encode("中文"));
        System.out.println(decode("%E4%B8%AD%E6%96%87"));
    }
}
