package com.example.adi.escuelaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adi.escuelaapp.DAO.DAO;
import com.example.adi.escuelaapp.DAO.UsuarioDAO;

public class AgregarAlumno extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_alumno);

        final EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        final EditText edtNick = (EditText)findViewById(R.id.edtNick);
        final EditText edtPass = (EditText)findViewById(R.id.edtPass);
        Button btRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNombre.getText().length() ==0 || edtNick.getText().length() == 0 || edtPass.getText().length() == 0){
                    Toast.makeText(AgregarAlumno.this, "Llene los campos", Toast.LENGTH_SHORT).show();
                }else {
                    UsuarioDAO dao = UsuarioDAO.getInstance();
                    dao.getRegistrarUsuario(AgregarAlumno.this, edtNombre.getText().toString(), edtNick.getText().toString(), edtPass.getText().toString(), new DAO.OnResultadoConsulta() {
                        @Override
                        public void consultaSuccess(Object o) {
                            Toast.makeText(AgregarAlumno.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void consultaFailed(String error, int codigo) {
                            Toast.makeText(AgregarAlumno.this, error+" "+codigo, Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
}
