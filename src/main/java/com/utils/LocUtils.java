package com.utils;

import com.domain.Constants;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class LocUtils {
    static String msg(String... messages) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.MSG_BUNDLE_BASE_NAME, Locale.US);

        StringBuilder output = new StringBuilder();

        for (String msg : messages) {
            String toAppend;

            try {
                toAppend = resourceBundle.getString(msg);
            } catch (MissingResourceException e) {
                toAppend = msg;
            }

            output.append(toAppend);
        }

        return output.toString();
    }
}
