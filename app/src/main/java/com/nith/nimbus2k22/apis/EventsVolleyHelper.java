package com.nith.nimbus2k22.apis;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nith.nimbus2k22.Models.Events_List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventsVolleyHelper {
    Context context;
    RequestQueue requestQueue;

    public EventsVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    String BaseUrl = "https://appteam.monuk7735.cf/";
    public static MutableLiveData<ArrayList<Events_List>> eventslist;
    public void getEvents(){
        eventslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "events/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Events_List> elist = new ArrayList<>();
                for(int i=0;i< response.length();i++){
                    try {
                        Log.e("eventlistresp", String.valueOf(response));
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String abtract = jsonObject.getString("abstract");
                        String info = jsonObject.getString("info");
                        String venue = jsonObject.getString("venue");
                        String start = jsonObject.getString("start");
                        String end = jsonObject.getString("end");
                        String image = jsonObject.getString("image");
                        String regUrl = jsonObject.getString("regURL");
                        int type = jsonObject.getInt("Type");
                        String department = jsonObject.getString("department");
                        elist.add(new Events_List(id,name,abtract,info,venue,start,end,image,regUrl,type,department));



                    } catch (JSONException e) {
                        Log.e("exceptioneventslist", e.getMessage());
                        e.printStackTrace();
                    }
                }
                eventslist.postValue(elist);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("eventslisterror", error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public static MutableLiveData<Events_List> eventread;

    public void readEvents(String title) {
        eventread = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "events/" + title, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("readevents", String.valueOf(response));
                    int id = response.getInt("id");
                    String name = response.getString("name");
                    String abtract = response.getString("abstract");
                    String info = response.getString("info");
                    String venue = response.getString("venue");
                    String start = response.getString("start");
                    String end = response.getString("end");
                    String image = response.getString("image");
                    String regUrl = response.getString("regURL");
                    int type = response.getInt("Type");
                    String department = response.getString("department");
                } catch (JSONException e) {
                    Log.e("eventreadexception", String.valueOf(e));
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorreadevents", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}