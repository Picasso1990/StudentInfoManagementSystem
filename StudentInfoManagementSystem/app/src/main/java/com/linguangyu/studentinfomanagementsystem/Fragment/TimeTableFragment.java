package com.linguangyu.studentinfomanagementsystem.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.activity.LoginActivity;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;
import com.linguangyu.studentinfomanagementsystem.model.TimetableTable;
import com.linguangyu.studentinfomanagementsystem.model.Weeks;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 光裕 on 2018/5/30.
 * 课程表Fragment
 */

public class TimeTableFragment extends Fragment implements View.OnClickListener{

    private View view;

    //星期一的RelativaLayout
    private RelativeLayout relatilayout_1_12;
    private RelativeLayout relatilayout_1_34;
    private RelativeLayout relatilayout_1_67;
    private RelativeLayout relatilayout_1_89;
    private RelativeLayout relatilayout_1_1012;

    //星期二的RelativaLayout
    private RelativeLayout relatilayout_2_12;
    private RelativeLayout relatilayout_2_34;
    private RelativeLayout relatilayout_2_67;
    private RelativeLayout relatilayout_2_89;
    private RelativeLayout relatilayout_2_1012;

    //星期三的RelativaLayout
    private RelativeLayout relatilayout_3_12;
    private RelativeLayout relatilayout_3_34;
    private RelativeLayout relatilayout_3_67;
    private RelativeLayout relatilayout_3_89;
    private RelativeLayout relatilayout_3_1012;

    //星期四的RelativaLayout
    private RelativeLayout relatilayout_4_12;
    private RelativeLayout relatilayout_4_34;
    private RelativeLayout relatilayout_4_67;
    private RelativeLayout relatilayout_4_89;
    private RelativeLayout relatilayout_4_1012;

    //星期五的RelativaLayout
    private RelativeLayout relatilayout_5_12;
    private RelativeLayout relatilayout_5_34;
    private RelativeLayout relatilayout_5_67;
    private RelativeLayout relatilayout_5_89;
    private RelativeLayout relatilayout_5_1012;

    //星期一的布局控件
    private TextView text_1_time_12;
    private TextView text_1_classname_12;
    private TextView text_1_teacher_12;
    private TextView text_1_place_12;
    private TextView text_1_time_34;
    private TextView text_1_classname_34;
    private TextView text_1_teacher_34;
    private TextView text_1_place_34;
    private TextView text_1_time_67;
    private TextView text_1_classname_67;
    private TextView text_1_teacher_67;
    private TextView text_1_place_67;
    private TextView text_1_time_89;
    private TextView text_1_classname_89;
    private TextView text_1_teacher_89;
    private TextView text_1_place_89;
    private TextView text_1_time_1012;
    private TextView text_1_classname_1012;
    private TextView text_1_teacher_1012;
    private TextView text_1_place_1012;

    //星期二的布局控件
    private TextView text_2_time_12;
    private TextView text_2_classname_12;
    private TextView text_2_teacher_12;
    private TextView text_2_place_12;
    private TextView text_2_time_34;
    private TextView text_2_classname_34;
    private TextView text_2_teacher_34;
    private TextView text_2_place_34;
    private TextView text_2_time_67;
    private TextView text_2_classname_67;
    private TextView text_2_teacher_67;
    private TextView text_2_place_67;
    private TextView text_2_time_89;
    private TextView text_2_classname_89;
    private TextView text_2_teacher_89;
    private TextView text_2_place_89;
    private TextView text_2_time_1012;
    private TextView text_2_classname_1012;
    private TextView text_2_teacher_1012;
    private TextView text_2_place_1012;

    //星期三的布局控件
    private TextView text_3_time_12;
    private TextView text_3_classname_12;
    private TextView text_3_teacher_12;
    private TextView text_3_place_12;
    private TextView text_3_time_34;
    private TextView text_3_classname_34;
    private TextView text_3_teacher_34;
    private TextView text_3_place_34;
    private TextView text_3_time_67;
    private TextView text_3_classname_67;
    private TextView text_3_teacher_67;
    private TextView text_3_place_67;
    private TextView text_3_time_89;
    private TextView text_3_classname_89;
    private TextView text_3_teacher_89;
    private TextView text_3_place_89;
    private TextView text_3_time_1012;
    private TextView text_3_classname_1012;
    private TextView text_3_teacher_1012;
    private TextView text_3_place_1012;

