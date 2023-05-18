import java.util.HashMap;
import java.util.Map;

public class CalcHelper {

    /**
     *
     * @param file path to read text from
     * @return Map occurrences and characters
     */
    public static Map<Character, Integer> occurenceTable(String file) {
        HashMap<Character, Integer> table = new HashMap<>();
        String text = IOhelper.readFile(file);

        for (char c : text.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        return table;
    }

    /**
     *
     * @param table Map occurrences and characters
     * @return frequency of characters in an Array
     */
    public static int[] getCharFreq(Map<Character, Integer> table) {
        int[] charfreq = new int[table.size()];
        int index = 0;
        for (Integer value : table.values()) {
            charfreq[index] = value;
            index++;
        }
        return charfreq;
    }

    /**
     *
     * @param table Map occurrences and characters
     * @return all the distinct characters of the text in an Array
     */
    public static char[] getCharArr(Map<Character, Integer> table) {
        char[] charArray = new char[table.size()];
        int index = 0;
        for (Character c : table.keySet()) {
            charArray[index] = c;
            index++;
        }
        return charArray;
    }

    /**
     *
     * @param text path to read text from
     * @return number of bytes used for the text
     */
    public static int originalBytes(String text) {
        int numberOfBytes = 0;
        for (Character c : text.toCharArray()) {
            numberOfBytes++;
        }
        return numberOfBytes * 8;
    }
}
