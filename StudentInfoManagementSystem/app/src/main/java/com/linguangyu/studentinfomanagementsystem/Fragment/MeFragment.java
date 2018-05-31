package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linguangyu.studentinfomanagementsystem.R;

import java.util.zip.Inflater;

/**
 * Created by 光裕 on 2018/5/31.
 * 我的Fragment
 */

public class MeFragment extends Fragment {
    private TextView mTextView;

    public MeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timetable_fragment,container,false);
        mTextView = view.findViewById(R.id.text2);
        mTextView.setText("Me");
        return view;
    }
}
