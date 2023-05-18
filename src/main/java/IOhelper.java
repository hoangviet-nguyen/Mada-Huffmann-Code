import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOhelper {

    public String readFile(String path){
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
            return"";
        }
        return text;
    }

    public Boolean writeFile(String text, String path){
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
}
