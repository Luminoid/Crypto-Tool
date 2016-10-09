package encryption;

import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Ethan on 16/10/5.
 */
public class VigenereCipher {
    private static final double IC_ENGLISH = 0.067;

    public static String encrypt(String plain, String key) {
        try {
            char[] cipher = plain.toLowerCase().toCharArray();
            int[] shift = key.toLowerCase().chars().map(c -> c - 'a').toArray();
            for (int i = 0; i < plain.length(); i++) {
                char c = cipher[i];
                cipher[i] = (char) ((c >= 97 && c <= 122) ? c + shift[i % key.length()] : c);
                cipher[i] = (char) (cipher[i] > 122 ? cipher[i] - 26 : cipher[i]);
            }
            return String.valueOf(cipher);
        } catch (Exception e) {
            return "Format Error!";
        }
    }

    public static String decrypt(String cipher) {
        try {
            // Compute the key length
            int length = findLen(cipher);
            // Compute the key value
            String[] seqs = sliceString(cipher, length);
            int[] shifts = Stream.of(seqs).mapToInt(VigenereCipher::findShift).toArray();
            String[] plains = new String[length];
            for (int i = 0; i < length; i++) {
                plains[i] = CaesarCipher.encrypt(seqs[i], shifts[i]);
            }
            return mergeString(plains);
        } catch (Exception e) {
            return "Format Error!";
        }
    }

    public static int findShift(String seq) {
        // Find all ics
        final double[] freq = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
                0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978,
                0.02360, 0.00150, 0.01974, 0.00074};
        double[] ics = new double[26];
        HashMap<Character, Integer> countChar = countChar(seq);
        for (int i = 0; i < 26; i++) {
            final int shift = i;
            HashMap<Character, Integer> shiftChar = new HashMap<>();
            countChar.keySet().forEach(c -> shiftChar.put((char) (c + shift - (c + shift > 122 ? 26 : 0)), countChar.get(c)));
            ics[i] = shiftChar.keySet().stream().mapToDouble(c -> (double) shiftChar.get(c) * freq[c - 'a'] / seq.length()).sum();
        }
//        System.out.println(Arrays.toString(ics));

        // Find the ic closest to IC_ENGLISH
        double dif = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < 26; i++) {
            double currentDif = Math.abs(ics[i] - IC_ENGLISH);
            index = currentDif < dif ? i : index;
            dif = currentDif < dif ? currentDif : dif;
        }
//        System.out.println(index);
        return index;
    }

    /**
     * Find the length of the key
     * The correct key length corresponds with the max IC
     */
    public static int findLen(String cipher) {
        // Get all ics
        double[] ics = new double[20];
        for (int i = 1; i <= 20; i++) {
            ics[i - 1] = averageIC(cipher, i);
        }
//        System.out.println(Arrays.toString(ics));

        // Find the max ic
        int index = 0;
        for (int i = 0; i < 20; i++) {
            index = (ics[i] - ics[index] > 0.005) ? i : index;
        }
        return index + 1;
    }

    /**
     * Index of coincidence
     */
    public static double averageIC(String cipher, int keyLen) {
        String[] group = sliceString(cipher, keyLen);
        return Stream.of(group).mapToDouble(s -> computeIC(s)).sum() / keyLen;
    }

    public static double computeIC(String seq) {
        int[] count = countChar(seq).values().stream().mapToInt(Integer::intValue).toArray();
        return IntStream.range(0, count.length).
                mapToDouble(i -> (double) (count[i] * (count[i] - 1)) / (seq.length() * (seq.length() - 1))).sum();
    }

    public static String[] sliceString(String str, int length) {
        String[] ret = new String[length];
        for (int seq = 0; seq < length; seq++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; seq + i * length < str.length(); i++) {
                sb.append(str.charAt(seq + i * length));
            }
            ret[seq] = sb.toString();
        }
        return ret;
    }

    public static String mergeString(String[] seqs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seqs[0].length(); i++) {
            for (String seq : seqs) {
                if (i < seq.length()) sb.append(seq.charAt(i));
            }
        }
        return sb.toString();
    }

    public static HashMap<Character, Integer> countChar(String str) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c : str.toCharArray())
            countMap.put(c, countMap.containsKey(c) ? countMap.get(c) + 1 : 1);
        return countMap;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("CRYPTOISSHORTFORCRYPTOGRAPHY", "abcd")); // csastpkvsiqutgqucsastpiuaqjb
        System.out.println(decrypt(encrypt
                        ("fourscoreandsevenyearsagoourfathersbroughtforthuponthiscontinentanewnationconceivedinlibertyanddedicatedtothepropositionthatallmenarecreatedequalnowweareengagedinagreatcivilwartestingwhetherthatnationoranynationsoconceivedandsodedicatedcanlongendurewearemetonagreatbattlefieldofthatwarwehavecometodedicateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveitisaltogetherfittingandproperthatweshoulddothis", "dream")));
        System.out.println(decrypt(encrypt
                ("fourscoreandsevenyearsagoourfathersbroughtforthuponthiscontinentanewnationconceivedinlibertyanddedicatedtothepropositionthatallmenarecreatedequalnowweareengagedinagreatcivilwartestingwhetherthatnationoranynationsoconceivedandsodedicatedcanlongendurewearemetonagreatbattlefieldofthatwarwehavecometodedicateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveitisaltogetherfittingandproperthatweshoulddothis", "dream"))
                .equals("fourscoreandsevenyearsagoourfathersbroughtforthuponthiscontinentanewnationconceivedinlibertyanddedicatedtothepropositionthatallmenarecreatedequalnowweareengagedinagreatcivilwartestingwhetherthatnationoranynationsoconceivedandsodedicatedcanlongendurewearemetonagreatbattlefieldofthatwarwehavecometodedicateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveitisaltogetherfittingandproperthatweshoulddothis"));
    }
}
