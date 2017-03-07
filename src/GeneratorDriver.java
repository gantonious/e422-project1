/**
 * Created by George on 2017-03-06.
 */
public class GeneratorDriver {
    public static void main(String[] args) {
        String inputFile = args[0];
        int numEntries = Integer.parseInt(args[1]);

        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateDate(inputFile, numEntries);
    }
}
