public class Huffman {

    // IMplementing the huffman algorithm
        public static void printCode(HuffmanNode root, String s) {
            if (root.left == null && root.right == null) {

                System.out.println(root.c + "   |  " + s);

                return;
            }
            printCode(root.left, s + "0");
            printCode(root.right, s + "1");
        }
}
