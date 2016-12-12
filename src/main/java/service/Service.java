package service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.logging.log4j.Logger;
import org.hbase.async.HBaseClient;
import util.ExceptionUtil;
import util.LogUtil;
import util.StringUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:34
 */
public class Service {
    private final Logger logger = LogUtil.getRootLogger();

    private HBaseClient client = new HBaseClient("localhost:2181");
    private String tableName;

    /**
     * Shred and save message.
     * <p>
     * Shred the message and insert into OpenTSDB
     * <p>
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
    public void shreadAndSaveMessage(String value) {
        try {
            String[] valueArray = value.split(",");

            String metric = valueArray[0];
            String timestamp = valueArray[1];
            String val = valueArray[2];
            Integer tagvalue = Integer.parseInt(valueArray[3]);
            String tagkey = "confidence";

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Date date = format.parse(timestamp);
            long epochTime = date.getTime();

            Configuration config = HBaseConfiguration.create();
            HTable table = new HTable(config, this.tableName);

//            PutRequest put/*Request = new PutRequest(tableName.getBytes(Charsets.UTF_8), rowKey,
//                    columnFamily.getBytes(Charsets.UTF_8), "payload".getBytes(Charsets.UTF_8),
//                    data.getByte*/s(Charsets.UTF_8));

        } catch (ParseException | IOException e) {
            logger.error(StringUtil.append("Exception while working on message: ", value));
            ExceptionUtil.getStackTraceString(e, "shredAndSaveMassage");
        }
    }
}
