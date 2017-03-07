/**
 * Created by George on 2017-03-06.
 */
public class SortingThread extends Thread {
    private int[] data;
    private Sorter sorter;

    private int totalMemAccesses = -1;

    public SortingThread(Sorter sorter, int[] data) {
        this.sorter = sorter;
        this.data = data;
    }

    @Override
    public void run() {
        totalMemAccesses = sorter.sort(data);
    }
}
