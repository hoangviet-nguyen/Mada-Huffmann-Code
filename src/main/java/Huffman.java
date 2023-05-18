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

        public static char getChar(HuffmanNode root,  char code) {
                if (code == '0') root = root.left;
                else if (code == '1') root = root.right;
                else if (root.left == null && root.right == null) return root.c;

            return '1';
        }

}
