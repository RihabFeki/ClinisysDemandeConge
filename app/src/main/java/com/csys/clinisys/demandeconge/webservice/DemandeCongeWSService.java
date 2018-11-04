package com.csys.clinisys.demandeconge.webservice;

import android.util.Log;

import com.csys.clinisys.demandeconge.helper.MessageLog;
import com.csys.clinisys.demandeconge.model.Authentification;
import com.csys.clinisys.demandeconge.model.DemandeConge;
import com.csys.clinisys.demandeconge.model.Employee;
import com.csys.clinisys.demandeconge.model.Employer;
import com.csys.clinisys.demandeconge.model.JourFerier;
import com.csys.clinisys.demandeconge.model.Qualification;
import com.csys.clinisys.demandeconge.model.TypeConge;
import com.csys.clinisys.demandeconge.param.Config;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;

/**
 * Created by admin on 13/06/2017.
 */

public class DemandeCongeWSService {

    public static final String NAMESPACE = "http://service.DemandeConge.csys.com/";
    public static final int SOAP_VERSION = SoapEnvelope.VER11;

    public static HttpTransportSE androidHttpTransportRS = new HttpTransportSE("");

    public static void Connection(String url) {
        androidHttpTransportRS = new HttpTransportSE(url + "/DemandeConge-core/DemandeCongeWSService?wsdl",1000000000);
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Config.LOGIN_WSDL, Config.PASSWORD_WSDL.toCharArray());
            }
        });
    }

    public static Authentification GetAuthentification(String username, String password) {
        Authentification authentification = new Authentification();

        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "Authentification";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("username", username);
            request.addProperty("password", password);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);
            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                        SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);
                        authentification.setActif(object.getProperty("actif").toString().replace("anyType{}", ""));
                        //TODO Parse Object

                       authentification.setDescription(object.getProperty("description").toString());
                        authentification.setGrp(object.getProperty("grp").toString());
                        authentification.setMatricule(object.getProperty("matricule").toString());
                        authentification.setMatriculeresp(object.getProperty("matriculeresp").toString());
                        authentification.setNomemp(object.getProperty("nomemp").toString());
                        authentification.setPassWord(object.getProperty("passWord").toString());
                        authentification.setUserName(object.getProperty("userName").toString());
                        authentification.setPrenemp(object.getProperty("prenemp").toString());




                    }
                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return authentification;
    }









    public static ArrayList<TypeConge>  getallTypeConge() {

        ArrayList<TypeConge> typeCongesList = new ArrayList<TypeConge>();
        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "getallTypeConge";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                        androidHttpTransportRS.call(SOAP_ACTION, envelope);
                        if (envelope.bodyIn instanceof SoapFault) {
                            String str = ((SoapFault) envelope.bodyIn).faultstring;
                            MessageLog.MessageError(new Exception().getStackTrace(), str);
                        } else {
                            SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                            MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                            for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                                SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);

                                try { TypeConge typeConge = new TypeConge();
                                    typeConge.setLibRepos(object.getProperty("libRepos").toString());
                                    typeConge.setCodeRepos(object.getProperty("codeRepos").toString());


                                   typeCongesList.add(typeConge);



                                }catch (Exception e) {
                                    Log.d("Exception C", e.getMessage());
                                }





                            }
                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return typeCongesList;
    }


