package com.csys.clinisys.demandeconge.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.csys.clinisys.demandeconge.helper.MessageLog;
import com.csys.clinisys.demandeconge.model.User;

import java.util.ArrayList;

public class UserDAO extends DAOBase {

    private static final String TABLE_NAME = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_PASSWORD = "passWord";
    private static final String KEY_CODEMED = "codeMed";
    private static final String KEY_CODECLI = "codeCli";
    private static final String KEY_STATU = "statu";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_ISINF = "isInf";
    private static final String KEY_ISLOCAL = "islocal";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ETAGE = "etage";
    private static final String KEY_CODEPIN = "codePin";
    private static final String KEY_ISBLOCKED = "isBlocked";
    private static final String KEY_DATEBLOCKAGE = "DateBlockage";

    private static final String KEY_MATRICULE = "matricule";
    private static final String KEY_NOMEMP = "nomemp";
    private static final String KEY_PRENEMP = "prenemp";
    private static final String KEY_MATRICULERESP = "matriculeresp";
    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + KEY_ID
            + " integer PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT, " + KEY_PASSWORD + " TEXT, " + KEY_CODEMED
            + " TEXT, " + KEY_CODECLI + " TEXT, " + KEY_STATU + " TEXT, " + KEY_ACTIVE + " TEXT, " + KEY_ISINF + " TEXT, "
            + KEY_ISLOCAL + " TEXT, " + KEY_DESCRIPTION + " TEXT, " + KEY_ETAGE + " TEXT, " + KEY_CODEPIN
            + " TEXT , " + KEY_ISBLOCKED + " TEXT , " +
            KEY_DATEBLOCKAGE + " TEXT , " +  KEY_MATRICULE + " TEXT , " +  KEY_NOMEMP + " TEXT , " + KEY_PRENEMP+ " TEXT , " + KEY_MATRICULERESP + " TEXT )";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    public UserDAO(Context pContext) {
        super(pContext);
        // TODO Auto-generated constructor stub
    }

