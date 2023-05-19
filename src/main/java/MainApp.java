import java.util.HashMap;
import java.util.Map;


public class MainApp {

    public static void main(String[] args) {

        //calculating occurrences of characters
        Map<Character,Integer> table = CalcHelper.occurenceTable("text.txt");
        String text = IOhelper.readFile("text.txt");

        //Generating Huffman Tree
        char[] charArray = CalcHelper.getCharArr(table);
        int[] charFreq = CalcHelper.getCharFreq(table);
        HuffmanNode root = Huffman.treeGenerator(charFreq, charArray);

        //Generating code table & encode text
        Map<Character, String> codeTable = Huffman.printCode( root, "", new HashMap<>());
        IOhelper.writeTable("dec tab.txt",codeTable);
        String output = Huffman.encode(text, codeTable);

        //saving the encoded text in byte array
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
        System.out.println("");
        System.out.println("Decoded: \n" + test +"\n");
        IOhelper.writeFile(test, "decoded-mada.txt"); // write decoded text to file
        System.out.println("Mit Huffman verfahren: " + decoded +" Bytes");
        System.out.println("Ohne Huffman verfahren: " + CalcHelper.originalBytes(test) +" Bytes");
        System.out.println("Einsparung = " +(((1 - ((double) decoded / CalcHelper.originalBytes(test))))*100)+" %");
    }
}
