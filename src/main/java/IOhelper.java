import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class IOhelper {

    public static String readFile(String path) {
        String text = "";
        try {
            Scanner reader = null;
            File textFile = new File(path);
            reader = new Scanner(textFile);
            while (reader.hasNextLine()) {
                text = reader.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Lesen der Datei");
            throw new RuntimeException(e);
        }

        if (text.isEmpty()) {
            System.out.println("Die Textdatei ist leer.");
            return "";
        }
        return text;
    }

    public static boolean writeFile(String text, String path) {
        try {
            java.io.FileWriter myWriter = new java.io.FileWriter(path);
            myWriter.write(text);
            myWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println("Fehler beim Schreiben der Datei");
            throw new RuntimeException(e);
        }
    }

    public static void writeByteToFile(byte[] array) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("output.dat");
            fos.write(array);
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readByte(String path) {
        File file = new File(path);
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

    public static String convertToString(byte[] bytes) {
        String code = "";

        for (int value : bytes) {
            code += Integer.toBinaryString((value & 0xFF) + 0x100).substring(1);
        }
        //tastet String von hinten nach nullen ab
        int i = code.length() - 1;

        while (i >= 0 && code.charAt(i) != '1') {
            i--;
        }
        code = code.substring(0, i);
        return code;
    }



    public static void writeTable(String path, Map<Character, String> codeTable) {
        String decTable = "";
        for (Character c : codeTable.keySet()) {
            decTable += (int) c + ":" + codeTable.get(c) + "-";
        }
        writeFile(decTable.substring(0, decTable.length() - 1), path);
    }

    public static Map<String, Character> getTableFromFile(String codeTable) throws RuntimeException {
        String code = readFile(codeTable);
        List<String> mapper = Arrays.stream(code.split("-")).toList();
        List<List<String>> raw = new ArrayList<>();
        for (String pair:mapper) {
            raw.add(Arrays.stream(pair.split(":")).toList());
        }

        Map<String, Character> codePoint = new HashMap<>();
        for (List pair : raw) {
            codePoint.put((String)pair.get(1), (char) Integer.parseInt((String)pair.get(0)));
        }
        return codePoint;
    }
}
