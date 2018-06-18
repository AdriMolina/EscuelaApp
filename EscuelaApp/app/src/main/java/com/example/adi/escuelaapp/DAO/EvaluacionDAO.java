package com.example.adi.escuelaapp.DAO;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.adi.escuelaapp.Entidades.Calificacion;
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

public class EvaluacionDAO {
    private static EvaluacionDAO dao;
    private ProgressDialog progressDialog;

    private EvaluacionDAO() {
    }

    public static EvaluacionDAO getInstance(){
        if (dao == null){
            dao = new EvaluacionDAO();
        }
        return dao;
    }

    public void getListarMaterias(Context context, final Usuario us, final DAO.OnResultadoListaConsulta<Calificacion>listener){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = Constantes.HOST+Constantes.CARPETA_DAO+"Main/listaCalificaciones.php?usuario="+us.getId();
        Log.i("url",url);

        PeticionHTTP.GET get = new PeticionHTTP.GET(context,url);
        get.getResponseString(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                try {
                    JSONArray jsonArray = new JSONArray(respuesta);
                    if (jsonArray.length() > 0){
                        List<Calificacion>lista = new ArrayList<Calificacion>();
                        Log.i("tamano",String.valueOf(jsonArray.length()));
                        for (int i = 0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Materia materia = new Materia(Integer.parseInt(jsonObject.getString("materia_id")),
                                    jsonObject.getString("clave"),jsonObject.getString("nombre"),Boolean.parseBoolean(jsonObject.getString("activo")));
                            Calificacion calificacion = new Calificacion(Integer.parseInt(jsonObject.getString("evaluacion_id"))
                                    ,Double.parseDouble(jsonObject.getString("calificacion")),jsonObject.getString("fecha"),materia,us);
                            lista.add(calificacion);
                        }
                        //regresamos de manera asincrona la lista
                        listener.consultaSuccess(lista);
                    }else{
                        //regresamos de manera asincrona la lista
                        listener.consultaSuccess(null);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    //regresamos de manera asincrona la lista
                    listener.consultaSuccess(null);
                }

            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                listener.consultaFailed(error,respuestaHTTP);
            }
        });

    }

    public void registrarCalificacion (Context context, final Double calificacion, int materiaID, int usuarioID,final DAO.OnResultadoConsulta listener){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Espere por favor...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = Constantes.HOST+Constantes.CARPETA_DAO+"Main/agregarCalificacion.php";
        Map<String,String> params = new HashMap<>();
        params.put("calificacion",String.valueOf(calificacion));
        params.put("materia_id",String.valueOf(materiaID));
        params.put("usuario_id",String.valueOf(usuarioID));
        Log.i("url",url);

        PeticionHTTP.POST post = PeticionHTTP.POST.getInstance(context,url,params);
        post.getResponse(new PeticionHTTP.OnConsultaListener<String>() {
            @Override
            public void onSuccess(String respuesta) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Calificacion calificacion1 =  new Calificacion();
                listener.consultaSuccess(calificacion1);

            }

            @Override
            public void onFailed(String error, int respuestaHTTP) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                listener.consultaFailed(error,respuestaHTTP);
            }
        });


    }
}