public static String getSoldConge(String matricule){

    String soldeConge="";
    try {
        final String SOAP_ACTION = "";
        final String METHOD_NAME = "getSoldConge";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("matricule", matricule);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
        envelope.setOutputSoapObject(request);


        try {
            androidHttpTransportRS.call(SOAP_ACTION, envelope);
            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                MessageLog.MessageError(new Exception().getStackTrace(), str);
            } else {
                SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                soldeConge=resultsRequestSOAP.getProperty("return").toString().replace("anyType{}", "");
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
    } catch (Exception e) {
        MessageLog.CatchMessage(new Exception().getStackTrace(), e);
    }

    return soldeConge ;

}

    public static String getSoldCongeRecuperation(String matricule){

       String soldeCongeRecuperation="";
        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "getSoldCongeRecuperation";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("matricule", matricule);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    soldeCongeRecuperation=resultsRequestSOAP.getProperty("return").toString().replace("anyType{}", "");


                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return soldeCongeRecuperation ;

    }


    public static String getsoldeDemande(String matricule){

        String soldeDemande="";
        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "getsoldeDemande";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("matricule", matricule);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    soldeDemande=resultsRequestSOAP.getProperty("return").toString().replace("anyType{}", "");


                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return soldeDemande ;

    }





       public static ArrayList<Employee>  getlistEmployerByMatriculeMaster(String matricule) {

        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "getlistEmployerByMatriculeMaster";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("master", matricule);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                        SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);

                        SoapObject qualificationObject = (SoapObject) object.getProperty("qualification");

                        try { Employee employee = new Employee();
                            Qualification qualification = new Qualification();

                            employee.setMatemp(object.getProperty("matemp").toString());
                            Log.d("matemp",object.getProperty("matemp").toString());
                            employee.setNomemp(object.getProperty("nomemp").toString());
                            Log.d("nomemp",object.getProperty("nomemp").toString());
                            employee.setPrenemp(object.getProperty("prenemp").toString());
                            Log.d("prenemp",object.getProperty("prenemp").toString());
                            try {
                                qualification.setLibqual(qualificationObject.getProperty("libqual").toString());
                            } catch (Exception e) {
                                qualification.setLibqual("");
                            }
                            Log.d("libqual",qualificationObject.getProperty("libqual").toString());
                            employee.setQualification(qualification);




                            employeeList.add(employee);



                        }catch (Exception e) {
                            Log.d("Exception C", e.getMessage());
                        }





                    }
                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return employeeList;
    }


    public static Employee  getEmployerByMatriculeMaster(String matricule) {

        Employee employee = new Employee();
        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "getEmployerByMatriculeMaster";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("mat", matricule);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                        SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);



                        try {
                              employee.setNbrjour(object.getProperty("nbrjour").toString());

                            Log.d("nbrjour",object.getProperty("nbrjour").toString());




                        }catch (Exception e) {
                            Log.d("Exception C", e.getMessage());
                        }





                    }
                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return employee;
    }

    public static ArrayList<JourFerier>  findJouFeriesByDate(String DateD, String DateF) {

        ArrayList<JourFerier> jourFeriers = new ArrayList<JourFerier>();
        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "findJouFeriesByDate";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("du", DateD);
            request.addProperty("au", DateF);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                        SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);



                        try { JourFerier jourFerier = new JourFerier();
                            jourFerier.setJour(object.getProperty("jour").toString());

                            Log.d("jour",object.getProperty("jour").toString());




                            jourFeriers.add(jourFerier);



                        }catch (Exception e) {
                            Log.d("Exception C", e.getMessage());
                        }





                    }
                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return jourFeriers;
    }


    public static Boolean addDemandeConge(String matricule, String du, String au, String nbrjour,
                                         String typeConge, String motif, String interime, String cordonnee, int etat,
                                         String num, String observResp, String resp, boolean parHeure, String heureDebut,
                                         String heureFin, String heureRecuperaton, String userCreate,
                                         String motifInterim, String matrespconnect){
        Boolean rslt=false;

        try {
            final String SOAP_ACTION = "";
            final String METHOD_NAME = "addDemandeConge";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("matricule", matricule);
            request.addProperty("du", du);
            request.addProperty("au", au);
            request.addProperty("nbrjour", nbrjour);
            request.addProperty("motif", motif);
            request.addProperty("cordonnee", cordonnee);
            request.addProperty("etat", etat);
            request.addProperty("num", num);
            request.addProperty("observResp", observResp);
            request.addProperty("resp", resp);
            request.addProperty("parHeure", parHeure);
            request.addProperty("heureDebut", heureDebut);
            request.addProperty("heureFin", heureFin);
            request.addProperty("heureRecuperaton", heureRecuperaton);
            request.addProperty("userCreate", userCreate);
            request.addProperty("motifInterim", motifInterim);
            request.addProperty("matrespconnect", matrespconnect);
            request.addProperty("typeConge", typeConge);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);


            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else { String Demande="";
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    Demande=resultsRequestSOAP.getProperty("return").toString().replace("anyType{}", "");
                    rslt =Boolean.valueOf(Demande);


                }
            } catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }

        return rslt ;

    }

    public static ArrayList<DemandeConge> getlistDemandeCongeByMatriculeAndAccord(String matricule, String accord, String search, String SG, String filterdu, String
            filterau, Boolean hisResp){

        ArrayList<DemandeConge> demandeCongeList=new ArrayList<>();

        final String SOAP_ACTION = "";
        final String METHOD_NAME = "getlistDemandeCongeByMatriculeAndAccord";

        try {


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("matricule", matricule);
            request.addProperty("accord", accord);
            request.addProperty("hisResp", hisResp);
            request.addProperty("search", "");
            request.addProperty("SG", "");
            //request.addProperty("filterdu", "");
            request.addProperty("filterdu", filterdu);
            //request.addProperty("filterau", "");
            request.addProperty("filterau", filterau);
            request.addProperty("service", "");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SOAP_VERSION);
            envelope.setOutputSoapObject(request);

            try {
                androidHttpTransportRS.call(SOAP_ACTION, envelope);
                if (envelope.bodyIn instanceof SoapFault) {
                    String str = ((SoapFault) envelope.bodyIn).faultstring;
                    MessageLog.MessageError(new Exception().getStackTrace(), str);
                } else {
                    SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                    for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++)
                    {
                        DemandeConge demandeConge = new DemandeConge();
                        Employer employer=new Employer();
                        Qualification qualification = new Qualification();

                        SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);
                        SoapObject employerobject = (SoapObject) object.getProperty("employer");
                        SoapObject qualificationObject = (SoapObject) employerobject.getProperty("qualification");


                        demandeConge.setDu(object.getProperty("du").toString());
                        demandeConge.setAu(object.getProperty("au").toString());
                        qualification.setCodqual(qualificationObject.getProperty("codqual").toString().replace("anyType{}", ""));
                        employer.setQualification(qualification);
                        employer.setNomemp(employerobject.getProperty("nomemp").toString());
                        demandeConge.setCordonnee(object.getProperty("cordonnee").toString());
                        demandeConge.setNbrjouraccorder(object.getProperty("nbrjouraccorder").toString());
                        demandeConge.setNbrjour(object.getProperty("nbrjour").toString());
                        demandeConge.setEtat(object.getProperty("etat").toString());
                        demandeConge.setHeureDeb(object.getProperty("heureDeb").toString());
                        demandeConge.setHeureFin(object.getProperty("heureFin").toString());
                        demandeConge.setTypeConge(object.getProperty("typeConge").toString());
                        demandeConge.setNum(object.getProperty("num").toString());
                        demandeConge.setMotif(object.getProperty("motif").toString());
                       /* demandeConge.setMotifInterim(object.getProperty("motifInterim").toString());
                        demandeConge.setMotifRefus(object.getProperty("motifRefus").toString());
                        demandeConge.setObservationresp(object.getProperty("observationresp").toString());
                        demandeConge.setLibRepos(object.getProperty("libRepos").toString());
                        demandeConge.setImporter(object.getProperty("importer").toString());*/


                        demandeCongeList.add(demandeConge);



                    }
                }
            }
            catch (Exception e) {
                MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            }


        }
        catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return demandeCongeList;
    }




}
