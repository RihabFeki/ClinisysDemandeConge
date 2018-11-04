package com.csys.clinisys.demandeconge.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.helper.OtherFunction;
import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.model.User;
import com.csys.clinisys.demandeconge.webservice.DemandeCongeWSService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.adapter.ListCongeAdapter;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.helper.OtherFunction;
import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.model.DemandeConge;
import com.csys.clinisys.demandeconge.model.Employer;
import com.csys.clinisys.demandeconge.model.User;
import com.csys.clinisys.demandeconge.webservice.DemandeCongeWSService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.csys.clinisys.demandeconge.R.id.edtTextNombre;


public class ListeDemandeCongeFragment extends Fragment {
    private Button popup;
    private Calendar calendarDD;
    private Calendar calendarDF;
    private EditText edtTxtdateDu;
    private EditText edtTxtdateAu;
    DatePickerDialog datePickerDialog;
    private String dateA,dateD;



    private RecyclerView recyclerView;
    private ListCongeAdapter listCongeAdapter;
    private ArrayList<DemandeConge> list = new ArrayList<>();




    private Clinique clinique = new Clinique();
    private User user = new User();
    Employer employer = new Employer();


    CliniqueDAO cliniqueDAO;
    UserDAO userDAO;

    public ListeDemandeCongeFragment() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_liste_demande_conge, container, false);
        popup = (Button) rootView.findViewById(R.id.button);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        OtherFunction.strictMode();
        userDAO = new UserDAO(getActivity());
        cliniqueDAO = new CliniqueDAO(getActivity());
        clinique = cliniqueDAO.getCliniqueActive();
        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());




        list = DemandeCongeWSService.getlistDemandeCongeByMatriculeAndAccord("0916", "R", "", "", "", "", false);



        listCongeAdapter = new ListCongeAdapter(list, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listCongeAdapter);







        popup.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(), popup);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu, popupMenu.getMenu());
                popupMenu.show();


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getTitle().toString()) {
                            case "Tous":
                                list = DemandeCongeWSService.getlistDemandeCongeByMatriculeAndAccord("0916", "R", "", "", "", "", false);
                                listCongeAdapter = new ListCongeAdapter(list, getActivity());
                                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(listCongeAdapter);

                                break;

                            case "Demande Accordée": //2
                                filtrer("2");

                                break;
                            case "Demande Refusée": //3
                            {
                                filtrer("3");}
                            break;
                            case "Demande en cours"://0
                            {
                                filtrer("0");}
                            break;
                            case "Demande en attente"://1
                            {
                                filtrer("1");}
                            break;
                            case "Demande Annulée"://4
                                filtrer("4");
                                break;

                        }
                        return true;
                    }
                });



            }
        });



        edtTxtdateDu = (EditText) rootView.findViewById(R.id.edtTextDateDu);
        calendarDD = Calendar.getInstance();


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
                // TODO Auto-generated method stub
                dateD=edtTxtdateDu.getText().toString();


            }
        });












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
        edtTxtdateAu = (EditText) rootView.findViewById(R.id.edtTextDateAu);

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
                // TODO Auto-generated method stub
                if ((!edtTxtdateAu.getText().toString().equals(""))&&(!edtTxtdateDu.getText().toString().equals(""))){


                    dateA=edtTxtdateAu.getText().toString();
                    ListCongeAdapter listCongeAdapterD;
                    ArrayList<DemandeConge> listFiltredByDate;
                    listFiltredByDate=DemandeCongeWSService.getlistDemandeCongeByMatriculeAndAccord("0916","R","","",dateD,dateA,false);
                    listCongeAdapterD = new ListCongeAdapter(listFiltredByDate, getActivity());
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(listCongeAdapterD);                }
            }
        });








        edtTxtdateAu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {


                    //ACTION UP
                    getDateAu();

                }

                return false;
            }
        });





        // Inflate the layout for this fragment
        return rootView;
    }









    public ArrayList<DemandeConge> getListFiltredByEtat(ArrayList<DemandeConge> list, String etat) {
        ArrayList<DemandeConge> listFiltred = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            if (etat.equalsIgnoreCase(list.get(i).getEtat()))
                listFiltred.add(list.get(i));

        }

        return listFiltred;
    }



    public void filtrer(String x){


        ArrayList<DemandeConge> listFiltred=getListFiltredByEtat(list,x);
        listCongeAdapter = new ListCongeAdapter(listFiltred, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listCongeAdapter);
    }


    public void filterByDate(String dateD,String dateA){
        ListCongeAdapter listCongeAdapterD;

        ArrayList<DemandeConge> listFiltredByDate;
        listFiltredByDate=DemandeCongeWSService.getlistDemandeCongeByMatriculeAndAccord("0916","R","","",dateD,dateA,false);
        listCongeAdapterD = new ListCongeAdapter(listFiltredByDate, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(listCongeAdapterD);

    }


    public ArrayList<DemandeConge> getList() {
        return list;
    }

    public void getDateDu() {
        datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth+"/" + (monthOfYear+1)+"/"+year;

                edtTxtdateDu.setText(date);
            }
        }, calendarDD.get(Calendar.YEAR), calendarDD.get(Calendar.MONTH), calendarDD.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setOkText("Enregistrer");
        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(false);
        datePickerDialog.setAccentColor(Color.parseColor("#012d79"));
        datePickerDialog.setTitle("Choisir Date Début");
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");

    }


    public void getDateAu() {

        datePickerDialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth+"/" + (monthOfYear+1)+"/"+year;
                edtTxtdateAu.setText(date);
            }
        }, calendarDF.get(Calendar.YEAR), calendarDF.get(Calendar.MONTH), calendarDF.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setOkText("Enregistrer");

        datePickerDialog.setThemeDark(false);
        datePickerDialog.showYearPickerFirst(false);

        datePickerDialog.setAccentColor(Color.parseColor("#012d79"));
        datePickerDialog.setTitle("Choisir Date Fin");
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
