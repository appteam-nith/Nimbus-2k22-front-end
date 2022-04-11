package com.nith.nimbus2k22.screens.home;

import android.content.Intent;
import android.os.Bundle;

import static com.nith.nimbus2k22.apis.MemesManiaVolleyHelper.Memeslist;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.nith.nimbus2k22.Models.UserSerializerForMemes;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.apis.MemesManiaVolleyHelper;
import com.nith.nimbus2k22.memeComment;

import com.nith.nimbus2k22.Models.Memes;
import com.nith.nimbus2k22.memePost;
import com.nith.nimbus2k22.screens.adapters.MemeManiaAdapter;
import com.nith.nimbus2k22.screens.models.MemeManiaModel;

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
    private static final String EXTRA_USERNAME = "MemeComment";
    private static final String EXTRA_IMAGE_ ="MemeMania" ;
    private List<Memes> memeList = new ArrayList<>();
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
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), memePost.class));
            }
        });
        MemesManiaVolleyHelper c3 = new MemesManiaVolleyHelper(getActivity());
        c3.getMemes();
        final androidx.lifecycle.Observer<List<Memes>> memesObserver = new androidx.lifecycle.Observer<List<Memes>>() {
            @Override
            public void onChanged(List<Memes> memes) {

                MemeManiaAdapter memeManiaAdapter = new MemeManiaAdapter(memes, getContext());

                recyclerView1.setAdapter(memeManiaAdapter);

            }

        };
        Memeslist.observe(getActivity(), memesObserver);
        return view;
    }


}