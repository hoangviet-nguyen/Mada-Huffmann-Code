import java.util.HashMap;

public class Huffman {
    // IMplementing the huffman algorithm
        public static HashMap<Character,String> printCode(HuffmanNode root, String s, HashMap<Character, String> table) {
            if (root.left == null && root.right == null) {

                //System.out.println(root.c + "   |  " + s);
                table.put(root.c, (s));

                return table;
            }
            printCode(root.left, s + "0",table);
            printCode(root.right, s + "1",table);
            return table;
        }

        public static String getString(HuffmanNode root,  String code) {
                HuffmanNode original = root;
                StringBuilder decoded = new StringBuilder();
            for (char c :code.toCharArray()) {
                if (c == '0') {
                    root = root.left;
                } else if (c == '1') {
                    root = root.right;
                }

                if (root.left == null && root.right == null) {
                    decoded.append(root.c);
                    root = original;
                }
            }

            return decoded.toString();
        }

}
