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
import com.nith.nimbus2k22.Models.Departments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsVolleyHelper {
    Context context;
    RequestQueue requestQueue;

    public DepartmentsVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    String BaseUrl = "https://appteam.monuk7735.cf/";
    public static MutableLiveData<List<Departments>> DepartmentList;
    public void getDepartments(){
        DepartmentList = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "departments/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            List<Departments> dlist = new ArrayList<>();
            for(int i=0;i<response.length();i++){
                try {
                    Log.e("departments", String.valueOf(response));
                    JSONObject jsonObject = response.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String password = jsonObject.getString("password");
                    String image = jsonObject.getString("image");
                    dlist.add(new Departments(name,password,image));
                } catch (JSONException e) {
                    Log.e("departmentsexception",e.getMessage());
                    e.printStackTrace();
                }
            }
            DepartmentList.postValue(dlist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           Log.e("departmentserror",error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public static MutableLiveData<Departments> departmentRead;
    public void readDepartment(String name){
        departmentRead = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "departments/" + name, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("readdepartmetn",String.valueOf(response));
                    String name = response.getString("name");
                    String password = response.getString("password");
                    String image = response.getString("image");
                    Departments departmen = new Departments(name,password,image);
                    departmentRead.postValue(departmen);
                } catch (JSONException e) {
                    Log.e("exceptoinreaddepart",e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
           Log.e("StirngErrordepartment",error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
