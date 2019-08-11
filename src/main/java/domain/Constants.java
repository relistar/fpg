package domain;

import java.text.SimpleDateFormat;

public class Constants {
    public static final String OUTPUT_DIRECTORY = "output/";
    public static final String OUTPUT_FILE_FORMAT_POSTFIX = ".out";
    private static final String DATE_PATTERN = "HH-mm-ss_dd-MM-yyyy";
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);

    public static final String SHORT_OPT_FILE = "f";
    public static final String SHORT_OPT_ALGORITHM = "a";
    public static final String SHORT_OPT_SUPPORT = "ms";
    public static final String SHORT_OPT_SET_SIZE = "ss";

    public static final String LONG_OPT_FILE = "file";
    public static final String LONG_OPT_ALGORITHM = "algorithm";
    public static final String LONG_OPT_SUPPORT = "support";
    public static final String LONG_OPT_SET_SIZE = "set-size";

    public static final String OPT_DESC_FILE = "File name.";
    public static final String OPT_DESC_ALGORITHM = "Algorithm name.";
    public static final String OPT_DESC_SUPPORT = "Min mining support.";
    public static final String OPT_DESC_SET_SIZE = "Min result set size.";

    static final String DEFAULT_VALUE_FILE_NAME = "transactions.txt";
    static final String DEFAULT_VALUE_ALGORITHM = "fpg";
    static final int DEFAULT_VALUE_MIN_SUPPORT = 2;
    static final int DEFAULT_VALUE_MIN_RESULT_SET_SIZE = 1;
}
