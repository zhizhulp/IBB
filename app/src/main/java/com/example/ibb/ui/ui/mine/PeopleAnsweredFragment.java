package com.example.ibb.ui.ui.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleAnsweredFragment extends Fragment {


    public PeopleAnsweredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people_answered, container, false);
    }

}
