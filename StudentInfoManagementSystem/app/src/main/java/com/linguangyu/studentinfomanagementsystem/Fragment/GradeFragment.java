package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.activity.SelectActivity;
import com.linguangyu.studentinfomanagementsystem.adapter.GradeAdapter;
import com.linguangyu.studentinfomanagementsystem.adapter.GradeRecyclerViewAdapter;
import com.linguangyu.studentinfomanagementsystem.model.GradeTable;
import com.linguangyu.studentinfomanagementsystem.model.SeasonTable;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;
import com.linguangyu.studentinfomanagementsystem.model.TimetableTable;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 光裕 on 2018/5/31.
 * 成绩Fragment
 */

public class GradeFragment extends Fragment {

    private static final int GIVE_LIST_GRADE = 1;
    private static final int GIVE_LIST_SEASON_GRADE = 2;

//    private ListView listViewGrade;
    private RecyclerView recyclerViewGrade;
    private TextView text_num_course_total;
    private TextView text_num_course_true;
    private ImageView image_true;
    private ImageView image_false;
    private ImageView image_gpa;
    private TextView text_gpa;
    private ImageView image_select;
    private TextView text_select;
    private TextView select_season;

    private List<GradeTable> listGrade;
    private List<GradeTable> listSeasonGrade;
    private List<GradeTable> listNull;
    private GradeRecyclerViewAdapter gradeRecyclerViewAdapter;

    private static String returnedData = "";
    private static String returnedSeason = "";

    public GradeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grade,container,false);

        text_num_course_total = view.findViewById(R.id.text_num_course_total);
        text_num_course_true = view.findViewById(R.id.text_num_course_true);
        select_season = view.findViewById(R.id.select_season);
        image_true = view.findViewById(R.id.image_true);
        image_false = view.findViewById(R.id.image_false);
        image_gpa = view.findViewById(R.id.image_gpa);
        text_gpa = view.findViewById(R.id.text_gpa);
        image_select = view.findViewById(R.id.image_select);
        text_select = view.findViewById(R.id.text_select);
//        listViewGrade = view.findViewById(R.id.listView_grade);
        recyclerViewGrade = view.findViewById(R.id.recyclerView_grade);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewGrade.setLayoutManager(linearLayoutManager);
        queryMoreToMoreSG();
