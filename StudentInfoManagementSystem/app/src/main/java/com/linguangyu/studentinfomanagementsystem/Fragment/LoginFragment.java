package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.activity.LoginActivity;
import com.linguangyu.studentinfomanagementsystem.activity.MainActivity;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;
import com.linguangyu.studentinfomanagementsystem.model.TimetableTable;
import com.linguangyu.studentinfomanagementsystem.model.UserTable;
import com.linguangyu.studentinfomanagementsystem.model.Weeks;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 光裕 on 2018/6/1.
 */

public class LoginFragment extends Fragment {

    private ImageView imagePleaseLogin;
    private Button button3;

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

        button3 = view.findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addMoreToMore();
//                queryMoreToMore();
            }
        });

        return view;
    }

    /**
     * 添加多对多
     */
    private void addMoreToMore(){

//        StudentUser studentUser = new StudentUser();
//        studentUser.setObjectId("3d06d85b58");
//        TimetableTable timetableTable = new TimetableTable();
//        timetableTable.setObjectId("ScYIXXXk");
//        BmobRelation relation1 = new BmobRelation();
//        relation1.add(timetableTable);
//        studentUser.setTimetable(relation1);
//        studentUser.update(new UpdateListener() {
//            @Override
//            public void done(BmobException e) {
//                if (e == null){
//                    Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getActivity(),"添加失败",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        Weeks weeks = new Weeks();
        weeks.setObjectId("mhyGDDDv");
        TimetableTable timetableTable = new TimetableTable();
        timetableTable.setObjectId("ATUREEEP");
        BmobRelation relation = new BmobRelation();
        relation.add(timetableTable);
        weeks.setTimetableWeeks(relation);
        weeks.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"添加失败",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 查询多对多关联
     */
    private void queryMoreToMore(){

//        BmobQuery<StudentUser> query = new BmobQuery<>();
//        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
//        query.addWhereEqualTo()
//        TimetableTable timetableTable = new TimetableTable();
//        timetableTable.setObjectId("eQpI444C");
//        query.addWhereRelatedTo("student",new BmobPointer(timetableTable));
//        query.addWhereEqualTo("name","林光裕");
//
////        query.addWhereContains("student",studentUser.getObjectId());
//
//        query.findObjects(new FindListener<StudentUser>() {
//            @Override
//            public void done(List<StudentUser> list, BmobException e) {
//                if (e == null){
//                    Toast.makeText(getActivity(),list.get(0).getObjectId(),Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        BmobQuery<Weeks> queryWeeks = new BmobQuery<>();
        TimetableTable timetableTable = new TimetableTable();
        timetableTable.setObjectId("eQpI444C");
        queryWeeks.addWhereRelatedTo("weeks",new BmobPointer(timetableTable));
//        queryWeeks.addWhereContains("weeks",timetableTable.getObjectId());
//        queryWeeks.addWhereEqualTo("week",1);
        queryWeeks.findObjects(new FindListener<Weeks>() {
            @Override
            public void done(List<Weeks> listWeeks, BmobException e) {
                if (e == null && (!listWeeks.isEmpty())) {
                    Toast.makeText(getActivity(), listWeeks.get(2).getObjectId(), Toast.LENGTH_SHORT).show();
                } else if (e == null && listWeeks.isEmpty()) {
                    Toast.makeText(getActivity(), "没有课程1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
       });
    }

}
