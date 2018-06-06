package com.linguangyu.studentinfomanagementsystem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.model.GradeTable;

import java.util.List;

/**
 * Created by 光裕 on 2018/6/6.
 */

public class GradeRecyclerViewAdapter extends RecyclerView.Adapter<GradeRecyclerViewAdapter.ViewHolder> {

    private List<GradeTable> mGradeTableList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView text_grade_courseName;
        TextView text_grade_bixiu;
        TextView text_grade_courseClass;
        TextView text_grade_testClass;
        TextView text_grade_xuefen;
        TextView text_grade_fenshu;
        View view_grade_isPass;

        public ViewHolder(View view) {
            super(view);
            text_grade_courseName = view.findViewById(R.id.text_grade_courseName);
            text_grade_bixiu = view.findViewById(R.id.text_grade_bixiu);
            text_grade_courseClass = view.findViewById(R.id.text_grade_courseClass);
            text_grade_testClass = view.findViewById(R.id.text_grade_testClass);
            text_grade_xuefen = view.findViewById(R.id.text_grade_xuefen);
            text_grade_fenshu = view.findViewById(R.id.text_grade_fenshu);
            view_grade_isPass = view.findViewById(R.id.view_grade_isPass);
        }
    }

    public GradeRecyclerViewAdapter(List<GradeTable> gradeTablesList){
        mGradeTableList = gradeTablesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grade,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        GradeTable gradeTable = mGradeTableList.get(position);
        holder.text_grade_courseName.setText(gradeTable.getCourseName());
        holder.text_grade_bixiu.setText(gradeTable.getIsBiXiu());
        holder.text_grade_courseClass.setText(gradeTable.getCourseClass());
        holder.text_grade_testClass.setText(gradeTable.getTestClass());
        holder.text_grade_xuefen.setText(gradeTable.getXuefen() + "学分");
        holder.text_grade_fenshu.setText(gradeTable.getFenshu()+"");
//        if (gradeTable.getFenshu() < 60){
//            holder.text_grade_fenshu.setText(gradeTable.getFenshu()+"");
//            holder.text_grade_fenshu.setTextColor(R.color.colorRed);
//            holder.view_grade_isPass.setBackgroundColor(R.color.colorRed);
//        }

    }

    @Override
    public int getItemCount() {
        return mGradeTableList.size();
    }

}
