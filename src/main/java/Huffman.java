import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    /**
     * @param root  root node of the Huffman tree
     * @param s builds codePoint String of the characters
     * @param table Map characters to occurences
     * @return return a Map with characters and Codepoint
     */
    public static Map<Character, String> printCode(HuffmanNode root, String s, Map<Character, String> table) {

        if (root.left == null && root.right == null) {
            table.put(root.c, s);
            return table;
        }

        printCode(root.left, s + "0", table);
        printCode(root.right, s + "1", table);

        return table;
    }

    /**
     * @param text text to encode
     * @param codeTable Map codePoint and character
     * @return encoded String
     */
    public static String encode(String text, Map<Character, String> codeTable) {

        StringBuilder output = new StringBuilder();

        for (char c : text.toCharArray()) {
            output.append(codeTable.get(c));
        }

        output.append("1");

        while (output.length() % 8 != 0) {
            output.append("0");
        }

        return output.toString();
    }

    /**
     * @param table Map codePoint to character
     * @param code with Huffman encoded String
     * @return decoded value of the code
     */
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

    /** Die Nodes wurden mithilfe von der Seite
     * https://www.programiz.com/dsa/huffman-coding#:~:text=Huffman%20coding%20first%20creates%20a,concept%20of%20prefix%20code%20ie
     * erstellt
     * @param charfreq  Array with frequencies of charactes
     * @param charArray Array with all distinct characters in text
     * @return root Huffman Node
     */
    public static HuffmanNode treeGenerator(int[] charfreq, char[] charArray) {

        int n = charArray.length;
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new NodeComparator());

        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.item = charfreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }

        HuffmanNode root = null;

        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.item = x.item + y.item;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }

        return root;
    }

}
