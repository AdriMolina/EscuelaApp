package com.example.adi.escuelaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adi.escuelaapp.DAO.DAO;
import com.example.adi.escuelaapp.DAO.MateriaDAO;

public class AgregarMateria extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_materia);

        final EditText edtClave = (EditText) findViewById(R.id.edtClave);
        final EditText edtNombre = (EditText)findViewById(R.id.edtNombre);
        Button btRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtClave.getText().length() ==0 || edtNombre.getText().length() == 0){
                    Toast.makeText(AgregarMateria.this, "Llene los campos", Toast.LENGTH_SHORT).show();
                }else {
                    MateriaDAO dao = MateriaDAO.getInstance();
                    dao.getRegistrarMaterias(AgregarMateria.this, edtClave.getText().toString(), edtNombre.getText().toString(), new DAO.OnResultadoConsulta() {
                        @Override
                        public void consultaSuccess(Object o) {
                            Toast.makeText(AgregarMateria.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void consultaFailed(String error, int codigo) {
                            Toast.makeText(AgregarMateria.this, error+" "+codigo, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }
}
