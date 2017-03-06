/**
 * Created by George on 2017-03-04.
 */
public class Driver {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        PrimaryDataSorter dataSorter = new PrimaryDataSorter();

        dataGenerator.generateDate("test.txt", 200);
        dataSorter.sort("test.txt", "sortedTest.txt", 2);
    }
}
