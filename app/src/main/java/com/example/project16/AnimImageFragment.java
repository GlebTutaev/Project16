package com.example.project16;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimImageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AnimImageFragment() {

    }


    public static AnimImageFragment newInstance(String param1, String param2) {
        AnimImageFragment fragment = new AnimImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void Init(View view) {
        Animation animation = AnimationUtils.loadAnimation(
                getContext(), R.anim.logo_rotate_animation);
        view.findViewById(R.id.imageForLoaded).startAnimation(animation);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anim_image, container, false);
        Init(view);
        return view;
    }
}