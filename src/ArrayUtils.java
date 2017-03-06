import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017-03-05.
 */
public class ArrayUtils {
    public static <T> String serialize(int[] array) {
        StringBuilder outputBuilder = new StringBuilder();

        for (int item: array) {
            outputBuilder.append(item);
            outputBuilder.append("\n");
        }

        return outputBuilder.toString();
    }

    public static int[] deserialize(String serializedList) {
        String[] serializedArrayItems = serializedList.split("\n");
        int[] output = new int[serializedArrayItems.length];

        for (int i = 0; i < output.length; i++) {
            output[i] = Integer.parseInt(serializedArrayItems[i]);
        }

        return output;
    }
}
