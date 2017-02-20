package com.wang.interviewassistant.widget;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.wang.interviewassistant.R;

import java.util.ArrayList;
import java.util.List;

import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.wheel2.WheelView2;


/**
 * Created on 2015/12/7.
 * Author: wang
 */
public class IOSSinglePickDialog<T> extends AppCompatDialog implements View.OnClickListener {

    private WheelView2 mPickerView;

    private AppCompatTextView mCancelTV;

    private AppCompatTextView mConfirmTV;

    private OnItemSelectListener<T> mOnItemSelectListener;

    private T mDefaultData;

    private int mDefaultPosition;

    private List<T> mDatas;

    private String mCancelText;

    private String mConfirmText;

    private boolean mCyclic;

    IOSSinglePickDialog(Context context, OnItemSelectListener<T> onItemSelectListener, List<T> datas, T defaultData, int defaultPosition, String cancelText, String confirmText, boolean cyclic) {
        super(context);
        mOnItemSelectListener = onItemSelectListener;
        mDatas = datas == null ? new ArrayList<T>() : datas;
        mDefaultData = defaultData;
        mDefaultPosition = defaultPosition;
        mCancelText = cancelText;
        mConfirmText = confirmText;
        mCyclic = cyclic;
    }


    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ios_single_pick);
        mCancelTV = (AppCompatTextView) findViewById(R.id.cancel_tv);
        mCancelTV.setOnClickListener(this);
        mCancelTV.setText(mCancelText);
        mConfirmTV = (AppCompatTextView) findViewById(R.id.complete_tv);
        mConfirmTV.setOnClickListener(this);
        mConfirmTV.setText(mConfirmText);
        mPickerView = (WheelView2) findViewById(R.id.picker_view);
        mPickerView.setCyclic(mCyclic);
        mPickerView.setAdapter(new ArrayWheelAdapter(getContext(), mDatas));
        if (mDefaultData != null) {
            mPickerView.setCurrentItem(mDatas.indexOf(mDefaultData));
        } else {
            mPickerView.setCurrentItem(mDefaultPosition);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.complete_tv) {
            T data = mDatas.get(mPickerView.getCurrentItem());
            if (mOnItemSelectListener != null) {
                mOnItemSelectListener.onDateSelected(mPickerView.getCurrentItem(), data);
            }
        }
        dismiss();
    }

    public interface OnItemSelectListener<C> {
        void onDateSelected(int currentItem, C c);
    }

    public static class Builder<E> {

        private OnItemSelectListener<E> mOnItemSelectListener;

        private E mDefaultData;

        private int mDefaultPosition;

        private List<E> mDatas;

        private String mCancelText = "取消";

        private String mConfirmText = "确认";

        private boolean mCyclic = true;

        public Builder<E> setOnItemSelectListener(OnItemSelectListener<E> onItemSelectListener) {
            mOnItemSelectListener = onItemSelectListener;
            return this;
        }

        public Builder<E> setDefaultData(E defaultData) {
            mDefaultData = defaultData;
            return this;
        }

        public Builder<E> setDefaultPosition(int defaultPosition) {
            mDefaultPosition = defaultPosition;
            return this;
        }

        public Builder<E> setDatas(List<E> datas) {
            mDatas = datas;
            return this;
        }

        public Builder<E> setCancelText(String cancelText) {
            mCancelText = cancelText;
            return this;
        }

        public Builder<E> setConfirmText(String confirmText) {
            mConfirmText = confirmText;
            return this;
        }

        public Builder<E> setCyclic(boolean cyclic) {
            mCyclic = cyclic;
            return this;
        }

        public IOSSinglePickDialog<E> builder(Context context) {
            return new IOSSinglePickDialog<>(context, mOnItemSelectListener, mDatas, mDefaultData, mDefaultPosition, mCancelText, mConfirmText, mCyclic);
        }
    }

}
