import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by George on 2017-03-04.
 */
public class DataGenerator {
    public void generateDate(String fileName, int numToGenerate) {
        String serializedList = ListUtils.serialize(generateRandomInts(numToGenerate));
        FileUtils.writeToFile(fileName, serializedList);
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
