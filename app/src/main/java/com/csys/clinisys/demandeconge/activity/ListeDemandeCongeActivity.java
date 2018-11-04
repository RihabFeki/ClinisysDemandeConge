package com.csys.clinisys.demandeconge.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.Fragment;


import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.fragment.DemandeCongeFragment;
import com.csys.clinisys.demandeconge.fragment.FragmentDrawer;
import com.csys.clinisys.demandeconge.fragment.ListeDemandeCongeFragment;
import com.csys.clinisys.demandeconge.model.Clinique;
import com.csys.clinisys.demandeconge.model.User;

public class ListeDemandeCongeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    Clinique clinique = new Clinique();
    User user = new User();
    Intent intent = getIntent();

    CliniqueDAO cliniqueDAO;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demande_conge);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Liste des demandes à traiter");
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        cliniqueDAO = new CliniqueDAO(getBaseContext());
        userDAO = new UserDAO(getBaseContext());

        clinique = cliniqueDAO.getCliniqueActive();

        user = userDAO.getUserActive();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);


    }

    private void displayView(int position) {


        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new DemandeCongeFragment();
                title = getString(R.string.title_demande_conge);

                break;
            case 1:
                fragment = new ListeDemandeCongeFragment();
                title = getString(R.string.title_liste_demande_conge);
                break;

            case 2:
                userDAO.deleteUserByCodeCli(clinique.getCodCli());
                Intent login = new Intent(ListeDemandeCongeActivity.this, LoginActivity.class);
                login.putExtra("Clinique", clinique);
                startActivity(login);
                ListeDemandeCongeActivity.this.finish();
                break;
            default:
                break;

        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction = fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }


    }

    public void strictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_body);
        fragment.onActivityResult(requestCode, resultCode, data);
    }*/
}
