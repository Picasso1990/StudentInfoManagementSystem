package com.linguangyu.studentinfomanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.adapter.SelectAdapter;
import com.linguangyu.studentinfomanagementsystem.model.SeasonTable;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 光裕 on 2018/6/6.
 */

public class SelectActivity extends AppCompatActivity {

    private static final int GIVE_LIST_SEASON = 4;
    private List<SeasonTable> listSelect;
    private ImageView image_select_back;
    private TextView text_select_back;
    private ImageView image_select_chongzhi;
    private TextView text_select_chongzhi;
    private ListView listView_select;
    private SelectAdapter selectAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//设置去除标题
        setContentView(R.layout.activity_select);
        SysApplication.getInstance().addActivity(this);//添加入MyApplication
        getWindow().setStatusBarColor(0xff1C86EE);
        image_select_back = findViewById(R.id.image_select_back);
        text_select_back = findViewById(R.id.text_select_back);
        image_select_chongzhi = findViewById(R.id.image_select_chongzhi);
        text_select_chongzhi = findViewById(R.id.text_select_chongzhi);
        listView_select = findViewById(R.id.listView_select);

        queryMoreToMoreSS();

        //返回
        image_select_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        text_select_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //重置
        image_select_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","全部");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        text_select_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","全部");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    /**
     * 查询多对多关联，查询StudentUser关联SeasonTable
     */
    private void queryMoreToMoreSS(){
        BmobQuery<SeasonTable> query = new BmobQuery<>();
        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
        query.addWhereRelatedTo("season",new BmobPointer(studentUser));
        query.findObjects(new FindListener<SeasonTable>() {
            @Override
            public void done(List<SeasonTable> list, BmobException e) {
                if (e == null && (!list.isEmpty())){
                    Message messageSelect = handlerSelect.obtainMessage();
                    messageSelect.what = GIVE_LIST_SEASON;
                    messageSelect.obj = list;
                    handlerSelect.sendMessage(messageSelect);
                }else if (e == null && list.isEmpty()){
                    Toast.makeText(SelectActivity.this, "没有可选的", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SelectActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Handler handlerSelect = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST_SEASON:
                    listSelect = (List<SeasonTable>) msg.obj;

                    //用冒泡排序对listSelect进行排序
//                    for (int i = 0;i<listSelect.size();i++){
//                        for (int j = 1;j<listSelect.size()-i;j++){
//                            if (listSelect.get(j-1).getYearSeason().compareTo(listSelect.get(j-1).getYearSeason()) > 0){
//                                String temp = listSelect.get(j-1).getYearSeason();
//                                listSelect.get(j-1).setYearSeason(listSelect.get(j).getYearSeason());
//                                listSelect.get(j).setYearSeason(temp);
//                            }
//                        }
//                    }

                    selectAdapter = new SelectAdapter(SelectActivity.this,R.layout.item_select,listSelect);
                    listView_select.setAdapter(selectAdapter);
                    //ListView的点击事件
                    listView_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SeasonTable seasonTable = listSelect.get(position);
                            Intent intent = new Intent();
                            intent.putExtra("data_return",seasonTable.getObjectId());
                            intent.putExtra("data_season",seasonTable.getYearSeason());
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 快速排序
     * @param listSelect
     */
//    public void sort(List<SeasonTable> listSelect,int low,int high){
//        int start = low;
//        int end = high;
//        String key = listSelect.get(start).getYearSeason();
//        while (end>start){
//            while (end>start && (listSelect.get(end).getYearSeason().compareTo(key) >= 0)){
//                end --;
//            }
//            if ((listSelect.get(end).getYearSeason().compareTo(key) <= 0)){
//                String temp = listSelect.get(end).getYearSeason();
//                listSelect.get(end).setYearSeason(listSelect.get(start).getYearSeason());
//                listSelect.get(start).setYearSeason(temp);
//            }
//            while (end>start && (listSelect.get(start).getYearSeason().compareTo(key) <= 0)){
//                start++;
//            }
//            if (listSelect.get(start).getYearSeason().compareTo(key) >= 0){
//                String temp = listSelect.get(start).getYearSeason();
//                listSelect.get(start).setYearSeason(listSelect.get(end).getYearSeason());
//                listSelect.get(end).setYearSeason(temp);
//            }
//        }
//        if (start>0) sort(listSelect,low,start-1);
//        if(end<high) sort(listSelect,end+1,high);
//
//    }



}

