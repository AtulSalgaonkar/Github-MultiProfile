package com.example.sith3.getretrofitdata.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.sith3.getretrofitdata.Pojo.UserResponse;
import com.example.sith3.getretrofitdata.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created on 2/3/2018.
 */

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {

    private Context mContext;
    private List<UserResponse> mDataList;


    public MyRvAdapter(List<UserResponse> dataList) {
        mDataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = (View) LayoutInflater.from(mContext).inflate(R.layout.recyclerview_child_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        UserResponse userData = mDataList.get(position);

        holder.mUsername.setText(userData.getUsername());
        holder.mProfileUrl.setText(userData.getProfileUrl());
        Picasso.with(mContext).load(userData.getProfilePicUrl()).into(holder.mProfilePic);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mUsername, mProfileUrl;
        private CircleImageView mProfilePic;

        public MyViewHolder(View itemView) {
            super(itemView);

            if (itemView != null) {
                mUsername = (TextView) itemView.findViewById(R.id.username_of_rv);
                mProfileUrl = (TextView) itemView.findViewById(R.id.profile_link);
                mProfilePic = (CircleImageView) itemView.findViewById(R.id.user_img_path_of_rv);
            }
        }
    }
}
