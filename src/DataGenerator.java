import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by George on 2017-03-04.
 */
public class DataGenerator {
    public void generateData(String fileName, int numToGenerate) {
        String serializedList = ArrayUtils.serialize(generateRandomInts(numToGenerate));
        FileUtils.writeToFile(fileName, serializedList);
    }

    private int[] generateRandomInts(int total) {
        int[] output = new int[total];
        Random rnd = new Random(System.currentTimeMillis());

        for (int i = 0; i < total; i++) {
            output[i] = rnd.nextInt();
        }

        return output;
    }
}
