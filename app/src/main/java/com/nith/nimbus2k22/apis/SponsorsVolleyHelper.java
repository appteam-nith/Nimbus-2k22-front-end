package com.nith.nimbus2k22.apis;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nith.nimbus2k22.Models.Sponsors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SponsorsVolleyHelper {
    Context context;
    RequestQueue requestQueue;

    public SponsorsVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    String BaseUrl = "https://api.festnimbus.com/api/";
    public static MutableLiveData<ArrayList<Sponsors>> sponsorslist;

    public void getSponsors() {
        sponsorslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "sponsors/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Sponsors> slist = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.e("sponsors", String.valueOf(response));
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                        String image = jsonObject.getString("image");
                        String website = jsonObject.getString("website");
                        int level = jsonObject.getInt("level");
                    } catch (JSONException e) {
                        Log.e("sponsorsexepction", String.valueOf(e));
                        e.printStackTrace();
                    }

                }
                sponsorslist.postValue(slist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e("errorsponsors", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
