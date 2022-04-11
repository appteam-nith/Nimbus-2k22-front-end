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
import com.nith.nimbus2k22.Models.ProductImage;
import com.nith.nimbus2k22.Models.StoreList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StoreVolleyHelper {
    Context context;
    RequestQueue requestQueue;
    String BaseUrl = "https://appteam.monuk7735.cf/";

    public StoreVolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }
    public static MutableLiveData<ArrayList<StoreList>> storelist;
    public void getStore(){
        storelist = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "store/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<StoreList> slist = new ArrayList<>();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String productName = jsonObject.getString("productName");
                        int price  = jsonObject.getInt("price");
                        String description = jsonObject.getString("description");
                        String payment = jsonObject.getString("payment");
                        String department = jsonObject.getString("department");
                        slist.add(new StoreList(id,productName,price,description,payment,department));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                storelist.postValue(slist);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public static MutableLiveData<StoreList> readstore;
    public void readStore(int id){
        readstore = new MutableLiveData<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BaseUrl + "store/" + id + "/", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String id = response.getString("id");
                    String productName = response.getString("productName");
                    int price  = response.getInt("price");
                    String description = response.getString("description");
                    String payment = response.getString("payment");
                    String department = response.getString("department");
                    StoreList store = new StoreList(id,productName,price,description,payment,department);
                    readstore.postValue(store);
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
    public static MutableLiveData<ArrayList<ProductImage>> storeimage;
    public void getStoreImage(String id){
        storeimage = new MutableLiveData<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BaseUrl + "store/image" + id + "/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<ProductImage> pimage = new ArrayList<>();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String product = jsonObject.getString("product");
                        String prodimageUrl = jsonObject.getString("prodimageUrl");
                        pimage.add(new ProductImage(id,product,prodimageUrl));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                storeimage.postValue(pimage);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}

