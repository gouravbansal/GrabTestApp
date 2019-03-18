package com.grab.grabnewstestapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonUtil {

    public static boolean isNetworkStatusAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() == null)
            return false;
        return connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static Long getHoursDiff(String date) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat dtf = new SimpleDateFormat(pattern);
        Date dateTime = null;
        try {
            dateTime = dtf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date curentDate = new Date();
        long diff = curentDate.getTime() - dateTime.getTime();
        return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
