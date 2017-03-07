import java.util.Random;

/**
 * Created by George on 2017-03-06.
 */
public class SortingThread extends Thread {
    private int[] data;
    private Sorter sorter;
    private boolean hasTimedout;
    private int totalMemAccesses;
    private double failureRate;

    public SortingThread(Sorter sorter, int[] data, double failureRate) {
        this.sorter = sorter;
        this.data = data;
        this.hasTimedout = false;
        this.totalMemAccesses = 0;
        this.failureRate = failureRate;
    }

    @Override
    public void run() {
        try {
            totalMemAccesses = sorter.sort(data);
        } catch (ThreadDeath threadDeath) {
            hasTimedout = true;
        }
    }

    private double getMemoryFailureHazard() {
        return totalMemAccesses * failureRate;
    }

    // from: http://stackoverflow.com/a/6078211
    private double randomDouble(double bound) {
        Random random = new Random(System.currentTimeMillis());
        return bound * random.nextFloat();
    }

    public void testForTimeout() {
        if (hasTimedout) {
            throw new TimeoutException();
        }
    }

    public void testForMemoryFailure() {
        double randDouble = randomDouble(1.0);

        if (randDouble >= 0.5 && randDouble <= 0.5 + getMemoryFailureHazard()) {
            throw new MemoryFailureException();
        }
    }
}
