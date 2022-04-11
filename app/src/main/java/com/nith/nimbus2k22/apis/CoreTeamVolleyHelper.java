package com.nith.nimbus2k22.apis;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
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
    public static MutableLiveData<List<TeamMemberlist>> memberslist;
    public void getTeamMembers(String teamname){
        memberslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseURL + "teams/" + teamname + "/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<TeamMemberlist>tmlist = new ArrayList<>();
                for(int i=0;i< response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String team_name = jsonObject.getString("team_name");
                        String position = jsonObject.getString("position");
                        String image = jsonObject.getString("image");
                        String photo = jsonObject.getString("photo");
                        tmlist.add(new TeamMemberlist(id,name,team_name,position,image,photo));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}