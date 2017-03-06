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

        int[] data = loadInputArray(inputFile);
        sortData(data);
        writeOutputArray(outputFile, data);
    }

    private void prepareForSorting(double failureRate) {
        this.memoryAccesses = 0;
        this.failureRate = failureRate;
    }

    private int[] loadInputArray(String inputFile) {
        String inputList = FileUtils.readFileContents(inputFile);
        return ArrayUtils.deserialize(inputList);
    }

    private void writeOutputArray(String outputFile, int[] outputList) {
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

    private void sortData(int[] inputData) {
        int endIndex = inputData.length - 1;
        heapify(inputData, endIndex);

        while (endIndex > 0) {
            swap(inputData, 0, endIndex);
            endIndex -= 1;
            fixDown(inputData, 0, endIndex);
        }
    }

    private void heapify(int[] input, int endIndex) {
        for (int i = 0; i < input.length / 2; i++) {
            fixDown(input, i, endIndex);
        }
    }

    private void fixDown(int[] input, int index, int endIndex) {
        int maxIndex;
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        if (leftChildIndex > endIndex) {
            return;
        }

        if (rightChildIndex > endIndex ||
            getFrom(input, leftChildIndex) > getFrom(input, rightChildIndex)) {
            maxIndex = leftChildIndex;
        } else {
            maxIndex = rightChildIndex;
        }

        if (getFrom(input, maxIndex) > getFrom(input, index)) {
            swap(input, index, maxIndex);
            fixDown(input, maxIndex, endIndex);
        }
    }

}
