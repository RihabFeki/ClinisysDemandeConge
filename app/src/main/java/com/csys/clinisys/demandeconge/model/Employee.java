package com.csys.clinisys.demandeconge.model;

/**
 * Created by lenovo on 10/07/2017.
 */

public class Employee {
    private String  matemp  ;
    private String  nomemp  ;
    private String  prenemp  ;
    private String nbrjour ;
    private Qualification qualification ;

    public Employee() {
    }


    public Employee(String matemp, String nomemp, String prenemp, String nbrjour, Qualification qualification) {
        this.matemp = matemp;
        this.nomemp = nomemp;
        this.prenemp = prenemp;
        this.nbrjour = nbrjour;
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "matemp='" + matemp + '\'' +
                ", nomemp='" + nomemp + '\'' +
                ", prenemp='" + prenemp + '\'' +
                ", nbrjour='" + nbrjour + '\'' +
                ", qualification=" + qualification +
                '}';
    }

    public String getNbrjour() {
        return nbrjour;
    }

    public void setNbrjour(String nbrjour) {
        this.nbrjour = nbrjour;
    }

    public String getMatemp() {
        return matemp;
    }

    public void setMatemp(String matemp) {
        this.matemp = matemp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public String getPrenemp() {
        return prenemp;
    }

    public void setPrenemp(String prenemp) {
        this.prenemp = prenemp;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
}
