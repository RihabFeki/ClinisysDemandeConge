package com.csys.clinisys.demandeconge.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.csys.clinisys.demandeconge.helper.MessageLog;
import com.csys.clinisys.demandeconge.model.Clinique;

import java.util.ArrayList;
import java.util.Iterator;


public class CliniqueDAO extends DAOBase {

    private static final String TABLE_NAME = "clinique";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    private static final String KEY_IDCLI = "idCli";
    private static final String KEY_NOMCLI = "nomCli";
    private static final String KEY_CODCLI = "codCli";
    private static final String KEY_ISACTIVE = "isActive";
    private static final String KEY_URLCLI = "urlCli";
    private static final String KEY_URLLOCALCLI = "urlLocalCli";


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + KEY_IDCLI + " TEXT PRIMARY KEY,"
            + KEY_NOMCLI + " TEXT, " + KEY_CODCLI + " TEXT, " + KEY_ISACTIVE + " TEXT, " + KEY_URLCLI + " TEXT, " + KEY_URLLOCALCLI + " TEXT)";

    public CliniqueDAO(Context pContext) {
        super(pContext);
        // TODO Auto-generated constructor stub
    }

    public Boolean addClinique(Clinique clinique) {
        try {
            open();
            ContentValues value = new ContentValues();
            value.put(KEY_IDCLI, clinique.getIdCli());
            value.put(KEY_NOMCLI, clinique.getNomCli());
            value.put(KEY_CODCLI, clinique.getCodCli());
            value.put(KEY_ISACTIVE, clinique.getActive());
            value.put(KEY_URLCLI, clinique.getUrlCli());
            value.put(KEY_URLLOCALCLI, clinique.getUrlLocalCli());

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

    public Boolean addListClinique(ArrayList<Clinique> cliniquesList) {
        try {
            open();
            mDb.beginTransaction();
            ContentValues value = new ContentValues();
            for (Iterator<Clinique> i = cliniquesList.iterator(); i.hasNext(); ) {
                Clinique clinique = i.next();
                value.put(KEY_IDCLI, clinique.getIdCli());
                value.put(KEY_NOMCLI, clinique.getNomCli());
                value.put(KEY_CODCLI, clinique.getCodCli());
                value.put(KEY_ISACTIVE, clinique.getActive());
                value.put(KEY_URLCLI, clinique.getUrlCli());
                value.put(KEY_URLLOCALCLI, clinique.getUrlLocalCli());
                mDb.insert(TABLE_NAME, null, value);
                value.clear();
            }
            mDb.setTransactionSuccessful();
            mDb.endTransaction();
            close();
        } catch (Exception e) {
            mDb.endTransaction();
            close();
            return false;
        }
        return true;
    }

    public ArrayList<Clinique> getAllClinique() {
        ArrayList<Clinique> cliniquesList = new ArrayList<Clinique>();
        try {
            open();
            Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " order by nomCli", null);
            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor != null && cursor.moveToFirst()) {
                    cursor.move(i);
                    Clinique clinique = new Clinique();
                    clinique.setIdCli(Integer.parseInt(cursor.getString(0)));
                    clinique.setNomCli(cursor.getString(1));
                    clinique.setCodCli(cursor.getString(2));
                    clinique.setActive(Boolean.valueOf(cursor.getString(3)));
                    clinique.setUrlCli(cursor.getString(4));
                    clinique.setUrlLocalCli(cursor.getString(5));
                    cliniquesList.add(clinique);
                }
            }
            cursor.close();
            close();
            return cliniquesList;
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return cliniquesList;
    }

