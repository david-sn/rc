package com.pltfhs.car.view;

import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static String getTimeAgo(Date date) {

        if (date == null) {
            return null;
        }

        long time = date.getTime();

        Date curDate = currentDate();
        long now = curDate.getTime();

        //time > now || time <= 0
        if (time > now) {
            return null;
        }

        int dim = getTimeDistanceInMinutes(time);

        // String timeAgo= null;
        String timeAgo = timeAgo = "less than " + " " + "a " + " " + "minute";

        if (dim == 0) {
            timeAgo = "less than " + "a " + "minute";
        } else if (dim == 1) {
            return "1 " + "minute";
        } else if (dim >= 2 && dim <= 44) {
            timeAgo = dim + " minutes";
        } else if (dim >= 45 && dim <= 89) {
            timeAgo = "about " + "an " + "hour";
        } else if (dim >= 90 && dim <= 1439) {
            timeAgo = "about " + (Math.round(dim / 60)) + " " + "hours";
        } else if (dim >= 1440 && dim <= 2519) {
            timeAgo = "1 " + "day";
        } else if (dim >= 2520 && dim <= 43199) {
            timeAgo = (Math.round(dim / 1440)) + " " + "days";
        } else if (dim >= 43200 && dim <= 86399) {
            timeAgo = "about " + " " + "a " + "month";
        } else if (dim >= 86400 && dim <= 525599) {
            timeAgo = (Math.round(dim / 43200)) + " " + "months";
        } else if (dim >= 525600 && dim <= 655199) {
            timeAgo = "about " + " " + "a " + "year";
        } else if (dim >= 655200 && dim <= 914399) {
            timeAgo = "over " + " " + "a " + "year ";
        } else if (dim >= 914400 && dim <= 1051199) {
            timeAgo = "almost " + " 2 " + "years ";
        } else {
            timeAgo = "about " + " " + (Math.round(dim / 525600)) + " " + "years";
        }

        return timeAgo + " " + "ago";
    }

    public static String getArTimeAgo(Date date) {

        if (date == null) {
            return null;
        }

        long time = date.getTime();

        Date curDate = currentDate();
        long now = curDate.getTime();

        //time > now || time <= 0
        if (time > now) {
            return null;
        }

        int dim = getTimeDistanceInMinutes(time);

        // String timeAgo= null;
        String timeAgo = "اقل من " + " " + "a " + " " + "دقيقه";

        if (dim == 0) {
            timeAgo = "اقل من " + "دقيقه ";
        } else if (dim == 1) {
            return "1 " + " دقيقه";
        } else if (dim >= 2 && dim <= 44) {
            timeAgo = dim + " دقيقه";
        } else if (dim >= 45 && dim <= 89) {
            timeAgo = "حوالي " + "ساعه ";
        } else if (dim >= 90 && dim <= 1439) {
            timeAgo = "حوالي  " + (Math.round(dim / 60)) + " " + "ساعه ";
        } else if (dim >= 1440 && dim <= 2519) {
            timeAgo = "1 " + "يوم ";
        } else if (dim >= 2520 && dim <= 43199) {
            timeAgo = (Math.round(dim / 1440)) + " " + " يوم ";
        } else if (dim >= 43200 && dim <= 86399) {
            timeAgo = "حوالي  " + "شهر ";
        } else if (dim >= 86400 && dim <= 525599) {
            timeAgo = (Math.round(dim / 43200)) + " " + "شهر ";
        } else if (dim >= 525600 && dim <= 655199) {
            timeAgo = "حوالي " + "عام ";
        } else if (dim >= 655200 && dim <= 914399) {
            timeAgo = "اكثر من عام ";
        } else if (dim >= 914400 && dim <= 1051199) {
            timeAgo = " حوالي عامين";
        } else {
            timeAgo = "حوالي " + " " + (Math.round(dim / 525600)) + " " + "عام";
        }

        return " منذ " + timeAgo;
    }

    private static int getTimeDistanceInMinutes(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000) / 60);
    }

    public static Date getTimeAgoAr(Date publish_Date) {
        // TODO Auto-generated method stub
        return null;
    }
}
