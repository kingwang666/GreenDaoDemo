package com.wang.interviewassistant.presenter;

import com.wang.interviewassistant.model.People;

import java.util.ArrayList;
import java.util.List;


/**
 * Created on 2016/6/3.
 * Author: wang
 */
public abstract class BasePresenter {

    private IBaseView mView;

    protected List<People> mItemArray;

    public BasePresenter() {
        mItemArray = new ArrayList<>();
    }


    public void setView(IBaseView view) {
        mView = view;
    }

    public IBaseView getView() {
        return mView;
    }

    public List<People> getItemArray() {
        return mItemArray;
    }

    public void setItemArray(List<People> itemArray) {
        if (mItemArray == null) {
            mItemArray = itemArray;
        } else {
            mItemArray.clear();
            mItemArray.addAll(itemArray);
        }

    }

    public People get(int position) {
        return mItemArray.get(position);
    }



    public interface IBaseView {

        void stopLoading();

        void notifyDataSetChanged();

        void notifyItemChanged(int position);

        void notifyItemRemoved(int position);

        void notifyItemInserted(int position);

        void notifyItemRangeChanged(int positionStart, int itemCount);

        void notifyItemRangeRemoved(int positionStart, int itemCount);

        void notifyItemRangeInserted(int positionStart, int itemCount);
    }

}
