package service;

import org.apache.commons.codec.Charsets;
import org.hbase.async.HBaseClient;
import org.hbase.async.PutRequest;
import util.ExceptionUtil;
import util.StringUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:34
 */
public class Service {

    private HBaseClient client;
    private String tableName;
    private static byte[] rowKey = new byte[1];

    /**
     * Instantiates a new Service.
     *
     * @param server
     * @param tableName <p>
     *                  misterbaykal
     *                  <p>
     *                  13/12/16 23:25
     */
    Service(String server, String tableName) {
        client = new HBaseClient(server);
        this.tableName = tableName;
        rowKey[0] = 0;
        System.out.println(StringUtil.append("Service started with rowKey=1. - Server: ", server, ". Table name: ", tableName));
    }

    /**
     * Shred and save message.
     * <p>
     * Shred the message and insert into OpenTSDB
     * <p>
     * izmit.raw.9G5_DTGGH28,2012-08-08 06:28:35,145.8146,100
     * izmit.raw.9G5_DTGGH28,2012-08-08 06:28:37,145.9306,100
     * izmit.raw.9G5_DTGGH28,2012-08-08 06:28:38,145.8502,100
     * izmit.raw.9G5_DTGGH28,2012-08-08 06:28:40,145.8141,100
     * <p>
     * metric : izmit.raw.9G5_DTGGH28
     * timestamp : 2012-08-08 06:28:35 (epoch)
     * value: 145.8146
     * tagkey: confidence
     * tagvalue: 100
     *
     * @param value the value
     *              <p>
     *              <p>
     *              misterbaykal
     *              <p>
     *              12/12/16 22:34
     */
    public void parseAndSaveMessage(String value) {
        try {
            rowKey[0]++;
            String[] valueArray = value.split(",");

            String metric = valueArray[0];
            String timestamp = valueArray[1];
            String val = valueArray[2];
            Integer tagvalue = Integer.parseInt(valueArray[3]);
            String tagkey = "confidence";

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Date date = format.parse(timestamp);
            long epochTime = date.getTime();

            String data = StringUtil.append(metric, ",", epochTime, ",", val, ",", tagvalue, ",", tagkey);

            String columnFamily = "cf";
            String columnQualifier = "a";

            PutRequest putRequest = new PutRequest(this.tableName.getBytes(Charsets.UTF_8), rowKey,
                    columnFamily.getBytes(Charsets.UTF_8), columnQualifier.getBytes(Charsets.UTF_8),
                    data.getBytes(Charsets.UTF_8));

            client.put(putRequest);

            System.out.println("Message is saved");
        } catch (Exception e) {
            System.out.println(StringUtil.append("Exception while working on message: ", value));
            ExceptionUtil.getStackTraceString(e, "shredAndSaveMassage");
        }
    }
}
