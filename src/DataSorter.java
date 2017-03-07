/**
 * Created by George on 2017-03-06.
 */
public class DataSorter {
    private Sorter primarySorter;
    private Sorter backupSorter;
    private SortAdjudicator adjudicator;

    public DataSorter(Sorter primarySorter, Sorter backupSorter, SortAdjudicator adjudicator) {
        this.primarySorter = primarySorter;
        this.backupSorter = backupSorter;
        this.adjudicator = adjudicator;
    }

    public void execute(String inputFile,
                        String outputFile,
                        double primaryFailureRate,
                        double backupFailureRate,
                        double timeout) {

        int[] data = loadInputArray(inputFile);
        primarySorter.sort(data);
        writeOutputArray(outputFile, data);
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
