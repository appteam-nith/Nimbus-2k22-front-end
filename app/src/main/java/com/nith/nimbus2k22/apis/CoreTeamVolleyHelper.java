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
    String BaseURL  = "https://anmol26.pythonanywhere.com/";

    public CoreTeamVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public static MutableLiveData<List<TeamList>> teamslist;
    public void getTeams(){
        teamslist = new MutableLiveData<>();
        List<TeamList> Tlist = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseURL + "team/team/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Teamresponse",String.valueOf(response));
             for(int i=0;i< response.length();i++){
                 try {

                     JSONObject jsonObject = response.getJSONObject(i);
                     int id = jsonObject.getInt("id");
                     String club_name = jsonObject.getString("club_name");
                     String image = jsonObject.getString("image");
                     Tlist.add(new TeamList(id,club_name,image));
                 } catch (JSONException e) {
                     Log.e("exceptionTeams",String.valueOf(e));
                     e.printStackTrace();
                 }
             }
             teamslist.postValue(Tlist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.e("TeamsErro",error.getMessage());
            }
        });
       requestQueue.add(jsonArrayRequest);

    }
    public static MutableLiveData<TeamList> teamRead;
    public void readTeam(int id){
        teamRead = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseURL + "team/team/"+id+ "/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
           Log.e("teamRead",String.valueOf(response));
                try {
                    Log.e("responseteamread",String.valueOf(response));
                    int id = response.getInt("id");
                    String club_name = response.getString("club_name");
                    String image = response.getString("image");
                    TeamList teams = new TeamList(id,club_name,image);
                    teamRead.postValue(teams);
                } catch (JSONException e) {
                    Log.e("exceptionreadteam",String.valueOf(e));
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorteamread",String.valueOf(error));
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
     public static MutableLiveData<List<TeamMemberlist>>MemberList;
     public void getTeamMembers(String teamName){
         MemberList = new MutableLiveData<>();

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseURL + "team/teamMember/" + teamName +"/", null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 Log.e("MemberList",String.valueOf(response));
                 try {

                     JSONArray jsonArray = response.getJSONArray(teamName+"Members");

                     List<TeamMemberlist> Tlist = new ArrayList<>();
                     for(int i=0;i<jsonArray.length();i++){
                         JSONObject jsonObject = jsonArray.getJSONObject(i);
                         int id = jsonObject.getInt("id");
                         String name = jsonObject.getString("name");
                         String team_name = jsonObject.getString("team_name");
                         String position = jsonObject.getString("position");
                         String image = jsonObject.getString("image");
                         Tlist.add(new TeamMemberlist(id,name,team_name,position,image));
                         MemberList.postValue(Tlist);

                     }
                 } catch (JSONException e) {
                     Log.e("ExceptionMember",e.getMessage());
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
           Log.e("ErrorMember",String.valueOf(error));
             }
         });
          requestQueue.add(jsonObjectRequest);
     }

}
