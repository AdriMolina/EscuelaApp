package com.example.adi.escuelaapp.DAO;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Morpheus on 31/05/2018.
 */

public class PeticionHTTP
{
    public interface OnConsultaListener<T>
    {
        void onSuccess(T respuesta);
        void onFailed(String error, int respuestaHTTP);
    }

    //Clase que contiene las peticiones GET
    public static class GET
    {
        private Context context;
        private String url;

        public GET(Context context, String url)
        {
            this.context = context;
            this.url = url;
        }

        //Método que hace una peticion y regresa un String
        public void getResponseString(final OnConsultaListener<String> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        //Método que hace una petición y regresa un JsonObject
        public void getResponseJson(final OnConsultaListener<JSONObject> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        //Método que hace una petición y regresa un JsonArray
        public void getResponseArray(final OnConsultaListener<JSONArray> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }
    }

    //Clase que contiene las peticiones POST
    public static class POST
    {
        private Context context;
        private String url;
        private Map<String, String> parametros;

        private POST(Context context, String url, Map<String, String> parametros)
        {
            this.context = context;
            this.url = url;
            this.parametros = parametros;
        }

        public static synchronized POST getInstance(Context context, String url, Map<String, String> parametros)
        {
            return new POST(context, url, parametros);
        }

        //Metodo que regresa un String
        public void getResponse(final OnConsultaListener<String> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            })
            {
                @Override
                public String getBodyContentType()
                {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    return parametros;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        //Método que regresa un Json
        /*public void getResponseJson(final OnConsultaListener<JSONObject> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    return parametros;
                }

                @Override
                public String getBodyContentType()
                {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        //Método que regresa un JsonArray
        public void getResponseArray(final OnConsultaListener<JSONArray> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    return parametros;
                }

                @Override
                public String getBodyContentType()
                {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }*/
    }

    //Clase que contiene las peticiones PUT
    public static class PUT
    {
        private Context context;
        private String url;
        private Map<String, String> parametros;

        private PUT(Context context, String url, Map<String, String> parametros)
        {
            this.context = context;
            this.url = url;
            this.parametros = parametros;
        }

        public static synchronized PUT getInstance(Context context, String url, Map<String, String> parametros)
        {
            return new PUT(context, url, parametros);
        }

        //Método que regresa un String
        public void getResponseString(final OnConsultaListener<String> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest request = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            })
            {
                @Override
                public String getBodyContentType()
                {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    return parametros;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        //Método que regresa un Json
        public void getResponseJson(final OnConsultaListener<JSONObject> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            })
            {
                @Override
                public String getBodyContentType()
                {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    return parametros;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        //Metodo que regresa un JsonArray
        public void getResponseArray(final OnConsultaListener<JSONArray> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, url, null, new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            })
            {
                @Override
                public String getBodyContentType()
                {
                    return "application/x-www-form-urlencoded; charset=utf-8";
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    return parametros;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }
    }

    //Clase que contiene las peticiones DELETE
    public static class DELETE
    {
        private Context context;
        private String url;

        private DELETE(Context context, String url)
        {
            this.context = context;
            this.url = url;
        }

        public static synchronized DELETE getInstance(Context context, String url)
        {
            return new DELETE(context, url);
        }

        public void getResponseString(final OnConsultaListener<String> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest request = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        public void getResponseJson(final OnConsultaListener<JSONObject> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>()
            {
                @Override
                public void onResponse(JSONObject response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }

        public void getResponseArray(final OnConsultaListener<JSONArray> listener)
        {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }
    }

    //Clase que contiene las peticiones de imagenes
    public static class IMAGEN
    {
        private Context context;
        private String url;

        private IMAGEN(Context context, String url)
        {
            this.context = context;
            this.url = url;
        }

        public static synchronized IMAGEN getInstance(Context context, String url)
        {
            return new IMAGEN(context, url);
        }

        public void getResponseBitmap(final OnConsultaListener<Bitmap> listener)
        {

            RequestQueue queue = Volley.newRequestQueue(context);
            ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>()
            {
                @Override
                public void onResponse(Bitmap response)
                {
                    listener.onSuccess(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    listener.onFailed(error.toString(), error.networkResponse != null ? error.networkResponse.statusCode : 0);
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);
        }
    }
}
