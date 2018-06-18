package com.example.adi.escuelaapp;

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

public class EditarUsuario extends AppCompatActivity {

    Usuario usuario;
    EditText edtNombre, edtNick,edtPass;
    Button btnEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        usuario =(Usuario)getIntent().getParcelableExtra("USUARIO");
        edtNick = (EditText)findViewById(R.id.edtUsuario);
        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtPass = (EditText)findViewById(R.id.edtPass);
        btnEditar = (Button) findViewById(R.id.btnEditar);

        edtPass.setText(usuario.getCredencial().getPass());
        edtNombre.setText(usuario.getNombre());
        edtNick.setText(usuario.getCredencial().getNick());



        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNombre.getText().toString() != usuario.getNombre() ||
                        edtNick.getText().toString() != usuario.getCredencial().getNick()
                        || edtPass.getText().toString() != usuario.getCredencial().getPass()){
                    UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
                    usuarioDAO.actualizarUsuario(EditarUsuario.this, usuario.getId(), edtNick.getText().toString(), edtPass.getText().toString(), edtNombre.getText().toString(), new DAO.OnResultadoConsulta() {
                        @Override
                        public void consultaSuccess(Object o) {
                            edtNick.setText("");
                            edtPass.setText("");
                            edtNombre.setText("");
                            Toast.makeText(EditarUsuario.this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void consultaFailed(String error, int codigo) {
                            Toast.makeText(EditarUsuario.this, "No se actualizo ", Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(EditarUsuario.this, "No hay cambios", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}
