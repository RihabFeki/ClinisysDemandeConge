package com.csys.clinisys.demandeconge.model;

/**
 * Created by lenovo on 30/06/2017.
 */

public class TypeConge {

    private String libRepos ;
    private String codeRepos ;

    @Override
    public String toString() {
        return "TypeConge{" +
                "libRepos='" + libRepos + '\'' +
                ", codeRepos='" + codeRepos + '\'' +
                '}';
    }

    public TypeConge(String libRepos, String codeRepos) {
        this.libRepos = libRepos;
        this.codeRepos = codeRepos;
    }

    public String getCodeRepos() {
        return codeRepos;

    }

    public void setCodeRepos(String codeRepos) {
        this.codeRepos = codeRepos;
    }

    public TypeConge() {
    }





    public String getLibRepos() {
        return libRepos;
    }

    public void setLibRepos(String libRepos) {
        this.libRepos = libRepos;
    }
}
