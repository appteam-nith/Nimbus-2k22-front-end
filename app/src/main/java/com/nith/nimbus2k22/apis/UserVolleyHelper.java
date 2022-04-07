package com.nith.nimbus2k22.apis;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nith.nimbus2k22.Models.Check_User;
import com.nith.nimbus2k22.Models.User_List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserVolleyHelper {
    Context context;
    RequestQueue requestQueue;

    public UserVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    String BaseUrl = "https://appteam.monuk7735.cf/";


    public static MutableLiveData<List<User_List>> user_list;

    public void getUserList() {
        user_list = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "users/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("UserList", String.valueOf(response));
                List<User_List> alist = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String firebase = jsonObject.getString("firebase");
                        String username = jsonObject.getString("username");
                        String phone = jsonObject.getString("phone");
                        String email = jsonObject.getString("email");
                        String name = jsonObject.getString("name");
                        boolean favTeamVote = jsonObject.getBoolean("favTeamVote");
                        int omegleReports = jsonObject.getInt("omegleReports");
                        boolean omegleAllowed = jsonObject.getBoolean("omegleAllowed");
                        String instaId = jsonObject.getString("instaId");
                        String profileImage = jsonObject.getString("profileImage");
                        int totalScore  = jsonObject.getInt("totalScore");

                        alist.add(new User_List(firebase, username, phone, email, name, favTeamVote, omegleReports, omegleAllowed,instaId, profileImage,totalScore ));


                    } catch (JSONException e) {
                        Log.e("UserListexception", String.valueOf(e));
                        e.printStackTrace();
                    }
                }
                user_list.postValue(alist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("UserListError", String.valueOf(error));
            }
        });
        requestQueue.add(jsonArrayRequest);


    }

    public static MutableLiveData<User_List> user_read;

    public void getUserRead(String firebase) {
        user_read = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "users/" + firebase + "/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("userRead", String.valueOf(response));
                    String firebase = response.getString("firebase");
                    String username = response.getString("username");
                    String phone = response.getString("phone");
                    String email = response.getString("email");
                    String name = response.getString("name");
                   boolean favTeamVote = response.getBoolean("favTeamVote");
                    int omegleReports = response.getInt("omegleReports");
                    boolean omegleAllowed = response.getBoolean("omegleAllowed");
                    String instaId = response.getString("instaId");
                    String profileImage = response.getString("profileImage");
                    int totalScore = response.getInt("totalScore");

                    User_List ulist = new User_List(firebase, username, phone, email, name, favTeamVote, omegleReports, omegleAllowed,instaId, profileImage,totalScore);
                    user_read.postValue(ulist);
                } catch (JSONException e) {
                    Log.e("userReadexception", e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("userreadError", String.valueOf(error));
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    public static MutableLiveData<Check_User> user_check;
    public void check_User(String email){
        user_check = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "users/checkUser/" + email , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("usercheck",String.valueOf(response));
                    String user_present = response.getString("user_present");
                    Check_User check = new Check_User(user_present);
                    user_check.postValue(check);
                } catch (JSONException e) {
                    Log.e("checkexception",String.valueOf(e));
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.e("Checkerrror",String.valueOf(error));
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    public void updateUser(User_List ulist,String firebase){
        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("firebase",ulist.getFirebase());
            jsonbody.put("username",ulist.getUsername());
            jsonbody.put("phone",ulist.getPhone());
            jsonbody.put("email",ulist.getEmail());
            jsonbody.put("name",ulist.getName());
            jsonbody.put("favTeamVote",ulist.isFavTeamVote());
            jsonbody.put("omegleReports",ulist.getOmegleReports());
            jsonbody.put("omegleAllowed",ulist.isOmegleAllowed());
            jsonbody.put("instaId",ulist.getInstaId());
            jsonbody.put("profileImage",ulist.getProfileImage());
            jsonbody.put("totalScore",ulist.getTotalScore());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, BaseUrl + "users/"+firebase+"/", jsonbody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            Log.e("updateUser",String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Usererror",String.valueOf(error));
                NetworkResponse response = error.networkResponse;
                if (response != null) {
                    if (response.statusCode == 400 || response.statusCode == 404 || response.statusCode == 422 || response.statusCode == 401) {
                        try {
                            JSONObject object = new JSONObject(new String(response.data));
                            if (object.getJSONObject("Errors:").has("username")) {
                                String usernameErr = object.getJSONObject("Errors:").getJSONArray("username").get(0).toString();
                                Log.e("jsonObject", usernameErr);
                                Toast.makeText(context, usernameErr, Toast.LENGTH_SHORT).show();

                            }
                            if (object.getJSONObject("Errors:").has("email")) {
                                String emailErr = object.getJSONObject("Errors:").getJSONArray("email").get(0).toString();
                                Log.e("jsonObject", emailErr);
                                Toast.makeText(context, emailErr, Toast.LENGTH_SHORT).show();

                            } else {
                                String ResultMsg = object.getString("Message");
                                Log.e("jsonObject", ResultMsg);
                                Toast.makeText(context, ResultMsg, Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("jsonerror", e.getMessage());

                        }
                    } else {
                        Log.e("jsonObject", "Error with response code " + response.data);
                        Toast.makeText(context, "Error with response code " + response.statusCode, Toast.LENGTH_SHORT).show();

                    }
                } else {

                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found. Please try again after some time!!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    }
                }


            }
        });
        requestQueue.add(jsonObjectRequest);


    }
    public void createUser(User_List ulist, String s){
        JSONObject jsonbody = new JSONObject();
        try {
            jsonbody.put("firebase",ulist.getFirebase());
            jsonbody.put("username",ulist.getUsername());
            jsonbody.put("phone",ulist.getPhone());
            jsonbody.put("email",ulist.getEmail());
            jsonbody.put("firstName",ulist.getName());
            jsonbody.put("favTeamVote",ulist.isFavTeamVote());
            jsonbody.put("omegleReports",ulist.getOmegleReports());
            jsonbody.put("omegleAllowed",ulist.isOmegleAllowed());
            jsonbody.put("instaId",ulist.getInstaId());
            jsonbody.put("profileImage",ulist.getProfileImage());
            jsonbody.put("totalScore",ulist.getTotalScore());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BaseUrl + "users/", jsonbody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("CreateUser",String.valueOf(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Usererror",String.valueOf(error));
                NetworkResponse response = error.networkResponse;
                if (response != null) {
                    if (response.statusCode == 400 || response.statusCode == 404 || response.statusCode == 422 || response.statusCode == 401) {
                        try {
                            JSONObject object = new JSONObject(new String(response.data));
                            if (object.getJSONObject("Errors:").has("username")) {
                                String usernameErr = object.getJSONObject("Errors:").getJSONArray("username").get(0).toString();
                                Log.e("jsonObject", usernameErr);
                                Toast.makeText(context, usernameErr, Toast.LENGTH_SHORT).show();

                            }
                            if (object.getJSONObject("Errors:").has("email")) {
                                String emailErr = object.getJSONObject("Errors:").getJSONArray("email").get(0).toString();
                                Log.e("jsonObject", emailErr);
                                Toast.makeText(context, emailErr, Toast.LENGTH_SHORT).show();

                            } else {
                                String ResultMsg = object.getString("Message");
                                Log.e("jsonObject", ResultMsg);
                                Toast.makeText(context, ResultMsg, Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("jsonerror", e.getMessage());

                        }
                    } else {
                        Log.e("jsonObject", "Error with response code " + response.data);
                        Toast.makeText(context, "Error with response code " + response.statusCode, Toast.LENGTH_SHORT).show();

                    }
                } else {

                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found. Please try again after some time!!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("error", message);
                    }
                }


            }
        });
        requestQueue.add(jsonObjectRequest);


    }



}
