package com.csys.clinisys.demandeconge.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.model.DemandeConge;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fatma on 05/07/2017.
 */

public class ListCongeAdapter extends RecyclerView.Adapter<ListCongeAdapter.ViewHolder> {

    private ArrayList<DemandeConge> ListDemandeConge;
    private Context context;
    String Etat, Type;
    public ImageView icon_i;




    public ListCongeAdapter(ArrayList<DemandeConge> ListDemandeConge, Context context) {
        this.ListDemandeConge = ListDemandeConge;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DemandeConge listItem = ListDemandeConge.get(position);







        switch (listItem.getTypeConge()) {
            case "CA":
                Type = "Congé annuel";
                break;
            case "CE":
                Type = "Congé exceptionnel ";
                break;
            case "CM":
                Type = "Congé maladie";
                break;
            case "CT":
                Type = "Congé maternité";
                break;
            case "CG":
                Type = "Congé mariage";
                break;
            case "CS":
                Type = "Congé sans solde";
                break;
            case "RC":
                Type = "Récupération";
                break;
            case "A":
                Type = "Autorisation";
                break;
            default:
                Type = listItem.getTypeConge();


        }


        switch (listItem.getEtat()) {
            case "0": {

                Etat = "Demande en cours";
                holder.Etat.setTextColor(context.getResources().getColor(R.color.colorAccent));
                holder.icon_i.setImageResource(R.drawable.encours);





            }
            break;
            case "1": {
                Etat = "Demande en attente";
                holder.Etat.setTextColor(context.getResources().getColor(R.color.yellow));
                holder.icon_i.setImageResource(R.drawable.enattente);

            }


            break;
            case "3": {
                Etat = "Demande Refusée";
                holder.Etat.setTextColor(context.getResources().getColor(R.color.red));
                holder.icon_i.setImageResource(R.drawable.annuler);


            }
            break;
            case "2": {
                Etat = "Demande Accordée";
                holder.Etat.setTextColor(context.getResources().getColor(R.color.green));
                holder.icon_i.setImageResource(R.drawable.valider);


            }
            break;
            case "4": {
                Etat = "Demande Annulée";
                holder.Etat.setTextColor(context.getResources().getColor(R.color.colorIndigo));
                holder.icon_i.setImageResource(R.drawable.annulere);


            }
            break;
            default:
                Etat = listItem.getEtat();
        }
        if((!listItem.getHeureDeb().equals(""))&&(!listItem.getHeureFin().equals("")))
        {



            String Au =convertirDate(listItem.getAu());

            holder.Au.setText(Au);
            String Du =convertirDate(listItem.getDu());
            holder.Du.setText(Du);
            holder.Etat.setText(Etat);
            String Nbrjouraccorder=convertir(listItem.getNbrjouraccorder());
            holder.HD.setText(listItem.getHeureDeb());
            holder.HF.setText(listItem.getHeureFin());
            holder.HDT.setVisibility(View.GONE);
            holder.HFT.setVisibility(View.GONE);


            holder.NbreJourAcoordée.setText(Nbrjouraccorder);
            String s=convertir(listItem.getNbrjour());
            holder.typedeconge.setText(Type);
            holder.NbrJ.setText(s);
        }
        else {


            String Au = convertirDate(listItem.getAu());

            holder.Au.setText(Au);
            String Du = convertirDate(listItem.getDu());
            holder.Du.setText(Du);
            holder.Etat.setText(Etat);
            String Nbrjouraccorder = convertir(listItem.getNbrjouraccorder());

            holder.NbreJourAcoordée.setText(Nbrjouraccorder);
            String s = convertir(listItem.getNbrjour());
            holder.typedeconge.setText(Type);
            holder.NbrJ.setText(s);

        }
    }

    public String convertirDate( String strDateD)
    {

        SimpleDateFormat formatterD = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStrD = null;
        try {

            dateStrD = formatterD.parse(strDateD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDateD = formatterD.format(dateStrD);
        System.out.println("yyyy-MM-dd date is ==>"+formattedDateD);
        Date date1D = null;
        try {
            date1D = formatterD.parse(formattedDateD);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        formatterD = new SimpleDateFormat("dd/MM/yyyy");
        formattedDateD = formatterD.format(date1D);
        System.out.println("dd/MM/yyyy date is ==>"+formattedDateD);
        return  formattedDateD;

    }


    public String convertir(String nbre){
        int i = (int) Float.parseFloat(nbre);
        String strI = Integer.toString(i);
        return strI;

    }

    @Override
    public int getItemCount() {
        return ListDemandeConge.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Du, Au, Etat, NbreJourAcoordée, typedeconge, NbrJ,HD,HF,HFT,HDT;
        public ImageView icon_i;


        public ViewHolder(View itemView) {
            super(itemView);
            Du = (TextView) itemView.findViewById(R.id.T1);
            Au = (TextView) itemView.findViewById(R.id.T2);
            NbreJourAcoordée = (TextView) itemView.findViewById(R.id.T4);
            typedeconge = (TextView) itemView.findViewById(R.id.T5);
            Etat = (TextView) itemView.findViewById(R.id.T6);
            NbrJ = (TextView) itemView.findViewById(R.id.T7);
            icon_i= (ImageView) itemView.findViewById(R.id.icon_id);
            HD= (TextView) itemView.findViewById(R.id.T8);
            HF= (TextView) itemView.findViewById(R.id.T9);
            HFT= (TextView) itemView.findViewById(R.id.HFT);
            HDT= (TextView) itemView.findViewById(R.id.HDT);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

        }
    }
    public void aficherHeure(String HD,String HF)
    {



    }



}


