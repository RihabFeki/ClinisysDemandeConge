package com.csys.clinisys.demandeconge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.adapter.InterimaireAdapter;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.model.Employee;
import com.csys.clinisys.demandeconge.model.User;
import com.csys.clinisys.demandeconge.webservice.DemandeCongeWSService;

import java.util.ArrayList;
import java.util.List;

public class ListeDesInterimairesActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private List<Employee> employeeList = new ArrayList<>();

    private RecyclerView recyclerView;
    private InterimaireAdapter mAdapter;
    Clinique clinique = new Clinique();
    CliniqueDAO cliniqueDAO;
    User user = new User();
    UserDAO userDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_des_interimaires);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        cliniqueDAO = new CliniqueDAO(getBaseContext());
        clinique=cliniqueDAO.getCliniqueActive();
        userDAO = new UserDAO(getBaseContext());
        user=userDAO.getUserActive();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());
        employeeList =DemandeCongeWSService.getlistEmployerByMatriculeMaster(user.getMatricule().toString());
        mAdapter = new InterimaireAdapter(employeeList,ListeDesInterimairesActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        DemandeCongeWSService.Connection(clinique.getUrlLocalCli());










    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu_search, menu);
        return true;
    }
}
