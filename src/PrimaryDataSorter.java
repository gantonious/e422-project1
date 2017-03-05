import java.util.List;

/**
 * Created by George on 2017-03-04.
 */
public class PrimaryDataSorter implements DataSorter {
    @Override
    public void sort(String inputFile, String outputFile, int failureRate) {
        List<Integer> inputList = loadInputList(inputFile);
    }

    private List<Integer> loadInputList(String inputFile) {
        String inputList = FileUtils.readFileContents(inputFile);
        return ListUtils.deserialie(inputList);
    }

    private void writeOutputList(String outputFile, List<Integer> outputList) {
        String serializedList = ListUtils.serialize(outputList);
        FileUtils.writeToFile(outputFile, serializedList);
    }
}
