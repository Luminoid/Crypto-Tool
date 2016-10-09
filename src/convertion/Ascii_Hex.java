package convertion;

/**
 * Created by Ethan on 16/10/3.
 */
public class Ascii_Hex {
    public static String asciiToHex(String asciiVal) {
        try {
            StringBuilder hex = new StringBuilder();
            for (char c : asciiVal.toCharArray()) {
                hex.append(Integer.toHexString((int) c));
            }
            return hex.toString();
        } catch (Exception ex) {
            return "Format Error!";
        }
    }

    public static String hexToAscii(String hexVal) {
        try {
            StringBuilder ascii = new StringBuilder();
            for (int i = 0; i < hexVal.length(); i += 2) {
                ascii.append((char) Integer.parseInt(hexVal.substring(i, i + 2), 16));
            }
            return ascii.toString();
        } catch (Exception ex) {
            return "Format Error!";
        }
    }

    public static void main(String[] args) {
        System.out.println(asciiToHex(" Aaz"));
        System.out.println(hexToAscii("2041617a"));
    }
}
