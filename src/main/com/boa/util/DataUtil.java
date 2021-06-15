package com.boa.util;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.math.BigDecimal;
import java.util.*;

public final class DataUtil {
    protected static final CharMatcher NOSPECIALS = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.inRange('0', '9')).precomputed();
    protected static final CharMatcher NUMERIC = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.inRange('0', '9')).precomputed().or(CharMatcher.is('.'));

    public static boolean isNull(Object value) {
        if (null == value) {
            return true;
        }
        return String.valueOf(value).equals("null");
    }

    public static boolean isEmpty(String value) {
        if (DataUtil.isNull(value)) {
            return true;
        }
        return value.trim().length() == 0;
    }

    public static boolean isEmpty(Object value) {
        if (DataUtil.isNull(value)) {
            return true;
        }
        if (value instanceof Collection) {
            return ((Collection) value).isEmpty();
        }
        if (value instanceof Map) {
            return ((Map) value).isEmpty();
        }
        if (value.getClass().getSimpleName().endsWith("[]")) {
            return ((Object[]) value).length == 0;
        }
        // TODO - isNumeric() method
        return DataUtil.isEmpty(value.toString());
    }

    public static boolean toBoolean(Object value) {
        //retrieve the stored string
        if (isNull(value)) {
            return false;
        }

        String text = value.toString().toLowerCase().trim();

        switch (text) {
            case "true":
            case "t":
            case "yes":
            case "y":
            case "ok":
            case "okay":
            case "on":
            case "accept":
            case "1":
            case "success":
            case "yeah":
                return true;
            default:
                return false;
        }

    }

    public static boolean isBoolean(Object value) {
        if (DataUtil.isEmpty(value)) {
            return false;
        }
        String text = value.toString().trim();
        return (text.equalsIgnoreCase("true") || (text.equalsIgnoreCase("false")));
    }

    public static String doubleToString(double value) {
        return value % 1 != 0 ? String.valueOf(value) : String.valueOf((int) value);
    }

    public static boolean isNumeric(String value) {
        value = value.trim().replace(" ", "");
        char decimalValue = '.';
        if (value != null && value.length() != 0) {
            int sz = value.length();
            for (int i = 0; i < sz; ++i) {
                if (decimalValue == value.charAt(i))
                    continue;
                if (!Character.isDigit(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumeric(Object value) {
        value = value.toString();
        return isNumeric(value);
    }

    public static BigDecimal getBigDecimal(Object input, double defaultValue) {
        String text = input.toString();

        if (!DataUtil.isNumeric(text) || DataUtil.isEmpty(text))
            return BigDecimal.valueOf(defaultValue);

        return new BigDecimal(text);
    }

    public static double getDouble(Object input, double defaultValue) {
        return DataUtil.getBigDecimal(input, defaultValue).doubleValue();
    }

    public static int getInt(Object input, int defaultValue) {
        return DataUtil.getBigDecimal(input, defaultValue).intValue();
    }

    public static double getDouble(Object input) {
        return DataUtil.getDouble(input, 0.0);
    }

    public static int getInt(Object input) {
        return DataUtil.getInt(input, 0);
    }


}

