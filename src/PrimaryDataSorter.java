/**
 * Created by George on 2017-03-04.
 */
public class PrimaryDataSorter implements Sorter {
    private int memoryAccesses;

    @Override
    public int sort(int data[]) {
        prepareForSorting();
        sortData(data);
        return memoryAccesses;
    }

    private void prepareForSorting() {
        this.memoryAccesses = 0;
    }

    private int getFrom(int[] data, int index) {
        memoryAccesses += 1;
        return data[index];
    }

    private void setTo(int[] data, int index, int item) {
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
