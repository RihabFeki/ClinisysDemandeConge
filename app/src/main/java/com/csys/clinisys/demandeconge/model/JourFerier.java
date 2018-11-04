package com.csys.clinisys.demandeconge.model;

/**
 * Created by lenovo on 13/07/2017.
 */

public class JourFerier {

private String jour;

    public JourFerier() {
    }

    @Override
    public String toString() {
        return "JourFerier{" +
                "jour='" + jour + '\'' +
                '}';
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public JourFerier(String jour) {
        this.jour = jour;

    }
}
