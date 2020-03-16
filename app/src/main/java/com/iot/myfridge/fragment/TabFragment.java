package com.iot.myfridge.fragment;
// make this part of function as a way to appeal to advertise
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iot.myfridge.R;
import com.iot.myfridge.activity.MainActivity;
import com.iot.myfridge.adapter.RecycleViewAdapter;
import com.iot.myfridge.data.BalanceGood;
import com.iot.myfridge.data.CurrentGood;
import com.iot.myfridge.data.HistoryGood;
import com.iot.myfridge.database.FridgeDatabase;
import com.iot.myfridge.utils.DataUtil;
import com.iot.myfridge.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    private List<String> mdata = new ArrayList<>();
    private List<String> imageUrl = new ArrayList<>();
    int mPosition;
    private RecycleViewAdapter mAdapter;
    private Banner mBanner;
    ArrayList<CurrentGood> testCGoods;
    ArrayList<HistoryGood> testHGoods;
    ArrayList<BalanceGood> testBGoods;
    private FridgeDatabase fridgeDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fridgeDatabase = ((MainActivity)getActivity()).getFridgeDatabase();
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mPosition = getArguments().getInt("position");
        initView();
        initData();

        return rootView;
    }


    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(R.layout.food_item_view, mdata,getActivity(), mPosition,fridgeDatabase);

        View top = getLayoutInflater().inflate(R.layout.layout_banner, (ViewGroup) mRecyclerView.getParent(), false);
        mBanner = top.findViewById(R.id.banner);
        mAdapter.addHeaderView(top);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData() {

        testCGoods = new ArrayList<>();
        testHGoods = new ArrayList<>();
        if(mPosition == 0){
            mdata = new ArrayList<>();
            testBGoods = fridgeDatabase.getBalanceGoods();
            for (BalanceGood b : testBGoods){
                mdata.add(b.getBid());
            }
        }
        else if(mPosition == 1){
            mdata = new ArrayList<>();
            testCGoods = fridgeDatabase.getCurrentGoods();
            for (CurrentGood c : testCGoods){
                mdata.add(c.getCid());
            }
        }
        else if(mPosition == 2){
            mdata = new ArrayList<>();
            testHGoods = fridgeDatabase.getHistoryGoods();
            for (HistoryGood h : testHGoods){
                mdata.add(h.getHid());
            }
        }
        else if(mPosition == 3){
            mdata.add("Apple");
        }



        mAdapter.setNewData(mdata);//模拟网络请求成功后要调用这个方法刷新数据
        if (mPosition == 0) {
            imageUrl.clear();
            imageUrl.add("https://pret-files.azureedge.net/pretamanger-usa/07846-us-catering/lunch-thumb-dt.jpg");
            imageUrl.add("https://dining.columbia.edu/sites/default/files/styles/cu_crop/public/content/Retail%20Location%20Photos/Butlerhero.jpg?h=da1da850&itok=yMKQPKs-");
            imageUrl.add("https://image.shutterstock.com/image-photo/hawaiian-salmon-shrimp-poke-bowls-260nw-1177552357.jpg");
            initBanner(imageUrl);
        } else {
            mBanner.setVisibility(View.GONE);
        }

    }

    private void initBanner(List<String> imageUrl) {
        mBanner.setImages(imageUrl)
                .setImageLoader(new GlideImageLoader())
                .setDelayTime(3000)
                .start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if(position == 0){
                    Uri uri = Uri.parse("https://www.pret.com/en-us/catering");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getActivity().startActivity(intent);
                }
                if(position == 1){
                    Uri uri = Uri.parse("https://dining.columbia.edu/content/blue-java-cafe-butler-library-0#/61");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getActivity().startActivity(intent);
                }
                if(position ==2){
                    Uri uri = Uri.parse("http://hulapokeusa.com/Gallery.html");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    getActivity().startActivity(intent);
                }

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        mBanner.stopAutoPlay();
    }

    public int getmPosition(){
        return mPosition;
    }

   public void updateView(){
        initData();
        //initView();
   }

}
