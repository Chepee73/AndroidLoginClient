package com.example.cezar.android_login_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.RequestBlock;
import utils.RequestUtils;

import static utils.RequestUtils.API_URL;
import static utils.RequestUtils.sendRequest;

public class AddUser extends AppCompatActivity
{
    EditText emailBox;
    EditText passBox;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
    }

    public void submitUser(View view)
    {
        emailBox = (EditText) findViewById(R.id.emailBox);
        passBox = (EditText) findViewById(R.id.passwordBox);
        JSONObject jObj = null;

        String endpoint = "users/";
        try
        {
            jObj = new JSONObject("{\"email\": " +  emailBox.getText() + "," +
            "\"password\": " + passBox.getText() + " }");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        sendRequest(API_URL, endpoint, jObj, Request.Method.POST, new RequestBlock() {

        });
    }
}
