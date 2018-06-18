package com.example.adi.escuelaapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adi.escuelaapp.Entidades.Materia;
import com.example.adi.escuelaapp.R;

import java.util.List;

/**
 * Created by Adi on 16/06/2018.
 */

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MateriaHolder>{
    private List<Materia> lista;
    private Context context;

    public MateriaAdapter(List<Materia> lista) {
        this.lista = lista;
    }


    @Override
    public MateriaAdapter.MateriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_materia,parent,false);

        return new MateriaHolder(view);
    }

    @Override
    public void onBindViewHolder(MateriaAdapter.MateriaHolder holder, int position) {
        holder.txtID.setText(String.valueOf(lista.get(position).getId()));
        holder.txtClave.setText(lista.get(position).getClave());
        holder.txtNombre.setText(lista.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class MateriaHolder extends RecyclerView.ViewHolder{
        protected TextView txtID,txtClave,txtNombre;

        public MateriaHolder(View itemView) {
            super(itemView);
            txtID = (TextView)itemView.findViewById(R.id.txtID);
            txtClave = (TextView)itemView.findViewById(R.id.txtClave);
            txtNombre = (TextView)itemView.findViewById(R.id.txtNombre);
        }
    }
}