    public Boolean addUser(User user) {
        try {
            open();
            ContentValues value = new ContentValues();
            value.putNull(KEY_ID);
            value.put(KEY_USERNAME, user.getUserName());
            value.put(KEY_PASSWORD, user.getPassWord());
            value.put(KEY_CODEMED, user.getCodeMed());
            value.put(KEY_CODECLI, user.getCodeCli());
            value.put(KEY_STATU, user.getStatu().toString());
            value.put(KEY_ACTIVE, user.getActive().toString());
            value.put(KEY_ISINF, user.getIsInf().toString());
            value.put(KEY_ISLOCAL, user.getIsLocal().toString());
            value.put(KEY_DESCRIPTION, user.getDescription().toString());
            value.put(KEY_ETAGE, user.getEtage().toString());
            value.put(KEY_CODEPIN, user.getCodePin().toString());
            value.put(KEY_ISBLOCKED, user.getBlocked().toString());
            value.put(KEY_DATEBLOCKAGE, user.getDateBlockage().toString());
            value.put(KEY_MATRICULE,user.getMatricule().toString());
            value.put(KEY_NOMEMP,user.getNomemp().toString());
            value.put(KEY_PRENEMP,user.getPrenemp().toString());
            value.put(KEY_MATRICULERESP,user.getMatriculeresp().toString());


            if (mDb.insert(TABLE_NAME, null, value) == -1) {
                // return -1 => false
                close();
                return false;
            }
            close();
            return true;
        } catch (Exception e) {
            close();
            return false;
        }
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> usersList = new ArrayList<User>();
        try {
            open();
            Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME, null);
            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor != null && cursor.moveToFirst()) {
                    cursor.move(i);
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getString(0)));
                    user.setUserName(cursor.getString(1));
                    user.setPassWord(cursor.getString(2));
                    user.setCodeMed(cursor.getString(3));
                    user.setCodeCli(cursor.getString(4));
                    user.setStatu(Boolean.valueOf(cursor.getString(5)));
                    user.setActive(Boolean.valueOf(cursor.getString(6)));
                    user.setIsInf(Boolean.valueOf(cursor.getString(7)));
                    user.setIsLocal(Boolean.valueOf(cursor.getString(8)));
                    user.setDescription(cursor.getString(9));
                    user.setEtage(cursor.getString(10));
                    user.setCodePin(cursor.getString(11));
                    user.setBlocked(Boolean.valueOf(cursor.getString(12)));

                    if (cursor.getString(13) != null)
                        user.setDateBlockage(Long.valueOf(cursor.getString(13)));
                    else
                        user.setDateBlockage(1L);


                    user.setMatricule(cursor.getString(14));
                    user.setNomemp(cursor.getString(15));
                    user.setPrenemp(cursor.getString(16));
                    user.setMatriculeresp(cursor.getString(17));

                    usersList.add(user);
                }
            }
            cursor.close();
            close();
            return usersList;
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return usersList;
    }

    public User getUserByCodeCli(String codCli) {
        User user = new User();
        open();
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + KEY_CODECLI + " = ?",
                new String[]{codCli});
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setPassWord(cursor.getString(2));
            user.setCodeMed(cursor.getString(3));
            user.setCodeCli(cursor.getString(4));
            user.setStatu(Boolean.valueOf(cursor.getString(5)));
            user.setActive(Boolean.valueOf(cursor.getString(6)));
            user.setIsInf(Boolean.valueOf(cursor.getString(7)));
            user.setIsLocal(Boolean.valueOf(cursor.getString(8)));
            user.setDescription(cursor.getString(9));
            user.setEtage(cursor.getString(10));
            user.setCodePin(cursor.getString(11));
            user.setBlocked(Boolean.valueOf(cursor.getString(12)));
            if (cursor.getString(13) != null)
                user.setDateBlockage(Long.valueOf(cursor.getString(13)));
            else
                user.setDateBlockage(1L);

            user.setMatricule(cursor.getString(14));
            user.setNomemp(cursor.getString(15));
            user.setPrenemp(cursor.getString(16));
            user.setMatriculeresp(cursor.getString(17));

            //user.setDateBlockage(Long.valueOf( cursor.getString(13)));
        }
        cursor.close();
        close();
        return user;
    }

    public User getUserActive() {
        User user = new User();
        open();
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + KEY_ACTIVE + " = ?",
                new String[]{"true"});
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setPassWord(cursor.getString(2));
            user.setCodeMed(cursor.getString(3));
            user.setCodeCli(cursor.getString(4));
            user.setStatu(Boolean.valueOf(cursor.getString(5)));
            user.setActive(Boolean.valueOf(cursor.getString(6)));
            user.setIsInf(Boolean.valueOf(cursor.getString(7)));
            user.setIsLocal(Boolean.valueOf(cursor.getString(8)));
            user.setDescription(cursor.getString(9));
            user.setEtage(cursor.getString(10));
            user.setCodePin(cursor.getString(11));
            user.setBlocked(Boolean.valueOf(cursor.getString(12)));
            if (cursor.getString(13) != null)
                user.setDateBlockage(Long.valueOf(cursor.getString(13)));
            else
                user.setDateBlockage(1L);

            user.setMatricule(cursor.getString(14));
            user.setNomemp(cursor.getString(15));
            user.setPrenemp(cursor.getString(16));
            user.setMatriculeresp(cursor.getString(17));

        }
        cursor.close();
        close();
        return user;
    }

    public int getUserByCodCliCount(String codCli) {

        int count = 0;
        try {
            open();
            Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + KEY_CODECLI + " = ?",
                    new String[]{codCli});
            count = cursor.getCount();
            cursor.close();
            close();
        } catch (Exception e) {
            // TODO: handle exception
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return count;
    }

    public Boolean updateUser(User user) {

        try {

            Log.e("User update", user.toString());
            open();
            ContentValues value = new ContentValues();
            value.put(KEY_USERNAME, user.getUserName());
            value.put(KEY_PASSWORD, user.getPassWord());
            value.put(KEY_CODEMED, user.getCodeMed());
            value.put(KEY_STATU, String.valueOf(user.getStatu().toString()));

            value.put(KEY_ACTIVE, String.valueOf(user.getActive().toString()));
            value.put(KEY_ISINF, String.valueOf(user.getIsInf().toString()));
            value.put(KEY_ISLOCAL, String.valueOf(user.getIsLocal().toString()));
            value.put(KEY_DESCRIPTION, user.getDescription());
            value.put(KEY_ETAGE, user.getEtage());
            value.put(KEY_CODEPIN, user.getCodePin());
            value.put(KEY_ISBLOCKED, String.valueOf(user.getBlocked()));
            value.put(KEY_DATEBLOCKAGE, String.valueOf(user.getDateBlockage()));
            value.put(KEY_MATRICULE,user.getMatricule());
            value.put(KEY_NOMEMP,user.getNomemp());
            value.put(KEY_PRENEMP,user.getPrenemp());
            value.put(KEY_MATRICULERESP,user.getMatriculeresp());

            if (mDb.update(TABLE_NAME, value, KEY_CODECLI + " = ?",
                    new String[]{String.valueOf(user.getCodeCli())}) != 1)
            // return 1 => true
            {
                close();
                return false;
            }
            close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            close();
            return false;
        }

    }

    public Boolean deleteUserByCodeCli(String codCli) {
        try {
            open();
            if (mDb.delete(TABLE_NAME, KEY_CODECLI + " = ?", new String[]{String.valueOf(codCli)}) == 0) {
                close();
                return false;
            }
            close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            return false;
        }
    }

    public Boolean deleteAllUser() {
        try {
            open();
            if (mDb.delete(TABLE_NAME, null, null) == 0) {
                close();
                return false;
            }
            close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
            return false;
        }
    }

}