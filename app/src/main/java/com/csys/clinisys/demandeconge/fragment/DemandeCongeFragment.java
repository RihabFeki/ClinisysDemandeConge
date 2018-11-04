package com.csys.clinisys.demandeconge.fragment;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import com.csys.clinisys.demandeconge.R.layout;
import com.csys.clinisys.demandeconge.activity.ListeDemandeCongeActivity;
import com.csys.clinisys.demandeconge.activity.ListeDesInterimairesActivity;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.helper.Alerte;
import com.csys.clinisys.demandeconge.helper.CongeFunction;
import com.csys.clinisys.demandeconge.helper.DateFunction;
import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.model.Employee;
import com.csys.clinisys.demandeconge.model.JourFerier;
import com.csys.clinisys.demandeconge.model.TypeConge;
import com.csys.clinisys.demandeconge.model.User;

import com.csys.clinisys.demandeconge.webservice.DemandeCongeWSService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import static com.csys.clinisys.demandeconge.R.*;


public class DemandeCongeFragment extends Fragment {
    private Calendar calendarDD;
    private Calendar calendarDF;
    private Calendar calendarHD;
    private Calendar calendarHF;
    private String MatriculeInterim;
    private String CodeTypeConge;
    private String MatriculeConnected;

    private EditText editTxtHD;
    private EditText editTxtHF;

    private EditText edtTxtdateDu;
    private EditText edtTxtdateAu;
    private EditText edtTextNombre;
    private EditText edtTxtNom;
    private EditText edtTxtPrenom;
    private EditText edtTxtMatricule;
    private EditText edtTextInterim;
    private EditText edtTextMotif;
    private EditText edtTextCoordonnee;
    private EditText edtTextObservation;


    private TextInputLayout inputEdtTxtSoldeConge;


    private RadioGroup radioGroup;
    private CheckBox checkBoxDJ;
    private CheckBox checkBoxH;
    private Spinner spinnerTypeConge;
    private EditText edtTxtSoldeConge;
    private EditText edtTextNbreJours;
    private TextView txtViewJour1;
    private LinearLayout LigneDemiJournnee;
    private LinearLayout LigneHeure;
    private Button buttonAjouter;
    private Button buttonAnnuler;
    private TextInputLayout DateD;
    private TextInputLayout DateF;
    private TextInputLayout Motif;
    private TextInputLayout Coordonnee;



    private boolean is24HourMode = true;
    Employee employee = new Employee();
    User user = new User();
    UserDAO userDAO;
    Clinique clinique = new Clinique();
    CliniqueDAO cliniqueDAO;


    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    public DemandeCongeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDAO = new UserDAO(getActivity());
        user = userDAO.getUserActive();
        cliniqueDAO = new CliniqueDAO(getActivity());
        clinique = cliniqueDAO.getCliniqueActive();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(layout.fragment_demande_conge, container, false);


        radioGroup = (RadioGroup) rootView.findViewById(id.radioDate);
        radioGroup.setVisibility(rootView.INVISIBLE);
        LigneDemiJournnee = (LinearLayout) rootView.findViewById(id.LigneDemiJournnee);
        edtTextInterim = (EditText) rootView.findViewById(id.edtTextInterim);
        buttonAjouter = (Button) rootView.findViewById(id.buttonAjouter);
        buttonAnnuler = (Button) rootView.findViewById(id.buttonAnnuler);
        edtTextMotif = (EditText) rootView.findViewById(id.edtTextMotif);
        edtTextCoordonnee = (EditText) rootView.findViewById(id.edtTextCoordonnee);
        edtTextObservation = (EditText) rootView.findViewById(id.edtTextObservation);
     /*  final TextInputLayout DateD = (TextInputLayout) rootView.findViewById(id.DateD);
        final TextInputLayout DateF = (TextInputLayout) rootView.findViewById(id.DateF);
        final TextInputLayout Motif = (TextInputLayout) rootView.findViewById(id.Motif);
        final TextInputLayout Coordonnee = (TextInputLayout) rootView.findViewById(id.coordonnee);*/


