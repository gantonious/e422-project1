import java.util.Random;

/**
 * Created by George on 2017-03-04.
 */
public class PrimaryDataSorter implements Sorter {
    private int memoryAccesses;
    private double failureRate;

    @Override
    public void sort(int data[], double failureRate) {
        prepareForSorting(failureRate);
        sortData(data);
    }

    private void prepareForSorting(double failureRate) {
        this.memoryAccesses = 0;
        this.failureRate = failureRate;
    }

    private double getMemoryFailureHazard() {
        return memoryAccesses * failureRate;
    }

    // from: http://stackoverflow.com/a/6078211
    private double randomDouble(double bound) {
        Random random = new Random(System.currentTimeMillis());
        return bound * random.nextFloat();
    }

    private void testMemory() {
        double randDouble = randomDouble(1.0);

        if (randDouble >= 0.5 && randDouble <= 0.5 + getMemoryFailureHazard()) {
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
        for (int i = input.length; i >= 0; i--) {
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
