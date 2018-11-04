package com.csys.clinisys.demandeconge.webservice;



import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.helper.MessageLog;
import com.csys.clinisys.demandeconge.param.Config;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;


@SuppressWarnings("unused")
public class DossierSoinWSServiceAndroid {

    private static final String NAMESPACE = "http://service.dmi.csys.com/";
    private static final int SOAP_VERSION = SoapEnvelope.VER11;

    private static final String URL = Config.SERVEUR_CSYS_CORE;

    public static HttpTransportSE androidHttpTransportDSS = new HttpTransportSE(URL);

    public static void Connection() {
        androidHttpTransportDSS = new HttpTransportSE(URL, 10000);
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("adminWS", "pom".toCharArray());
            }
        });
    }

    public static ArrayList<Clinique> ListCliniqueForAndroid() {
        final String SOAP_ACTION = "";
        final String METHOD_NAME = "ListCliniqueForAndroid";
        ArrayList<Clinique> cliniqueList = new ArrayList<Clinique>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        try {
            androidHttpTransportDSS.call(SOAP_ACTION, envelope);
            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                MessageLog.MessageError(new Exception().getStackTrace(), str);
            } else {
                SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                    SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);
                    Clinique clinique = new Clinique();
                    clinique.setIdCli(Integer.valueOf(getProperty(object, "id")));
                    clinique.setNomCli(getProperty(object, "nom".replace("anyType{}", "")));
                    clinique.setCodCli(getProperty(object, "code").replace("anyType{}", ""));
                    clinique.setUrlCli(getProperty(object, "url").replace("anyType{}", ""));
                    clinique.setUrlLocalCli(getProperty(object, "urllocal").replace("anyType{}", ""));
                    cliniqueList.add(clinique);
                }
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return cliniqueList;

    }

    public static ArrayList<Clinique> ListCliniqueForAndroidByModule(String module) {
        final String SOAP_ACTION = "";
        final String METHOD_NAME = "ListCliniqueForAndroidByModule";
        ArrayList<Clinique> cliniqueList = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        request.addProperty("arg0", module);
        envelope.setOutputSoapObject(request);
        try {
            androidHttpTransportDSS.call(SOAP_ACTION, envelope);
            if (envelope.bodyIn instanceof SoapFault) {
                String str = ((SoapFault) envelope.bodyIn).faultstring;
                MessageLog.MessageError(new Exception().getStackTrace(), str);
            } else {
                SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                MessageLog.MessageDebug(new Exception().getStackTrace(), String.valueOf(resultsRequestSOAP));
                for (int i = 0; i < resultsRequestSOAP.getPropertyCount(); i++) {
                    SoapObject object = (SoapObject) resultsRequestSOAP.getProperty(i);
                    Clinique clinique = new Clinique();
                    clinique.setIdCli(Integer.valueOf(getProperty(object, "id")));
                    clinique.setNomCli(getProperty(object, "nom".replace("anyType{}", "")));
                    clinique.setCodCli(getProperty(object, "code").replace("anyType{}", ""));
                    clinique.setUrlCli(getProperty(object, "url").replace("anyType{}", ""));
                    clinique.setUrlLocalCli(getProperty(object, "urllocal").replace("anyType{}", ""));
                    cliniqueList.add(clinique);
                }
            }
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return cliniqueList;

    }

    public static String getProperty(SoapObject object, String property) {
        try {
            return object.getProperty(property).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
