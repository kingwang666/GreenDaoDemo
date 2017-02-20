package com.wang.interviewassistant.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.wang.interviewassistant.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created on 2016/6/22.
 * Author: wang
 */
public abstract class BaseActivity extends AppCompatActivity {

    private boolean isRunning;

    private boolean isDestroyed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestroyed = false;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initOtherView();
        initListener();
        initData(getIntent());
        afterView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = true;
    }

    protected abstract int getLayoutId();

    protected abstract void initOtherView();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化传入的数据
     */
    protected abstract void initData(Intent intent);

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
        ToastUtil.get(this).showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    public void showToast(@StringRes int resId) {
        ToastUtil.get(this).showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    public void showCenterToast(String content) {
        ToastUtil.get(this).showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    public void showCenterToast(@StringRes int resId) {
        ToastUtil.get(this).showCenterToast(resId);
    }

    /**
     * 弹出长时间底部的Toast
     *
     * @param content 内容
     */
    public void showLongToast(String content) {
        ToastUtil.get(this).showToast(content, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间底部的Toast
     *
     * @param resId 内容
     */
    public void showLongToast(@StringRes int resId) {
        ToastUtil.get(this).showToast(resId, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间中间的Toast
     *
     * @param content 内容
     */
    public void showLongCenterToast(String content) {
        ToastUtil.get(this).showCenterToast(content, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间中间的Toast
     *
     * @param resId 内容
     */
    public void showLongCenterToast(@StringRes int resId) {
        ToastUtil.get(this).showCenterToast(resId, Toast.LENGTH_LONG);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showSuperToast(String content) {
//        SuperToastUtil.get(this).showToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showSuperToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showSuperCenterToast(String content) {
//        SuperToastUtil.get(this).showCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showSuperCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showNormalToast(String content) {
//        SuperToastUtil.get(this).showNormalToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showNormalToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showNormalToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showNormalCenterToast(String content) {
//        SuperToastUtil.get(this).showNormalCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showNormalCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showNormalCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showWarningToast(String content) {
//        SuperToastUtil.get(this).showWarningToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showWarningToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showWarningToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showWarningCenterToast(String content) {
//        SuperToastUtil.get(this).showWarningCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showWarningCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showWarningCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showErrorToast(String content) {
//        SuperToastUtil.get(this).showErrorToast(content);
        showToast(content);
    }

    /**
     * 弹出短时间底部的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showErrorToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showErrorToast(resId);
        showToast(resId);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param content 内容
     */
    @Deprecated
    public void showErrorCenterToast(String content) {
//        SuperToastUtil.get(this).showErrorCenterToast(content);
        showCenterToast(content);
    }

    /**
     * 弹出短时间中间的Toast
     *
     * @param resId 内容
     */
    @Deprecated
    public void showErrorCenterToast(@StringRes int resId) {
//        SuperToastUtil.get(this).showErrorCenterToast(resId);
        showCenterToast(resId);
    }

    /**
     * 添加fragment
     *
     * @param containerViewId fragment容器的id
     * @param fragment        使用的fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加带tag的fragment
     *
     * @param containerViewId fragment的容器id
     * @param fragment        使用的fragment
     * @param tag             标识
     */
    protected void addFragmentWithTag(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
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
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.replace(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    protected void attachFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.attach(fragment);
        fragmentTransaction.commit();
    }

    protected void detachFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.detach(fragment);
        fragmentTransaction.commit();
    }

    /**
     * 移除fragment
     *
     * @param fragment 对应的fragment
     */
    protected void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    protected boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationOnScreen(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();

            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }

        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean isKeyBoardOpened() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    /**
     * 若软键盘隐藏，则显示软键盘
     * 若软键盘显示，则隐藏软键盘
     */
    public void toggleKeyboard() {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && !isDestroyed) {
            destroy();
            isDestroyed = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
        if (isFinishing() && !isDestroyed) {
            destroy();
            isDestroyed = true;
        }
    }

    @Override
    protected void onDestroy() {
        if (!isDestroyed) {
            destroy();
            isDestroyed = true;
        }
        super.onDestroy();
    }

    protected void destroy() {

    }
}
