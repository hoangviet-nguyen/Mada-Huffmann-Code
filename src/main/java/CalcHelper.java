import java.util.HashMap;

public class CalcHelper {

    public static HashMap<Character, Integer> occurenceTable(String file) {
        HashMap<Character, Integer> table = new HashMap<>();
        String text = IOhelper.readFile(file);

        for (char c : text.toCharArray()) {
            table.put(c, table.getOrDefault(c, 0) + 1);
        }
        return table;
    }

    public static int[] getCharFreq(HashMap<Character, Integer> table) {
        int[] charfreq = new int[table.size()];
        int index = 0;
        for (Integer value : table.values()) {
            charfreq[index] = value;
            index++;
        }
        return charfreq;
    }

    public static char[] getCharArr(HashMap<Character, Integer> table) {
        char[] charArray = new char[table.size()];
        int index = 0;
        for (Character c : table.keySet()) {
            charArray[index] = c;
            index++;
        }
        return charArray;
    }
}
