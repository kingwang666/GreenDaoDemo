package com.wang.interviewassistant.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.activity.MainActivity;
import com.wang.interviewassistant.activity.PeopleDetailActivity;
import com.wang.interviewassistant.adapter.PeopleAdapter;
import com.wang.interviewassistant.interfaces.OnRecyclerViewClickListener;
import com.wang.interviewassistant.model.People;
import com.wang.interviewassistant.model.UserInfo;
import com.wang.interviewassistant.presenter.PeopleFragmentPresenter;
import com.wang.interviewassistant.util.CallUtil;
import com.wang.interviewassistant.util.SMSUtil;
import com.wang.interviewassistant.util.UserHelper;
import com.wang.interviewassistant.widget.RecyclerViewDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by wang
 * on 2017/2/14
 */
@RuntimePermissions
public class PeopleFragment extends BaseKWFragment implements PeopleFragmentPresenter.IView, SwipeRefreshLayout.OnRefreshListener, PeopleAdapter.PeopleClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private PeopleFragmentPresenter mPresenter;

    private int mType;

    public static PeopleFragment getInstance(int type) {
        PeopleFragment fragment = new PeopleFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData(Bundle arg) {
        mType = arg.getInt("type");
        mPresenter = new PeopleFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_people;
    }

    @Override
    protected void initListener(View rootView) {
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void afterView() {
        mPresenter.setView(this);
        mRefreshLayout.setColorSchemeResources(R.color.blue600);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new PeopleAdapter(mPresenter.getItemArray(), mType, this));
        delayLoad();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PeopleFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return mRecyclerView.getAdapter();
    }

    @Override
    protected void delayLoad() {
        if (isCanLoad()) {
            onRefresh();
            super.delayLoad();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getTypeList(mType);
        isTwo = false;
    }

    @Override
    public void onItemClick(People people, int position) {
        toPeopleDetail(1, people, MainActivity.DETAIL);
    }

    @Override
    public void onItemLongClick(final People people, int position) {
        ArrayList<String> names = new ArrayList<>();
        names.add("发送面试短信");
        names.add("拨打面试电话");
        final RecyclerViewDialogFragment dialog = RecyclerViewDialogFragment.getInstance(null, names);
        dialog.setListener(new OnRecyclerViewClickListener() {
            @Override
            public void onClick(int itemType, int position, Object data) {
                if (position == 0) {
                    PeopleFragmentPermissionsDispatcher.sendSMSWithCheck(PeopleFragment.this, people);
                } else {
                    PeopleFragmentPermissionsDispatcher.callWithCheck(PeopleFragment.this, people);
                }
                dialog.dismiss();
            }
        });
        dialog.show(getChildFragmentManager(), "list_dialog");
    }

    @Override
    public void onGood(People people, int position) {
        people.setType(PeopleAdapter.TYPE_GOOD);
        mPresenter.update(people, position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBad(People people, int position) {
        people.setType(PeopleAdapter.TYPE_BAD);
        mPresenter.update(people, position);
        notifyItemRemoved(position);
    }

    @Override
    public void onDelete(People people, int position) {
        mPresenter.delete(people, position);
        notifyItemRemoved(position);
    }

    @NeedsPermission(Manifest.permission.SEND_SMS)
    public void sendSMS(People people) {
        UserInfo user = UserHelper.readUserInfo(getContext());
        SMSUtil.sendMessageByIntent(getContext(), people.getPhone(), String.format(getString(R.string.sms), user.Company, people.getInterviewTime(), user.Site, user.Name, user.Phone));
    }

    @OnShowRationale(Manifest.permission.SEND_SMS)
    public void showSMSRationale(PermissionRequest request) {
        showRationaleDialog(R.string.rationale_sms, request);
    }

    @OnPermissionDenied(Manifest.permission.SEND_SMS)
    public void SMSDenied() {
        showToast("无法发送短信");
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    public void call(People people) {
        CallUtil.callPhone(getContext(), people.getPhone());
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    public void showCallRationale(PermissionRequest request) {
        showRationaleDialog(R.string.rationale_take_photo, request);
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    public void callDenied() {
        showToast("无法拨打电话");
    }

    public void toPeopleDetail(int type, People people, int requestCode) {
        Intent intent = PeopleDetailActivity.getCallingIntent(getContext(), type, people);
        getActivity().startActivityForResult(intent, requestCode);
    }

    @Override
    public void stopLoading() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}
