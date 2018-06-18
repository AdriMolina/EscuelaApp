package com.example.adi.escuelaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adi.escuelaapp.DAO.DAO;
import com.example.adi.escuelaapp.DAO.UsuarioDAO;
import com.example.adi.escuelaapp.Entidades.Usuario;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText edtNick = (EditText)findViewById(R.id.txtUsuario);
        final EditText edtPass = (EditText)findViewById(R.id.txtPass);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNick.getText().length() == 0 || edtPass.getText().length() == 0){
                    Toast.makeText(LoginActivity.this, "Llene los campos", Toast.LENGTH_SHORT).show();
                }else{
                    UsuarioDAO dao = UsuarioDAO.getInstance();
                    dao.obtenerDatosLogin(LoginActivity.this, edtNick.getText().toString(), edtPass.getText().toString(), new DAO.OnResultadoConsulta<Usuario>() {
                        @Override
                        public void consultaSuccess(Usuario s) {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("USUARIO",s);
                            startActivity(intent);
                            finish();
                        }
                        @Override
                        public void consultaFailed(String error, int codigo) {
                            Toast.makeText(LoginActivity.this, error+" "+codigo, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

}
