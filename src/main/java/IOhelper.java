import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

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

    public static byte[] readByte() {
        File file = new File("output.dat");
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bFile);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bFile;
    }
}
