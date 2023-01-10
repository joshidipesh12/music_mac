package com.spring_samples.Mac.Music.utils;

public class Formatters {
    public static String timeFormatter(int durInSec) {
        int hrs = (int) Math.floor((double) durInSec / 3600);
        int mins = (int) Math.floor((double) durInSec / 60 % 60);
        int secs = (int) Math.floor((double) durInSec % 60);
        String hrsFormat = (hrs >= 10 ? hrs : "0" + hrs) + ":";
        return (hrs > 0 ? hrsFormat : "") + (mins >= 10 ? mins : "0" + mins) + ":"
                + (secs >= 10 ? secs : "0" + secs);
    }
}
