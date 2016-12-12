package util;

/**
 * misterbaykal
 * <p>
 * 12/12/16 23:25
 */
public class StringUtil {
    /**
     * Append string.
     *
     * @param argStringArr the arg string arr
     * @return the string
     * <p>
     * misterbaykal
     * <p>
     * 12/12/16 23:25
     */
    public static String append(Object... argStringArr) {
        String result = null;
        if (argStringArr != null) {
            StringBuilder builder = new StringBuilder();
            for (Object str : argStringArr) {
                if (str == null) {
                    builder.append("NULL");
                } else {
                    builder.append(str.toString());
                }

            }
            result = builder.toString();
        }
        return result;

    }
}
