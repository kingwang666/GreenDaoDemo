package com.wang.interviewassistant.adapter;

import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.model.People;
import com.wang.interviewassistant.widget.SwipeItemView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by wang
 * on 2017/2/13
 */

public class PeopleAdapter extends RecyclerSwipeAdapter<PeopleAdapter.PeopleViewHolder> {

    @IntDef({TYPE_NO_INTERVIEW, TYPE_GOOD, TYPE_BAD})
    public @interface PeopleType {
    }

    public static final int TYPE_NO_INTERVIEW = 0;

    public static final int TYPE_GOOD = 1;

    public static final int TYPE_BAD = 2;

    private List<People> mPeoples;

    private PeopleClickListener mListener;

    @PeopleType
    private int mType;

    public PeopleAdapter(List<People> peoples, int type, PeopleClickListener listener) {
        mPeoples = peoples;
        mType = type;
        mListener = listener;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false);
        return new PeopleViewHolder(itemView, mType);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        People people = mPeoples.get(position);
        holder.mNameTV.setText(people.getName());
        holder.mPhoneTV.setText(people.getPhone());
        holder.mInterviewTimeTV.setText(people.getInterviewTime());
        holder.mTimeTV.setText(people.getCallbackTime());
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_tv)
        AppCompatTextView mNameTV;
        @BindView(R.id.phone_tv)
        AppCompatTextView mPhoneTV;
        @BindView(R.id.interview_time_tv)
        AppCompatTextView mInterviewTimeTV;
        @BindView(R.id.time_tv)
        AppCompatTextView mTimeTV;
        @BindView(R.id.good_btn)
        AppCompatButton mGoodBtn;
        @BindView(R.id.bad_btn)
        AppCompatButton mBadBtn;

        public PeopleViewHolder(View itemView, int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            switch (type) {
                case TYPE_NO_INTERVIEW:
                    mGoodBtn.setVisibility(View.VISIBLE);
                    mBadBtn.setText(R.string.bad);
                    break;
                case TYPE_GOOD:
                case TYPE_BAD:
                    mGoodBtn.setVisibility(View.GONE);
                    mBadBtn.setText(R.string.delete);
                    break;
            }
        }

        @OnClick({R.id.good_btn, R.id.bad_btn, R.id.click_view})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.good_btn:
                    mListener.onGood(mPeoples.get(position), position);
                    break;
                case R.id.bad_btn:
                    if (mType == TYPE_NO_INTERVIEW) {
                        mListener.onBad(mPeoples.get(position), position);
                    } else {
                        mListener.onDelete(mPeoples.get(position), position);
                    }
                    break;
                case R.id.click_view:
                    mListener.onItemClick(mPeoples.get(position), position);
                    break;
            }
        }

        @OnLongClick(R.id.click_view)
        public boolean onItemLongClick(){
            int position = getAdapterPosition();
            mListener.onItemLongClick(mPeoples.get(position), position);
            return true;
        }
    }

    public interface PeopleClickListener {

        void onItemClick(People people, int position);

        void onItemLongClick(People people, int position);

        void onGood(People people, int position);

        void onBad(People people, int position);

        void onDelete(People people, int position);

    }
}
