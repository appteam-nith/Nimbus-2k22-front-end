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
import com.nith.nimbus2k22.Models.TeamList;
import com.nith.nimbus2k22.Models.TeamMemberlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CoreTeamVolleyHelper {
    Context context;
    RequestQueue requestQueue;
    String BaseURL = "https://appteam.monuk7735.cf/";

    public CoreTeamVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public static MutableLiveData<List<TeamList>> teamlist;
    public void getTeamList(){
        teamlist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseURL + "departments", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<TeamList>tlist = new ArrayList<>();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String image = jsonObject.getString("image");
                        tlist.add(new TeamList(name,image));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } teamlist.postValue(tlist);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
     public static MutableLiveData<List<TeamMemberlist>> newmemberlist;
    public void getTeamMembers(String team_name){
        newmemberlist = new MutableLiveData<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseURL + "teams/" + team_name, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("Response",String.valueOf(response));
                    JSONArray jsonArray = response.getJSONArray(team_name+"Members");
                    List<TeamMemberlist> mmlist = new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String team_name = jsonObject.getString("team_name");
                        String position = jsonObject.getString("position");
                        String image = jsonObject.getString("image");
                        mmlist.add(new TeamMemberlist(id,name,team_name,position,image));

                    }
                    newmemberlist.postValue(mmlist);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}