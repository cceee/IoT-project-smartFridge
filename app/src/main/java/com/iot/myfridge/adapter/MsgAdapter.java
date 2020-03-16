package com.iot.myfridge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.myfridge.R;
import com.iot.myfridge.data.Msg;

import java.util.List;


public class MsgAdapter extends BaseAdapter{

    private List<Msg> mMsgList;

    private LayoutInflater mLayoutInflater;

    private Context mContext;

    public MsgAdapter(List<Msg> msgList, Context context) {

        this.mMsgList = msgList;

        this.mLayoutInflater = LayoutInflater.from(context);

        this.mContext = context;

    }

    @Override

    public int getCount() {

        return mMsgList.size();

    }

    @Override

    public Msg getItem(int position) {

        return mMsgList.get(position);

    }

    @Override

    public long getItemId(int position) {

        return position;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){

            convertView = mLayoutInflater.inflate(R.layout.noti_item_view,parent,false);

            viewHolder = new ViewHolder();

            viewHolder.imageView = convertView.findViewById(R.id.noti_imageview);

            viewHolder.titleTV = convertView.findViewById(R.id.noti_title);

            viewHolder.contentTV = convertView.findViewById(R.id.noti_date);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }

        Msg msg = mMsgList.get(position);

        viewHolder.imageView.setImageResource(msg.getImageResourceID());

        viewHolder.titleTV.setText(msg.getTitle());

        viewHolder.contentTV.setText(msg.getContent());

        return convertView;

    }

    private static class ViewHolder{

        ImageView imageView;

        TextView titleTV;

        TextView contentTV;

    }

}
