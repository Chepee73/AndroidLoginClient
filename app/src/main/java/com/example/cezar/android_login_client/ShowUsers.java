package com.example.cezar.android_login_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import utils.RequestBlock;
import utils.RequestQueueSingleton;
import utils.RequestUtils;

import static utils.RequestUtils.API_URL;
import static utils.RequestUtils.sendRequest;

public class ShowUsers extends AppCompatActivity
{

    TextView userTxt;
    TextView passTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        RequestQueueSingleton.getInstance(this);
    }

    public void onClickSearch(View view)
    {
        System.out.println("Entered onClickSearch");

        userTxt = (TextView) findViewById(R.id.userTxt);

        JSONArray jArr = null;

        sendRequest(API_URL, "users", jArr, Request.Method.GET, new RequestBlock()
        {
            @Override
            public void run()
            {
                try
                {
                    for (int i = 0; i < RequestUtils.getjArr().length(); i++)
                        userTxt.append(RequestUtils.getjArr().getJSONObject(i).getString("email") + "\n");
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    public void addUser(View view)
    {
        Intent intent = new Intent(this, AddUser.class);
        startActivity(intent);
    }
}
