package com.linguangyu.studentinfomanagementsystem.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;
import com.linguangyu.studentinfomanagementsystem.model.TimetableTable;
import com.linguangyu.studentinfomanagementsystem.model.UserTable;
import com.linguangyu.studentinfomanagementsystem.model.Weeks;


import junit.framework.Test;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mStudentId;
    private EditText mPassword;
    private Button mLogin;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(0xff1C86EE);
        init();//初始化

        /**
         * 查询是否存在该学生
         */
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentIdString = mStudentId.getText().toString();
                String passwordString =  mPassword.getText().toString();
                if (studentIdString.equals("")||passwordString.equals("")){
                    Toast.makeText(LoginActivity.this,"学号或密码不能为空值",Toast.LENGTH_SHORT).show();
                }else {
//                    BmobQuery<UserTable> queryUser = new BmobQuery<UserTable>();
//                    queryUser.addWhereEqualTo("studentId",studentIdString);
//                    queryUser.addWhereEqualTo("password",passwordString);
//                    queryUser.findObjects(new FindListener<UserTable>() {
//                        @Override
//                        public void done(List<UserTable> list, BmobException e) {
//                            if (e == null && (!list.isEmpty())){
//                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
////                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
////                                startActivity(intent);
//                                Intent intent = new Intent();
//                                setResult(RESULT_OK,intent);
//                                finish();
//                            }else if (e == null && list.isEmpty()){
//                                Toast.makeText(LoginActivity.this,"学号或密码错误",Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(LoginActivity.this,"服务器连接错误",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

                    BmobUser.loginByAccount(studentIdString, passwordString, new LogInListener<StudentUser>() {
                        @Override
                        public void done(StudentUser studentUser, BmobException e) {
                            if (e == null && studentUser != null){
                                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                setResult(RESULT_OK,intent);
                                finish();
                            }else if (e == null && studentUser == null){
                                Toast.makeText(LoginActivity.this,"学号或密码错误",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this,"服务器连接错误",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        /**
         * 返回
         */
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        mStudentId = findViewById(R.id.edit_studentId);
        mPassword = findViewById(R.id.edit_password);
        mLogin = findViewById(R.id.button_login);
        mBack = findViewById(R.id.image_back);
    }

}