    //星期四的布局控件
    private TextView text_4_time_12;
    private TextView text_4_classname_12;
    private TextView text_4_teacher_12;
    private TextView text_4_place_12;
    private TextView text_4_time_34;
    private TextView text_4_classname_34;
    private TextView text_4_teacher_34;
    private TextView text_4_place_34;
    private TextView text_4_time_67;
    private TextView text_4_classname_67;
    private TextView text_4_teacher_67;
    private TextView text_4_place_67;
    private TextView text_4_time_89;
    private TextView text_4_classname_89;
    private TextView text_4_teacher_89;
    private TextView text_4_place_89;
    private TextView text_4_time_1012;
    private TextView text_4_classname_1012;
    private TextView text_4_teacher_1012;
    private TextView text_4_place_1012;

    //星期五的布局控件
    private TextView text_5_time_12;
    private TextView text_5_classname_12;
    private TextView text_5_teacher_12;
    private TextView text_5_place_12;
    private TextView text_5_time_34;
    private TextView text_5_classname_34;
    private TextView text_5_teacher_34;
    private TextView text_5_place_34;
    private TextView text_5_time_67;
    private TextView text_5_classname_67;
    private TextView text_5_teacher_67;
    private TextView text_5_place_67;
    private TextView text_5_time_89;
    private TextView text_5_classname_89;
    private TextView text_5_teacher_89;
    private TextView text_5_place_89;
    private TextView text_5_time_1012;
    private TextView text_5_classname_1012;
    private TextView text_5_teacher_1012;
    private TextView text_5_place_1012;

    private static final int GIVE_LIST = 1;
    private static final int GIVE_LIST_WEEK = 2;
    private static final int GIVE_WEEK = 3;

    private List<TimetableTable> list;
    private List<TimetableTable> listWeeks;
//    List<TimetableTable> listCommon;

    //头部控件
    private RelativeLayout relativeLayout_upward;
    private RelativeLayout relativeLayout_down;
    private TextView text_title_timetable_week;

    private static int selectWeek = 1;
    private String objectId = "lQEMDDDP";

