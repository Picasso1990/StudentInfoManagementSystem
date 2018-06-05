package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.adapter.GradeAdapter;
import com.linguangyu.studentinfomanagementsystem.model.GradeTable;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;
import com.linguangyu.studentinfomanagementsystem.model.TimetableTable;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 光裕 on 2018/5/31.
 * 成绩Fragment
 */

public class GradeFragment extends Fragment {

    private static final int GIVE_LIST_GRADE = 1;
    private ListView listViewGrade;
    private TextView text_num_course_total;
    private TextView text_num_course_true;
    private ImageView image_true;
    private ImageView image_false;
    private ImageView image_gpa;
    private TextView text_gpa;

    private List<GradeTable> listGrade;
    private GradeAdapter gradeAdapter;

    public static int isTrue = 0;
    private float gpa = 0;

    public GradeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grade,container,false);

        text_num_course_total = view.findViewById(R.id.text_num_course_total);
        text_num_course_true = view.findViewById(R.id.text_num_course_true);
        image_true = view.findViewById(R.id.image_true);
        image_false = view.findViewById(R.id.image_false);
        image_gpa = view.findViewById(R.id.image_gpa);
        text_gpa = view.findViewById(R.id.text_gpa);
        listViewGrade = view.findViewById(R.id.listView_grade);
        queryMoreToMore();

//        image_gpa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text_gpa.setText(gpa+"");
//            }
//        });
//        text_gpa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text_gpa.setText(gpa+"");
//            }
//        });

        return view;
    }


    /**
     * 查询多对多关联，StudentUser关联TimetableTable
     */
    private void queryMoreToMore(){

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
     * StudentUser关联查询TimetableTable的Handler
     */
    private Handler handlerGrade = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST_GRADE:
                    listGrade = (List<GradeTable>) msg.obj;
                    gradeAdapter = new GradeAdapter(getActivity(),R.layout.item_grade,listGrade);
                    listViewGrade.setAdapter(gradeAdapter);

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
}
