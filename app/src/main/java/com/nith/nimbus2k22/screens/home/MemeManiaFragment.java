package com.nith.nimbus2k22.screens.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.memeComment;
import com.nith.nimbus2k22.memePost;
import com.nith.nimbus2k22.screens.adapters.MemeManiaAdapter;
import com.nith.nimbus2k22.screens.models.MemeManiaModel;
import com.nith.nimbus2k22.screens.teams.TeamDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MemeManiaFragment extends Fragment  {
    private static final String TAG = "Meme Mania Fragment";
    private List<MemeManiaModel> memeList = new ArrayList<>();
    FloatingActionButton fab;
    ImageView comment_btn;

    public MemeManiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meme_mania, container, false);
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView);
        MemeManiaAdapter memeManiaAdapter = new MemeManiaAdapter(memeList, getContext());
        recyclerView1.setAdapter(memeManiaAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        FloatingActionButton fab=(FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),memePost.class));
            }
        });

        addTeamDataFromJSON1();
        return view;

    }

    private void addTeamDataFromJSON1() {
        try {
            String jsonDataString = readJSONDataFromFile1();
            JSONArray jsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < jsonArray.length(); ++i) {
                System.out.println(jsonArray.get(i).toString());
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String username = itemObj.getString("username");
                String userimage = itemObj.getString("userimage");
                String memeImage = itemObj.getString("memeimage");
                String caption = itemObj.getString("caption");
                MemeManiaModel memeDetailData = new MemeManiaModel(username, caption, userimage, memeImage);
                memeList.add(memeDetailData);

            }
        } catch (JSONException | IOException e) {
            Log.d(TAG, "addTeamDataFromJSON:", e);
        }

    }

    private String readJSONDataFromFile1() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.memedata);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);

    }
}