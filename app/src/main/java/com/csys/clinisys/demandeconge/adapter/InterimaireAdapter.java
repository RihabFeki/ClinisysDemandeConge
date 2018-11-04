package com.csys.clinisys.demandeconge.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.activity.ListeDesInterimairesActivity;
import com.csys.clinisys.demandeconge.model.Employee;

import java.util.List;

/**
 * Created by lenovo on 10/07/2017.
 */

public class InterimaireAdapter  extends RecyclerView.Adapter<InterimaireAdapter.MyViewHolder> {
    private List<Employee> interimairesList;
    private ListeDesInterimairesActivity listeDesInterimairesActivity;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView matemp , nomemp, libqual;



        public MyViewHolder(View view) {
            super(view);
            matemp = (TextView) view.findViewById(R.id.TxtViewMatricule);
            nomemp = (TextView) view.findViewById(R.id.TxtViewNomPrenom);

            libqual = (TextView)view.findViewById(R.id.TxtViewlibqual);
            view.setOnClickListener(this);





        }
        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
           Employee employee = interimairesList.get(position);


            Intent returnIntent = new Intent();
            returnIntent.putExtra("Nom", employee.getNomemp() );
            returnIntent.putExtra("Prenom", employee.getPrenemp() );
            returnIntent.putExtra("Matricule",employee.getMatemp());
            listeDesInterimairesActivity.setResult(Activity.RESULT_OK, returnIntent);
            listeDesInterimairesActivity.finish();

        }
    }


    public InterimaireAdapter(List<Employee> interimairesList, ListeDesInterimairesActivity listeDesInterimairesActivity) {
       this.listeDesInterimairesActivity = listeDesInterimairesActivity;
        this.interimairesList = interimairesList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.interimaire_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Employee employee = interimairesList.get(position);
        holder.matemp.setText(employee.getMatemp());
        holder.nomemp.setText(employee.getNomemp()+" " + employee.getPrenemp());

        holder.libqual.setText(employee.getQualification().getLibqual());
    }

    @Override
    public int getItemCount() {
        return interimairesList.size();
    }


}
