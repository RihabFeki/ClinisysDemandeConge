package com.csys.clinisys.demandeconge.helper;


import com.csys.clinisys.demandeconge.model.Clinique;

import java.util.ArrayList;

public class GetListClinique {

    public static ArrayList<Clinique> getListCliniqueStatique() {
        ArrayList<Clinique> cliniquesList = new ArrayList<>();
        cliniquesList.add(new Clinique(3, "Csys Test", "AMN01", "http://192.168.0.124:8084", "http://192.168.0.124:8084", false));
        return cliniquesList;
    }
}

