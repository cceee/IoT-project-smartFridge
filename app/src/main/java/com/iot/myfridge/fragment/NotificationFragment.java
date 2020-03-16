package com.iot.myfridge.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.iot.myfridge.R;
import com.iot.myfridge.activity.LineChartActivity1;
import com.iot.myfridge.activity.MainActivity;
import com.iot.myfridge.activity.PiePolylineChartActivity;
import com.iot.myfridge.activity.RadarChartActivity;
import com.iot.myfridge.data.CurrentGood;
import com.iot.myfridge.database.FridgeDatabase;
import com.iot.myfridge.utils.Constants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

import static android.content.Context.NOTIFICATION_SERVICE;


public class NotificationFragment extends Fragment {

    private EditText urlText;
    private Button connectBtn;
    @BindView(R.id.fridge)
    ImageView fridgeBtn;
    @BindView(R.id.nutrition)
    Button nutriBtn;
    @BindView(R.id.pyramid)
    Button pyraBtn;
    @BindView(R.id.calorie)
    Button calBtn;
    //@BindView(R.id.button_send_noti)
    //Button sendBtn;
    private String urlFridge;
    private BottomSheetDialog bottomSheetDialog;
    private ArrayList<Boolean> bl = new ArrayList<>();
    private SimpleAdapter simpleAdapter;
    private ArrayList<Map<String, Object>> mData;
    private ArrayList<String> foodDetected;

