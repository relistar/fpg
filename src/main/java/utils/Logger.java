package utils;

import domain.Constants;

public class Logger {
    public static void log(String... messages) {
        if (messages == null) {
            return;
        }

        System.out.println(Constants.TERMINAL_LINE_PREFIX + LocUtils.msg(messages));
    }
}
