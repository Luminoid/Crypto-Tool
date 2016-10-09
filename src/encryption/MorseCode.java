package encryption;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 16/9/28.
 */
public class MorseCode {
    public static String encrypt(String text) {
        HashMap<String, String> codeMap = init();
        try {
            return Arrays.stream(text.split("")).map(e -> codeMap.get(e) + " ").collect(Collectors.joining());
        } catch (Exception ex) {
            return "Format error!";
        }
    }

    public static String decrypt(String code) {
        HashMap<String, String> codeMap = init();
        try {
            return Arrays.stream(code.split(" ")).map(e -> getKey(codeMap, e)).collect(Collectors.joining());
        } catch (Exception ex) {
            return "Format Error!";
        }
    }

    public static HashMap<String, String> init() {
        HashMap<String, String> morseCode = new HashMap<>();
        morseCode.put("a", ".-");
        morseCode.put("b", "-...");
        morseCode.put("c", "-.-.");
        morseCode.put("d", "-..");
        morseCode.put("e", ".");
        morseCode.put("f", "..-.");
        morseCode.put("g", "--.");
        morseCode.put("h", "....");
        morseCode.put("i", "..");
        morseCode.put("j", ".---");
        morseCode.put("k", "-.-");
        morseCode.put("l", ".-..");
        morseCode.put("m", "--");
        morseCode.put("n", "-.");
        morseCode.put("o", "---");
        morseCode.put("p", ".--.");
        morseCode.put("q", "--.-");
        morseCode.put("r", ".-.");
        morseCode.put("s", "...");
        morseCode.put("t", "-");
        morseCode.put("u", "..-");
        morseCode.put("v", "...-");
        morseCode.put("w", ".--");
        morseCode.put("x", "-..-");
        morseCode.put("y", "-.--");
        morseCode.put("z", "--..");
        return morseCode;
    }

    private static String getKey(HashMap<String, String> hashMap, String value) {
        return hashMap.entrySet().stream().filter(e -> e.getValue().equals(value)).map(Map.Entry::getKey).findFirst().get();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("morsecode"));
        System.out.println(decrypt("-- --- .-. ... . -.-. --- -.. ."));
    }
}
