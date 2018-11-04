package com.csys.clinisys.demandeconge.helper;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 14/07/2017.
 */

public class CongeFunction {
    public static int calculDeJoursParMois(int mois, int year) {


        int numDays = 0;
        int aux;

        if (((year % 4 == 0) &&
                !(year % 100 == 0))
                || (year % 400 == 0))
            aux = 29;
        else
            aux = 28;


        switch (mois) {
            case 1:
                numDays = 31;
                break;

            case 2:
                numDays = aux + 31;
                break;
            case 3:
                numDays = aux + 31 + 31;
                break;
            case 4:
                numDays = aux + 31 + 31 + 30;
                break;
            case 5:
                numDays = aux + 31 + 31 + 30 + 31;
                break;
            case 6:
                numDays = aux + 31 + 31 + 30 + 31 + 30;
                break;
            case 7:
                numDays = aux + 31 + 31 + 30 + 31 + 30 + 31;
                break;
            case 8:
                numDays = aux + 31 + 31 + 30 + 31 + 30 + 31 + 30;
                break;
            case 9:
                numDays = aux + 31 + 31 + 30 + 31 + 30 + 31 + 30 + 31;
                break;
            case 10:
                numDays = aux + 31 + 31 + 30 + 31 + 30 + 31 + 30 + 31 + 30;
                break;
            case 11:
                numDays = aux + 31 + 31 + 30 + 31 + 30 + 31 + 30 + 31 + 30 + 31;
                break;
            case 12:
                numDays = aux + 31 + 31 + 30 + 31 + 30 + 31 + 30 + 31 + 30 + 31 + 30;
                break;

        }


        return (numDays);

    }
    public static int checkIfYearIsBissextile(int year) {
        int nbre =0 ;


        if (((year % 4 == 0) &&
                !(year % 100 == 0))
                || (year % 400 == 0))
            nbre = 366;
        else
            nbre = 365;

        return nbre;
    }
    public static int calculNbreJour(String DateD, String DateF) {

        int Res1, Res2,difAn ,Res=0 ,anneeFin, anneeFinale,anneeDebut;
        try {
            String [] PartsD= DateD.split("/");


            String [] PartsF= DateF.split("/");


            anneeFinale=CongeFunction.checkIfYearIsBissextile(Integer.parseInt(PartsF[2]));
            anneeDebut=Integer.parseInt(PartsD[2]);
            anneeFin=Integer.parseInt(PartsF[2]);
            difAn=anneeFin-anneeDebut;


            for (int i=0 ;i<difAn;i++) {

                anneeFinale = anneeFinale + CongeFunction.checkIfYearIsBissextile(anneeDebut + i);
            }


            int nombreJMD = CongeFunction.calculDeJoursParMois(Integer.parseInt(PartsD[1])-1 ,Integer.parseInt(PartsD[2])) ;

            int nombreJMF = CongeFunction.calculDeJoursParMois(Integer.parseInt(PartsF[1])-1,Integer.parseInt(PartsF[2]));


            Res1= nombreJMD+Integer.parseInt(PartsD[0]) +CongeFunction.checkIfYearIsBissextile(anneeDebut);

            Res2 = nombreJMF+Integer.parseInt(PartsF[0])+anneeFinale;

            Res=Res2-Res1+1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return Res ;
    }


    public static  void showView(View view) {
        LinearLayout.LayoutParams layoutParamsShow = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParamsShow);
    }


    public static  void hideView(View view) {
        LinearLayout.LayoutParams layoutParamsHide = new LinearLayout.LayoutParams(0, 0);
        layoutParamsHide.gravity = Gravity.RIGHT;
        view.setLayoutParams(layoutParamsHide);

    }


}
