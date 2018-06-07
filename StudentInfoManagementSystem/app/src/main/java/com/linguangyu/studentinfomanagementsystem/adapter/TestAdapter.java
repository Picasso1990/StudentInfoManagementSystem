package com.linguangyu.studentinfomanagementsystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.model.TestTable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by 光裕 on 2018/6/7.
 */

public class TestAdapter extends ArrayAdapter<TestTable> {

    private int resourceId;

    public TestAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<TestTable> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TestTable testTable = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text_test_courseName = view.findViewById(R.id.text_test_courseName);
            viewHolder.text_test_timeStart = view.findViewById(R.id.text_test_timeStart);
            viewHolder.text_test_timeLength = view.findViewById(R.id.text_test_timeLength);
            viewHolder.text_test_testClass = view.findViewById(R.id.text_test_testClass);
            viewHolder.text_test_testTeacher = view.findViewById(R.id.text_test_testTeacher);
            viewHolder.text_test_place = view.findViewById(R.id.text_test_place);
            viewHolder.text_test_campus = view.findViewById(R.id.text_test_campus);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text_test_courseName.setText(testTable.getCourseName());
        viewHolder.text_test_timeStart.setText(testTable.getTestTime());
        viewHolder.text_test_timeLength.setText("("+testTable.getTestLength()+")");
        viewHolder.text_test_testClass.setText(testTable.getTestClass());
        viewHolder.text_test_testTeacher.setText(testTable.getTestTeacher());
        viewHolder.text_test_place.setText(testTable.getTestPlase());
        viewHolder.text_test_campus.setText(testTable.getTestCompus());

        return view;
    }

    class ViewHolder {
        TextView text_test_courseName;
        TextView text_test_timeStart;
        TextView text_test_timeLength;
        TextView text_test_testClass;
        TextView text_test_testTeacher;
        TextView text_test_place;
        TextView text_test_campus;
    }

}
