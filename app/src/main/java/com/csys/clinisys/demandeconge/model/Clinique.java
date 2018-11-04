package com.csys.clinisys.demandeconge.model;

import java.io.Serializable;

public class Clinique implements Serializable {
    private static final long serialVersionUID = 1L;

    private int idCli;
    private String nomCli;
    private String codCli;
    private String urlCli;
    private String urlLocalCli;
    private Boolean isActive;

    public Clinique() {
        isActive = false;
    }

    public Clinique(int idCli, String nomCli, String codCli, String urlCli, String urlLocalCli, Boolean isActive) {
        this.idCli = idCli;
        this.nomCli = nomCli;
        this.codCli = codCli;
        this.urlCli = urlCli;
        this.urlLocalCli = urlLocalCli;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Clinique [idCli=" + idCli + ", " + (nomCli != null ? "nomCli=" + nomCli + ", " : "")
                + (codCli != null ? "codCli=" + codCli + ", " : "") + (urlCli != null ? "urlCli=" + urlCli : "") + (isActive != null ? "isActive=" + isActive : "") + "]";
    }

    public int getIdCli() {
        return idCli;
    }

    public void setIdCli(int idCli) {
        this.idCli = idCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public String getUrlCli() {
        return urlCli;
    }

    public void setUrlCli(String urlCli) {
        this.urlCli = urlCli;
    }

    public String getUrlLocalCli() {
        return urlLocalCli;
    }

    public void setUrlLocalCli(String urlLocalCli) {
        this.urlLocalCli = urlLocalCli;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