    private Spinner spinner;
    private int selectHistory;
    private ArrayAdapter<String> selectAdapter;
    private static final String[] years = { "Today", "Last Week", "Last Month", "History" };
    private ArrayList<String> array = new ArrayList<String>();
    private FridgeDatabase fridgeDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fridge, container, false);
        ButterKnife.bind(this,view);
        setSelectHistory(0);
        fridgeDatabase = ((MainActivity)getActivity()).getFridgeDatabase();
        fridgeBtn = view.findViewById(R.id.fridge);
        fridgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConnectFridgeDialog();
            }
        });

        nutriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PiePolylineChartActivity.class);
                //intent.putExtra("select_history", selectHistory);
                getActivity().startActivity(intent);
            }
        });
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LineChartActivity1.class);
                //intent.putExtra("select_history", selectHistory);
                getActivity().startActivity(intent);
            }
        });
        pyraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RadarChartActivity.class);
                //intent.putExtra("select_history", selectHistory);
                getActivity().startActivity(intent);
            }
        });
        setSpinner(view);

        /***
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);

                Notification notification = new Notification.Builder(getActivity())

                        .setContentTitle("标题")

                        .setContentText("这里展示的是通知内容~")

                        .setWhen(System.currentTimeMillis())

                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)

                        //.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_black_24dp))

                        .build();

                manager.notify(1, notification);
            }

        });
         ***/
        return view;
    }

    void showConnectFridgeDialog(){
        if (bottomSheetDialog ==null){
            bottomSheetDialog = new BottomSheetDialog(getActivity());
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.connect_fridge_view,null);
            bottomSheetDialog.setCancelable(true);
            bottomSheetDialog.setContentView(v);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            //click
            urlText = v.findViewById(R.id.fridge_id);
            connectBtn = v.findViewById(R.id.connect_button);
            connectBtn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    String res = "connect";
                    urlFridge = urlText.getText().toString();
                    String path = "http://" + urlFridge + "/?msg=";
                    sendConnect(path, res);
                }
            });
            final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet));
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        Log.i("BottomSheet","onStateChanged");
                        bottomSheetDialog.dismiss();
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }else{
            bottomSheetDialog.show();
        }

    }

    private void sendConnect(String path, String msg){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(path + msg, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String resp = new String(bytes);
                resp = "Get response from raspberry: " + resp;
                Toast.makeText(getActivity().getApplicationContext(), resp, Toast.LENGTH_LONG).show();
                getFood(resp);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //the rest for test
                String test = "Fruit/Food/Plant/Apple/Juice/Broccoli/";
                //Toast.makeText(getActivity().getApplicationContext(), " ", Toast.LENGTH_LONG).show();
                 getFood(test);
                Toast.makeText(getActivity().getApplicationContext(), "Cannot not reach : " + urlFridge, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getFood(String response){
        String[] foodRawDetected = response.split("/");
        foodDetected = new ArrayList<>();
        ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        ArrayList<Boolean> bl = new ArrayList<>();
        for (String f : foodRawDetected){
            if(Arrays.asList(Constants.FOODLISTS.FOOD_LISTS).contains(f)){
                foodDetected.add(f);
            }
        }
        Map<String, Object> allSItem = new HashMap<String, Object>();
        allSItem.put("text", "ALL");
        mData.add(allSItem);
        bl.add(false);
        //allSItem.put("text", "Apple");
        //mData.add(allSItem);
        //bl.add(false);
        for (int fd =0; fd<foodDetected.size();fd++){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("text",foodDetected.get(fd));
            mData.add(item);
            bl.add(false);
        }
        setBl(bl);
        setMData(mData);
        showFoodDetectedDialog(mData);
    }

    public void showFoodDetectedDialog(ArrayList<Map<String, Object>> mData){
        // 动态加载一个listview的布局文件进来
        View vf = LayoutInflater.from(getActivity()).inflate(R.layout.listview,null);

        // 给ListView绑定内容
        ListView listview = (ListView) vf.findViewById(R.id.X_listview);
        SimpleAdapter adapter = new SetSimpleAdapter(getActivity(), mData, R.layout.listitem, new String[] { "text" },
                new int[] { R.id.X_item_text });
        setSimpleAdapter(adapter);
        // 给listview加入适配器
        listview.setAdapter(adapter);
        listview.setItemsCanFocus(false);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setOnItemClickListener(new ItemOnClick());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select your food");
        builder.setIcon(R.drawable.ic_food_black_24dp);
        //设置加载的listview
        builder.setView(vf);
        builder.setPositiveButton("OK", new DialogOnClick());
        builder.setNegativeButton("Cancel", new DialogOnClick());
        builder.create().show();
    }

    class DialogOnClick implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:

                    ArrayList<String> r = new ArrayList<>();
                    for (int i = 0 ; i< foodDetected.size();i++){
                        if (bl.get(i)){
                            r.add(foodDetected.get(i));
                        }
                    }
                    ArrayList<CurrentGood> currentGoods = new ArrayList<>();
                    for (String j : r){
                        CurrentGood c = new CurrentGood(j,"12",1);
                        currentGoods.add(c);
                    }
                    Toast.makeText(getActivity().getApplicationContext(), "select"+ r.toString(), Toast.LENGTH_LONG).show();
                    ((MainActivity)getActivity()).reloadFragView(currentGoods);

                    break;
                case Dialog.BUTTON_NEGATIVE:
                    //取消按钮的事件
                    break;
                default:
                    break;
            }
        }
    }

    //重写simpleadapterd的getview方法
    class SetSimpleAdapter extends SimpleAdapter {

        public SetSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from,
                                int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LinearLayout.inflate(getActivity().getBaseContext(), R.layout.listitem, null);
            }
            CheckBox ckBox = (CheckBox) convertView.findViewById(R.id.X_checkbox);
            //每次都根据 bl[]来更新checkbox
            if (bl.get(position) == true) {
                ckBox.setChecked(true);
            } else if (bl.get(position) == false) {
                ckBox.setChecked(false);
            }
            return super.getView(position, convertView, parent);
        }
    }
    class ItemOnClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
            CheckBox cBox = (CheckBox) view.findViewById(R.id.X_checkbox);
            if (cBox.isChecked()) {
                cBox.setChecked(false);
            } else {
                //Log.i("TAG", "取消该选项");
                cBox.setChecked(true);
            }

            if (position == 0 && (cBox.isChecked())) {
                //如果是选中 全选  就把所有的都选上 然后更新
                for (int i = 0; i < bl.size(); i++) {
                    bl.set(i,true);
                }
                simpleAdapter.notifyDataSetChanged();
            } else if (position == 0 && (!cBox.isChecked())) {
                //如果是取消全选 就把所有的都取消 然后更新
                for (int i = 0; i < bl.size(); i++) {
                    bl.set(i,false);
                }
                simpleAdapter.notifyDataSetChanged();
            }
            if (position != 0 && (!cBox.isChecked())) {
                // 如果把其它的选项取消   把全选取消
                bl.set(0,false);
                bl.set(position,false);
                simpleAdapter.notifyDataSetChanged();
            } else if (position != 0 && (cBox.isChecked())) {
                //如果选择其它的选项，看是否全部选择
                //先把该选项选中 设置为true
                bl.set(position,true);
                int a = 0;
                for (int i = 1; i < bl.size(); i++) {
                    if (bl.get(i) == false) {
                        //如果有一个没选中  就不是全选 直接跳出循环
                        break;
                    } else {
                        //计算有多少个选中的
                        a++;
                        if (a == bl.size() - 1) {
                            //如果选项都选中，就把全选 选中，然后更新
                            bl.set(0,true);
                            simpleAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }

    }

    private void setBl(ArrayList<Boolean> bl){
        this.bl = new ArrayList<>();
        this.bl.addAll(bl);
    }
    private void setSimpleAdapter(SimpleAdapter adapter){
        this.simpleAdapter = adapter;
    }
    public void setSpinner(View view){
        spinner = (Spinner) view.findViewById(R.id.day_select);

        //tv = (TextView) view.findViewById(R.id.textView1);

        for (int i = 0; i < years.length; i++) {
            array.add(years[i]);
        }
        selectAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, array);
        selectAdapter.setDropDownViewResource(R.layout.dropdown);

        spinner.setAdapter(selectAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO 自动生成的方法存根
                setSelectHistory(position);
                parent.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO 自动生成的方法存根
                setSelectHistory(0);
            }

        });
    }
    private void setSelectHistory(int postion){
        selectHistory = postion;
    }

    public void setMData( ArrayList<Map<String, Object>> mData) {
        this.mData = mData;
    }
}
