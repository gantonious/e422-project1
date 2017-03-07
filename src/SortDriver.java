/**
 * Created by George on 2017-03-04.
 */
public class SortDriver {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        double primaryFailRate = Double.parseDouble(args[2]);
        double backupFailRate = Double.parseDouble(args[3]);
        double timeout = Double.parseDouble(args[4]);

        Sorter primarySort = new PrimaryDataSorter();
        Sorter backupSort = new BackupDataSort();
        SortAdjudicator adjudicator = new SortAdjudicator();

        DataSorter dataSorter = new DataSorter(primarySort, backupSort, adjudicator);
        dataSorter.execute(inputFile, outputFile, primaryFailRate, backupFailRate, timeout);
    }
}
