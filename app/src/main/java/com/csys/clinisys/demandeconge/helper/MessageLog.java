package com.csys.clinisys.demandeconge.helper;


import android.util.Log;

import com.csys.clinisys.demandeconge.param.Config;


public class MessageLog {

    public static void CatchMessage(StackTraceElement[] stackTraceElement, Exception e) {
        //MessageLog.CatchMessage(new Exception().getStackTrace(),e);
        if (Config.IS_DEBUGGING) {
            try {
                String errorMessage = "";
                try {
                    errorMessage = (e.getMessage() == null) ? "Message is empty"
                            : e.getClass().getName() + ", LIGNE: " + e.getStackTrace()[2].getLineNumber() + " , " + e.getMessage() + " , " + e.getCause();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Log.e("Class :" + stackTraceElement[0].getClassName()
                        , "Method: " + stackTraceElement[0].getMethodName()
                                + " Ligne: " + stackTraceElement[0].getLineNumber()
                                + "\n" + errorMessage + "");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void MessageError(StackTraceElement[] stackTraceElement, String message) {
        if (Config.IS_DEBUGGING) {
            try {
                Log.e("Class :" + stackTraceElement[0].getClassName()
                        , "Method: " + stackTraceElement[0].getMethodName() + " Ligne: " + stackTraceElement[0].getLineNumber()
                                + "\n" + message + "");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void MessageDebug(StackTraceElement[] stackTraceElement, String message) {

        if (Config.IS_DEBUGGING) {
            try {
                Log.d("Class :" + stackTraceElement[0].getClassName()
                        , "Method: " + stackTraceElement[0].getMethodName() + " Ligne: " + stackTraceElement[0].getLineNumber()
                                + "\n" + message + "");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void MessageInfo(StackTraceElement[] stackTraceElement, String message) {

        if (Config.IS_DEBUGGING) {
            try {
                Log.i("Class :" + stackTraceElement[0].getClassName()
                        , "Method: " + stackTraceElement[0].getMethodName() + " Ligne: " + stackTraceElement[0].getLineNumber()
                                + "\n" + message + "");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
