package com.example.cezar.android_login_client;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowUsers extends AppCompatActivity {

    String API_URL = "https://boiling-taiga-37825.herokuapp.com/";
    RequestQueue queue;

    TextView userTxt;
    TextView passTxt;

    class RequestBlock implements Runnable
    {
        JSONArray jArr;
        class Block implements Runnable {
            RequestBlock parent;

            public RequestBlock getParent()
            {
                return  parent;
            }

            Block(RequestBlock parent) {
                this.parent = parent;
            }


            @Override
            public void run() {

            }
        }
        Block block;

        public void initJson(JSONArray jArr) {
            this.jArr = jArr;
        }

        public JSONArray getjArr()
        {
            return jArr;
        }

        @Override
        public void run() {
            block.run();
        }

        public RequestBlock(/*Block block*/)
        {
            /*this.block = block;
            this.block.parent = this;*/
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
    }

    public void onClickSearch(View view) {
        System.out.println("Entered onClickSearch");
        queue = Volley.newRequestQueue(this);

        userTxt = (TextView) findViewById(R.id.userTxt);
        passTxt = (TextView) findViewById(R.id.passwordTxt);

        getJSON(API_URL, "users", Request.Method.GET, new RequestBlock(){
            @Override
            public void run() {
                try {
                    userTxt.setText(this.getjArr().getJSONObject(0).getString("email"));
                    passTxt.setText(this.getjArr().getJSONObject(0).getString("password"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public JSONArray getJSON(String url, String endpoint, int method, final RequestBlock block) {
        final JSONArray[] array = new JSONArray[1];
        ;
        JsonArrayRequest jsArrRequest = new JsonArrayRequest
                (method, url + endpoint, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        array[0] = response;
                        block.initJson(response);
                        block.run();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        queue.add(jsArrRequest);
        return array[0];
    }
}
