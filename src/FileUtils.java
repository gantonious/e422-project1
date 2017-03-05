import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by George on 2017-03-05.
 */
public class FileUtils {
    public static void writeToFile(String fileName, String fileContents) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File(fileName)));
            bf.write(fileContents);
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFileContents(String fileName) {
        return "";
    }
}
