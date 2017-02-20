package com.wang.interviewassistant.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created on 2015/11/26.
 * Author: wang
 */
public class ToastUtil {

    private volatile static ToastUtil sInstance;
    private Toast mToast;
    private int mDuration = Toast.LENGTH_SHORT;
    private int mYOffset;
    private Context mContext;

    public static ToastUtil get(Context context) {
        ToastUtil toastUtil = sInstance;
        if (toastUtil == null) {
            synchronized (ToastUtil.class) {
                toastUtil = sInstance;
                if (toastUtil == null) {
                    toastUtil = new ToastUtil(context.getApplicationContext());
                    sInstance = toastUtil;
                }
            }
        }
        return toastUtil;
    }

    private ToastUtil(Context applicationContext) {
        mContext = applicationContext;
        mYOffset = Dip2PxUtil.Dip2Px(applicationContext, 64);
    }

    public void showToast(@StringRes int resId) {
        showToast(resId, mDuration);
    }

    public void showToast(String content) {
        showToast(content, mDuration);
    }

    public void showToast(@StringRes int resId, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, resId, duration);
        } else {
            mToast.setText(resId);
            mToast.setDuration(duration);
        }
        mToast.setGravity(Gravity.BOTTOM, 0, mYOffset);
        mToast.show();
    }

    public void showToast(String content, int duration) {
        if (!TextUtils.isEmpty(content)) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, content, duration);
            } else {
                mToast.setText(content);
                mToast.setDuration(duration);
            }
            mToast.setGravity(Gravity.BOTTOM, 0, mYOffset);
            mToast.show();
        }
    }

    public void showCenterToast(@StringRes int resId) {
        showCenterToast(resId, mDuration);
    }

    public void showCenterToast(@StringRes int resId, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, resId, duration);
        } else {
            mToast.setDuration(duration);
            mToast.setText(resId);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    public void showCenterToast(String content) {
        showCenterToast(content, mDuration);
    }

    public void showCenterToast(String content, int duration) {
        if (!TextUtils.isEmpty(content)) {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, content, duration);
            } else {
                mToast.setDuration(duration);
                mToast.setText(content);
            }
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        }
    }
}
