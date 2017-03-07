/**
 * Created by George on 2017-03-04.
 */
public class SortDriver {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        double primaryFailRate = Double.parseDouble(args[2]);
        double backupFailRate = Double.parseDouble(args[3]);
        long timeout = Long.parseLong(args[4]);

        System.loadLibrary("InsertionSort");

        Sorter primarySort = new PrimaryDataSorter();
        Sorter backupSort = new BackupDataSort();
        SortAdjudicator adjudicator = new SortAdjudicator();

        DataSorter dataSorter = new DataSorter(primarySort, backupSort, adjudicator);

        boolean didSortFail = false;

        try {
            System.out.println("Attempting to sort data...");
            dataSorter.execute(inputFile, outputFile, primaryFailRate, backupFailRate, timeout);
        } catch (Exception e) {
            didSortFail = true;
            System.out.println("Failed to sort data.");
        }

        if (!didSortFail) {
            System.out.println("Data successfully sorted!");
        }
    }
}