//        queryMoreToMoreSeasonG();


        image_select.setOnClickListener(new View.OnClickListener() {//跳转到筛选界面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivityForResult(intent,10);
            }
        });
        text_select.setOnClickListener(new View.OnClickListener() {//跳转到筛选界面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivityForResult(intent, 10);
            }
        });

        return view;
    }


    /**
     * 查询多对多关联，StudentUser关联GradeTable
     */
    private void queryMoreToMoreSG(){

        BmobQuery<GradeTable> query = new BmobQuery<>();
        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
        query.addWhereRelatedTo("grade",new BmobPointer(studentUser));
        query.findObjects(new FindListener<GradeTable>() {
            @Override
            public void done(List<GradeTable> list, BmobException e) {
                if (e==null && (!list.isEmpty())){
                    Message message = handlerGrade.obtainMessage();
                    message.what = GIVE_LIST_GRADE;
                    message.obj = list;
                    handlerGrade.sendMessage(message);
                }else if (e == null && list.isEmpty()){
                    Toast.makeText(getActivity(),"没有成绩",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 查询多对多关联，SeasonTable关联GradeTable
     */
    private void queryMoreToMoreSeasonG(){

        BmobQuery<GradeTable> query = new BmobQuery<>();
        SeasonTable seasonTable = new SeasonTable();
        seasonTable.setObjectId(returnedData);
        query.addWhereRelatedTo("gradeSeason",new BmobPointer(seasonTable));
        query.findObjects(new FindListener<GradeTable>() {
            @Override
            public void done(List<GradeTable> list, BmobException e) {
                if (e==null && (!list.isEmpty())){
                    Message message = handlerSeasonGrade.obtainMessage();
                    message.what = GIVE_LIST_SEASON_GRADE;
                    message.obj = list;
                    handlerSeasonGrade.sendMessage(message);
                }else if (e == null && list.isEmpty()){
                    Toast.makeText(getActivity(),"没有成绩",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * SeasonTable关联查询GradeTable的Handler
     */
    private Handler handlerSeasonGrade = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST_SEASON_GRADE:
                    listSeasonGrade = (List<GradeTable>) msg.obj;
                    gradeRecyclerViewAdapter = new GradeRecyclerViewAdapter(listSeasonGrade);
                    recyclerViewGrade.setAdapter(gradeRecyclerViewAdapter);

                    select_season.setText(returnedSeason);//显示选择的季节

                    int isTrueSeason = 0;
                    float gpaSeason = 0;
                    float totalFen = 0;
                    float totalXueFen = 0;
                    for (int i = 0;i<listSeasonGrade.size();i++){
                        if (listSeasonGrade.get(i).getFenshu() < 60){//找到多少个没有几个的
                            isTrueSeason++;
                        }else {
                            //计算绩点，没有及格的科目不算在绩点里
                            totalFen = totalFen + ((listSeasonGrade.get(i).getFenshu())*(listSeasonGrade.get(i).getXuefen()));
                            totalXueFen = totalXueFen + listSeasonGrade.get(i).getXuefen();
                        }
                    }
                    gpaSeason = totalFen/totalXueFen;
                    gpaSeason = (gpaSeason-50)/10;
                    text_gpa.setText(gpaSeason+"");
                    if (isTrueSeason != 0){
                        text_num_course_total.setText(listSeasonGrade.size()+"");
                        text_num_course_true.setText((listSeasonGrade.size()-isTrueSeason)+"");
                        image_false.setVisibility(View.VISIBLE);
                    }else {
                        text_num_course_total.setText(listSeasonGrade.size()+"");
                        text_num_course_true.setText(listSeasonGrade.size()+"");
                        image_true.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    };



    /**
     * StudentUser关联查询GradeTable的Handler
     */
    private Handler handlerGrade = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST_GRADE:
                    listGrade = (List<GradeTable>) msg.obj;
                    gradeRecyclerViewAdapter = new GradeRecyclerViewAdapter(listGrade);
                    recyclerViewGrade.setAdapter(gradeRecyclerViewAdapter);

                    select_season.setText("全部");//显示全部的成绩

                    int isTrue = 0;
                    float gpa = 0;
                    float totalFen = 0;
                    float totalXueFen = 0;
                    for (int i = 0;i<listGrade.size();i++){
                        if (listGrade.get(i).getFenshu() < 60){//找到多少个没有几个的
                            isTrue++;
                        }else {
                            //计算绩点，没有及格的科目不算在绩点里
                            totalFen = totalFen + ((listGrade.get(i).getFenshu())*(listGrade.get(i).getXuefen()));
                            totalXueFen = totalXueFen + listGrade.get(i).getXuefen();
                        }
                    }
                    gpa = totalFen/totalXueFen;
                    gpa = (gpa-50)/10;
                    text_gpa.setText(gpa+"");
                    if (isTrue != 0){
                        text_num_course_total.setText(listGrade.size()+"");
                        text_num_course_true.setText((listGrade.size()-isTrue)+"");
                        image_false.setVisibility(View.VISIBLE);
                    }else {
                        text_num_course_total.setText(listGrade.size()+"");
                        text_num_course_true.setText(listGrade.size()+"");
                        image_true.setVisibility(View.VISIBLE);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(getActivity(),"2222",Toast.LENGTH_SHORT).show();
        switch (requestCode){
            case 10:
                if (resultCode == RESULT_OK){
                    returnedData = data.getStringExtra("data_return");
                    returnedSeason = data.getStringExtra("data_season");
//                    Toast.makeText(getActivity(),"1111",Toast.LENGTH_SHORT).show();
                    if (returnedData.equals("全部")){
                        queryMoreToMoreSG();
                    }else {
                        queryMoreToMoreSeasonG();
                    }
                }
        }
    }
}
