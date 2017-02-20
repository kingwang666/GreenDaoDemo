package com.wang.interviewassistant.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.presenter.BasePresenter;

import permissions.dispatcher.PermissionRequest;


/**
 * Created on 2016/4/15.
 * Author: wang
 */
public abstract class BaseKWActivity extends BaseActivity implements BasePresenter.IBaseView {

    protected Toolbar mToolbar;

    @Override
    protected void initOtherView() {
        try {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(Intent intent) {

    }

    protected RecyclerView.Adapter getAdapter(){
        throw new RuntimeException("this method must override");
    }

    protected void showRationaleDialog(int messageId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(messageId)
                .setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }


    @Override
    public void stopLoading() {

    }

    @Override
    public void notifyDataSetChanged() {
        try {
            getAdapter().notifyDataSetChanged();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemChanged(int position) {
        try {
            getAdapter().notifyItemChanged(position);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRemoved(int position) {
        try {
            getAdapter().notifyItemRemoved(position);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemInserted(int position) {
        try {
            getAdapter().notifyItemInserted(position);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        try {
            getAdapter().notifyItemRangeChanged(positionStart, itemCount);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRangeRemoved(int positionStart, int itemCount) {
        try {
            getAdapter().notifyItemRangeRemoved(positionStart, itemCount);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRangeInserted(int positionStart, int itemCount) {
        try {
            getAdapter().notifyItemRangeInserted(positionStart, itemCount);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