        inputEdtTxtSoldeConge = (TextInputLayout) rootView.findViewById(id.inputEdtTxtSoldeConge);
        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListeDemandeCongeActivity.class);
                startActivity(intent);
            }
        });
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int etat;
                String resp,matricule,dateDu,dateAu,motif,coordonnee,heureD,heureF,userName,observation;
                String nombreJ;
                Boolean isChecked;
/*

                if(edtTxtdateDu.getText().toString().equals("")){
                    DateD.setError("veuillez saisir la date début");
                }else
                {

                    DateD.setError("");

                }
                if(edtTxtdateAu.getText().toString().equals("")){
                    DateF.setError("veuillez saisir la date Fin");
                }else
                {

                    DateF.setError("");

                }
                if(edtTextMotif.getText().toString().equals("")){
                    Motif.setError("veuillez mentionner le motif");
                }else
                {

                    Motif.setError("");

                }
                if(edtTextCoordonnee.getText().toString().equals("")){
                    Coordonnee.setError("veuillez taper votre n° Télf");
                }else
                {

                    Coordonnee.setError("");

                }
*/



                DemandeCongeWSService.Connection(clinique.getUrlLocalCli());

                if (user.getMatriculeresp().equals("")) {
                    etat = 0;
                    resp = "non";
                } else {
                    etat = 1;
                    resp = "oui";
                }


                DemandeCongeWSService.Connection(clinique.getUrlLocalCli());

                matricule=user.getMatricule();
                dateDu=edtTxtdateDu.getText().toString();
                dateAu=edtTxtdateAu.getText().toString();

                if (edtTextNombre.getText().toString().equals(""))
                {
                    nombreJ="0";
                }else
                {
                    nombreJ=edtTextNombre.getText().toString();
                }
                observation =edtTextObservation.getText().toString();
                motif=edtTextMotif.getText().toString();
                coordonnee=edtTextCoordonnee.getText().toString();
                isChecked=checkBoxH.isChecked();
                heureD=editTxtHD.getText().toString();
                heureF=editTxtHF.getText().toString();
                userName=user.getUserName().toString();



              if(  DemandeCongeWSService.addDemandeConge(matricule,dateDu ,dateAu,nombreJ,
                        CodeTypeConge,observation, MatriculeInterim, coordonnee, etat, "", "", resp, isChecked,
                      heureD, heureF, "0",userName,motif,matricule))
                {


                Intent intent = new Intent(getActivity(), ListeDemandeCongeActivity.class);
                startActivity(intent);}

