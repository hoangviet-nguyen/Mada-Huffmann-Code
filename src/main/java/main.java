import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class main {

    public static void main(String[] args) {


        HashMap<Character, Integer> table = CalcHelper.occurenceTable("text.txt");
        String text = IOhelper.readFile("text.txt");
        int n = table.size();
        char[] charArray = CalcHelper.getCharArr(table);
        int[] charfreq = CalcHelper.getCharFreq(table);

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
        System.out.println(" Charakter | Codepoint ");
        System.out.println("--------------------");

        HashMap<Character, String> codeTable = Huffman.printCode(root, "", new HashMap<>());

        IOhelper.createCodeTable("dec tab.txt",codeTable);

        String output = "";
        for (char c : text.toCharArray()) {
            output += codeTable.get(c);
        }

        System.out.println("Encoded: " + output);
        output = output + "1";
        while (output.length() % 8 != 0) {
            output = output + "0";
        }
        System.out.println("Encoded: " + output);
        IOhelper.writeFile(output, "encoded.txt");

        byte[] bytes = new byte[output.length() / 8];
        for (int i = 0; i < output.length(); i += 8) {
            String byteString = output.substring(i, i + 8);
            bytes[i / 8] = (byte) Integer.parseInt(byteString, 2);
        }

        System.out.println("Encoded: " + Arrays.toString(bytes));
        String binaryString = "";
        IOhelper.writeByteToFile(bytes);
        for (byte b : bytes) {
            binaryString += Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
        }
        System.out.println("Encoded: " + binaryString);
        String test = Huffman.getString(root, binaryString);
        System.out.println("Decoded: " + test);

    }
}
