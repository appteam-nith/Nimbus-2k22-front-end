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
import com.nith.nimbus2k22.Models.EventList;
import com.nith.nimbus2k22.Models.EventList;

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

    String BaseUrl = "https://api.festnimbus.com/api/";
    public static MutableLiveData<ArrayList<EventList>> eventslist;
    public void getEvents(){
        eventslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "events/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<EventList> elist = new ArrayList<>();
                for(int i=0;i< response.length();i++){
                    try {
                        Log.e("eventlistresp", String.valueOf(response));
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String type = jsonObject.getString("type");
                        String shortDescription = jsonObject.getString("shortDescription");
                        String description = jsonObject.getString("description");
                        String from = jsonObject.getString("from");
                        String to = jsonObject.getString("to");
                        String registrationUrl = jsonObject.getString("registrationUrl");
                        String image = jsonObject.getString("image");
                        String pdf = jsonObject.getString("pdf");
                        String username = jsonObject.getString("username");

                        elist.add(new EventList(id,name,type,shortDescription,description,from,to,registrationUrl,image,pdf,username));



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

    public static MutableLiveData<EventList> eventread;

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
                    int type = response.getInt("type");
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