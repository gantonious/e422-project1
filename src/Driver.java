/**
 * Created by George on 2017-03-04.
 */
public class Driver {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        Sorter dataSorter = new BackupDataSort();

        System.loadLibrary("InsertionSort");

        dataGenerator.generateDate("test.txt", 200);
        int[] inputArray = loadInputArray("test.txt");
        dataSorter.sort(inputArray, 2);
        writeOutputArray("sortedTest.txt", inputArray);
    }

    private static int[] loadInputArray(String inputFile) {
        String inputList = FileUtils.readFileContents(inputFile);
        return ArrayUtils.deserialize(inputList);
    }

    private static void writeOutputArray(String outputFile, int[] outputList) {
        String serializedList = ArrayUtils.serialize(outputList);
        FileUtils.writeToFile(outputFile, serializedList);
    }
}
