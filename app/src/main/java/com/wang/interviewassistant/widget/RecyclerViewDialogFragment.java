package com.wang.interviewassistant.widget;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wang.interviewassistant.R;
import com.wang.interviewassistant.interfaces.OnRecyclerViewClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created on 2015/12/23.
 * Author: wang
 */
public class RecyclerViewDialogFragment extends AppCompatDialogFragment implements DialogInterface.OnKeyListener {


    @BindView(R.id.title_tv)
    TextView mTitleTV;
    @BindView(R.id.title_view)
    RelativeLayout mTitleView;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<String> itemArray;
    private OnRecyclerViewClickListener listener;

    private Unbinder mbinder;


    public static RecyclerViewDialogFragment getInstance(String title, ArrayList<String> itemArray) {
        RecyclerViewDialogFragment dialog = new RecyclerViewDialogFragment();
        Bundle arg = new Bundle();
        arg.putString("title", title);
        arg.putStringArrayList("list", itemArray);
        dialog.setArguments(arg);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((AppCompatDialog) getDialog()).supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setOnKeyListener(this);
        setCancelable(false);
        View view = inflater.inflate(R.layout.dialog_recycler_view, container, false);
        mbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        String title = getArguments().getString("title");
        itemArray = getArguments().getStringArrayList("list");
        mTitleTV.setText(TextUtils.isEmpty(title) ? "" : title);
        mTitleView.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemArray = itemArray == null ? new ArrayList<String>() : itemArray;
        mRecyclerView.setAdapter(new DialogAdapter());
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
            return true;
        }
        return false;
    }

    public void setItemArray(ArrayList<String> itemArray) {
        this.itemArray = itemArray;
        if (mRecyclerView != null) {
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }


    public void setListener(OnRecyclerViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mbinder.unbind();
    }

    class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text_center, parent, false);
            return new TextViewHolder(itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextViewHolder vh = (TextViewHolder) holder;
            vh.textView.setText(itemArray.get(position));
        }

        @Override
        public int getItemCount() {
            return itemArray.size();
        }


        class TextViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.text_tv)
            TextView textView;

            public TextViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(-1, getAdapterPosition(), null);
                    }
                });
            }
        }
    }
}
