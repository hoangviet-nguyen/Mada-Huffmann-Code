import java.util.HashMap;
import java.util.Map;

public class CalcHelper {

    public static HashMap<Character, Integer> occurenceTable(String file) {
        HashMap<Character, Integer> table = new HashMap<>();
        String text = IOhelper.readFile(file);

        for (char c : text.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        return table;
    }

    public static int[] getCharFreq(Map<Character, Integer> table) {
        int[] charfreq = new int[table.size()];
        int index = 0;
        for (Integer value : table.values()) {
            charfreq[index] = value;
            index++;
        }
        return charfreq;
    }

    public static char[] getCharArr(Map<Character, Integer> table) {
        char[] charArray = new char[table.size()];
        int index = 0;
        for (Character c : table.keySet()) {
            charArray[index] = c;
            index++;
        }
        return charArray;
    }

    public static int originalBytes(String text) {
        int numberOfBytes = 0;
        for (Character c : text.toCharArray()) {
            numberOfBytes++;
        }
        return numberOfBytes * 8;
    }

    public static int decodedBytes(byte[] bytes) {
        return bytes.length;
    }
}
