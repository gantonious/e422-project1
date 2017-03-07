/**
 * Created by George on 2017-03-04.
 */
public class SortDriver {
    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            double primaryFailRate = Double.parseDouble(args[2]);
            double backupFailRate = Double.parseDouble(args[3]);
            long timeout = Long.parseLong(args[4]);

            run(inputFile, outputFile, primaryFailRate, backupFailRate, timeout);
        } catch (Exception e) {
            System.out.println("Usage: java SortDriver <input_filename> <output_filename> <primary_fail_rate> <backup_fail_rate> <timeout>");
        }
    }

    public static void run(String inputFile,
                           String outputFile,
                           double primaryFailRate,
                           double backupFailRate,
                           long timeout) {

        System.loadLibrary("InsertionSort");

        Sorter primarySort = new PrimaryDataSorter();
        Sorter backupSort = new BackupDataSorter();
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
