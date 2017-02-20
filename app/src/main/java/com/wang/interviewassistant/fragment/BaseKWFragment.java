package com.wang.interviewassistant.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.presenter.BasePresenter;

import permissions.dispatcher.PermissionRequest;


/**
 * Created on 2015/11/26.
 * Author: wang
 */
public abstract class BaseKWFragment extends BaseFragment implements BasePresenter.IBaseView {


    @Override
    protected void initData(Bundle arg) {

    }

    @Override
    protected void initOtherView(View rootView) {
        try {
            Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener(View rootView) {

    }

    protected RecyclerView.Adapter getAdapter() {
        throw new RuntimeException("this method must override");
    }

    protected void showRationaleDialog(int messageId, final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemChanged(int position) {
        try {
            getAdapter().notifyItemChanged(position);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRemoved(int position) {
        try {
            getAdapter().notifyItemRemoved(position);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemInserted(int position) {
        try {
            getAdapter().notifyItemInserted(position);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        try {
            getAdapter().notifyItemRangeChanged(positionStart, itemCount);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRangeRemoved(int positionStart, int itemCount) {
        try {
            getAdapter().notifyItemRangeRemoved(positionStart, itemCount);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyItemRangeInserted(int positionStart, int itemCount) {
        try {
            getAdapter().notifyItemRangeInserted(positionStart, itemCount);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
