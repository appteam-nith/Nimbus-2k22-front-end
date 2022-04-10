package com.nith.nimbus2k22.apis;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nith.nimbus2k22.Models.FlappyBirdGame;
import com.nith.nimbus2k22.Models.FlappyBirdScore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlappyBirdVolleyHelper {
    Context context;
    RequestQueue requestQueue;
    String BaseUrl = "https://appteam.monuk7735.cf/";

    public FlappyBirdVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public static MutableLiveData<List<FlappyBirdGame>> flappy;
    public void getFlappyBird(){
        flappy = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "flappybird/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<FlappyBirdGame> fpbird = new ArrayList<>();
                for(int i=0;i< response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String userFirebaseId = jsonObject.getString("userFirebaseId");
                        int score = jsonObject.getInt("score");
                        fpbird.add(new FlappyBirdGame(id,userFirebaseId,score));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                flappy.postValue(fpbird);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public static MutableLiveData<List<FlappyBirdScore>> flappyscore;
    public void getFlappyBirdScore(){
        flappyscore = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "flappybird/totalScore/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<FlappyBirdScore> fpscore = new ArrayList<>();
                Log.e("flappyresponse",String.valueOf(response));
                for(int i=0;i<response.length();i++){
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        String firebase = jsonObject.getString("firebase");
                        String username = jsonObject.getString("username");
                        String name = jsonObject.getString("name");
                        int totalScore = jsonObject.getInt("totalScore");
                        fpscore.add(new FlappyBirdScore(firebase,username,name,totalScore));

                    } catch (JSONException e) {
                        Log.e("exceptionflappy",String.valueOf(e));
                        e.printStackTrace();
                    }
                }
                flappyscore.postValue(fpscore);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           Log.e("flappyerror",error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getSharedPreferences("Token", MODE_PRIVATE);
       String s = sharedPreferences.getString("idToken", "");
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer " + s );
                return headers;
            }};
        requestQueue.add(jsonArrayRequest);


    }
    public void createFlappyBird(String UserFirebaseId,int score){
        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("userFirebaseId",UserFirebaseId);
            jsonbody.put("score",score);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseUrl + "flappybird/", jsonbody, new Response.Listener<JSONObject>() {
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

