import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * misterbaykal
 * <p>
 * 12/12/16 22:48
 */
public class BasicTests
{
    public static void main(String[] args) throws ParseException {
        String s = "izmit.raw.9G5_DTGGH28,2012-08-08 06:28:40,145.8141,100";
        System.out.println(s.split(",")[0]);
        System.out.println(s.split(",")[1]);
        System.out.println(s.split(",")[2]);
        System.out.println(s.split(",")[3]);

        String[] valueArray = s.split(",");

        String metric = valueArray[0];
        String timestamp = valueArray[1];
        String val = valueArray[2];
        Integer tagvalue = Integer.parseInt(valueArray[3]);
        String tagkey = "confidence";

        String[] timestampArray = timestamp.split(" ");
        String date = timestampArray[0];
        String time = timestampArray[1];

        String string = "2012-08-08 06:28:40";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date1 = format.parse(string);

        System.out.println(date1);
        System.out.println(date1.getTime());
    }
}
