package com.example.baitapjson1210;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textresult;
    private RequestQueue Queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textresult = findViewById(R.id.txt_parse);
        Button buttonparse = findViewById(R.id.btn_parse);

        Queue = Volley.newRequestQueue(this);

        buttonparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonparse();
            }
        });
    }
    private void jsonparse(){
        String url="https://api.jsonserve.com/gpdXJa";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("employee");

                    for(int i=0; i< jsonArray.length(); i++){
                        JSONObject employee = jsonArray.getJSONObject(i);

                        String firstname = employee.getString("firstname");
                        int age = employee.getInt("age");
                        String mail = employee.getString("mail");

                        textresult.append("firstname:  " + firstname + " , age: " + String.valueOf(age) + ",\nmail: " + mail + "\n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Queue.add(request);
    }
}