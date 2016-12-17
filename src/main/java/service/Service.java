package service;

import net.opentsdb.core.TSDB;
import net.opentsdb.core.Tags;
import net.opentsdb.core.WritableDataPoints;
import net.opentsdb.utils.Config;
import util.ExceptionUtil;
import util.StringUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * Emre Baykal
 * <p>
 * 12/12/16 22:34
 */
public class Service {

    private TSDB tsdb;
    private String wordSplitter = " ";

    /**
     * Instantiates a new Service.
     *
     * @param tsdbConfigPath the path to tsdb configuration
     * @param wordSplitter   the character to identify how to split words
     *                       <p>
     *                       <p>
     *                       Emre Baykal
     *                       <p>
     *                       13/12/16 23:25
     */
    Service(String tsdbConfigPath, String wordSplitter) {
        try {
            Config config = new Config(tsdbConfigPath);
            this.tsdb = new TSDB(config);
            if (!wordSplitter.equals(""))
            {
                this.wordSplitter = wordSplitter;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(StringUtil.append("Service started. - Config Path: ", tsdbConfigPath, ". Word Splitter: ", wordSplitter));
    }

    /**
     * Parse and save message.
     * <p>
     * Parse the message and insert into OpenTSDB
     * <p>
     * 47TI860.PV 49.31173 100 131257404790210000
     * izmit.raw.47TI860.PV 1312574047 49.31173 confidence=100
     * <p>
     * metric : izmit.raw.9G5_DTGGH28
     * timestamp : 1481171320000 (epoch)
     * value: 49.31173
     * tag: confidence=100
     *
     * @param value the value
     *              <p>
     *              <p>
     *              Emre Baykal
     *              <p>
     *              12/12/16 22:34
     */
    public void parseAndSaveMessage(String value) {
        try {
            String[] w = value.split(this.wordSplitter);

            String[] words = new String[w.length];
            words[0] = StringUtil.append("izmit.raw.",w[0]);
            words[1] = w[3].substring(0,10);
            String[] temp = w[1].split("\\.");
            words[2] = StringUtil.append(temp[0], ".", temp[1]);
            words[3] = StringUtil.append("confidence=", w[2]);

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

            System.out.println("Message is saved");
        } catch (Exception e) {
            System.out.println(StringUtil.append("Exception while working on message: ", value));
            ExceptionUtil.getStackTraceString(e, "parseAndSaveMassage");
        }
    }
}
