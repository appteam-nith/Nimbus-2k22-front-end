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
import com.nith.nimbus2k22.Models.Memes;
import com.nith.nimbus2k22.Models.UserSerializerForMemes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MemesManiaVolleyHelper {
    Context context;
    RequestQueue requestQueue;

    public MemesManiaVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    String BaseUrl = "https://anmol26.pythonanywhere.com/";
    public static MutableLiveData<List<Memes>> Memeslist;
    public void getMemes(){
        Memeslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "imagefeed/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
             List<Memes> mlist = new ArrayList<>();

//                UserSerializerForMemes serializerForMemes = new UserSerializerForMemes();
                for(int i=0;i< response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        JSONObject auth = jsonObject.getJSONObject("author");
                        String username = auth.getString("username");
                        UserSerializerForMemes author = new UserSerializerForMemes(username);
                        String photo = jsonObject.getString("photo");
                        String text = jsonObject.getString("text");
                        String location = jsonObject.getString("location");
                        String posted_on = jsonObject.getString("posted_on");
                        String number_of_likes = jsonObject.getString("number_of_likes");
                        String number_of_comments = jsonObject.getString("number_of_comments");
                        mlist.add(new Memes(id,author,photo,text,location,posted_on,number_of_likes,number_of_comments));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Memeslist.postValue(mlist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void createMeme(String firebase,String photo,String text,String location){
        JSONObject jsonbody = new JSONObject();

        try {
            jsonbody.put("photo",photo);
            jsonbody.put("text",text);
            jsonbody.put("location",location);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseUrl + "imagefeed/" + firebase+"/", jsonbody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("createresponse",String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("memecreaeerro",error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
