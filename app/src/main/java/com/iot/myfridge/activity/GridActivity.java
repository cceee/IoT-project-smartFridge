package com.iot.myfridge.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.iot.myfridge.R;
import com.iot.myfridge.adapter.EndlessRecyclerViewScrollListener;
import com.iot.myfridge.adapter.MyItemListAdapter;
import com.iot.myfridge.model.Hit;
import com.iot.myfridge.model.MyResponse;
import com.iot.myfridge.utils.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GridActivity extends AppCompatActivity {

    private static final String TAG = "GridActivity";

    @BindView(R.id.rvQueryList)
    RecyclerView rvQueryList;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    private String query;
    private int from, to;
//    private List<Recipe> recipeList = new ArrayList<>();
    private List<Hit> hitList = new ArrayList<>();
    private MyItemListAdapter myItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //getSupportActionBar().setHomeButtonEnabled(true);
        ButterKnife.bind(this);

        query = getIntent().getStringExtra(getString(R.string.intent_query_key));
        setTitle(query);

        int cols = 3;
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE )
            cols = 5;

        GridLayoutManager manager = new GridLayoutManager(this, cols);
        myItemListAdapter = new MyItemListAdapter(hitList);
        rvQueryList.setLayoutManager(manager);
        rvQueryList.setItemAnimator(new DefaultItemAnimator());
        rvQueryList.setAdapter(myItemListAdapter);
        rvQueryList.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                Toast.makeText(GridActivity.this, "onLoadMore", Toast.LENGTH_SHORT).show();
                from = to + 1;
                to += 20;
                search( query, from, to, totalItemsCount );
            }
        });

        from = 0;
        to = 20;
        search(query, from, to, to);
    }

    public void search(String q, final int from, int to, final int totalItemsCount) {
        tvStatus.setText( "Searching...");

        RetrofitHelper.getResponse(q, from, to)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyResponse myResponse) {
//                        Log.d(TAG, "onNext: " + myResponse.getQ());
//                        Log.d(TAG, "onNext: " + myResponse.getCount());

                        hitList.addAll( myResponse.getHits());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        e.printStackTrace();
                        tvStatus.setText( "No Results.");
                    }

                    @Override
                    public void onComplete() {
                        myItemListAdapter.notifyItemRangeChanged( from, totalItemsCount);

                        String s = (hitList.size() > 0) ? "" : "No Results";
                        tvStatus.setText( s );
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}