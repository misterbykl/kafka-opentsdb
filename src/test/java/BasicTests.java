import org.apache.commons.codec.Charsets;
import org.hbase.async.GetRequest;
import org.hbase.async.HBaseClient;
import org.hbase.async.KeyValue;
import org.hbase.async.PutRequest;
import util.ExceptionUtil;
import util.StringUtil;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:48
 */
public class BasicTests {
    public static void main(String[] args) throws ParseException {

        //parseAndSaveMessage("abc,2012-08-08 06:28:40,145.8141,100");
        getLastRowKey();
    }

    private static void getLastRowKey(){
        HBaseClient client = new HBaseClient("localhost:2181");

        byte[] rowKey = new byte[1];
        rowKey[0] = 1;

        GetRequest getRequest = new GetRequest("test".getBytes(Charsets.UTF_8), rowKey);

        ArrayList<KeyValue> kvs = null;
        try {
            kvs = client.get(getRequest).join();
            System.out.println(Arrays.toString(kvs.get(0).value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void shreadAndSaveMessage(String value) {
        try {
            HBaseClient client = new HBaseClient("localhost:2181");

            String[] valueArray = value.split(",");

            String metric = valueArray[0];
            String timestamp = valueArray[1];
            String val = valueArray[2];
            Integer tagvalue = Integer.parseInt(valueArray[3]);
            String tagkey = "a";

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Date date = format.parse(timestamp);
            long epochTime = date.getTime();

            String data = StringUtil.append(metric, ",", epochTime, ",", val, ",", tagvalue, ",", tagkey);

            byte[] rowKey = new byte[1];
            rowKey[0] = 1;

            String columnFamily = "cf";
            String columnQualifier = "a";
            String tableName = "test";

            PutRequest putRequest = new PutRequest(tableName.getBytes(Charsets.UTF_8), rowKey,
                    columnFamily.getBytes(Charsets.UTF_8), columnQualifier.getBytes(Charsets.UTF_8),
                    data.getBytes(Charsets.UTF_8));

            client.put(putRequest);
            //rowKey[0]++;

        } catch (Exception e) {
            ExceptionUtil.getStackTraceString(e, "shredAndSaveMessage");
        }
    }
}
