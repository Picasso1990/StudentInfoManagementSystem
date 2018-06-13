package com.linguangyu.studentinfomanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linguangyu.studentinfomanagementsystem.Fragment.GradeFragment;
import com.linguangyu.studentinfomanagementsystem.Fragment.LoginFragment;
import com.linguangyu.studentinfomanagementsystem.Fragment.MeFragment;
import com.linguangyu.studentinfomanagementsystem.Fragment.TestFragment;
import com.linguangyu.studentinfomanagementsystem.Fragment.TimeTableFragment;
import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by 光裕 on 2018/5/30.
 * 主activity
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout mTabMenuTimetable;
    private RelativeLayout mTabMenuGrade;
    private RelativeLayout mTabMenuTest;
    private RelativeLayout mTabMenuMe;

    private ImageView mImageTimetable;
    private ImageView mImageGrade;
    private ImageView mImageTest;
    private ImageView mImageMe;

    private TextView mTextTimetable;
    private TextView mTextGrade;
    private TextView mTextTest;
    private TextView mTextMe;

    private TimeTableFragment timeTableFragment;
    private GradeFragment gradeFragment;
    private TestFragment testFragment;
    private MeFragment meFragment;
    private LoginFragment loginFragment;

//    private RelativeLayout titleTimetable;
//    private RelativeLayout titleGrade;
//    private RelativeLayout titleTest;
//    private RelativeLayout titleMe;

    private ImageView image_main_login;
    private TextView text_main_login;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static int panduan = 0;//判断是否登录，若为0，还未登录，若为1，已登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//设置去除标题
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);//添加入MyApplication
        getWindow().setStatusBarColor(0xff1C86EE);//设置状态栏和title颜色一致，0xff表示透明度100% 加上颜色值就行了。如上：颜色值是: 0xff + 1C86EE，要求API最小是21。
        fragmentManager = getSupportFragmentManager();
        init();

        image_main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//                startActivityForResult(intent,1000);
                startActivity(intent);
            }
        });

        mTabMenuTimetable.performClick();
    }

//    public static void huanCun(){
//        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
//        if (studentUser != null){
//            panduan = 1;
//        }else {
//            panduan = 0;
//        }
//    }

    /**
     * 初始化控件
     */
    public void init(){
        mTabMenuTimetable = findViewById(R.id.tab_menu_timetable);
        mTabMenuGrade = findViewById(R.id.tab_menu_grade);
        mTabMenuTest = findViewById(R.id.tab_menu_test);
        mTabMenuMe = findViewById(R.id.tab_menu_me);

        mImageTimetable = findViewById(R.id.image_timetable);
        mImageGrade = findViewById(R.id.image_grade);
        mImageTest = findViewById(R.id.image_test);
        mImageMe = findViewById(R.id.image_me);

        mTextTimetable = findViewById(R.id.text_timetable);
        mTextGrade = findViewById(R.id.text_grade);
        mTextTest = findViewById(R.id.text_test);
        mTextMe = findViewById(R.id.text_me);

//        titleTimetable = findViewById(R.id.layout_timetable);
//        titleGrade = findViewById(R.id.layout_grade);
//        titleTest = findViewById(R.id.layout_test);
//        titleMe = findViewById(R.id.layout_me);

        image_main_login = findViewById(R.id.image_main_login);
        text_main_login = findViewById(R.id.text_main_login);

        mTabMenuTimetable.setOnClickListener(this);
        mTabMenuGrade.setOnClickListener(this);
        mTabMenuTest.setOnClickListener(this);
        mTabMenuMe.setOnClickListener(this);

    }

    /**
     * 重置所有图片和文本的选中状态
     */
    public void selected(){

        mImageTimetable.setSelected(false);
        mImageGrade.setSelected(false);
        mImageTest.setSelected(false);
        mImageMe.setSelected(false);
        mTextTimetable.setSelected(false);
        mTextGrade.setSelected(false);
        mTextTest.setSelected(false);
        mTextMe.setSelected(false);
//        mTextTimetable.setTextColor(getResources().getColor(R.color.colortabno));
//        mTextGrade.setTextColor(getResources().getColor(R.color.colortabno));
//        mTextTest.setTextColor(getResources().getColor(R.color.colortabno));
//        mTextMe.setTextColor(getResources().getColor(R.color.colortabno));

    }

    /**
     * 隐藏所有Fragment
     * @param transaction
     */
    public void hideAllFrament(FragmentTransaction transaction){
        if (timeTableFragment != null) {
            transaction.hide(timeTableFragment);
        }
        if (gradeFragment != null){
            transaction.hide(gradeFragment);
        }
        if (testFragment != null){
            transaction.hide(testFragment);
        }
        if (meFragment != null){
            transaction.hide(meFragment);
        }
//        if (loginFragment != null){
//            transaction.hide(loginFragment);
//        }
    }

    /**
     * 隐藏所有的title
     */
//    public void goneAllTitle(){
//        titleTimetable.setVisibility(View.GONE);
//        titleGrade.setVisibility(View.GONE);
//        titleTest.setVisibility(View.GONE);
//        titleMe.setVisibility(View.GONE);
//    }

    /**
     * 处理Login登录成功返回的数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//因为Fragment要取到数据，一定要加上这句
        switch (requestCode) {
            case 1000:
                if (resultCode == RESULT_OK) {
                    panduan = 1;
                }
                break;
            default:
        }
    }

    /**
     * 添加显示LoginFragment
     */
//    public void addLoginFragment(){
//        if (loginFragment == null){
//            loginFragment = new LoginFragment();
//            fragmentTransaction.add(R.id.layout_content,loginFragment);//将Fragment添加到布局中
//        }else{
//            fragmentTransaction.show(loginFragment);
//        }
//    }

    /**
     * 控件的点击事件实现
     */
