package com.wang.interviewassistant.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

import java.util.List;

/**
 * Created by wang
 * on 2017/2/15
 */

public class SMSUtil {

    /**
     * 调用系统发短信界面 不需要用户自己输入接收方的电话号码
     *
     * @param context    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessageByIntent(Context context, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", smsContent);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }

    }

    /** 调用系统发短信界面,需要用户自己输入接收方的电话号码
     *
     * 示例：SMSUtil.sendMessageByIntent(MainActivity.this,"你好");
     *
     * @param context
     * @param message
     */
    public static void sendMessageByIntent(Context context, String message) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms_body", message);
        intent.setType("vnd.android-dir/mms-sms");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    /**
     * 直接调用短信接口发短信
     * @param phoneNumber
     * @param message
     */
    public static void sendSMS(String phoneNumber, String message) {

        SmsManager smsManager = SmsManager.getDefault();
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }
}
