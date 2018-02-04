package com.example.sith3.getretrofitdata.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sith3.getretrofitdata.Adapter.MyRvAdapter;
import com.example.sith3.getretrofitdata.Pojo.User;
import com.example.sith3.getretrofitdata.Pojo.UserResponse;
import com.example.sith3.getretrofitdata.R;
import com.example.sith3.getretrofitdata.Retrofit.GetCallData;
import com.example.sith3.getretrofitdata.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mShowErrorTv;

    private Context mContext = MainActivity.this;
    private GetCallData getInterface;
    private List<UserResponse> mDataList;
    private MyRvAdapter mAdapter;
    private static int mPageNo;
    private static final String mSearch = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_1);
        mShowErrorTv = (TextView) findViewById(R.id.show_error_tv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);

        mPageNo = 1;
        getData();
        setListenerForScroll();
    }

    private void getData() {
        getInterface = RetrofitClient.getInstance().create(GetCallData.class);
        Call<User> call = getInterface.getDataFromCall(mSearch, mPageNo);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    mShowErrorTv.setVisibility(View.GONE);
                    mDataList = response.body().getItems();
                    mAdapter = new MyRvAdapter(mDataList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mShowErrorTv.setVisibility(View.VISIBLE);
                String error = "Error: " + t.getMessage();
                mShowErrorTv.setText(error);
            }
        });
    }

    private void setListenerForScroll() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && mPageNo < 35) {
                    getAfterScrollData();
                }
            }
        });
    }

    public void getAfterScrollData() {
        mPageNo += 1;
        getInterface = RetrofitClient.getInstance().create(GetCallData.class);
        Call<User> call = getInterface.getDataFromCall(mSearch, mPageNo);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    mShowErrorTv.setVisibility(View.GONE);
                    List<UserResponse> data = response.body().getItems();
                    mDataList.addAll(data);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mShowErrorTv.setVisibility(View.VISIBLE);
                String error = "Error: " + t.getMessage();
                mShowErrorTv.setText(error);
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setMessage("Are you sure you want to quit this App?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .create();

        dialog.show();
    }
}
