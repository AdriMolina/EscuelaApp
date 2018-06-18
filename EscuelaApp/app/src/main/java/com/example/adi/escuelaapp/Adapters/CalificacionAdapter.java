package com.example.adi.escuelaapp.Adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adi.escuelaapp.Entidades.Calificacion;
import com.example.adi.escuelaapp.R;

import java.util.List;

/**
 * Created by Adi on 16/06/2018.
 */

public class CalificacionAdapter extends RecyclerView.Adapter<CalificacionAdapter.CalificacionHolder>{

    private List<Calificacion> lista;
    private Context context;

    public CalificacionAdapter(List<Calificacion> lista) {
        this.lista = lista;
    }

    public static class CalificacionHolder extends  RecyclerView.ViewHolder{
        protected TextView txtID,txtClave,txtxNombre,txtCalificacion;
        protected CardView cardView;
        public CalificacionHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardv);
            txtID = (TextView)itemView.findViewById(R.id.txtIDCal);
            txtClave = (TextView)itemView.findViewById(R.id.txtClaveCal);
            txtxNombre = (TextView)itemView.findViewById(R.id.txtNombreCal);
            txtCalificacion = (TextView)itemView.findViewById(R.id.txtCalificacionCal);
        }

    }


    @Override
    public CalificacionAdapter.CalificacionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_evaluacion,parent,false);
        return new CalificacionHolder(view);

    }

    @Override
    public void onBindViewHolder(CalificacionAdapter.CalificacionHolder holder, final int position) {
        holder.txtID.setText(String.valueOf(lista.get(position).getId()));
        holder.txtCalificacion.setText(String.valueOf(lista.get(position).getCalificacion()));
        holder.txtClave.setText(lista.get(position).getMateria().getClave());
        holder.txtxNombre.setText(lista.get(position).getMateria().getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensaje ="";
                switch (lista.get(position).getMateria().getNombre()){
                    case "Java":
                        mensaje = "Es la clase de Java";
                        break;
                    case "C++":
                        mensaje = "Es la clase de C++";
                        break;
                    case "Android":
                        mensaje = "Es la clase de Android";
                        break;
                    case "PHP":
                        mensaje = "Es la clase de PHP";
                        break;

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Descripcion");
                builder.setMessage(mensaje);
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
