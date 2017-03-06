/**
 * Created by George on 2017-03-04.
 */
public class BackupDataSort implements DataSorter {
    public native void sort(String inputFile, String outputFile, double failureRate);
}
