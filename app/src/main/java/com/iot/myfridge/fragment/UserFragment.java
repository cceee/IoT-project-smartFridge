package com.iot.myfridge.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.iot.myfridge.R;
import com.iot.myfridge.activity.GridActivity;
import com.iot.myfridge.adapter.MsgAdapter;
import com.iot.myfridge.data.Msg;
import com.iot.myfridge.utils.MsgUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserFragment extends Fragment  {

    private FloatingActionButton fab;
    private ViewPager viewPager;
    private UserPagerAdapter userPagerAdapter;
    private ListView listView;

    private List<Msg> msgList;

    private MsgAdapter adapter;
    @BindView(R.id.tvQuery)
    EditText tvQuery;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        //userPagerAdapter = new UserPagerAdapter(getSupportFragementManagement())
        viewPager = view.findViewById(R.id.userpager);
        //viewPager.setAdapter(userPagerAdapter);
        unbinder = ButterKnife.bind(this, view);
        listView = view.findViewById(R.id.noti_list);
        msgList = MsgUtil.getMsgList();
        adapter = new MsgAdapter(msgList,getActivity());
        listView.setAdapter(adapter);

        tvQuery.setText( "broccoli carrot" );
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPressed(view);
            }
        });
        return view;
    }

    public void buttonPressed(View view) {
        String q = tvQuery.getText().toString();

        if( !q.isEmpty() ) {
            Intent intent = new Intent(getActivity(), GridActivity.class);
            intent.putExtra(getString(R.string.intent_query_key), tvQuery.getText().toString());
            getActivity().startActivity(intent);
        }
    }


    //使用BottomSheetDialog方式实现底部弹窗
    void showConnectFridgeDialog(){
        BottomSheetDialog bottomSheet = new BottomSheetDialog(getActivity());
        bottomSheet.setCancelable(true);
        bottomSheet.setContentView(R.layout.connect_fridge_view);
        bottomSheet.show();
    }




}
