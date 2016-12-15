import net.opentsdb.core.TSDB;
import net.opentsdb.core.Tags;
import net.opentsdb.core.WritableDataPoints;
import net.opentsdb.utils.Config;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

/**
 * Emre Baykal
 * <p>
 * 12/12/16 22:48
 */
public class BasicTests {
    public static void main(String[] args) throws ParseException {
        BasicTests.tsdb();
    }

    private static void tsdb() {
        try {
            final Config config = new Config("/path/to/opentsdbconfig");
            final TSDB tsdb = new TSDB(config);

            String[] words = "abc,1481171320000,41,host=web01,cpu=0".split(" ");

            final HashMap<String, String> tags = new HashMap<>();
            for (int i = 3; i < words.length; i++) {
                if (!words[i].isEmpty()) {
                    Tags.parse(tags, words[i]);
                }
            }

            long timestamp = Tags.parseLong(words[1]);
            final String key = words[0] + tags;

            HashMap<String, WritableDataPoints> datapoints = new HashMap<>();
            WritableDataPoints points = datapoints.get(key);

            if (points == null) {
                points = tsdb.newDataPoints();
                points.setSeries(words[0], tags);
                points.setBatchImport(true);
                datapoints.put(key, points);
            }

            if (Tags.looksLikeInteger(words[2])) {
                points.addPoint(timestamp, Tags.parseLong(words[2]));
            } else {
                points.addPoint(timestamp, Float.parseFloat(words[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
