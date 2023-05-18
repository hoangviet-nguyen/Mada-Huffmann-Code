import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class main {
    public static IOhelper iohelper = new IOhelper();

    public static void main(String[] args) {


        HashMap<Character,Integer> table = new HashMap<>();
        String text = iohelper.readFile("text.txt");

        for (char c: text.toCharArray()) {
            table.put(c, table.getOrDefault(c,0)+1);
        }
        int n = table.size();
        char[] charArray = new char[table.size()];
        int[] charfreq = new int[table.size()];
        Arrays.sort(charfreq);
        int index = 0;
        for (Character c : table.keySet()) {
            charArray[index] = c;
            index++;
        }

        index = 0;
        for (Integer value : table.values()) {
            charfreq[index] = value;
            index++;
        }



        PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(n, new NodeComparator());

        // erstellen der einzelnen nodes
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();

            hn.c = charArray[i];
            hn.item = charfreq[i];

            hn.left = null;
            hn.right = null;

            q.add(hn);
        }

        HuffmanNode root = null;

        //Verknüpfen von Nodes
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
        Huffman.printCodeTable(root, "");
    }
}
