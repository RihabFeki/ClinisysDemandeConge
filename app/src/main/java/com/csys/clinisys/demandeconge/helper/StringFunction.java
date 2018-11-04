package com.csys.clinisys.demandeconge.helper;

public class StringFunction {

    public static String subString(String nom, int nbr) {

        try {
            if (nom.length() > nbr)
                return nom.substring(0, Math.min(nom.length(), nbr)) + "...";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return nom;
    }

    public static String subStringFormatter(String nom, int nbr) {

        try {
            if (nom.length() > nbr)
                return nom.substring(0, Math.min(nom.length(), nbr));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return nom;
    }

    public static String html2text(String html) {

        String htmlArray[] = html.split("par");
        if (htmlArray.length > 2) {
            html = htmlArray[1].replace("\\", "");
        } else if (htmlArray.length > 3) {
            html += htmlArray[3].replace("\\", "") + " \n";
        } else if (htmlArray.length > 4) {
            html += htmlArray[5].replace("\\", "") + " \n";
        }
        return html.replaceAll("'e9", "é");
    }
}
