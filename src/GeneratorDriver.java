/**
 * Created by George on 2017-03-06.
 */
public class GeneratorDriver {
    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            int numEntries = Integer.parseInt(args[1]);

            run(inputFile, numEntries);
        } catch (Exception e) {
            System.out.println("Usage: java GeneratorDriver <input_filename> <num_entries>");
        }
    }

    public static void run(String inputFile, int numEntries) {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateDate(inputFile, numEntries);
    }
}
