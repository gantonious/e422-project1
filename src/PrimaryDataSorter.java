import java.util.List;
import java.util.Random;

/**
 * Created by George on 2017-03-04.
 */
public class PrimaryDataSorter implements DataSorter {
    private int memoryAccesses;
    private double failureRate;

    @Override
    public void sort(String inputFile, String outputFile, double failureRate) {
        prepareForSorting(failureRate);
        int[] inputData = loadInputList(inputFile);
        int[] sortedData = sortData(inputData);
        writeOutputList(outputFile, sortedData);
    }

    private void prepareForSorting(double failureRate) {
        this.memoryAccesses = 0;
        this.failureRate = failureRate;
    }

    private int[] loadInputList(String inputFile) {
        String inputList = FileUtils.readFileContents(inputFile);
        return ArrayUtils.deserialize(inputList);
    }

    private void writeOutputList(String outputFile, int[] outputList) {
        String serializedList = ArrayUtils.serialize(outputList);
        FileUtils.writeToFile(outputFile, serializedList);
    }

    private double getMemoryFailureHazard() {
        return memoryAccesses * failureRate;
    }

    private void testMemory() {
        Random random = new Random(System.currentTimeMillis());
        int randInt = random.nextInt(1);

        if (randInt >= 0.5 && randInt <= 0.5 + getMemoryFailureHazard()) {
            throw new MemoryFailureException();
        }
    }

    private int getFrom(int[] data, int index) {
        testMemory();
        memoryAccesses += 1;
        return data[index];
    }

    private void setTo(int[] data, int index, int item) {
        testMemory();
        memoryAccesses += 1;
        data[index] = item;
    }

    private void swap(int[] data, int index1, int index2) {
        int temp = getFrom(data, index1);
        setTo(data, index1, getFrom(data, index2));
        setTo(data, index2, temp);
    }

    private int[] sortData(int[] inputData) {
        return inputData;
    }

    private void heapify(int[] input) {
        for (int i = 0; i < input.length; i++) {

        }
    }

    private void fixDown(List<Integer> input, int index, int endIndex) {

    }

}
