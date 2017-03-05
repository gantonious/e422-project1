import java.io.*;

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

    // From: http://stackoverflow.com/a/5445161
    public static String readFileContents(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            java.util.Scanner s = new java.util.Scanner(br).useDelimiter("\\A");
            String content = s.hasNext() ? s.next() : "";

            br.close();

            return content;
        } catch (Exception e) {
            return "";
        }
    }
}
