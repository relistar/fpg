package domain;

import java.io.File;
import java.text.SimpleDateFormat;

public class Constants {
    public static final String SYMBOL_WHITESPACE = " ";
    public static final String SYMBOL_NEW_LINE = "\n";
    public static final String HELP_FORMATTER_CMD_SYNTAX = "java -jar pm";
    public static final String HELP_FORMATTER_HEADER = "Options:";
    public static final String PATH_DELIMITER = File.separator;
    public static final String OUTPUT_DIRECTORY = "output" + PATH_DELIMITER;
    public static final String TERMINAL_LINE_PREFIX = "- ";
    public static final String ALGORITHM_APRIORI = "apriori";
    public static final String MSG_BUNDLE_BASE_NAME = "MessagesBundle";
    public static final String MSG_KEY_STEP_SEPARATOR = "msg.step.separator";
    public static final String MSG_KEY_PATTERN_MINING_START_POSTFIX = "msg.pattern.mining.start.postfix";
    public static final String MSG_KEY_PATTERN_MINING_SUCCESS = "msg.pattern.mining.success";
    public static final String MSG_KEY_WRITING_SET_FILE_START = "msg.writing.set.file.start";
    public static final String MSG_KEY_WRITING_MAP_FILE_START = "msg.writing.map.file.start";
    public static final String MSG_KEY_WRITING_SET_FILE_SUCCESS = "msg.writing.set.file.success";
    public static final String MSG_KEY_WRITING_MAP_FILE_SUCCESS = "msg.writing.map.file.success";

    public static final String SHORT_OPT_FILE = "f";
    public static final String SHORT_OPT_ALGORITHM = "a";
    public static final String SHORT_OPT_SUPPORT = "ms";
    public static final String SHORT_OPT_SET_SIZE = "ss";
    public static final String SHORT_OPT_MAX_TR_SIZE = "mt";

    public static final String LONG_OPT_FILE = "file";
    public static final String LONG_OPT_ALGORITHM = "algorithm";
    public static final String LONG_OPT_SUPPORT = "support";
    public static final String LONG_OPT_SET_SIZE = "set-size";
    public static final String LONG_OPT_MAX_TR_SIZE = "max-tr-size";

    public static final String OPT_DESC_FILE = "File name.";
    public static final String OPT_DESC_ALGORITHM = "Algorithm name.";
    public static final String OPT_DESC_SUPPORT = "Min mining support.";
    public static final String OPT_DESC_SET_SIZE = "Min result set size.";
    public static final String OPT_DESC_MAX_TR_SIZE = "Max transaction size.";
    public static final String MSG_KEY_READING_FILE_START_PREFIX = "msg.reading.file.start.prefix";
    public static final String MSG_KEY_READING_FILE_SUCCESS = "msg.reading.file.success";
    static final int DEFAULT_VALUE_MIN_SUPPORT = 2;
    static final int DEFAULT_VALUE_MIN_RESULT_SET_SIZE = 1;
    static final int DEFAULT_VALUE_MAX_TRANSACTION_SIZE = Integer.MAX_VALUE;
    public static final String MSG_KEY_ELLIPSIS = "msg.ellipsis";
    public static final String MSG_KEY_READING_FILE_ERROR = "error.reading.file";
    public static final String MSG_KEY_WRITING_FILE_ERROR = "error.writing.to.file";
    public static final String MSG_KEY_NOT_ALL_REQUIRED_OPTIONS_PRESENTED = "error.not.all.options.presented";
    public static final String MSG_KEY_DONE = "msg.done";
    private static final String SYMBOL_COLON = ":";
    public static final String RECOMMENDATION_KEY_VALUE_DELIMITER = SYMBOL_COLON + SYMBOL_WHITESPACE;
    private static final String OUTPUT_FILE_FORMAT = ".txt";
    public static final String OUTPUT_FILE_SET = "sets" + OUTPUT_FILE_FORMAT;
    public static final String OUTPUT_FILE_MAP = "map" + OUTPUT_FILE_FORMAT;
    static final String DEFAULT_VALUE_FILE_NAME = "transactions" + OUTPUT_FILE_FORMAT;
    private static final String DATE_PATTERN = "yyyy.MM.dd_hh_mm_ss";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    private static final String ALGORITHM_FPG = "fpg";
    static final String DEFAULT_VALUE_ALGORITHM = ALGORITHM_FPG;

}
