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
import java.util.List;

public class EventsVolleyHelper {
    Context context;
    RequestQueue requestQueue;
    public EventsVolleyHelper(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    String BaseUrl = "https://anmol26.pythonanywhere.com/";
    public static MutableLiveData<ArrayList<Events_List>> eventslist;
    public void getEvents(){
        eventslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "events/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Events_List> elist = new ArrayList<>();
                for(int i=0;i< response.length();i++){
                    try {
                        Log.e("eventlistresp",String.valueOf(response));
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String startTime = jsonObject.getString("startTime");
                        String endTime = jsonObject.getString("endTime");
                        String clubName = jsonObject.getString("clubName");
                        String platform = jsonObject.getString("platform");
                        String image = jsonObject.getString("image");
                        String regUrl = jsonObject.getString("regURL");
                        int type = jsonObject.getInt("type");
                        elist.add(new Events_List(title,description,startTime,endTime,clubName,platform,image,regUrl,type));
                    } catch (JSONException e) {
                        Log.e("exceptioneventslist",e.getMessage());
                        e.printStackTrace();
                    }
                }
                eventslist.postValue(elist);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
         Log.e("eventslisterror",error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public static MutableLiveData<Events_List> eventread;
    public void readEvents(String title){
        eventread = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "events/" + title, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("readevents",String.valueOf(response));
                    String title = response.getString("title");
                    String description = response.getString("description");
                    String startTime = response.getString("startTime");
                    String endTime = response.getString("endTime");
                    String clubName = response.getString("clubName");
                    String platform = response.getString("platform");
                    String image = response.getString("image");
                    String regUrl = response.getString("regURL");
                    int type = response.getInt("type");
                    Events_List elist = new Events_List(title,description,startTime,endTime,clubName,platform,image,regUrl,type);
                    eventread.postValue(elist);
                } catch (JSONException e) {
                    Log.e("eventreadexception",String.valueOf(e));
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             Log.e("errorreadevents",error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
