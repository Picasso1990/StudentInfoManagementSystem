package com.linguangyu.studentinfomanagementsystem.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linguangyu.studentinfomanagementsystem.R;
import com.linguangyu.studentinfomanagementsystem.adapter.TestAdapter;
import com.linguangyu.studentinfomanagementsystem.model.StudentUser;
import com.linguangyu.studentinfomanagementsystem.model.TestTable;
import com.linguangyu.studentinfomanagementsystem.model.TimetableTable;
import com.linguangyu.studentinfomanagementsystem.model.Weeks;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 光裕 on 2018/5/31.
 * 测试Fragment
 */

public class TestFragment extends Fragment {

    private static final int GIVE_LIST_TEST = 1;
    private List<TestTable> listTest;
    private TestAdapter testAdapter;
    private ListView listView_test;

    public TestFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test,container,false);
        listView_test = view.findViewById(R.id.listView_test);

        queryMoreToMoreST();
        return view;
    }

    /**
     * 查询多对多，StudentUser关联TestTable
     */
    private void queryMoreToMoreST(){
        BmobQuery<TestTable> query = new BmobQuery<>();
        StudentUser studentUser = BmobUser.getCurrentUser(StudentUser.class);
        query.addWhereRelatedTo("test",new BmobPointer(studentUser));
        query.findObjects(new FindListener<TestTable>() {
            @Override
            public void done(List<TestTable> list, BmobException e) {
                if (e == null && (!list.isEmpty())){
                    Message message = handlerTest.obtainMessage();
                    message.what = GIVE_LIST_TEST;
                    message.obj = list;
                    handlerTest.sendMessage(message);
                }else if (e == null && list.isEmpty()){
                    Toast.makeText(getActivity(),"没有考试",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Handler handlerTest = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GIVE_LIST_TEST:
                    listTest = (List<TestTable>) msg.obj;
                    testAdapter = new TestAdapter(getActivity(),R.layout.item_test,listTest);
                    listView_test.setAdapter(testAdapter);
                    break;
                default:
                    break;
            }
        }
    };


}
