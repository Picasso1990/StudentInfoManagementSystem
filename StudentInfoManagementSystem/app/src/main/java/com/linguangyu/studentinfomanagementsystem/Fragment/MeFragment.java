package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.activity.LoginActivity;
import com.linguangyu.studentinfomanagementsystem.activity.MyApplication;
import com.linguangyu.studentinfomanagementsystem.activity.SysApplication;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by 光裕 on 2018/5/31.
 * 我的Fragment
 */

public class MeFragment extends Fragment {

    private ImageView image_login;
    private TextView text_login;
    private TextView text_me_name;
    private TextView text_me_college;
    private TextView text_me_nianji;
    private LinearLayout linearLayout_me_out;

    public MeFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);

        image_login = view.findViewById(R.id.image_login);
        text_login = view.findViewById(R.id.text_login);
        text_me_name = view.findViewById(R.id.text_me_name);
        text_me_college = view.findViewById(R.id.text_me_college);
        text_me_nianji = view.findViewById(R.id.text_me_nianji);
        linearLayout_me_out = view.findViewById(R.id.linearLayout_me_out);

        image_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();   //清除缓存用户对象
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();   //清除缓存用户对象
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        linearLayout_me_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                SysApplication.getInstance().exit();
            }
        });

        xinXi();

        return view;
    }

    private void xinXi(){
        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
        if (studentUser != null){
            text_me_name.setText(studentUser.getName());
            text_me_college.setText(studentUser.getCollege());
            text_me_nianji.setText(studentUser.getNianji());
        }else {
            text_me_name.setText("");
            text_me_college.setText("");
            text_me_nianji.setText("");
        }
    }

}
