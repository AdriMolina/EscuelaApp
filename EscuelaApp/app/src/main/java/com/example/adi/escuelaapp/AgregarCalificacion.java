package com.example.adi.escuelaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adi.escuelaapp.Adapters.SpinnerMateriasAdapter;
import com.example.adi.escuelaapp.Adapters.SpinnerUsuariosAdapter;
import com.example.adi.escuelaapp.DAO.DAO;
import com.example.adi.escuelaapp.DAO.EvaluacionDAO;
import com.example.adi.escuelaapp.DAO.MateriaDAO;
import com.example.adi.escuelaapp.DAO.UsuarioDAO;
import com.example.adi.escuelaapp.Entidades.Materia;
import com.example.adi.escuelaapp.Entidades.Usuario;

import java.util.List;

public class AgregarCalificacion extends AppCompatActivity {

    EditText edtCalificacion;
    Spinner spUsuarios,spMaterias;
    Button btnAgregar;
    private int materiaID;
    private int usuarioID;
    SpinnerUsuariosAdapter spinnerAdapter;
    SpinnerMateriasAdapter spinnerMateriasAdapter;
    UsuarioDAO usuarioDAO;
    MateriaDAO materiaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_calificacion);

        edtCalificacion = (EditText)findViewById(R.id.edtCalificacion);
        spMaterias = (Spinner)findViewById(R.id.spinnerMaterias);
        spUsuarios = (Spinner)findViewById(R.id.spinnerUsuarios);
        btnAgregar = (Button)findViewById(R.id.btnAgregarCalificacion);
        usuarioDAO = UsuarioDAO.getInstance();
        materiaDAO = MateriaDAO.getInstance();

        spMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                materiaID = (int)spinnerMateriasAdapter.getItemId(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usuarioID = (int)spinnerAdapter.getItemId(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //LLENAMOS EL SPINNER DE LOS USUARIOS
        usuarioDAO.getlistaUsuarios(this, new DAO.OnResultadoListaConsulta<Usuario>() {
            @Override
            public void consultaSuccess(List<Usuario> t) {
                if (t != null){
                    Toast.makeText(AgregarCalificacion.this,String.valueOf(t.size()), Toast.LENGTH_SHORT).show();
                    spinnerAdapter = new SpinnerUsuariosAdapter(t,AgregarCalificacion.this);
                    spUsuarios.setAdapter(spinnerAdapter);
                }else{
                    Toast.makeText(AgregarCalificacion.this, "La lista esta vacia", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void consultaFailed(String error, int codigo) {
            }
        });
        //LLENAMOS EL SPINNER MATERIAS
        materiaDAO.getListaMaterias(AgregarCalificacion.this, new DAO.OnResultadoListaConsulta<Materia>() {
            @Override
            public void consultaSuccess(List<Materia> t) {
                if (t != null){
                    Toast.makeText(AgregarCalificacion.this,String.valueOf(t.size()), Toast.LENGTH_SHORT).show();
                    spinnerMateriasAdapter = new SpinnerMateriasAdapter(t,AgregarCalificacion.this);
                    spMaterias.setAdapter(spinnerMateriasAdapter);
                }else{
                    Toast.makeText(AgregarCalificacion.this, "La lista esta vacia", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void consultaFailed(String error, int codigo) {

            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtCalificacion.getText().length()>0){
                    Toast.makeText(AgregarCalificacion.this,String.valueOf(materiaID)+" "+String.valueOf(usuarioID), Toast.LENGTH_SHORT).show();
                    EvaluacionDAO evaluacionDAO = EvaluacionDAO.getInstance();
                    evaluacionDAO.registrarCalificacion(AgregarCalificacion.this, Double.parseDouble(edtCalificacion.getText().toString()), materiaID, usuarioID, new DAO.OnResultadoConsulta() {
                        @Override
                        public void consultaSuccess(Object o) {
                            Toast.makeText(AgregarCalificacion.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                            edtCalificacion.setText("");
                        }

                        @Override
                        public void consultaFailed(String error, int codigo) {
                            Toast.makeText(AgregarCalificacion.this, "No se pudo insertar", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(AgregarCalificacion.this, "Inserte una calificacion", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
