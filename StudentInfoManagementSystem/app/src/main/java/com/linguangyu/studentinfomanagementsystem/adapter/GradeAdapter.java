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
import com.linguangyu.studentinfomanagementsystem.model.GradeTable;

import java.util.List;

/**
 * Created by 光裕 on 2018/6/5.
 */

public class GradeAdapter extends ArrayAdapter<GradeTable> {

    private int resourceId;

    public GradeAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<GradeTable> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GradeTable gradeTable = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text_grade_courseName = view.findViewById(R.id.text_grade_courseName);
            viewHolder.text_grade_bixiu = view.findViewById(R.id.text_grade_bixiu);
            viewHolder.text_grade_courseClass = view.findViewById(R.id.text_grade_courseClass);
            viewHolder.text_grade_testClass = view.findViewById(R.id.text_grade_testClass);
            viewHolder.text_grade_xuefen = view.findViewById(R.id.text_grade_xuefen);
            viewHolder.text_grade_fenshu = view.findViewById(R.id.text_grade_fenshu);
            viewHolder.view_grade_isPass = view.findViewById(R.id.view_grade_isPass);
            view.setTag(viewHolder);//将ViewHolder存储在View中
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.text_grade_courseName.setText(gradeTable.getCourseName());
        viewHolder.text_grade_bixiu.setText(gradeTable.getIsBiXiu());
        viewHolder.text_grade_courseClass.setText(gradeTable.getCourseClass());
        viewHolder.text_grade_testClass.setText(gradeTable.getTestClass());
        viewHolder.text_grade_xuefen.setText(gradeTable.getXuefen() + "学分");
        viewHolder.text_grade_fenshu.setText(gradeTable.getFenshu()+"");
//        if (gradeTable.getFenshu() < 60){
//            viewHolder.text_grade_fenshu.setText(gradeTable.getFenshu()+"");
//            viewHolder.text_grade_fenshu.setTextColor(getContext().getResources().getColor(R.color.colorRed));
//            viewHolder.view_grade_isPass.setBackgroundColor(getContext().getResources().getColor(R.color.colorRed));
//        }

        return view;
    }

    /**
     * 设置缓存类
     */
    static class ViewHolder{

        TextView text_grade_courseName;
        TextView text_grade_bixiu;
        TextView text_grade_courseClass;
        TextView text_grade_testClass;
        TextView text_grade_xuefen;
        TextView text_grade_fenshu;
        View view_grade_isPass;

    }

}
