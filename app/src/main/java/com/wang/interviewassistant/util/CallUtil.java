package com.wang.interviewassistant.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by wang
 * on 2017/2/16
 */

public class CallUtil {

    public static void callPhone(Context context, String phone) {
        if (context != null) {
            Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
            context.startActivity(intent);
        }
    }

}
