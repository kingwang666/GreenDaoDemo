package com.wang.interviewassistant.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.wang.interviewassistant.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2016/6/22.
 * Author: wang
 */
public abstract class BaseFragment extends Fragment {

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    /**
     * 是否视图已经初始化
     */
    protected boolean isPrepared = false;
    /**
     * 是否二次加载
     */
    protected boolean isTwo = false;

    protected Unbinder binder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        binder = ButterKnife.bind(this, rootView);
        initOtherView(rootView);
        initListener(rootView);
        isPrepared = true;
        afterView();
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    /**
     * 初始化其他视图
     *
     * @param rootView
     */
    protected abstract void initOtherView(View rootView);

    /**
     * 初始化监听
     *
     * @param rootView
     */
    protected abstract void initListener(View rootView);

    /**
     * 初始化传入的数据(视图还未初始化)(也可进行dagger2注入)
     */
    protected abstract void initData(Bundle arg);

    /**
     * 视图的操作和dagger2的初始化
     */
    protected abstract void afterView();


    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    public void showToast(String content) {
        ToastUtil.get(getContext()).showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    public void showToast(@StringRes int resId) {
        ToastUtil.get(getContext()).showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    public void showCenterToast(String content) {
        ToastUtil.get(getContext()).showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    public void showCenterToast(@StringRes int resId) {
        ToastUtil.get(getContext()).showCenterToast(resId);
    }

    /**
     * 弹出长时间底部的Toast
     *
     * @param content 内容
     */
    public void showLongToast(String content) {
        ToastUtil.get(getContext()).showToast(content, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间底部的Toast
     *
     * @param resId 内容
     */
    public void showLongToast(@StringRes int resId) {
        ToastUtil.get(getContext()).showToast(resId, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间中间的Toast
     *
     * @param content 内容
     */
    public void showLongCenterToast(String content) {
        ToastUtil.get(getContext()).showCenterToast(content, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间中间的Toast
     *
     * @param resId 内容
     */
    public void showLongCenterToast(@StringRes int resId) {
        ToastUtil.get(getContext()).showCenterToast(resId, Toast.LENGTH_LONG);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showSuperToast(String content) {
//        SuperToastUtil.get(getContext()).showToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showSuperToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showSuperCenterToast(String content) {
//        SuperToastUtil.get(getContext()).showCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showSuperCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showNormalToast(String content) {
//        SuperToastUtil.get(getContext()).showNormalToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showNormalToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showNormalToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showNormalCenterToast(String content) {
//        SuperToastUtil.get(getContext()).showNormalCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showNormalCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showNormalCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showWarningToast(String content) {
//        SuperToastUtil.get(getContext()).showWarningToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showWarningToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showWarningToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showWarningCenterToast(String content) {
//        SuperToastUtil.get(getContext()).showWarningCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showWarningCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showWarningCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showErrorToast(String content) {
//        SuperToastUtil.get(getContext()).showErrorToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showErrorToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showErrorToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showErrorCenterToast(String content) {
//        SuperToastUtil.get(getContext()).showErrorCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showErrorCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(getContext()).showErrorCenterToast(resId);
        showCenterToast(resId);
    }


    /**
     * 添加fragment
     *
     * @param containerViewId fragment容器的id
     * @param fragment        使用的fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).commit();
    }

    /**
     * 添加fragment
     *
     * @param containerViewId fragment容器的id
     * @param fragment        使用的fragment
     */
    protected void addFragmentChild(int containerViewId, Fragment fragment) {
        getChildFragmentManager().beginTransaction().add(containerViewId, fragment).commit();
    }

    /**
     * 添加带tag的fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     * @param tag             标识
     */
    protected void addFragmentWithTag(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * 添加带tag的fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     * @param tag             标识
     */
    protected void addFragmentTagChild(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换没有则添加fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 替换没有则添加fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     */
    protected void replaceFragmentChild(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 替换没有则添加带tag的fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     * @param tag             标识
     */
    protected void replaceFragmentWithTag(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.replace(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换没有则添加带tag的fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     * @param tag             标识
     */
    protected void replaceFragmentTagChild(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
//        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.replace(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * 移除fragment
     *
     * @param fragment 对应的fragment
     */
    protected void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    /**
     * 移除fragment
     *
     * @param fragment 对应的fragment
     */
    protected void removeFragmentChild(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        delayLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    protected boolean isCanLoad() {
        return isPrepared && isVisible && !isTwo;
    }

    /**
     * 延迟加载
     */
    protected void delayLoad() {
        isTwo = true;
    }

    @Override
    public void onDestroyView() {
        isPrepared = false;
        if (binder != null) {
            binder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        isPrepared = false;
        super.onDestroy();
    }
}
