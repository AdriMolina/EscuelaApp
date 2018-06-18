package com.example.adi.escuelaapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.adi.escuelaapp.Entidades.Materia;
import com.example.adi.escuelaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adi on 16/06/2018.
 */

public class SpinnerMateriasAdapter extends BaseAdapter {

    List<Materia>list = new ArrayList<>();
    Context context ;

    public SpinnerMateriasAdapter(List<Materia> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Materia getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View vista = view;
        if (view == null)
            vista = inflater.inflate(R.layout.item_spinner, null);

        TextView txtTexto = (TextView)vista.findViewById(R.id.txtxDato);
        txtTexto.setText(getItem(i).getNombre());

        return vista;
    }
}
