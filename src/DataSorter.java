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
                        long timeout) {

        int[] data = loadInputArray(inputFile);

        try {
            executePrimarySort(data, primaryFailureRate, timeout);
        } catch (LocalException e) {
            // reload data from last good state
            data = loadInputArray(inputFile);
            executeBackupSort(data, backupFailureRate, timeout);
        }

        writeOutputArray(outputFile, data);
    }

    private void executePrimarySort(int[] data, double failureRate, long timeout) {
        try {
            executeSortingStrategy(data, primarySorter, failureRate, timeout);
        } catch (Exception e) {
            System.out.println("Primary Sorting Strategy failed due to: " + e.toString());
            throw new LocalException();
        }
    }

    private void executeBackupSort(int[] data, double failureRate, long timeout) {
        try {
            executeSortingStrategy(data, backupSorter, failureRate, timeout);
        } catch (Exception e) {
            System.out.println("Backup Sorting Strategy failed due to: " + e.toString());
            throw new FailureException();
        }
    }

    private void executeSortingStrategy(int[] data, Sorter sorter, double failureRate, long timeout) {
        SortingThread sortingThread = new SortingThread(sorter, data, failureRate);
        Watchdog sortWatchdog = new Watchdog(sortingThread);

        sortWatchdog.startWatch(timeout);
        sortWatchdog.waitOnThread();

        sortingThread.testForTimeout();
        sortingThread.testForMemoryFailure();
        adjudicator.testIsArraySorted(data);
    }

    private int[] loadInputArray(String inputFile) {
        String inputList = FileUtils.readFileContents(inputFile);
        return ArrayUtils.deserialize(inputList);
    }

    private void writeOutputArray(String outputFile, int[] outputList) {
        String serializedList = ArrayUtils.serialize(outputList);
        FileUtils.writeToFile(outputFile, serializedList);
    }
}
