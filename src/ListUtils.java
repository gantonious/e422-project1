import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017-03-05.
 */
public class ListUtils {
    public static <T> String serialize(List<T> list) {
        StringBuilder outputBuilder = new StringBuilder();

        for (T item: list) {
            outputBuilder.append(item.toString());
            outputBuilder.append("\n");
        }

        return outputBuilder.toString();
    }

    public static List<Integer> deserialie(String serializedList) {
        List<Integer> output = new ArrayList<>();

        for (String item: serializedList.split("\n")) {
            output.add(Integer.parseInt(item));
        }

        return output;
    }
}
