public class Huffman {

    // Implementing the huffman algorithm
        public static void printCodeTable(HuffmanNode root, String s) {
            if (root.left == null && root.right == null) {

                System.out.println(root.c + "   |  " + s);

                return;
            }
            printCodeTable(root.left, s + "0");
            printCodeTable(root.right, s + "1");
        }

        public static char getChar(HuffmanNode root,  char code) {
                if (code == '0') root = root.left;
                else if (code == '1') root = root.right;
                else if (root.left == null && root.right == null) return root.c;

            return '1';
        }

}
