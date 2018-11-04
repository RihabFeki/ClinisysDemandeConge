package com.csys.clinisys.demandeconge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.adapter.CliniqueAdapter;
import com.csys.clinisys.demandeconge.dao.CliniqueDAO;
import com.csys.clinisys.demandeconge.dao.UserDAO;
import com.csys.clinisys.demandeconge.helper.GetListClinique;
import com.csys.clinisys.demandeconge.helper.OtherFunction;
import com.csys.clinisys.demandeconge.model.Clinique;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvClinique;
    CliniqueAdapter cliniqueAdapter;
    ArrayList<Clinique> cliniquesList = new ArrayList<>();
    UserDAO userDAO;
    CliniqueDAO cliniqueDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        lvClinique = (ListView) findViewById(R.id.lvClinique);
        OtherFunction.strictMode();

        cliniqueDAO = new CliniqueDAO(getBaseContext());
         userDAO = new UserDAO(getBaseContext());

        if (cliniqueDAO.getCliniqueActive().getActive()) {
            if (userDAO.getUserActive().getActive()) {
                Intent intent = new Intent(getBaseContext(), ListeDemandeCongeActivity.class);
                startActivity(intent);
                this.finish();
            } else {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.putExtra("Clinique", cliniqueDAO.getCliniqueActive());
                startActivity(intent);
                this.finish();
            }
        } else {
            cliniquesList = GetListClinique.getListCliniqueStatique();
            //  DossierSoinWSServiceAndroid.Connection();
            // cliniquesList = DossierSoinWSServiceAndroid.ListCliniqueForAndroidByModule(Config.MODULE);
            cliniqueDAO.deleteAllClinique();
            cliniqueDAO.addListClinique(cliniquesList);

        }
        cliniqueAdapter = new CliniqueAdapter(getBaseContext(), R.layout.list_clinique, cliniquesList);
        lvClinique.setAdapter(cliniqueAdapter);


        lvClinique.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.putExtra("Clinique", cliniquesList.get(position));
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}
