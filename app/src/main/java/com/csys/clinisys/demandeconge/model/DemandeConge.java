package com.csys.clinisys.demandeconge.model;

/**
 * Created by lenovo on 18/07/2017.
 */
public class DemandeConge {
    private String au;
    private String cordonnee;
    private String du;
    private Employer employer;
    private String etat;
    private String heureDeb;
    private String  heureFin;
    private String importer;
    private Employer interime;
    private String libRepos;
    private String motif;
    private String motifInterim;
    private String motifRefus;
    private String nbrjour;
    private String nbrjouraccorder;
    private String num;
    private String observationresp;
    private String typeConge;



    public DemandeConge() {
    }




    public String getAu() {
        return au;
    }

    public void setAu(String au) {
        this.au = au;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getCordonnee() {
        return cordonnee;
    }

    public void setCordonnee(String cordonnee) {
        this.cordonnee = cordonnee;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getHeureDeb() {
        return heureDeb;
    }

    public void setHeureDeb(String heureDeb) {
        this.heureDeb = heureDeb;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getImporter() {
        return importer;
    }

    public void setImporter(String importer) {
        this.importer = importer;
    }

    public Employer getInterime() {
        return interime;
    }

    public void setInterime(Employer interime) {
        this.interime = interime;
    }

    public String getLibRepos() {
        return libRepos;
    }

    public void setLibRepos(String libRepos) {
        this.libRepos = libRepos;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getMotifInterim() {
        return motifInterim;
    }

    public void setMotifInterim(String motifInterim) {
        this.motifInterim = motifInterim;
    }

    public String getMotifRefus() {
        return motifRefus;
    }

    public void setMotifRefus(String motifRefus) {
        this.motifRefus = motifRefus;
    }

    public String getNbrjour() {
        return nbrjour;
    }

    public void setNbrjour(String nbrjour) {
        this.nbrjour = nbrjour;
    }

    public String getNbrjouraccorder() {
        return nbrjouraccorder;
    }

    public void setNbrjouraccorder(String nbrjouraccorder) {
        this.nbrjouraccorder = nbrjouraccorder;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getObservationresp() {
        return observationresp;
    }

    public void setObservationresp(String observationresp) {
        this.observationresp = observationresp;
    }

    public String getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(String typeConge) {
        this.typeConge = typeConge;
    }
}
