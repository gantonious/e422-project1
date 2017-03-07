/**
 * Created by George on 2017-03-06.
 */
public class SortAdjudicator {
    public boolean testIsArraySorted(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if (i == data.length -1) {
                break;
            }
            if (data[i] > data[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