else{
                  //do something

                  Alerte.getAlerte(getActivity(),false,"veuillez remplir les champs obligatoires ");

              }






            }
        });


        LigneHeure = (LinearLayout) rootView.findViewById(id.LigneHeure);
        CongeFunction.hideView(LigneHeure);
        edtTxtSoldeConge = (EditText) rootView.findViewById(id.edtTxtSoldeConge);
        edtTextNbreJours = (EditText) rootView.findViewById(id.edtTextNbreJours);
        txtViewJour1 = (TextView) rootView.findViewById(id.txtViewJour1);

        checkBoxDJ = (CheckBox) rootView.findViewById(id.checkBoxDJ);

        checkBoxDJ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    radioGroup.setVisibility(View.VISIBLE);
                    edtTxtdateAu.setText("");
                    edtTextNombre.setText("");
                    edtTxtdateAu.setEnabled(false);


                } else {
                    radioGroup.setVisibility(View.INVISIBLE);
                    edtTxtdateAu.setEnabled(true);
                }
            }
        });
      checkBoxH = (CheckBox) rootView.findViewById(id.checkBoxH);/*
        checkBoxH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {

                    edtTxtdateAu.setText("");
                    edtTxtdateAu.setEnabled(false);

                } else {

                    edtTxtdateAu.setEnabled(true);
                }
            }
        });
*/
        edtTxtMatricule = (EditText) rootView.findViewById(id.edtTextMatricule);
        edtTxtMatricule.setText(user.getMatricule());
        edtTxtNom = (EditText) rootView.findViewById(id.editTxtNom);
        edtTxtNom.setText(user.getNomemp());
        edtTxtPrenom = (EditText) rootView.findViewById(id.edtTxtPrenom);
        edtTxtPrenom.setText(user.getPrenemp());
        edtTxtMatricule.setEnabled(true);
        edtTxtMatricule.setClickable(false);
        edtTxtMatricule.setFocusable(false);
        edtTxtNom.setEnabled(true);
        edtTxtNom.setClickable(false);
        edtTxtNom.setFocusable(false);
        edtTxtPrenom.setEnabled(true);
        edtTxtPrenom.setClickable(false);
        edtTxtPrenom.setFocusable(false);

        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());

        edtTextNbreJours.setText(DemandeCongeWSService.getsoldeDemande(user.getMatricule()).toString());

        edtTextNbreJours.setEnabled(true);
        edtTextNbreJours.setClickable(false);
        edtTextNbreJours.setFocusable(false);


        calendarDD = Calendar.getInstance();
        edtTxtdateDu = (EditText) rootView.findViewById(id.edtTextDateDu);

        edtTxtdateDu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {  //ACTION UP
                    getDateDu();
                }

                return false;
            }
        });
        calendarDF = Calendar.getInstance();
        edtTxtdateAu = (EditText) rootView.findViewById(id.edtTextDateAu);
        edtTextNombre = (EditText) rootView.findViewById(id.edtTextNombre);
        edtTxtdateAu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {  //ACTION UP
                    getDateAu();


                }

                return false;
            }
        });
        edtTextInterim.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {  //ACTION UP

                    Intent intent = new Intent(getActivity(), ListeDesInterimairesActivity.class);
                    startActivityForResult(intent, 1);
                }

                return false;
            }
        });


        calendarHD = Calendar.getInstance();
        editTxtHD = (EditText) rootView.findViewById(id.editTxtHD);
        editTxtHD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {  //ACTION UP
                    getHeureD();
                }

                return false;
            }
        });
        calendarHF = Calendar.getInstance();
        editTxtHF = (EditText) rootView.findViewById(id.editTxtHF);
        editTxtHF.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {  //ACTION UP
                    getHeureF();
                }

                return false;
            }
        });


        spinnerTypeConge = (Spinner) rootView.findViewById(id.spinnerTypeConge);
        addItemsOnSpinner();
        spinnerTypeConge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
                ArrayList<TypeConge> typeCongesList = DemandeCongeWSService.getallTypeConge();
                CodeTypeConge = typeCongesList.get(position).getCodeRepos().toString();


                if (position == 0) {

                    DemandeCongeWSService.Connection(clinique.getUrlLocalCli());

                    edtTxtSoldeConge.setText(DemandeCongeWSService.getSoldConge(user.getMatricule()).toString());
                    edtTxtSoldeConge.setEnabled(true);
                    edtTxtSoldeConge.setClickable(false);
                    edtTxtSoldeConge.setFocusable(false);
                    CongeFunction.hideView(LigneHeure);
                    CongeFunction.showView(LigneDemiJournnee);
                    edtTxtdateAu.setEnabled(true);
                    inputEdtTxtSoldeConge.setVisibility(rootView.VISIBLE);
                    txtViewJour1.setVisibility(rootView.VISIBLE);
                    edtTxtSoldeConge.setVisibility(rootView.VISIBLE);


                } else if (position == 6) {

                    DemandeCongeWSService.Connection(clinique.getUrlLocalCli());

                    edtTxtSoldeConge.setText(DemandeCongeWSService.getSoldCongeRecuperation(user.getMatricule()).toString());
                    edtTxtSoldeConge.setEnabled(true);
                    edtTxtSoldeConge.setClickable(false);
                    edtTxtSoldeConge.setFocusable(false);
                    CongeFunction.hideView(LigneDemiJournnee);
                    CongeFunction.hideView(LigneHeure);
                    inputEdtTxtSoldeConge.setVisibility(rootView.VISIBLE);
                    txtViewJour1.setVisibility(rootView.VISIBLE);
                    edtTxtSoldeConge.setVisibility(rootView.VISIBLE);
                    edtTxtdateAu.setEnabled(true);


                } else if (position == 7) {

                    CongeFunction.showView(LigneHeure);

                    CongeFunction.hideView(LigneDemiJournnee);
                    /* edtTextNombre.setText("");
                    edtTextNombre.setEnabled(true);
                    edtTextNombre.setClickable(false);
                    edtTextNombre.setFocusable(false);*/

                    inputEdtTxtSoldeConge.setVisibility(rootView.INVISIBLE);
                    txtViewJour1.setVisibility(rootView.INVISIBLE);
                    edtTxtSoldeConge.setVisibility(rootView.INVISIBLE);
                    edtTxtdateAu.setText("");
                    edtTxtdateAu.setEnabled(false);
                } else

                {
                    edtTxtdateAu.setEnabled(true);
                    CongeFunction.hideView(LigneDemiJournnee);
                    CongeFunction.hideView(LigneHeure);
                    inputEdtTxtSoldeConge.setVisibility(rootView.INVISIBLE);
                    txtViewJour1.setVisibility(rootView.INVISIBLE);
                    edtTxtSoldeConge.setVisibility(rootView.INVISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }

        });


        edtTxtdateAu.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(! checkBoxDJ.isChecked()){
                // TODO Auto-generated method stub
                try {
                    int nbre;
                    ArrayList<JourFerier> jourFeriers = new ArrayList<JourFerier>();
                    if (!edtTxtdateDu.getText().toString().equals("")) {
                        nbre = CongeFunction.calculNbreJour(edtTxtdateDu.getText().toString(), edtTxtdateAu.getText().toString());
                        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
                        employee = DemandeCongeWSService.getEmployerByMatriculeMaster(user.getMatricule().toString());

                        if (((int) Double.parseDouble(employee.getNbrjour().toString()) == 26) || (((int) Double.parseDouble(employee.getNbrjour().toString()) == 30) && (nbre <= 6))) {


                            edtTextNombre.setText("" + nbre);
                            edtTextNombre.setEnabled(true);
                            edtTextNombre.setClickable(false);
                            edtTextNombre.setFocusable(false);
                        }

                        if ((((int) Double.parseDouble(employee.getNbrjour().toString()) == 30) && (nbre > 6))) {

                            int Dim = 0;

                            DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
                            jourFeriers = DemandeCongeWSService.findJouFeriesByDate(edtTxtdateDu.getText().toString(), edtTxtdateAu.getText().toString());

                            Dim = nbre / 7;
                            nbre = nbre - jourFeriers.size() - Dim;
                            edtTextNombre.setText("" + nbre);
                            edtTextNombre.setEnabled(true);
                            edtTextNombre.setClickable(false);
                            edtTextNombre.setFocusable(false);

                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }}


        });


        edtTxtdateDu.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(! checkBoxDJ.isChecked()){
                try {
                    // TODO Auto-generated method stub
                    int nbre;
                    ArrayList<JourFerier> jourFeriers = new ArrayList<JourFerier>();
                    if (!edtTxtdateAu.getText().toString().equals("")) {
                        nbre = CongeFunction.calculNbreJour(edtTxtdateDu.getText().toString(), edtTxtdateAu.getText().toString());
                        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
                        employee = DemandeCongeWSService.getEmployerByMatriculeMaster(user.getMatricule().toString());

                        if (((int) Double.parseDouble(employee.getNbrjour().toString()) == 26) || (((int) Double.parseDouble(employee.getNbrjour().toString()) == 30) && (nbre <= 6))) {


                            edtTextNombre.setText("" + nbre);
                            edtTextNombre.setEnabled(true);
                            edtTextNombre.setClickable(false);
                            edtTextNombre.setFocusable(false);
                        }

                        if ((((int) Double.parseDouble(employee.getNbrjour().toString()) == 30) && (nbre > 6))) {

                            int Dim = 0;

                            DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
                            jourFeriers = DemandeCongeWSService.findJouFeriesByDate(edtTxtdateDu.getText().toString(), edtTxtdateAu.getText().toString());

                            Dim = nbre / 7;
                            nbre = nbre - jourFeriers.size() - Dim;
                            edtTextNombre.setText("" + nbre);
                            edtTextNombre.setEnabled(true);
                            edtTextNombre.setClickable(false);
                            edtTextNombre.setFocusable(false);

                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }}


        });

        return rootView;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void getDateDu() {
        datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                calendarDD.set(year, monthOfYear, dayOfMonth);
                Date date = calendarDD.getTime();

                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = simpleDate.format(date);
                edtTxtdateDu.setText(formattedDate);

            }
        }, calendarDD.get(Calendar.YEAR), calendarDD.get(Calendar.MONTH), calendarDD.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setMinDate(Calendar.getInstance());
        datePickerDialog.setOkText("Enregistrer");

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(false);

        datePickerDialog.setAccentColor(Color.parseColor("#2770B0"));
        datePickerDialog.setTitle("Choisir Date Début");
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");

    }

    public void getDateAu() {

        datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                calendarDF.set(year, monthOfYear, dayOfMonth);
                Date date = calendarDF.getTime();
                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = simpleDate.format(date);
                edtTxtdateAu.setText(formattedDate);


            }
        }, calendarDF.get(Calendar.YEAR), calendarDF.get(Calendar.MONTH), calendarDF.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.setMinDate(calendarDD);
        datePickerDialog.setOkText("Enregistrer");

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(false);

        datePickerDialog.setAccentColor(Color.parseColor("#2770B0"));
        datePickerDialog.setTitle("Choisir Date Fin");
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");

    }


    public void addItemsOnSpinner() {
        try {
            DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
            ArrayList<TypeConge> typeCongesList = DemandeCongeWSService.getallTypeConge();
            ArrayList<String> CodeList = new ArrayList<String>();


            ArrayList<String> TypeList = new ArrayList<String>();


            for (int i = 0; i < typeCongesList.size(); i++) {
                TypeList.add(typeCongesList.get(i).getLibRepos());
            }

            for (int i = 0; i < CodeList.size(); i++) {
                CodeList.add(typeCongesList.get(i).getCodeRepos());
            }


            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, TypeList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTypeConge.setAdapter(dataAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getHeureD() {

        timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {


                calendarHD.set(calendarHD.HOUR_OF_DAY, hourOfDay);
                calendarHD.set(calendarHD.MINUTE, minute);
                calendarHD.set(calendarHD.SECOND, second);


                editTxtHD.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));


            }


        }, calendarHD.get(Calendar.HOUR_OF_DAY), calendarHD.get(Calendar.MINUTE), is24HourMode);

        timePickerDialog.setOkText("Enregistrer");

        timePickerDialog.setThemeDark(false);


        timePickerDialog.setAccentColor(Color.parseColor("#2770B0"));
        timePickerDialog.setTitle("Choisir Heure Début");
        timePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");

    }

    public void getHeureF() {

        timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                editTxtHF.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));

            }


        }, calendarHF.get(Calendar.HOUR_OF_DAY), calendarHF.get(Calendar.MINUTE), is24HourMode);

        timePickerDialog.setOkText("Enregistrer");
        timePickerDialog.setThemeDark(false);
        timePickerDialog.setMinTime(calendarHD.get(Calendar.HOUR_OF_DAY), calendarHD.get(Calendar.MINUTE), 0);


        timePickerDialog.setAccentColor(Color.parseColor("#2770B0"));
        timePickerDialog.setTitle("Choisir Heure Fin");
        timePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String Nom = data.getStringExtra("Nom");
                String Prenom = data.getStringExtra("Prenom");
                MatriculeInterim = data.getStringExtra("Matricule");

                edtTextInterim.setText(Prenom + "  " + Nom);

            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


}

