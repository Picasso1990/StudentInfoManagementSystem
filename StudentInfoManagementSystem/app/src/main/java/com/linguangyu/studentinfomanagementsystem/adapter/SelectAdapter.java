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
import com.linguangyu.studentinfomanagementsystem.model.SeasonTable;

import java.util.List;

/**
 * Created by 光裕 on 2018/6/6.
 */

public class SelectAdapter extends ArrayAdapter<SeasonTable> {

    private int resourceId;

    public SelectAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<SeasonTable> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SeasonTable seasonTable = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text_item_select = view.findViewById(R.id.text_item_select);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.text_item_select.setText(seasonTable.getYearSeason());
        return view;
    }

    class ViewHolder{
        TextView text_item_select;
    }

}
