import net.opentsdb.core.TSDB;
import net.opentsdb.core.Tags;
import net.opentsdb.core.WritableDataPoints;
import net.opentsdb.utils.Config;
import util.StringUtil;

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
        String[] w = "47TI860.PV 49.31173 100 131257404790210000".split(" ");

        String[] words = new String[w.length];
        words[0] = StringUtil.append("izmit.raw.",w[0]);
        words[1] = w[3].substring(0,10);
        String[] temp = w[1].split("\\.");
        words[2] = StringUtil.append(temp[0], ".", temp[1]);
        words[3] = StringUtil.append("confidence=", w[2]);

        System.out.println(words[0]);
        System.out.println(words[1]);
        System.out.println(words[2]);
        System.out.println(words[3]);
        //BasicTests.tsdb();
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
