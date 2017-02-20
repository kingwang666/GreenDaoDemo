package com.wang.interviewassistant.util;

import android.text.Editable;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2016/6/12.
 * Author: wang
 */
public class InputTypeCheck {


    public static boolean isPhoneNumber(String phone) {
        String phoneRegex = "^1[3587]\\d{9}$|^(0\\d{2,3}-?|\\(0\\d{2,3}\\))?[1-9]\\d{4,7}(-\\d{1,8})?$";
        return isRegex(phone, phoneRegex);
    }


    /**
     * 匹配符合regex的字符串（全匹配）
     * @param source
     * @param regex
     * @return
     */
    public static boolean isRegex(String source, String regex) {
        return !(TextUtils.isEmpty(source) || TextUtils.isEmpty(regex)) && source.matches(regex);
    }

}
