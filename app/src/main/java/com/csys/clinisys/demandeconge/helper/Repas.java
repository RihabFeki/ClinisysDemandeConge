package com.csys.clinisys.demandeconge.helper;

import java.util.HashMap;

/**
 * Created by admin on 10/05/2017.
 */

public class Repas {
    public static final String DESPETITDEJEUNER = "Petit déjeuner";
    public static final String DESDEJEUNER = "Déjeuner";
    public static final String DESDINER = "Diner";
    public static final String DESGOUTER = "Goûter";
    public static final String DESPLATEAUREVEIL = "Plateau du réveil";

    public static final String CODEPETITDEJEUNER = "01";
    public static final String CODEDEJEUNER = "02";
    public static final String CODEDINER = "03";
    public static final String CODEGOUTER = "04";
    public static final String CODEPLATEAUREVEIL = "PtReveil";


    public static HashMap<String, String> listRepasPatients = null;
    public static HashMap<String, String> listRepasAccompagnant = null;

    public static void initRepasPatient() {
        listRepasPatients = new HashMap<String, String>();
        listRepasPatients.put("01", "Petit déjeuner");
        listRepasPatients.put("02", "Déjeuner");
        listRepasPatients.put("04", "Gouter");
        listRepasPatients.put("03", "Diner");
    }

    public static void initRepasAccompagnant() {
        listRepasAccompagnant = new HashMap<String, String>();
        listRepasAccompagnant.put("01", "Petit déjeuner");
        listRepasAccompagnant.put("02", "Déjeuner");
        listRepasAccompagnant.put("03", "Diner");
    }
}