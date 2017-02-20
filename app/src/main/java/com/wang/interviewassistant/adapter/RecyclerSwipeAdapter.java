package com.wang.interviewassistant.adapter;

import android.support.v7.widget.RecyclerView;


import com.wang.interviewassistant.interfaces.SwipeAdapterInterface;
import com.wang.interviewassistant.interfaces.SwipeItemMangerInterface;
import com.wang.interviewassistant.util.SwipeItemMangerImpl;
import com.wang.interviewassistant.widget.SwipeItemView;

import java.util.List;


public abstract class RecyclerSwipeAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements SwipeItemMangerInterface, SwipeAdapterInterface {

    protected SwipeItemMangerImpl mItemManger = new SwipeItemMangerImpl(this);

    @Override
    public void notifyDatasetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void openItem(int position) {
        mItemManger.openItem(position);
    }

    @Override
    public void closeItem(int position) {
        mItemManger.closeItem(position);
    }

    @Override
    public void closeAllExcept(SwipeItemView layout) {
        mItemManger.closeAllExcept(layout);
    }

    @Override
    public void closeAllItems() {
        mItemManger.closeAllItems();
    }

    @Override
    public List<Integer> getOpenItems() {
        return mItemManger.getOpenItems();
    }

    @Override
    public List<SwipeItemView> getOpenLayouts() {
        return mItemManger.getOpenLayouts();
    }

    @Override
    public void removeShownLayouts(SwipeItemView layout) {
        mItemManger.removeShownLayouts(layout);
    }

    @Override
    public boolean isOpen(int position) {
        return mItemManger.isOpen(position);
    }

    @Override
    public SwipeItemView.Mode getMode() {
        return mItemManger.getMode();
    }

    @Override
    public void setMode(SwipeItemView.Mode mode) {
        mItemManger.setMode(mode);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        if (holder.itemView instanceof SwipeItemView){
            mItemManger.bind((SwipeItemView) holder.itemView, position);
        }
    }
}
