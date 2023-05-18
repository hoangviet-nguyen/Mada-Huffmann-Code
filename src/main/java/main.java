import java.util.PriorityQueue;

public class main {

    public static void main(String[] args) {

        int n = 4;
        char[] charArray = {' ', 'B', 'C', 'D'};
        int[] charfreq = {5, 1, 6, 3};

        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new NodeComparator());

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
        System.out.println(" Char | Huffman code ");
        System.out.println("--------------------");
        Huffman.printCode(root, "");
    }
}