    public ArrayList<Clinique> getOtherClinique() {
        ArrayList<Clinique> cliniquesList = new ArrayList<Clinique>();
        try {
            open();
            Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where  codCli not in (select codecli from user) order by nomCli",
                    null);
            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor != null && cursor.moveToFirst()) {
                    cursor.move(i);
                    Clinique clinique = new Clinique();
                    clinique.setIdCli(Integer.parseInt(cursor.getString(0)));
                    clinique.setNomCli(cursor.getString(1));
                    clinique.setCodCli(cursor.getString(2));
                    clinique.setActive(Boolean.valueOf(cursor.getString(3)));
                    clinique.setUrlCli(cursor.getString(4));
                    clinique.setUrlLocalCli(cursor.getString(5));
                    cliniquesList.add(clinique);
                }
            }
            cursor.close();
            close();
            return cliniquesList;
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return cliniquesList;
    }

    public ArrayList<Clinique> getCliniqueFavorie() {
        ArrayList<Clinique> cliniquesList = new ArrayList<Clinique>();
        try {
            open();
            Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where  codCli in (select codecli from user) order by nomCli",
                    null);
            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor != null && cursor.moveToFirst()) {
                    cursor.move(i);
                    Clinique clinique = new Clinique();
                    clinique.setIdCli(Integer.parseInt(cursor.getString(0)));
                    clinique.setNomCli(cursor.getString(1));
                    clinique.setCodCli(cursor.getString(2));
                    clinique.setActive(Boolean.valueOf(cursor.getString(3)));
                    clinique.setUrlCli(cursor.getString(4));
                    clinique.setUrlLocalCli(cursor.getString(5));
                    cliniquesList.add(clinique);
                }
            }
            cursor.close();
            close();
            return cliniquesList;
        } catch (Exception e) {
            MessageLog.CatchMessage(new Exception().getStackTrace(), e);
        }
        return cliniquesList;
    }

    public Clinique getCliniqueByCodeCli(String codCli) {
        Clinique clinique = new Clinique();
        open();
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + KEY_CODCLI + " = ?",
                new String[]{codCli});
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            clinique.setIdCli(Integer.parseInt(cursor.getString(0)));
            clinique.setNomCli(cursor.getString(1));
            clinique.setCodCli(cursor.getString(2));
            clinique.setActive(Boolean.valueOf(cursor.getString(3)));
            clinique.setUrlCli(cursor.getString(4));
            clinique.setUrlLocalCli(cursor.getString(5));
        }
        cursor.close();
        close();
        return clinique;
    }

    public Clinique getCliniqueActive() {
        Clinique clinique = new Clinique();
        open();
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + KEY_ISACTIVE + " = ?",
                new String[]{"true"});
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            clinique.setIdCli(Integer.parseInt(cursor.getString(0)));
            clinique.setNomCli(cursor.getString(1));
            clinique.setCodCli(cursor.getString(2));
            clinique.setActive(Boolean.valueOf(cursor.getString(3)));
            clinique.setUrlCli(cursor.getString(4));
            clinique.setUrlLocalCli(cursor.getString(5));
        }
        cursor.close();
        close();
        return clinique;
    }

    public Boolean updateClinique(Clinique clinique) {
        try {
            open();
            ContentValues value = new ContentValues();
            value.put(KEY_IDCLI, clinique.getIdCli());
            value.put(KEY_NOMCLI, clinique.getNomCli());
            value.put(KEY_CODCLI, clinique.getCodCli());
            value.put(KEY_ISACTIVE, String.valueOf(clinique.getActive().toString()));
            value.put(KEY_URLCLI, clinique.getUrlCli());
            value.put(KEY_URLLOCALCLI, clinique.getUrlLocalCli());
            if (mDb.update(TABLE_NAME, value, KEY_CODCLI + " = ?",
                    new String[]{String.valueOf(clinique.getCodCli())}) != 1)
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

    public Boolean updateCliniqueDesactiveAll(ArrayList<Clinique> cliniquesList) {
        try {
            open();
            mDb.beginTransaction();
            ContentValues value = new ContentValues();
            for (Iterator<Clinique> i = cliniquesList.iterator(); i.hasNext(); ) {
                Clinique clinique = i.next();
                value.put(KEY_IDCLI, clinique.getIdCli());
                value.put(KEY_NOMCLI, clinique.getNomCli());
                value.put(KEY_CODCLI, clinique.getCodCli());
                value.put(KEY_ISACTIVE, String.valueOf("false"));
                value.put(KEY_URLCLI, clinique.getUrlCli());
                value.put(KEY_URLLOCALCLI, clinique.getUrlLocalCli());
                if (mDb.update(TABLE_NAME, value, KEY_CODCLI + " = ?",
                        new String[]{String.valueOf(clinique.getCodCli())}) != 1)
                // return 1 => true
                {
                    mDb.endTransaction();
                    close();
                    return false;
                }
                value.clear();
            }
            mDb.setTransactionSuccessful();
            mDb.endTransaction();
            close();
        } catch (Exception e) {
            mDb.endTransaction();
            close();
            return false;
        }
        return true;

    }

    public Boolean deleteCliniqueByCodeCli(String codCli) {
        try {
            open();
            if (mDb.delete(TABLE_NAME, KEY_CODCLI + " = ?", new String[]{String.valueOf(codCli)}) == 0) {
                close();
                return false;
            }
            close();
            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deleteAllClinique() {
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
