package com.csys.clinisys.demandeconge.model;

public class User {

    private int id;
    private String nomemp;
    private String prenemp;
    private String userName;
    private String passWord;
    private String codeMed;
    private String codeCli;
    private Boolean statu;
    private Boolean active;
    private Boolean isInf;
    private Boolean isLocal;
    private String description;
    private String etage;
    private String codePin;
    private String matricule;
    private Boolean isBlocked;
    private Long DateBlockage;
    private  String matriculeresp;



    public User() {
        // TODO Auto-generated constructor stub
        nomemp="";
        prenemp="";
        matriculeresp="";
        userName = "";
        passWord = "";
        codeMed = "";
        codeCli = "";
        statu = false;
        active = false;
        isInf = false;
        isLocal = false;
        description = "";
        etage = "";
        codePin = "";
        matricule="";
        isBlocked = false;
        DateBlockage = 1L;



    }

    @Override
    public String toString() {
        return "User [id=" + id +" , nomemp=" +nomemp+ " , prenemp"+prenemp+", userName=" + userName + ", passWord=" + passWord + ", codeMed=" + codeMed
                + ", codeCli=" + codeCli + ", statu=" + statu + ", active=" + active + ", isInf=" + isInf + ", isLocal="
                + isLocal + ", description=" + description + ", etage=" + etage + ", codePin=" + codePin +", matricule="+matricule +", isBlocked = " + isBlocked + ", DateBlockage=" + DateBlockage +", matriculeResp=" + matriculeresp+ "]";
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

    public String getPrenemp() {
        return prenemp;
    }

    public void setPrenemp(String prenemp) {
        this.prenemp = prenemp;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCodeMed() {
        return codeMed;
    }

    public void setCodeMed(String codeMed) {
        this.codeMed = codeMed;
    }

    public String getCodeCli() {
        return codeCli;
    }

    public void setCodeCli(String codeCli) {
        this.codeCli = codeCli;
    }

    public Boolean getStatu() {
        return statu;
    }

    public void setStatu(Boolean statu) {
        this.statu = statu;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsInf() {
        return isInf;
    }

    public void setIsInf(Boolean isInf) {
        this.isInf = isInf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    public String getCodePin() {
        return codePin;
    }

    public void setCodePin(String codePin) {
        this.codePin = codePin;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Long getDateBlockage() {
        return DateBlockage;
    }

    public void setDateBlockage(Long dateBlockage) {
        DateBlockage = dateBlockage;
    }
}
