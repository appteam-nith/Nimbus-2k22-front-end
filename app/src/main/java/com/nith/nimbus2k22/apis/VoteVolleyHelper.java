package com.nith.nimbus2k22.apis;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nith.nimbus2k22.Models.VoteTeam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VoteVolleyHelper {
    Context context;
    RequestQueue requestQueue;
    String BaseUrl = "https://appteam.monuk7735.cf/";

    public VoteVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public static MutableLiveData<List<VoteTeam>> voteteam;
    public void voteTeamList(){
        voteteam = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "/vote/createTeam/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            List<VoteTeam>vlist = new ArrayList<>();
            for(int i=0;i< response.length();i++){
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                     String id = jsonObject.getString("id");
                     String name = jsonObject.getString("name");
                     String image = jsonObject.getString("image");
                     vlist.add(new VoteTeam(id,name,image));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            voteteam.postValue(vlist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void createVote(VoteTeam votelist){
        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("id",votelist.getId());
            jsonbody.put("name",votelist.getName());
            jsonbody.put("image",votelist.getImage());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseUrl + "vote/", jsonbody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    public void createVoteTeam(VoteTeam voteteam){
        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("id",voteteam.getId());
            jsonbody.put("name",voteteam.getName());
            jsonbody.put("image",voteteam.getImage());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseUrl + "vote/createTeam/", jsonbody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }


}
