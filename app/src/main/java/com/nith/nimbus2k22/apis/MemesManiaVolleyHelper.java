package com.nith.nimbus2k22.apis;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Telephony.Carriers.BEARER;

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
import com.nith.nimbus2k22.Models.CommentList;
import com.nith.nimbus2k22.Models.Memes;
import com.nith.nimbus2k22.Models.UserSerializerForMemes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemesManiaVolleyHelper {
    Context context;
    RequestQueue requestQueue;

    public MemesManiaVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    String BaseUrl = "https://appteam.monuk7735.cf/";
    public static MutableLiveData<List<Memes>> Memeslist;
    public void getMemes(){
        Memeslist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "imagefeed/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Memes> mlist = new ArrayList<>();

//                UserSerializerForMemes serializerForMemes = new UserSerializerForMemes();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        //JSONObject auth = jsonObject.getJSONObject("author");
                      //  String username = auth.getString("username");
                      //  UserSerializerForMemes author = new UserSerializerForMemes(username);
                        String author = jsonObject.getString("author");
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

    public void createMeme(String firebase, String photo, String text, String location, String Uid) {
        JSONObject jsonbody = new JSONObject();
//        SharedPreferences sharedPreferences = context.getSharedPreferences("Token", MODE_PRIVATE);
//        String s = sharedPreferences.getString("idToken", Uid);
        Log.e("helloRohitYadav", "Bye Bye");


        try {
            jsonbody.put("author", firebase);
            jsonbody.put("photo", photo);
            jsonbody.put("text", text);
            jsonbody.put("location", location);
           // jsonbody.put("Authorization",Uid);
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
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getSharedPreferences("User",MODE_PRIVATE);
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", Uid);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


    public void commentCreate( String post_id,String firebase, String text) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("post",post_id);
            jsonObject.put("author",firebase);
            jsonObject.put("text", text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseUrl + "imagefeed/comment/" , jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("CreateComment", String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorCommentCreate", error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getSharedPreferences("User", MODE_PRIVATE);
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Bearer "  );
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);


    }
    public void commentUpdate(String comment_id,String text,String Uid){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("text",text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, BaseUrl + "imagefeed/comment/"+ comment_id + "/", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("updateComment",String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErrorCommentupdate",error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                SharedPreferences sharedPreferences = context.getSharedPreferences("User",MODE_PRIVATE);
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", Uid);
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }
    public static MutableLiveData<List<CommentList>> commentlist;
    public void getCommentList(String post_id){
        commentlist = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "imagefeed/getcomment/" + post_id + "/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Commentlist",String.valueOf(response));
                try {
                    JSONArray jsonArray = response.getJSONArray(post_id +"\'scommenters");
                    List<CommentList> clist = new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String author = jsonObject.getString("author");
                        String text = jsonObject.getString("text");
                        String posted_on = jsonObject.getString("posted_on");
                        clist.add(new CommentList(id,author,text,posted_on));
                    }
                      commentlist.postValue(clist);
                } catch (JSONException e) {
                    Log.e("exceptioncommentList",e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
          Log.e("errorcommentlist",String.valueOf(error));
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
