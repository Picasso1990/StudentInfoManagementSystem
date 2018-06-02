package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linguangyu.studentinfomanagementsystem.R;

/**
 * Created by 光裕 on 2018/5/31.
 * 成绩Fragment
 */

public class GradeFragment extends Fragment {

    private TextView mTextView;

    public GradeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timetable,container,false);
        mTextView = view.findViewById(R.id.text2);
        mTextView.setText("Grade");
        return view;
    }

}
