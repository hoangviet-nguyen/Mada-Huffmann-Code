import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class main {

    public static void main(String[] args) {

        //calculating occurrences of characters
        Map<Character,Integer> table = CalcHelper.occurenceTable("text.txt");
        String text = IOhelper.readFile("text.txt");

        //Generating Huffman Tree
        char[] charArray = CalcHelper.getCharArr(table);
        int[] charFreq = CalcHelper.getCharFreq(table);
        HuffmanNode root = Huffman.treeGenerator(charFreq, charArray);

        //Generating code table
        Map<Character, String> codeTable = Huffman.printCode( root, "", new HashMap<>());
        IOhelper.writeTable("dec tab.txt",codeTable);
        String output = Huffman.encode(text, codeTable);

        byte[] bytes = new byte[output.length() / 8];
        for (int i = 0; i < output.length(); i += 8) {
            String byteString = output.substring(i, i + 8);
            bytes[i / 8] = (byte) Integer.parseInt(byteString, 2);
        }

        IOhelper.writeByteToFile(bytes);

        // read and decode from output data
        bytes = IOhelper.readByte("output-mada.dat");
        int decoded = bytes.length;
        String binaryString = IOhelper.convertToString(bytes);
        Map<String, Character> mapper = IOhelper.getTableFromFile("dec_tab-mada.txt");
        String test = Huffman.decode(mapper, binaryString);
        System.out.println("Decoded: " + test);
        System.out.println("Mit Huffman verfahren: " + decoded);
        System.out.println("Ohne Huffman verfahren: " + CalcHelper.originalBytes(test));
        System.out.println("Einsparung = " + (double) decoded / CalcHelper.originalBytes(test));
    }
}
