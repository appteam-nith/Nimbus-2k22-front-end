package com.nith.nimbus2k22.screens.account;

import static com.nith.nimbus2k22.apis.UserVolleyHelper.user_read;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.nith.nimbus2k22.Models.User_List;
import com.nith.nimbus2k22.R;
import com.nith.nimbus2k22.apis.UserVolleyHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewProfileFragment() {
        // Required empty public constructor
    }
    TextView name;
    TextView emailAdd;
    TextView phoneNumber;
    TextView instaID;
    ImageView img;
    Button btnLogOut;
    FirebaseAuth auth;
    private  Button btnEdit;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewProfileFragment newInstance(String param1, String param2) {
        ViewProfileFragment fragment = new ViewProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_profile, container, false);

        name=view.findViewById(R.id.name);
        emailAdd=view.findViewById(R.id.emailAddress);
        phoneNumber=view.findViewById(R.id.phonenumber);
        instaID=view.findViewById(R.id.instaId);
        img=view.findViewById(R.id.image);
        btnLogOut=view.findViewById(R.id.btnlogout);
        auth=FirebaseAuth.getInstance();
        btnEdit=view.findViewById(R.id.btnedit);

        UserVolleyHelper user=new UserVolleyHelper(getActivity());
        Log.d("testtest", "onCreateView: " + auth.getCurrentUser());
        Log.d("testtest", "onCreateView: " + FirebaseAuth.getInstance().getCurrentUser());
        user.getUserRead(auth.getUid());

//        Log.d("firebase",FirebaseAuth.getInstance().getUid());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),EditProfileActivity.class));
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });

        final androidx.lifecycle.Observer<User_List> userobserver = new androidx.lifecycle.Observer<User_List>() {
            @Override
            public void onChanged(User_List user_list) {
//                    String name=user_list.getUsername();
                    Glide.with(getActivity()).load(user_list.getProfileImage().replace("http","https")).into(img);

                    name.setText(user_list.getUsername());
                    emailAdd.setText(user_list.getEmail());
                    phoneNumber.setText(user_list.getPhone());
                    Log.d("hey1",name.getText().toString());
                    Log.d("hey2",emailAdd.getText().toString());
                    Log.d("hey3",phoneNumber.getText().toString());

//                    instaID.setText(user_list.get);
            }
        };
        user_read.observe(getActivity(),userobserver);
        return view;
    }
}