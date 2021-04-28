package com.boa.util;

import com.google.common.base.CharMatcher;

import java.util.*;

public final class DataUtil {
    protected static final CharMatcher NOSPECIALS = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.inRange('0', '9')).precomputed();
    protected static final CharMatcher NUMERIC = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).or(CharMatcher.inRange('0', '9')).precomputed().or(CharMatcher.is('.'));

    public static Boolean isNull(Object value) {
        if (null == value) {
            return true;
        }
        return String.valueOf(value).equals("null");
    }

    public static Boolean isEmpty(String value) {
        if (DataUtil.isNull(value)) {
            return true;
        }
        return value.trim().length() == 0;
    }

    public static Boolean isEmpty(Object value) {
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

    public static Boolean toBoolean(Object value) {
        //retrieve the stored string
        if(isNull(value)){
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
            case "success" :
            case "yeah":
                return true;
            default:
                return false;
        }

    }

    public static Boolean isBoolean(Object value) {
        if (DataUtil.isEmpty(value)) {
            return false;
        }
        String text = value.toString().trim();
        return (text.equalsIgnoreCase("true") || (text.equalsIgnoreCase("false")));
    }

    public static String doubleToString(double value){
        return value % 1 != 0 ? String.valueOf(value) : String.valueOf((int) value);
    }

}

