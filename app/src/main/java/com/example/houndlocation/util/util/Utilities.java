package com.example.houndlocation.util.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {

    public static String dateToString(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.getDefault());

        if (date != null)
        return dateFormat.format(date);
        else return dateFormat.format(System.currentTimeMillis());
    }
}
