package com.csys.clinisys.demandeconge.model;

/**
 * Created by lenovo on 10/07/2017.
 */

public class Qualification {
    private String autorite;
    private String codqual;
    private String libqual;
    private String mission ;
    private String profil ;
    private String responsabilite ;

    public Qualification() {
    }

    @Override
    public String toString() {
        return "qualification{" +
                "autorite='" + autorite + '\'' +
                ", codqual=" + codqual +
                ", libqual='" + libqual + '\'' +
                ", mission='" + mission + '\'' +
                ", profil='" + profil + '\'' +
                ", responsabilite='" + responsabilite + '\'' +
                '}';
    }

    public String getAutorite() {
        return autorite;
    }

    public void setAutorite(String autorite) {
        this.autorite = autorite;
    }

    public String getCodqual() {
        return codqual;
    }

    public void setCodqual(String codqual) {
        this.codqual = codqual;
    }

    public String getLibqual() {
        return libqual;
    }

    public void setLibqual(String libqual) {
        this.libqual = libqual;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getResponsabilite() {
        return responsabilite;
    }

    public void setResponsabilite(String responsabilite) {
        this.responsabilite = responsabilite;
    }

}
