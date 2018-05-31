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
 * Created by 光裕 on 2018/5/30.
 * 课程表Fragment
 */

public class TimeTableFragment extends Fragment {

    private String context;
    private TextView mTextView;

    public TimeTableFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.timetable_fragment,container,false);//加载timetable_fragment布局
//          mTextView = view.findViewById(R.id.text2);
//        mTextView.setText(context);
        return view;
    }
}
