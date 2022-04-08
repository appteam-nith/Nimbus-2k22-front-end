package com.nith.nimbus2k22.screens.home;

import android.content.Intent;
import android.os.Bundle;
import static com.nith.nimbus2k22.apis.DepartmentsVolleyHelper.DepartmentList;
import static com.nith.nimbus2k22.apis.MemesManiaVolleyHelper.Memeslist;

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
import com.nith.nimbus2k22.apis.MemesManiaVolleyHelper;
import com.nith.nimbus2k22.memeComment;
import com.nith.nimbus2k22.Models.Departments;
import com.nith.nimbus2k22.Models.Memes;
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

public class MemeManiaFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Meme Mania Fragment";
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
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView);
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
                recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
            }

        };
//        addTeamDataFromJSON1();
        Memeslist.observe(getActivity(), memesObserver);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(),memeComment.class);
    }
}
