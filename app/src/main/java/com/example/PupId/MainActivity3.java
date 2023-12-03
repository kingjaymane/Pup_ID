package com.example.PupId;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.flowerid.ButtonAdapter;
import com.example.flowerid.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private ListView buttonListView;
    private ArrayList<String> dataList;
    private String api_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonListView = findViewById(R.id.buttonListView);

        api_key = "live_seQpPbIL9SST7tcgIDq4HtE9Pwceo2D2IOiZEXOqQIlkaxB3w5Kz8ONIEjxcQ0ak";

        fetchDataFromAPI();

        ButtonAdapter adapter = new ButtonAdapter(this, dataList);
        buttonListView.setAdapter(adapter);
    }

    private void fetchDataFromAPI() {
        String tag_json_obj = "json_obj_req";

        String url = "https://api.thedogapi.com/breeds";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(response);
                            JSONObject jsonObject = gson.fromJson(jsonString, JSONObject.class);
                            JSONArray breedsArray = jsonObject.getJSONArray("your_response_key");
                            // Assuming the response contains a JSON array of breeds

                            // Iterate through the array and extract information
                            for (int i = 0; i < breedsArray.length(); i++) {
                                JSONObject breedObject = breedsArray.getJSONObject(i);
                                // Extract information from breedObject
                                String breedName = breedObject.getString("name");
                                dataList.add(breedName);
                            }

                            // Display the data or perform further operations
                            // For example, update UI, populate a list, etc.
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity3.this, "Error parsing JSON response", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity3.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("x-api-key", "live_seQpPbIL9SST7tcgIDq4HtE9Pwceo2D2IOiZEXOqQIlkaxB3w5Kz8ONIEjxcQ0ak");
                return headers;
            }

        };

        AppController.getInstance()
                .addToRequestQueue(jsonObjReq, tag_json_obj);

    }
}