//    public void onClick(View v) {
//
//        fragmentTransaction = fragmentManager.beginTransaction();//开启另一个事物
//        hideAllFrament(fragmentTransaction);//隐藏所有Fragment方法
//        if (panduan == 0){
//            switch (v.getId()){
//                case R.id.tab_menu_timetable:
//                    selected();//重置所有文本的选中状态的方法
//                    mImageTimetable.setSelected(true);
//                    mTextTimetable.setSelected(true);
////                    addLoginFragment();
//                    break;
//                case R.id.tab_menu_grade:
//                    selected();
//                    mImageGrade.setSelected(true);
//                    mTextGrade.setSelected(true);
////                    addLoginFragment();
//                    break;
//                case R.id.tab_menu_test:
//                    selected();
//                    mImageTest.setSelected(true);
//                    mTextTest.setSelected(true);
////                    addLoginFragment();
//                    break;
//                case R.id.tab_menu_me:
//                    selected();
//                    mImageMe.setSelected(true);
//                    mTextMe.setSelected(true);
////                    addLoginFragment();
//                    break;
//            }
////            fragmentTransaction.commit();//提交事务
//        }else if (panduan == 1){
//            image_main_login.setVisibility(View.GONE);
//            text_main_login.setVisibility(View.GONE);
//            switch (v.getId()){
//                case R.id.tab_menu_timetable:
//                    selected();//重置所有文本的选中状态的方法
//                    mImageTimetable.setSelected(true);
//                    mTextTimetable.setSelected(true);
////                    mTextTimetable.setTextColor(getResources().getColor(R.color.colorBlue));
//                    if (timeTableFragment == null){
//                        timeTableFragment = new TimeTableFragment();
//                        fragmentTransaction.add(R.id.layout_content,timeTableFragment);//将Fragment添加到布局中
//                    }else{
//                        fragmentTransaction.show(timeTableFragment);
//                    }
//                    break;
//                case R.id.tab_menu_grade:
//                    selected();
//                    mImageGrade.setSelected(true);
//                    mTextGrade.setSelected(true);
////                    mTextGrade.setTextColor(getResources().getColor(R.color.colorBlue));
//                    if (gradeFragment == null){
//                        gradeFragment = new GradeFragment();
//                        fragmentTransaction.add(R.id.layout_content,gradeFragment);
//                    }else {
//                        fragmentTransaction.show(gradeFragment);
//                    }
//                    break;
//                case R.id.tab_menu_test:
//                    selected();
//                    mImageTest.setSelected(true);
//                    mTextTest.setSelected(true);
////                    mTextTest.setTextColor(getResources().getColor(R.color.colorBlue));
//                    if (testFragment == null){
//                        testFragment = new TestFragment();
//                        fragmentTransaction.add(R.id.layout_content,testFragment);
//                    }else {
//                        fragmentTransaction.show(testFragment);
//                    }
//                    break;
//                case R.id.tab_menu_me:
//                    selected();
//                    mImageMe.setSelected(true);
//                    mTextMe.setSelected(true);
////                    mTextMe.setTextColor(getResources().getColor(R.color.colorBlue));
//                    if (meFragment == null){
//                        meFragment = new MeFragment();
//                        fragmentTransaction.add(R.id.layout_content,meFragment);
//                    }else {
//                        fragmentTransaction.show(meFragment);
//                    }
//                    break;
//            }
//            fragmentTransaction.commit();//提交事务
//        }
//    }

    /**
     * 控件的点击事件实现
     * @param v
     */
    @Override
    public void onClick(View v) {

        fragmentTransaction = fragmentManager.beginTransaction();//开启另一个事物
        hideAllFrament(fragmentTransaction);//隐藏所有Fragment方法

            image_main_login.setVisibility(View.GONE);
            text_main_login.setVisibility(View.GONE);
            switch (v.getId()){
                case R.id.tab_menu_timetable:
                    selected();//重置所有文本的选中状态的方法
                    mImageTimetable.setSelected(true);
                    mTextTimetable.setSelected(true);
                    if (timeTableFragment == null){
                        timeTableFragment = new TimeTableFragment();
                        fragmentTransaction.add(R.id.layout_content,timeTableFragment);//将Fragment添加到布局中
                    }else{
                        fragmentTransaction.show(timeTableFragment);
                    }
                    break;
                case R.id.tab_menu_grade:
                    selected();
                    mImageGrade.setSelected(true);
                    mTextGrade.setSelected(true);
                    if (gradeFragment == null){
                        gradeFragment = new GradeFragment();
                        fragmentTransaction.add(R.id.layout_content,gradeFragment);
                    }else {
                        fragmentTransaction.show(gradeFragment);
                    }
                    break;
                case R.id.tab_menu_test:
                    selected();
                    mImageTest.setSelected(true);
                    mTextTest.setSelected(true);
                    if (testFragment == null){
                        testFragment = new TestFragment();
                        fragmentTransaction.add(R.id.layout_content,testFragment);
                    }else {
                        fragmentTransaction.show(testFragment);
                    }
                    break;
                case R.id.tab_menu_me:
                    selected();
                    mImageMe.setSelected(true);
                    mTextMe.setSelected(true);
                    if (meFragment == null){
                        meFragment = new MeFragment();
                        fragmentTransaction.add(R.id.layout_content,meFragment);
                    }else {
                        fragmentTransaction.show(meFragment);
                    }
                    break;
            }
            fragmentTransaction.commit();//提交事务
        }


}

