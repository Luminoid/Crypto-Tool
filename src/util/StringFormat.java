package util;

import java.util.ArrayList;

/**
 * Created by Ethan on 16/9/27.
 */
public class StringFormat {
    public static String formatArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (String s : arr)
            sb.append(s).append("\n");
        return sb.toString();
    }

    public static String formatList(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(it -> sb.append(it).append("\n"));
        return sb.toString();
    }
}
