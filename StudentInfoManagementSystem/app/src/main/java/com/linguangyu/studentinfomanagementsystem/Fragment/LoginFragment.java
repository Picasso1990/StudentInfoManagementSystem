package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.activity.LoginActivity;
import com.linguangyu.studentinfomanagementsystem.activity.MainActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 光裕 on 2018/6/1.
 */

public class LoginFragment extends Fragment {

    private ImageView imagePleaseLogin;

    public LoginFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        imagePleaseLogin = view.findViewById(R.id.image_please_login);
        imagePleaseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivityForResult(intent,1000);//启动活动，传递请求码为1000
            }
        });
        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode,resultCode,data);
//        switch (requestCode) {
//            case 1000:
//                if (resultCode == RESULT_OK) {
//                    MainActivity.panduan = 1;
//                }
//                break;
//            default:
//        }
//    }
}
