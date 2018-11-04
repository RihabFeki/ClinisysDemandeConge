package com.csys.clinisys.demandeconge.helper;

import android.annotation.SuppressLint;
import android.text.format.Time;
import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFunction {
    public static String getDateStringFromTimeStamp(long timeStamp) {

        try {
            String format = "dd/MM/yyyy HH:mm";
            SimpleDateFormat formater = new SimpleDateFormat(format);
            java.util.Date netDate = (new java.util.Date(timeStamp));
            return formater.format(netDate);
        } catch (Exception ex) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), ex);
            return "00/00/0000 00:00";
        }
    }

    public static String getStringToDate(String dateF) {

        try {
            String date[] = dateF.split("/");

            return date[2] + "-" + date[1] + "-" + date[0] + "T00:00:00";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String getDate(long timeStamp) {

        try {
            String format = "dd/MM/yyyy HH:mm";
            SimpleDateFormat formater = new SimpleDateFormat(format);
            java.util.Date netDate = (new java.util.Date(timeStamp));
            return formater.format(netDate);
        } catch (Exception ex) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), ex);
            return "";
        }
    }

    public static Date StringToDate(String dateInString) {

        Date date = null;

        String[] inString = dateInString.split(" ");

        String[] inDate = inString[0].split("-");

        String dateToConvert = inDate[2] + "/" + inDate[1] + "/" + inDate[0];

        // String dateInString = "07/06/2013";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try {

            date = (Date) formatter.parse(dateToConvert);

        } catch (ParseException e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return date;

    }

    public static String convertSeconde(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String convertMilliSecondeToMinute(int totalMilli) {

        int minutes = ((totalMilli / 1000) % 3600) / 60;
        int seconds = (totalMilli / 1000) % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateNow() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()) + " "
                + today.format("%k:%M:%S");
        return date;
    }

    public static String getDateNowNum() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String date = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime())
                + today.format("%k%M%S");
        return date;
    }

    public static int getAgeEnJours(String date) {
        int ageS = 0;
        // 1900-01-01T00:00:00+01:00
        String[] array = date.split("T");
        array = array[0].split("-");

        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        ageS = ((today.monthDay + today.month * 30 + 30 + today.year * 365) - (day + month * 30 + year * 365));
        return ageS;


    }

    public static String getAge(String date) {
        int jour = 0;
        jour = getAgeEnJours(date);
        String age = "";
        if (jour < 30) {
            age = getAgeEnJours(date) + " Jours";
        } else if (jour < 1095) {
            age = (getAgeEnJours(date)) / 30 + " Mois";
        } else {
            age = (getAgeEnJours(date)) / 365 + " Ans";
        }
        if (age.contains("-")) {
            age = "0 Mois";
        }
        return age;
    }

    public static String getAgeArabe(String date) {
        int jour = 0;
        jour = getAgeEnJours(date);
        String age = "";
        if (jour < 30) {
            age = getAgeEnJours(date) + " يوم";
        } else if (jour < 1095) {
            age = (getAgeEnJours(date)) / 30 + " شهر";
        } else {
            int ages = (getAgeEnJours(date)) / 365;
            if (ages < 10) {
                age = ages + " سنوات";
            } else {

                age = ages + " سنة";
            }
        }
        if (age.contains("-")) {
            age = "0 شهر ";
        }
        return age;
    }

    public static String getMonth(String date) {

        // 1900-01-01T00:00:00+01:00
        String[] array = date.split("T");
        array = array[0].split("-");
        int year = Integer.parseInt(array[0]);
        int month = Integer.parseInt(array[1]);
        int day = Integer.parseInt(array[2]);

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = (today.get(Calendar.MONTH) + 1) - dob.get(Calendar.MONTH);
        Log.v("tody", String.valueOf(today.get(Calendar.MONTH)));
        Log.v("NAISS", String.valueOf(dob.get(Calendar.MONTH)));
        /*
         * if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
		 * age--; }
		 */

        Integer ageInt = new Integer(age);

        String ageS = ageInt.toString();

        return ageS.replace("-", "");
    }

    public static int getNbrJour(String datedeb, String datefin) {
        int nbrjour = 0;
        String[] datedebarray = datedeb.split(" ");
        datedebarray = datedebarray[0].split("-");

        String[] datefinarray = datefin.split(" ");
        datefinarray = datefinarray[0].split("-");

        nbrjour = ((Integer.parseInt(datefinarray[2]) + Integer.parseInt(datefinarray[1]) * 30
                + Integer.parseInt(datefinarray[0]) * 365)
                - (Integer.parseInt(datedebarray[2]) + Integer.parseInt(datedebarray[1]) * 30
                + Integer.parseInt(datedebarray[0]) * 365));

        return nbrjour;
    }

    public static int getDiffDate(String datedeb, String datefin) {
        int jour = 0;
        String[] array = datedeb.split("T");
        array = array[0].split("-");
        String[] array2 = datefin.split("T");
        array2 = array2[0].split("-");
        jour = ((Integer.parseInt(array2[2]) + Integer.parseInt(array2[1]) * 30 + Integer.parseInt(array2[0]) * 365)
                - (Integer.parseInt(array[2]) + Integer.parseInt(array[1]) * 30 + Integer.parseInt(array[0]) * 365));
        return jour + 1;
    }

    public static String getDate(String date) {
        try {
            String[] array = date.split("T");
            array = array[0].split("-");
            return array[2] + "/" + array[1] + "/" + array[0];
        } catch (Exception e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            return "";
        }
    }

    public static Calendar getDateCalender(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            String[] array = date.split("T");
            array = array[0].split("-");

            calendar.set(Calendar.YEAR, Integer.valueOf(array[0]));
            calendar.set(Calendar.MONTH, Integer.valueOf(array[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(array[2]));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            return calendar;
        }
        return calendar;
    }

    public static int getHeureInteger(String date) {
        int heure = 0;
        try {
            String[] array = date.split("T");
            array = array[1].split(":");
            heure = Integer.valueOf(array[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return heure;
    }

    public static String getHeure(String date) {
        String[] array = date.split("T");
        array = array[1].split(":");
        return array[0] + ":" + array[1];
    }

    public static String getHeure1(String date) {
        String[] array = date.split(" ");
        array = array[1].split(":");
        return array[0] + ":" + array[1];
    }

    public static Calendar getHeureCalendar(String date) {
        Calendar calendar = Calendar.getInstance();
        try {
            String[] array = date.split("T");
            array = array[1].split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(array[0]));
            calendar.set(Calendar.MINUTE, Integer.valueOf(array[1]));
        } catch (NumberFormatException e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return calendar;
    }

    public static String getJourMois(String date) {
        String[] array = date.split("T");
        array = array[0].split("-");
        return array[2] + "/" + array[1];
    }

    public static String setUpDate(String date) {
        String[] array = date.split("T");
        array = array[0].split("-");
        String dt = array[0] + "-" + array[1] + "-" + array[2]; // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        c.add(Calendar.DATE, 1); // number of days to add
        dt = sdf.format(c.getTime()); // dt is now the new date
        String[] array2 = dt.split("-");
        return array2[0] + "-" + array2[1] + "-" + array2[2] + "T00:00:00+00:00";
    }

    public static String setUpDate_String(String dt, String nbJour) {
        // String dt= "01/01/2017"; // Start date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, Integer.valueOf(nbJour) - 1); // number of days to add
            dt = sdf.format(c.getTime()); // dt is now the new date
        } catch (ParseException e1) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e1);
            return "";
        }
        return dt.toString();
    }

    public static String setUpDate(String date, String nbJour) {
        String[] array = date.split("T");
        array = array[0].split("-");
        String dt = array[0] + "-" + array[1] + "-" + array[2]; // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        c.add(Calendar.DATE, Integer.valueOf(nbJour) - 1); // number of days to add
        dt = sdf.format(c.getTime()); // dt is now the new date
        String[] array2 = dt.split("-");
        return array2[0] + "-" + array2[1] + "-" + array2[2] + "T00:00:00+00:00";
    }

    public static String setUpDateFormat(String date) {
        String[] array = date.split(" ");
        array = array[0].split("-");
        String dt = array[0] + "-" + array[1] + "-" + array[2]; // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        c.add(Calendar.DATE, 1); // number of days to add
        dt = sdf.format(c.getTime()); // dt is now the new date
        String[] array2 = dt.split("-");
        return array2[0] + "-" + array2[1] + "-" + array2[2];
    }

    public static String setDownDateFormat(String date) {
        String[] array = date.split(" ");
        array = array[0].split("-");
        String dt = array[0] + "-" + array[1] + "-" + array[2]; // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        c.add(Calendar.DATE, -1); // number of days to add
        dt = sdf.format(c.getTime()); // dt is now the new date
        String[] array2 = dt.split("-");
        return array2[0] + "-" + array2[1] + "-" + array2[2];
    }

    public static String getDateJourMoinsUn(String dt) {
        // String dt= "01/01/2017"; // Start date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, -1); // number of days to add
            dt = sdf.format(c.getTime()); // dt is now the new date
        } catch (ParseException e1) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e1);
            return "";
        }
        return dt.toString();
    }

    public static String getHourPlusNbrString(String dt, int nbr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(dt));
            cal.add(Calendar.HOUR_OF_DAY, nbr);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return dateFormat.format(cal.getTime());
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            return "-1";
        }
    }

    public static String getHourMoinsNbrString(String dt) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(dt));
            cal.add(Calendar.HOUR_OF_DAY, -1);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            return dateFormat.format(cal.getTime());
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            return "-1";
        }
    }

    public static int getHourFromDate(String date) {
        // dd/MM/yyyy HH:mm

        try {
            String[] arrayDate = date.split(" ");
            String[] arrayHeure = arrayDate[1].split(":");

            return Integer.valueOf(arrayHeure[0]);

        } catch (NumberFormatException e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return -1;
    }

    public static String getDateFromDateString(String date) {
        // dd/MM/yyyy HH:mm

        try {
            String[] arrayDate = date.split(" ");

            return arrayDate[0];

        } catch (NumberFormatException e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return "-1";
    }

    public static Boolean beforeOrEqualMaxDate(String maxDate, String newDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar maxDat = Calendar.getInstance();
        Calendar newDat = Calendar.getInstance();
        try {
            maxDat.setTime(sdf.parse(maxDate));
            newDat.setTime(sdf.parse(newDate));

            MessageLog.MessageInfo(new Exception().getStackTrace(), "compareTo " + maxDat.compareTo(newDat));

            if (maxDat.compareTo(newDat) >= 0) {
                MessageLog.MessageInfo(new Exception().getStackTrace(), "beforeOrEqualMaxDate true");

                return true;
            } else {
                MessageLog.MessageInfo(new Exception().getStackTrace(), "beforeOrEqualMaxDate false");

                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public static Boolean afterOrEqualMinDate(String minDate, String newDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar minDat = Calendar.getInstance();
        Calendar newDat = Calendar.getInstance();
        try {
            minDat.setTime(sdf.parse(minDate));
            newDat.setTime(sdf.parse(newDate));

            MessageLog.MessageInfo(new Exception().getStackTrace(), "compareTo " + minDat.compareTo(newDat));

            if (minDat.compareTo(newDat) == -1) {
                MessageLog.MessageInfo(new Exception().getStackTrace(), "afterOrEqualMinDate true");

                return true;
            } else {
                MessageLog.MessageInfo(new Exception().getStackTrace(), "afterOrEqualMinDate false");

                return false;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public static int compareDateString(String minDate, String newDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar minDat = Calendar.getInstance();
        Calendar newDat = Calendar.getInstance();
        try {
            minDat.setTime(sdf.parse(minDate));
            newDat.setTime(sdf.parse(newDate));

            MessageLog.MessageInfo(new Exception().getStackTrace(), "compareTo " + minDat.compareTo(newDat));

            return minDat.compareTo(newDat);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 1;
    }

    public static String getDateJourPlusUn(String dt) {
        // String dt= "01/01/2017"; // Start date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, 1); // number of days to add
            dt = sdf.format(c.getTime()); // dt is now the new date
        } catch (ParseException e1) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e1);
            return "";
        }
        return dt.toString();
    }
}
