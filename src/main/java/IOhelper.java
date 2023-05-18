import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOhelper {

    /**
     * @param path path to src file
     * @return text from file saved in a String
     */

    public static String readFile(String path) {

        String text = "";

        try {

            Scanner reader;
            File textFile = new File(path);
            reader = new Scanner(textFile);

            while (reader.hasNextLine()) {
                text = reader.nextLine();
            }

            if (text.isEmpty()) {
                System.out.println("Die Textdatei ist leer.");
                return "";
            }

        } catch (FileNotFoundException e) {

            System.out.println("Fehler beim Lesen der Datei");
            throw new RuntimeException(e);

        }

        return text;
    }

    /**
     * @param text original text
     * @param path file output path
     */

    public static void writeFile(String text, String path) {

        try {

            java.io.FileWriter myWriter = new java.io.FileWriter(path);
            myWriter.write(text);
            myWriter.close();

        } catch (Exception e) {

            System.out.println("Fehler beim Schreiben der Datei");
            throw new RuntimeException(e);

        }
    }

    /**
     * @param array encoded String that has been converted to byte array / writes the byte array to a file
     */

    public static void writeByteToFile(byte[] array) {

        FileOutputStream fos;

        try {

            fos = new FileOutputStream("output.dat");
            fos.write(array);
            fos.close();

        } catch (IOException e) {

            System.out.println("Error in writing File");
            throw new RuntimeException(e);

        }
    }

    /**
     * @param code path for encoded bytes
     * @return returns the byte array from the file
     */

    public static byte[] readByte(String code) {

        File file = new File(code);
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fis;

        try {

            fis = new FileInputStream(file);
            fis.read(bFile);
            fis.close();

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

        return bFile;
    }

    /**
     * @param bytes encoded String saved in a byte array
     * @return String of 1 and 0 that is saved within the array
     */

    public static String convertToString(byte[] bytes) {

        StringBuilder code = new StringBuilder();

        for (int value : bytes) {
            code.append(Integer.toBinaryString((value & 0xFF) + 0x100).substring(1));
        }

        //searches for the last 1 in the String and cuts it
        int i = code.length() - 1;
        while (i >= 0 && code.charAt(i) != '1') {
            i--;
        }

        code = new StringBuilder(code.substring(0, i));
        return code.toString();
    }


    /**
     * @param outputFile path for the outputFile
     * @param codeTable CodePoint to character mapper
     */

    public static void writeTable(String outputFile, Map<Character, String> codeTable) {

        StringBuilder decTable = new StringBuilder();

        for (Character c : codeTable.keySet()) {
            decTable.append((int) c).append(":").append(codeTable.get(c)).append("-");
        }

        writeFile(decTable.substring(0, decTable.length() - 1), outputFile);
    }

    /**
     * @param codeTable src path for file code table
     * @return CodePoint to character mapper
     */

    public static Map<String, Character> getTableFromFile(String codeTable) {

        String code = readFile(codeTable);
        List<String> mapper = Arrays.stream(code.split("-")).toList();
        List<List<String>> raw = new ArrayList<>();

        for (String pair : mapper) {
            raw.add(Arrays.stream(pair.split(":")).toList());
        }

        Map<String, Character> codePoint = new HashMap<>();

        for (List<String> pair : raw) {
            codePoint.put(pair.get(1), (char) Integer.parseInt(pair.get(0)));   // 1 = codePoint, 0 = character
        }

        return codePoint;
    }

}
