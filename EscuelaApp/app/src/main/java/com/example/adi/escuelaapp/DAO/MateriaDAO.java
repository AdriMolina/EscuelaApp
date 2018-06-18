package com.example.adi.escuelaapp.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.adi.escuelaapp.Entidades.Categoria;
import com.example.adi.escuelaapp.Entidades.Credencial;
import com.example.adi.escuelaapp.Entidades.Materia;
import com.example.adi.escuelaapp.Entidades.Usuario;
import com.example.adi.escuelaapp.Recursos.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adi on 16/06/2018.
 */

public class MateriaDAO {
    private static MateriaDAO dao;
    private ProgressDialog progressDialog;


    public MateriaDAO() {
    }

    public static MateriaDAO getInstance()
    {
        if (dao == null){
            dao = new MateriaDAO();
        }
        return dao;
    }

    public void getListaMaterias(Context context, final DAO.OnResultadoListaConsulta<Materia>listaConsulta){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = Constantes.HOST+Constantes.CARPETA_DAO+"Main/listaMaterias.php";
        PeticionHTTP.GET get = new PeticionHTTP.GET(context,url);
        get.getResponseString(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                try {
                    JSONArray array = new JSONArray(respuesta);
                    if (array.length() > 0){
                        List<Materia> lista = new ArrayList<Materia>();
                        for (int i = 0; i < array.length(); i++){
                            Log.i("ciclo",String.valueOf(i));
                            //Armamos la lista de objetos de tipo Materia
                            JSONObject object = array.getJSONObject(i);
                            Materia materia = new Materia(Integer.parseInt(object.getString("id")),object.getString("clave"),
                                    object.getString("nombre"),Boolean.parseBoolean("activo"));
                            lista.add(materia);
                        }
                        //regresamos d emanera asincrona la lista
                        listaConsulta.consultaSuccess(lista);
                    }else{
                        listaConsulta.consultaSuccess(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listaConsulta.consultaSuccess(null);
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                listaConsulta.consultaFailed(error,respuestaHTTP);
            }
        });




    }
    public void getRegistrarMaterias(Context context, final String clave, final String nombre, final DAO.OnResultadoConsulta resultado) {
        //inicia el progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();



        //Se crea la ruta del archivo de registrar materia
        String url = Constantes.HOST + Constantes.CARPETA_DAO + "Main/agregarMaterias.php";
        //Creacion del Map
        Map<String, String> params = new HashMap<>();
        params.put("clave", clave);
        params.put("nombre", nombre);

        PeticionHTTP.POST post = PeticionHTTP.POST.getInstance(context, url, params);
        post.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                Log.i("RESPUESTA", respuesta);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Materia materia = new Materia();
                resultado.consultaSuccess(materia);

            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
               Log.i("error", error);

            }
        });


    }
}
