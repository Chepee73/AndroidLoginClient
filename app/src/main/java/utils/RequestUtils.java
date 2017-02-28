package utils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by cezar on 28/02/17.
 */

public class RequestUtils
{
    public static JSONArray getjArr()
    {
        return jArr;
    }

    public static void setjArr(JSONArray jArr)
    {
        RequestUtils.jArr = jArr;
    }

    public static final String API_URL = "https://boiling-taiga-37825.herokuapp.com/";

    static JSONArray jArr;

    public static void sendRequest(String url, String endpoint, JSONArray jArray, int method, final RequestBlock block) {
        JsonArrayRequest jsArrRequest = new JsonArrayRequest
                (method, url + endpoint, jArray, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jArr = response;
                        System.out.println(jArr);
                        if (block != null)
                        {
                            block.run();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        RequestQueueSingleton.getmInstance().addToRequestQueue(jsArrRequest);
    }

    public static void sendRequest(String url, String endpoint, JSONObject jObj, int method, final RequestBlock block) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (method, url + endpoint, jObj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(jArr);
                        if (block != null)
                        {
                            block.run();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        RequestQueueSingleton.getmInstance().addToRequestQueue(jsObjRequest);
    }
}
