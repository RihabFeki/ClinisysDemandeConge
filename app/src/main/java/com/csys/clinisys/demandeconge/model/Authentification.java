package com.csys.clinisys.demandeconge.model;

/**
 * Created by admin on 13/06/2017.
 */

public class Authentification {

    private String actif;
    private String description;
    private String grp;
    private String matricule;
    private String matriculeresp;
    private String nomemp;
    private String passWord;
    private String prenemp;
    private String userName;


    public Authentification() {
        actif = "-1";
    }

    public Authentification(String actif, String description, String grp, String matricule, String matriculeresp, String nomemp, String passWord, String prenemp, String userName) {
        this.actif = actif;
        this.description = description;
        this.grp = grp;
        this.matricule = matricule;
        this.matriculeresp = matriculeresp;
        this.nomemp = nomemp;
        this.passWord = passWord;
        this.prenemp = prenemp;
        this.userName = userName;
    }

    public String getActif() {
        return actif;
    }

    public void setActif(String actif) {
        this.actif = actif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMatriculeresp() {
        return matriculeresp;
    }

    public void setMatriculeresp(String matriculeresp) {
        this.matriculeresp = matriculeresp;
    }

    public String getNomemp() {
        return nomemp;
    }

    public void setNomemp(String nomemp) {
        this.nomemp = nomemp;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPrenemp() {
        return prenemp;
    }

    public void setPrenemp(String prenemp) {
        this.prenemp = prenemp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
