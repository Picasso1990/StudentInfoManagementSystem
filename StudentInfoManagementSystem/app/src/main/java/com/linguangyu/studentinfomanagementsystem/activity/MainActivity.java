package com.linguangyu.studentinfomanagementsystem.activity;

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
import com.linguangyu.studentinfomanagementsystem.Fragment.MeFragment;
import com.linguangyu.studentinfomanagementsystem.Fragment.TestFragment;
import com.linguangyu.studentinfomanagementsystem.Fragment.TimeTableFragment;
import com.linguangyu.studentinfomanagementsystem.R;

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

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//设置去除标题
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(0xff1C86EE);//设置状态栏和title颜色一致，0xff表示透明度100% 加上颜色值就行了。如上：颜色值是: 0xff + 1C86EE，要求API最小是21。
        fragmentManager = getSupportFragmentManager();
        init();
        mTabMenuTimetable.performClick();
    }

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

        mTabMenuTimetable.setOnClickListener(this);
        mTabMenuGrade.setOnClickListener(this);
        mTabMenuTest.setOnClickListener(this);
        mTabMenuMe.setOnClickListener(this);

    }

    /**
     * 重置所有文本的选中状态
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
    }

    /**
     * 控件的点击事件实现
     * @param v
     */
    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
        hideAllFrament(fragmentTransaction);//隐藏所有Fragment方法
        switch (v.getId()){
            case R.id.tab_menu_timetable:
                selected();//重置所有文本的选中状态的方法
                mImageTimetable.setSelected(true);
                mTextTimetable.setSelected(true);
//                mTextTimetable.setTextColor(getResources().getColor(R.color.colorBlue));
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
//                mTextGrade.setTextColor(getResources().getColor(R.color.colorBlue));
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
//                mTextTest.setTextColor(getResources().getColor(R.color.colorBlue));
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
//                mTextMe.setTextColor(getResources().getColor(R.color.colorBlue));
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
