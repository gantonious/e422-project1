import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by George on 2017-03-04.
 */
public class DataGenerator {
    public void generateDate(String fileName, int numToGenerate) {
        writeDataToFile(fileName, generateRandomInts(numToGenerate));
    }

    private void writeDataToFile(String fileName, List<Integer> data) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(new File(fileName)));
            for (int item: data) {
                bf.write(String.format("%d\n", item));
            }
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Integer> generateRandomInts(int total) {
        List<Integer> output = new ArrayList<>();
        Random rnd = new Random(System.currentTimeMillis());

        for (int i = 0; i < total; i++) {
            output.add(rnd.nextInt());
        }

        return output;
    }
}
