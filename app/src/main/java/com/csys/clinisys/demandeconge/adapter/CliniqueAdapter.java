package com.csys.clinisys.demandeconge.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csys.clinisys.demandeconge.R;
import com.csys.clinisys.demandeconge.model.Clinique;

import java.util.ArrayList;

public class CliniqueAdapter extends ArrayAdapter<Clinique> {
    ArrayList<Clinique> cliniqueList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    Context context;

    public CliniqueAdapter(Context context, int resource, ArrayList<Clinique> objects) {
        super(context, resource, objects);
        this.vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.Resource = resource;
        this.cliniqueList = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.txtNomCli = (TextView) v.findViewById(R.id.txtNomCli);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (cliniqueList.get(position).getNomCli().toString().equals("Autre Cliniques: ")) {
            holder.txtNomCli.setText(cliniqueList.get(position).getNomCli().toString());
            holder.txtNomCli.setTextColor(Color.parseColor("#3276b1"));
        } else {
            holder.txtNomCli.setText("● " + cliniqueList.get(position).getNomCli().toString());
            holder.txtNomCli.setTextColor(Color.parseColor("#000000"));
        }

        return v;
    }

    static class ViewHolder {
        public TextView txtNomCli;
    }

}