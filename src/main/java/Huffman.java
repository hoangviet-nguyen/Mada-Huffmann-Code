import java.util.HashMap;
import java.util.Map;

public class Huffman {

    /**
     *
     * @param root
     * @param s
     * @param table
     * @return
     */
    public static Map<Character, String> printCode(HuffmanNode root, String s, Map<Character, String> table) {
        if (root.left == null && root.right == null) {
            table.put(root.c, (s));

            return table;
        }
        printCode(root.left, s + "0", table);
        printCode(root.right, s + "1", table);
        return table;
    }

    public static String decode(Map<String, Character> table, String code) {
        StringBuilder decoded = new StringBuilder();
        String codepoint = "";
        for (char c : code.toCharArray()) {
            codepoint += c;
            if (table.containsKey(codepoint)) {
                decoded.append(table.get(codepoint));
                codepoint = "";
            }
        }
        return decoded.toString();
    }


}
