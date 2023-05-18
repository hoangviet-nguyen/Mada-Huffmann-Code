import java.util.HashMap;
import java.util.Map;

public class CalcHelper {

    /**
     * @param file Datei pfad aus dem der Text gelesen wird
     * @return Anzahl Vorkommnisse werden auf die Zeichen gemapped
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
     * @param table Zeichen und ihre Anzahl im Text
     * @return Frequenz der Buchstaben im einem Array
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
     * @param table Zeichen und ihre Anzahl im Text
     * @return Einzelne Zeichen im Array
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
     * @param text text aus dem gelesen wird
     * @return Anzahl Bytes die für den text benutz wurden
     */
    public static int originalBytes(String text) {
        int numberOfBytes = 0;
        for (Character c : text.toCharArray()) {
            numberOfBytes++;
        }
        return numberOfBytes * 8;
    }
}