    public TimeTableFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_timetable,container,false);//加载timetable_fragment布局

        init();
        relativeLayoutGone();
        queryMoreToMore();

        return view;
    }


    /**
     * 初始化控件
     */
    private void init(){

        //星期一RelativeLayout
        relatilayout_1_12 = view.findViewById(R.id.relatilayout_1_12);
        relatilayout_1_34 = view.findViewById(R.id.relatilayout_1_34);
        relatilayout_1_67 = view.findViewById(R.id.relatilayout_1_67);
        relatilayout_1_89 = view.findViewById(R.id.relatilayout_1_89);
        relatilayout_1_1012 = view.findViewById(R.id.relatilayout_1_1012);

        //星期二RelativeLayout
        relatilayout_2_12 = view.findViewById(R.id.relatilayout_2_12);
        relatilayout_2_34 = view.findViewById(R.id.relatilayout_2_34);
        relatilayout_2_67 = view.findViewById(R.id.relatilayout_2_67);
        relatilayout_2_89 = view.findViewById(R.id.relatilayout_2_89);
        relatilayout_2_1012 = view.findViewById(R.id.relatilayout_2_1012);

        //星期三RelativeLayout
        relatilayout_3_12 = view.findViewById(R.id.relatilayout_3_12);
        relatilayout_3_34 = view.findViewById(R.id.relatilayout_3_34);
        relatilayout_3_67 = view.findViewById(R.id.relatilayout_3_67);
        relatilayout_3_89 = view.findViewById(R.id.relatilayout_3_89);
        relatilayout_3_1012 = view.findViewById(R.id.relatilayout_3_1012);

        //星期四RelativeLayout
        relatilayout_4_12 = view.findViewById(R.id.relatilayout_4_12);
        relatilayout_4_34 = view.findViewById(R.id.relatilayout_4_34);
        relatilayout_4_67 = view.findViewById(R.id.relatilayout_4_67);
        relatilayout_4_89 = view.findViewById(R.id.relatilayout_4_89);
        relatilayout_4_1012 = view.findViewById(R.id.relatilayout_4_1012);

        //星期五RelativeLayout
        relatilayout_5_12 = view.findViewById(R.id.relatilayout_5_12);
        relatilayout_5_34 = view.findViewById(R.id.relatilayout_5_34);
        relatilayout_5_67 = view.findViewById(R.id.relatilayout_5_67);
        relatilayout_5_89 = view.findViewById(R.id.relatilayout_5_89);
        relatilayout_5_1012 = view.findViewById(R.id.relatilayout_5_1012);

        //星期一TextView
        text_1_time_12 = view.findViewById(R.id.text_1_time_12);
        text_1_classname_12 = view.findViewById(R.id.text_1_classname_12);
        text_1_teacher_12 = view.findViewById(R.id.text_1_teacher_12);
        text_1_place_12 = view.findViewById(R.id.text_1_place_12);
        text_1_time_34 = view.findViewById(R.id.text_1_time_34);
        text_1_classname_34 = view.findViewById(R.id.text_1_classname_34);
        text_1_teacher_34 = view.findViewById(R.id.text_1_teacher_34);
        text_1_place_34 = view.findViewById(R.id.text_1_place_34);
        text_1_time_67 = view.findViewById(R.id.text_1_time_67);
        text_1_classname_67 = view.findViewById(R.id.text_1_classname_67);
        text_1_teacher_67 = view.findViewById(R.id.text_1_teacher_67);
        text_1_place_67 = view.findViewById(R.id.text_1_place_67);
        text_1_time_89 = view.findViewById(R.id.text_1_time_89);
        text_1_classname_89 = view.findViewById(R.id.text_1_classname_89);
        text_1_teacher_89 = view.findViewById(R.id.text_1_teacher_89);
        text_1_place_89 = view.findViewById(R.id.text_1_place_89);
        text_1_time_1012 = view.findViewById(R.id.text_1_time_1012);
        text_1_classname_1012 = view.findViewById(R.id.text_1_classname_1012);
        text_1_teacher_1012 = view.findViewById(R.id.text_1_teacher_1012);
        text_1_place_1012 = view.findViewById(R.id.text_1_place_1012);

        //星期二TextView
        text_2_time_12 = view.findViewById(R.id.text_2_time_12);
        text_2_classname_12 = view.findViewById(R.id.text_2_classname_12);
        text_2_teacher_12 = view.findViewById(R.id.text_2_teacher_12);
        text_2_place_12 = view.findViewById(R.id.text_2_place_12);
        text_2_time_34 = view.findViewById(R.id.text_2_time_34);
        text_2_classname_34 = view.findViewById(R.id.text_2_classname_34);
        text_2_teacher_34 = view.findViewById(R.id.text_2_teacher_34);
        text_2_place_34 = view.findViewById(R.id.text_2_place_34);
        text_2_time_67 = view.findViewById(R.id.text_2_time_67);
        text_2_classname_67 = view.findViewById(R.id.text_2_classname_67);
        text_2_teacher_67 = view.findViewById(R.id.text_2_teacher_67);
        text_2_place_67 = view.findViewById(R.id.text_2_place_67);
        text_2_time_89 = view.findViewById(R.id.text_2_time_89);
        text_2_classname_89 = view.findViewById(R.id.text_2_classname_89);
        text_2_teacher_89 = view.findViewById(R.id.text_2_teacher_89);
        text_2_place_89 = view.findViewById(R.id.text_2_place_89);
        text_2_time_1012 = view.findViewById(R.id.text_2_time_1012);
        text_2_classname_1012 = view.findViewById(R.id.text_2_classname_1012);
        text_2_teacher_1012 = view.findViewById(R.id.text_2_teacher_1012);
        text_2_place_1012 = view.findViewById(R.id.text_2_place_1012);

        //星期三TextView
        text_3_time_12 = view.findViewById(R.id.text_3_time_12);
        text_3_classname_12 = view.findViewById(R.id.text_3_classname_12);
        text_3_teacher_12 = view.findViewById(R.id.text_3_teacher_12);
        text_3_place_12 = view.findViewById(R.id.text_3_place_12);
        text_3_time_34 = view.findViewById(R.id.text_3_time_34);
        text_3_classname_34 = view.findViewById(R.id.text_3_classname_34);
        text_3_teacher_34 = view.findViewById(R.id.text_3_teacher_34);
        text_3_place_34 = view.findViewById(R.id.text_3_place_34);
        text_3_time_67 = view.findViewById(R.id.text_3_time_67);
        text_3_classname_67 = view.findViewById(R.id.text_3_classname_67);
        text_3_teacher_67 = view.findViewById(R.id.text_3_teacher_67);
        text_3_place_67 = view.findViewById(R.id.text_3_place_67);
        text_3_time_89 = view.findViewById(R.id.text_3_time_89);
        text_3_classname_89 = view.findViewById(R.id.text_3_classname_89);
        text_3_teacher_89 = view.findViewById(R.id.text_3_teacher_89);
        text_3_place_89 = view.findViewById(R.id.text_3_place_89);
        text_3_time_1012 = view.findViewById(R.id.text_3_time_1012);
        text_3_classname_1012 = view.findViewById(R.id.text_3_classname_1012);
        text_3_teacher_1012 = view.findViewById(R.id.text_3_teacher_1012);
        text_3_place_1012 = view.findViewById(R.id.text_3_place_1012);

        //星期四TextView
        text_4_time_12 = view.findViewById(R.id.text_4_time_12);
        text_4_classname_12 = view.findViewById(R.id.text_4_classname_12);
        text_4_teacher_12 = view.findViewById(R.id.text_4_teacher_12);
        text_4_place_12 = view.findViewById(R.id.text_4_place_12);
        text_4_time_34 = view.findViewById(R.id.text_4_time_34);
        text_4_classname_34 = view.findViewById(R.id.text_4_classname_34);
        text_4_teacher_34 = view.findViewById(R.id.text_4_teacher_34);
        text_4_place_34 = view.findViewById(R.id.text_4_place_34);
        text_4_time_67 = view.findViewById(R.id.text_4_time_67);
        text_4_classname_67 = view.findViewById(R.id.text_4_classname_67);
        text_4_teacher_67 = view.findViewById(R.id.text_4_teacher_67);
        text_4_place_67 = view.findViewById(R.id.text_4_place_67);
        text_4_time_89 = view.findViewById(R.id.text_4_time_89);
        text_4_classname_89 = view.findViewById(R.id.text_4_classname_89);
        text_4_teacher_89 = view.findViewById(R.id.text_4_teacher_89);
        text_4_place_89 = view.findViewById(R.id.text_4_place_89);
        text_4_time_1012 = view.findViewById(R.id.text_4_time_1012);
        text_4_classname_1012 = view.findViewById(R.id.text_4_classname_1012);
        text_4_teacher_1012 = view.findViewById(R.id.text_4_teacher_1012);
        text_4_place_1012 = view.findViewById(R.id.text_4_place_1012);

        //星期五TextView
        text_5_time_12 = view.findViewById(R.id.text_5_time_12);
        text_5_classname_12 = view.findViewById(R.id.text_5_classname_12);
        text_5_teacher_12 = view.findViewById(R.id.text_5_teacher_12);
        text_5_place_12 = view.findViewById(R.id.text_5_place_12);
        text_5_time_34 = view.findViewById(R.id.text_5_time_34);
        text_5_classname_34 = view.findViewById(R.id.text_5_classname_34);
        text_5_teacher_34 = view.findViewById(R.id.text_5_teacher_34);
        text_5_place_34 = view.findViewById(R.id.text_5_place_34);
        text_5_time_67 = view.findViewById(R.id.text_5_time_67);
        text_5_classname_67 = view.findViewById(R.id.text_5_classname_67);
        text_5_teacher_67 = view.findViewById(R.id.text_5_teacher_67);
        text_5_place_67 = view.findViewById(R.id.text_5_place_67);
        text_5_time_89 = view.findViewById(R.id.text_5_time_89);
        text_5_classname_89 = view.findViewById(R.id.text_5_classname_89);
        text_5_teacher_89 = view.findViewById(R.id.text_5_teacher_89);
        text_5_place_89 = view.findViewById(R.id.text_5_place_89);
        text_5_time_1012 = view.findViewById(R.id.text_5_time_1012);
        text_5_classname_1012 = view.findViewById(R.id.text_5_classname_1012);
        text_5_teacher_1012 = view.findViewById(R.id.text_5_teacher_1012);
        text_5_place_1012 = view.findViewById(R.id.text_5_place_1012);

        //初始化头部控件
        relativeLayout_upward = view.findViewById(R.id.relatilayout_upward);
        relativeLayout_down = view.findViewById(R.id.relatilayout_down);
        text_title_timetable_week = view.findViewById(R.id.text_title_timetable_week);

        relativeLayout_upward.setOnClickListener(this);
        relativeLayout_down.setOnClickListener(this);

    }

    /**
     * 将所有的RelativeLayout隐藏
     */
    private void relativeLayoutGone(){

        relatilayout_1_12.setVisibility(View.GONE);
        relatilayout_1_34.setVisibility(View.GONE);
        relatilayout_1_67.setVisibility(View.GONE);
        relatilayout_1_89.setVisibility(View.GONE);
        relatilayout_1_1012.setVisibility(View.GONE);

        relatilayout_2_12.setVisibility(View.GONE);
        relatilayout_2_34.setVisibility(View.GONE);
        relatilayout_2_67.setVisibility(View.GONE);
        relatilayout_2_89.setVisibility(View.GONE);
        relatilayout_2_1012.setVisibility(View.GONE);

        relatilayout_3_12.setVisibility(View.GONE);
        relatilayout_3_34.setVisibility(View.GONE);
        relatilayout_3_67.setVisibility(View.GONE);
        relatilayout_3_89.setVisibility(View.GONE);
        relatilayout_3_1012.setVisibility(View.GONE);

        relatilayout_4_12.setVisibility(View.GONE);
        relatilayout_4_34.setVisibility(View.GONE);
        relatilayout_4_67.setVisibility(View.GONE);
        relatilayout_4_89.setVisibility(View.GONE);
        relatilayout_4_1012.setVisibility(View.GONE);

        relatilayout_5_12.setVisibility(View.GONE);
        relatilayout_5_34.setVisibility(View.GONE);
        relatilayout_5_67.setVisibility(View.GONE);
        relatilayout_5_89.setVisibility(View.GONE);
        relatilayout_5_1012.setVisibility(View.GONE);

    }

    /**
     * 查询多对多关联，StudentUser关联TimetableTable
     */
    private void queryMoreToMore(){

        BmobQuery<TimetableTable> query = new BmobQuery<>();
        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
        query.addWhereRelatedTo("timetable",new BmobPointer(studentUser));
        query.findObjects(new FindListener<TimetableTable>() {
            @Override
            public void done(List<TimetableTable> list, BmobException e) {
                if (e==null && (!list.isEmpty())){
                    Message message = handler.obtainMessage();
                    message.what = GIVE_LIST;
                    message.obj = list;
                    handler.sendMessage(message);
                }else if (e == null && list.isEmpty()){
                    Toast.makeText(getActivity(),"没有课程2",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 查询多对多关联，TimetableTable关联Weeks
     */
    private void queryMoreToMoreWeeks(){
        BmobQuery<TimetableTable> queryWeeks = new BmobQuery<>();
        Weeks weeks = new Weeks();
        weeks.setObjectId(objectId);
        queryWeeks.addWhereRelatedTo("timetableWeeks",new BmobPointer(weeks));
        queryWeeks.findObjects(new FindListener<TimetableTable>() {
            @Override
            public void done(List<TimetableTable> listWeeks, BmobException e) {
                if (e==null && (!list.isEmpty())){
                    Message messageWeeks = handlerWeeks.obtainMessage();
                    messageWeeks.what = GIVE_LIST_WEEK;
                    messageWeeks.obj = listWeeks;
                    handlerWeeks.sendMessage(messageWeeks);
                }else if (e == null && list.isEmpty()){
                    Toast.makeText(getActivity(),"没有课程Weeks",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 查询周数
     */
    private void queryWeeks(){
        BmobQuery<Weeks> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("week",selectWeek);
        query1.findObjects(new FindListener<Weeks>() {
            @Override
            public void done(List<Weeks> list, BmobException e) {
                if (e == null && (!list.isEmpty())){
                    Message message_1 = handler_1.obtainMessage();
                    message_1.what = GIVE_WEEK;
                    message_1.obj = list.get(0).getObjectId();
                    handler_1.sendMessage(message_1);
                }else if (e == null && (list.size()==0)){
                    Toast.makeText(getActivity(),"没有找到该周",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * StudentUser关联查询TimetableTable的Handler
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST:
                    list = (List<TimetableTable>) msg.obj;
                    queryMoreToMoreWeeks();
//                    playText(list);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Weeks关联查询TimetableTable的Handler
     */
    private Handler handlerWeeks = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST_WEEK:
                    listWeeks = (List<TimetableTable>) msg.obj;
                    findCommonTimetable(listWeeks,list);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 查询当前周的Handler
     */
    private Handler handler_1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_WEEK:
                    objectId = (String) msg.obj;
                    queryMoreToMore();
                    break;
                default:
                    break;
            }
        }
    };



    /**
     * 遍历查询Weeks和StudentUser得到的相同timetableTable
     * @param listWeeks1
     * @param list
     */
    private void findCommonTimetable(List<TimetableTable> listWeeks1,List<TimetableTable> list){

        for (int i = 0;i<listWeeks1.size();i++){
            for (int j = 0;j<list.size();j++){
                if (list.get(j).getObjectId().equals(listWeeks1.get(i).getObjectId())){
//                    Toast.makeText(getActivity(),list1.get(j).getCourseName(),Toast.LENGTH_SHORT).show();
                    if (list.get(j).getWeekend().equals("星期一")){
                        if (list.get(j).getPitchNumber().equals("1-2")){
                            relatilayout_1_12.setVisibility(View.VISIBLE);
                            text_1_classname_12.setText(list.get(j).getCourseName());
                            text_1_teacher_12.setText(list.get(j).getTeacher());
                            text_1_place_12.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("3-4")){
                            relatilayout_1_34.setVisibility(View.VISIBLE);
                            text_1_classname_34.setText(list.get(j).getCourseName());
                            text_1_teacher_34.setText(list.get(j).getTeacher());
                            text_1_place_34.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("6-7")){
                            relatilayout_1_67.setVisibility(View.VISIBLE);
                            text_1_classname_67.setText(list.get(j).getCourseName());
                            text_1_teacher_67.setText(list.get(j).getTeacher());
                            text_1_place_67.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("8-9")){
                            relatilayout_1_89.setVisibility(View.VISIBLE);
                            text_1_classname_89.setText(list.get(j).getCourseName());
                            text_1_teacher_89.setText(list.get(j).getTeacher());
                            text_1_place_89.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("10-12")){
                            relatilayout_1_1012.setVisibility(View.VISIBLE);
                            text_1_classname_1012.setText(list.get(j).getCourseName());
                            text_1_teacher_1012.setText(list.get(j).getTeacher());
                            text_1_place_1012.setText(list.get(j).getVenue());
                        }
                    }else if (list.get(j).getWeekend().equals("星期二")){
                        if (list.get(j).getPitchNumber().equals("1-2")){
                            relatilayout_2_12.setVisibility(View.VISIBLE);
                            text_2_classname_12.setText(list.get(j).getCourseName());
                            text_2_teacher_12.setText(list.get(j).getTeacher());
                            text_2_place_12.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("3-4")){
                            relatilayout_2_34.setVisibility(View.VISIBLE);
                            text_2_classname_34.setText(list.get(j).getCourseName());
                            text_2_teacher_34.setText(list.get(j).getTeacher());
                            text_2_place_34.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("6-7")){
                            relatilayout_2_67.setVisibility(View.VISIBLE);
                            text_2_classname_67.setText(list.get(j).getCourseName());
                            text_2_teacher_67.setText(list.get(j).getTeacher());
                            text_2_place_67.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("8-9")){
                            relatilayout_2_89.setVisibility(View.VISIBLE);
                            text_2_classname_89.setText(list.get(j).getCourseName());
                            text_2_teacher_89.setText(list.get(j).getTeacher());
                            text_2_place_89.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("10-12")){
                            relatilayout_2_1012.setVisibility(View.VISIBLE);
                            text_2_classname_1012.setText(list.get(j).getCourseName());
                            text_2_teacher_1012.setText(list.get(j).getTeacher());
                            text_2_place_1012.setText(list.get(j).getVenue());
                        }
                    }else if (list.get(j).getWeekend().equals("星期三")){
                        if (list.get(j).getPitchNumber().equals("1-2")){
                            relatilayout_3_12.setVisibility(View.VISIBLE);
                            text_3_classname_12.setText(list.get(j).getCourseName());
                            text_3_teacher_12.setText(list.get(j).getTeacher());
                            text_3_place_12.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("3-4")){
                            relatilayout_3_34.setVisibility(View.VISIBLE);
                            text_3_classname_34.setText(list.get(j).getCourseName());
                            text_3_teacher_34.setText(list.get(j).getTeacher());
                            text_3_place_34.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("6-7")){
                            relatilayout_3_67.setVisibility(View.VISIBLE);
                            text_3_classname_67.setText(list.get(j).getCourseName());
                            text_3_teacher_67.setText(list.get(j).getTeacher());
                            text_3_place_67.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("8-9")){
                            relatilayout_3_89.setVisibility(View.VISIBLE);
                            text_3_classname_89.setText(list.get(j).getCourseName());
                            text_3_teacher_89.setText(list.get(j).getTeacher());
                            text_3_place_89.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("10-12")){
                            relatilayout_3_1012.setVisibility(View.VISIBLE);
                            text_3_classname_1012.setText(list.get(j).getCourseName());
                            text_3_teacher_1012.setText(list.get(j).getTeacher());
                            text_3_place_1012.setText(list.get(j).getVenue());
                        }
                    }else if (list.get(j).getWeekend().equals("星期四")){
                        if (list.get(j).getPitchNumber().equals("1-2")){
                            relatilayout_4_12.setVisibility(View.VISIBLE);
                            text_4_classname_12.setText(list.get(j).getCourseName());
                            text_4_teacher_12.setText(list.get(j).getTeacher());
                            text_4_place_12.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("3-4")){
                            relatilayout_4_34.setVisibility(View.VISIBLE);
                            text_4_classname_34.setText(list.get(j).getCourseName());
                            text_4_teacher_34.setText(list.get(j).getTeacher());
                            text_4_place_34.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("6-7")){
                            relatilayout_4_67.setVisibility(View.VISIBLE);
                            text_4_classname_67.setText(list.get(j).getCourseName());
                            text_4_teacher_67.setText(list.get(j).getTeacher());
                            text_4_place_67.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("8-9")){
                            relatilayout_4_89.setVisibility(View.VISIBLE);
                            text_4_classname_89.setText(list.get(j).getCourseName());
                            text_4_teacher_89.setText(list.get(j).getTeacher());
                            text_4_place_89.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("10-12")){
                            relatilayout_4_1012.setVisibility(View.VISIBLE);
                            text_4_classname_1012.setText(list.get(j).getCourseName());
                            text_4_teacher_1012.setText(list.get(j).getTeacher());
                            text_4_place_1012.setText(list.get(j).getVenue());
                        }
                    }else if (list.get(j).getWeekend().equals("星期五")){
                        if (list.get(j).getPitchNumber().equals("1-2")){
                            relatilayout_5_12.setVisibility(View.VISIBLE);
                            text_5_classname_12.setText(list.get(j).getCourseName());
                            text_5_teacher_12.setText(list.get(j).getTeacher());
                            text_5_place_12.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("3-4")){
                            relatilayout_5_34.setVisibility(View.VISIBLE);
                            text_5_classname_34.setText(list.get(j).getCourseName());
                            text_5_teacher_34.setText(list.get(j).getTeacher());
                            text_5_place_34.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("6-7")){
                            relatilayout_5_67.setVisibility(View.VISIBLE);
                            text_5_classname_67.setText(list.get(j).getCourseName());
                            text_5_teacher_67.setText(list.get(j).getTeacher());
                            text_5_place_67.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("8-9")){
                            relatilayout_5_89.setVisibility(View.VISIBLE);
                            text_5_classname_89.setText(list.get(j).getCourseName());
                            text_5_teacher_89.setText(list.get(j).getTeacher());
                            text_5_place_89.setText(list.get(j).getVenue());
                        }else if (list.get(j).getPitchNumber().equals("10-12")){
                            relatilayout_5_1012.setVisibility(View.VISIBLE);
                            text_5_classname_1012.setText(list.get(j).getCourseName());
                            text_5_teacher_1012.setText(list.get(j).getTeacher());
                            text_5_place_1012.setText(list.get(j).getVenue());
                        }
                    }

                }
            }
        }
    }


    /**
     * 遍历数据
     * @param list
     */
    private void playText(List<TimetableTable> list){

            for (int j = 0;j<list.size();j++){

                if (list.get(j).getWeekend().equals("星期一")){
                    if (list.get(j).getPitchNumber().equals("1-2")){
                        relatilayout_1_12.setVisibility(View.VISIBLE);
                        text_1_classname_12.setText(list.get(j).getCourseName());
                        text_1_teacher_12.setText(list.get(j).getTeacher());
                        text_1_place_12.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("3-4")){
                        relatilayout_1_34.setVisibility(View.VISIBLE);
                        text_1_classname_34.setText(list.get(j).getCourseName());
                        text_1_teacher_34.setText(list.get(j).getTeacher());
                        text_1_place_34.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("6-7")){
                        relatilayout_1_67.setVisibility(View.VISIBLE);
                        text_1_classname_67.setText(list.get(j).getCourseName());
                        text_1_teacher_67.setText(list.get(j).getTeacher());
                        text_1_place_67.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("8-9")){
                        relatilayout_1_89.setVisibility(View.VISIBLE);
                        text_1_classname_89.setText(list.get(j).getCourseName());
                        text_1_teacher_89.setText(list.get(j).getTeacher());
                        text_1_place_89.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("10-12")){
                        relatilayout_1_1012.setVisibility(View.VISIBLE);
                        text_1_classname_1012.setText(list.get(j).getCourseName());
                        text_1_teacher_1012.setText(list.get(j).getTeacher());
                        text_1_place_1012.setText(list.get(j).getVenue());
                    }
                }else if (list.get(j).getWeekend().equals("星期二")){
                    if (list.get(j).getPitchNumber().equals("1-2")){
                        relatilayout_2_12.setVisibility(View.VISIBLE);
                        text_2_classname_12.setText(list.get(j).getCourseName());
                        text_2_teacher_12.setText(list.get(j).getTeacher());
                        text_2_place_12.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("3-4")){
                        relatilayout_2_34.setVisibility(View.VISIBLE);
                        text_2_classname_34.setText(list.get(j).getCourseName());
                        text_2_teacher_34.setText(list.get(j).getTeacher());
                        text_2_place_34.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("6-7")){
                        relatilayout_2_67.setVisibility(View.VISIBLE);
                        text_2_classname_67.setText(list.get(j).getCourseName());
                        text_2_teacher_67.setText(list.get(j).getTeacher());
                        text_2_place_67.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("8-9")){
                        relatilayout_2_89.setVisibility(View.VISIBLE);
                        text_2_classname_89.setText(list.get(j).getCourseName());
                        text_2_teacher_89.setText(list.get(j).getTeacher());
                        text_2_place_89.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("10-12")){
                        relatilayout_2_1012.setVisibility(View.VISIBLE);
                        text_2_classname_1012.setText(list.get(j).getCourseName());
                        text_2_teacher_1012.setText(list.get(j).getTeacher());
                        text_2_place_1012.setText(list.get(j).getVenue());
                    }
                }else if (list.get(j).getWeekend().equals("星期三")){
                    if (list.get(j).getPitchNumber().equals("1-2")){
                        relatilayout_3_12.setVisibility(View.VISIBLE);
                        text_3_classname_12.setText(list.get(j).getCourseName());
                        text_3_teacher_12.setText(list.get(j).getTeacher());
                        text_3_place_12.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("3-4")){
                        relatilayout_3_34.setVisibility(View.VISIBLE);
                        text_3_classname_34.setText(list.get(j).getCourseName());
                        text_3_teacher_34.setText(list.get(j).getTeacher());
                        text_3_place_34.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("6-7")){
                        relatilayout_3_67.setVisibility(View.VISIBLE);
                        text_3_classname_67.setText(list.get(j).getCourseName());
                        text_3_teacher_67.setText(list.get(j).getTeacher());
                        text_3_place_67.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("8-9")){
                        relatilayout_3_89.setVisibility(View.VISIBLE);
                        text_3_classname_89.setText(list.get(j).getCourseName());
                        text_3_teacher_89.setText(list.get(j).getTeacher());
                        text_3_place_89.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("10-12")){
                        relatilayout_3_1012.setVisibility(View.VISIBLE);
                        text_3_classname_1012.setText(list.get(j).getCourseName());
                        text_3_teacher_1012.setText(list.get(j).getTeacher());
                        text_3_place_1012.setText(list.get(j).getVenue());
                    }
                }else if (list.get(j).getWeekend().equals("星期四")){
                    if (list.get(j).getPitchNumber().equals("1-2")){
                        relatilayout_4_12.setVisibility(View.VISIBLE);
                        text_4_classname_12.setText(list.get(j).getCourseName());
                        text_4_teacher_12.setText(list.get(j).getTeacher());
                        text_4_place_12.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("3-4")){
                        relatilayout_4_34.setVisibility(View.VISIBLE);
                        text_4_classname_34.setText(list.get(j).getCourseName());
                        text_4_teacher_34.setText(list.get(j).getTeacher());
                        text_4_place_34.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("6-7")){
                        relatilayout_4_67.setVisibility(View.VISIBLE);
                        text_4_classname_67.setText(list.get(j).getCourseName());
                        text_4_teacher_67.setText(list.get(j).getTeacher());
                        text_4_place_67.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("8-9")){
                        relatilayout_4_89.setVisibility(View.VISIBLE);
                        text_4_classname_89.setText(list.get(j).getCourseName());
                        text_4_teacher_89.setText(list.get(j).getTeacher());
                        text_4_place_89.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("10-12")){
                        relatilayout_4_1012.setVisibility(View.VISIBLE);
                        text_4_classname_1012.setText(list.get(j).getCourseName());
                        text_4_teacher_1012.setText(list.get(j).getTeacher());
                        text_4_place_1012.setText(list.get(j).getVenue());
                    }
                }else if (list.get(j).getWeekend().equals("星期五")){
                    if (list.get(j).getPitchNumber().equals("1-2")){
                        relatilayout_5_12.setVisibility(View.VISIBLE);
                        text_5_classname_12.setText(list.get(j).getCourseName());
                        text_5_teacher_12.setText(list.get(j).getTeacher());
                        text_5_place_12.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("3-4")){
                        relatilayout_5_34.setVisibility(View.VISIBLE);
                        text_5_classname_34.setText(list.get(j).getCourseName());
                        text_5_teacher_34.setText(list.get(j).getTeacher());
                        text_5_place_34.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("6-7")){
                        relatilayout_5_67.setVisibility(View.VISIBLE);
                        text_5_classname_67.setText(list.get(j).getCourseName());
                        text_5_teacher_67.setText(list.get(j).getTeacher());
                        text_5_place_67.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("8-9")){
                        relatilayout_5_89.setVisibility(View.VISIBLE);
                        text_5_classname_89.setText(list.get(j).getCourseName());
                        text_5_teacher_89.setText(list.get(j).getTeacher());
                        text_5_place_89.setText(list.get(j).getVenue());
                    }else if (list.get(j).getPitchNumber().equals("10-12")){
                        relatilayout_5_1012.setVisibility(View.VISIBLE);
                        text_5_classname_1012.setText(list.get(j).getCourseName());
                        text_5_teacher_1012.setText(list.get(j).getTeacher());
                        text_5_place_1012.setText(list.get(j).getVenue());
                    }
                }
            }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relatilayout_upward:
                if (selectWeek > 1){
                    selectWeek = selectWeek - 1;
                    text_title_timetable_week.setText("(第"+selectWeek+"周)");
                    relativeLayoutGone();
                    queryWeeks();
                }else {
                    Toast.makeText(getActivity(),"周数不可过小",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.relatilayout_down:
                if (selectWeek <= 30){
                    selectWeek = selectWeek + 1;
                    text_title_timetable_week.setText("(第"+selectWeek+"周)");
                    relativeLayoutGone();
                    queryWeeks();
                }else {
                    Toast.makeText(getActivity(),"周数不可过大",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
