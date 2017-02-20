package com.wang.interviewassistant.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Px;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wang
 * on 2017/1/6
 */

public class StringUtil {

    public static StringBuilder with(Context context) {
        return new StringBuilder(context);
    }

    private static final String MARK = "\u3000";

    public static String join(String separated, String source, Object newString) {
        if (TextUtils.isEmpty(source)) {
            return String.valueOf(newString);
        }
        return source + separated + String.valueOf(newString);
    }

    /**
     * 去除浮点数多于的0和点
     *
     * @param source
     * @return
     */
    public static String subZeroAndDot(String source) {
        if (source.indexOf(".") > 0) {
            source = source.replaceAll("0+?$", "");//去掉多余的0
            source = source.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return source;
    }

    public static String subZeroAndDot(String format, float f) {
        return subZeroAndDot(String.format(format, f));
    }

    public static String subZeroAndDot(float f) {
        return subZeroAndDot(Float.toString(f));
    }

    /**
     * 设置首行缩进n个字符
     *
     * @param num  缩进个数
     * @param text 文本内容
     */
    public static String setIndent(int num, String text) {
        String first = "";
        for (int i = 0; i < num; i++) {
            first = first + MARK;
        }
        return first + text;
    }

    public static class StringBuilder {

        private SpannableString mSource;

        private Context mContext;

        private int mStartIndex = 0;

        private int mEndIndex = 0;

        public StringBuilder(Context context) {
            mContext = context;
        }

        public StringBuilder source(CharSequence source) {
            if (source instanceof SpannableString) {
                mSource = (SpannableString) source;
            } else {
                mSource = new SpannableString(source);
            }
            return this;
        }

        public StringBuilder startAndCount(int startIndex, int count) {
            mStartIndex = startIndex;
            mEndIndex = startIndex + count;
            return this;
        }

        public StringBuilder startAndEnd(int startIndex, int endIndex) {
            mStartIndex = startIndex;
            mEndIndex = endIndex;
            return this;
        }

        /**
         * 设置部分文本字体颜色值
         */
        public StringBuilder colorRes(@ColorRes int colorId) {
            return color(ContextCompat.getColor(mContext, colorId));
        }

        /**
         * 设置部分文本字体颜色值
         */
        public StringBuilder color(@ColorInt int color) {
            mSource.setSpan(new ForegroundColorSpan(color), mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置部分文本背景颜色值
         */
        public StringBuilder backgroundColorRes(@ColorRes int colorId) {
            return backgroundColor(ContextCompat.getColor(mContext, colorId));
        }

        /**
         * 设置部分文本背景颜色值
         */
        public StringBuilder backgroundColor(@ColorInt int color) {
            mSource.setSpan(new BackgroundColorSpan(color), mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置部分文本大小
         */
        public StringBuilder sizeSp(int size) {
            return size(sp2px(size));
        }

        /**
         * 设置部分文本大小
         */
        public StringBuilder size(@Px int size) {
            mSource.setSpan(new AbsoluteSizeSpan(size), mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片
         */
        public StringBuilder image(@DrawableRes int resId) {
            ImageSpan imageSpan = new ImageSpan(mContext, resId);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片
         */
        public StringBuilder image(Bitmap bitmap) {
            ImageSpan imageSpan = new ImageSpan(mContext, bitmap);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片
         */
        public StringBuilder image(Drawable drawable) {
            ImageSpan imageSpan = new ImageSpan(drawable);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片(指定同样的宽和高)
         */
        public StringBuilder image(@DrawableRes int resId, int size) {
            Drawable drawable = ContextCompat.getDrawable(mContext, resId);
            int sizePx = dp2px(size);
            drawable.setBounds(0, 0, sizePx, sizePx);// 这里设置图片的大小
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片(指定同样的宽和高)
         */
        public StringBuilder image(Bitmap bitmap, int size) {
            Drawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);
            int sizePx = dp2px(size);
            drawable.setBounds(0, 0, sizePx, sizePx);// 这里设置图片的大小
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片(指定同样的宽和高)
         */
        public StringBuilder image(Drawable drawable, int size) {
            int sizePx = dp2px(size);
            drawable.setBounds(0, 0, sizePx, sizePx);// 这里设置图片的大小
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片(指定宽和高)
         */
        public StringBuilder image(@DrawableRes int resId, int width, int height) {
            Drawable drawable = ContextCompat.getDrawable(mContext, resId);
            drawable.setBounds(0, 0, dp2px(width), dp2px(height));
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片(指定宽和高)
         */
        public StringBuilder image(Bitmap bitmap, int width, int height) {
            Drawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);
            drawable.setBounds(0, 0, dp2px(width), dp2px(height));
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置图片(指定宽和高)
         */
        public StringBuilder image(Drawable drawable, int width, int height) {
            drawable.setBounds(0, 0, dp2px(width), dp2px(height));
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            mSource.setSpan(imageSpan, mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置部分文本字体风格
         *
         * @param style 字体风格(Typeface的枚举)
         */
        public StringBuilder typeface(int style) {
            mSource.setSpan(new StyleSpan(style), mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 将部分文本设置为粗体
         */
        public StringBuilder bold() {
            return typeface(Typeface.BOLD);
        }

        /**
         * 设置部分文本删除线
         */
        public StringBuilder strikethrough() {
            mSource.setSpan(new StrikethroughSpan(), mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置部分文本下划线
         */
        public StringBuilder underLine() {
            mSource.setSpan(new UnderlineSpan(), mStartIndex, mEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         *设置引用
         */
        public StringBuilder quote() {
            mSource.setSpan(new QuoteSpan(), mStartIndex, mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置字体相对大小,参数表示为默认字体大小的多少倍
         *
         * @param proportion 倍数
         */
        public StringBuilder relativeSize(float proportion) {
            mSource.setSpan(new RelativeSizeSpan(proportion), mStartIndex, mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置下标
         */
        public StringBuilder subscript() {
            mSource.setSpan(new SubscriptSpan(), mStartIndex, mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置上标
         */
        public StringBuilder superscript() {
            mSource.setSpan(new SuperscriptSpan(), mStartIndex, mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 超级链接（需要添加{@link TextView#setMovementMethod(MovementMethod)}方法附加响应
         * example 电话 "tel:123456789", 邮件 "mailto:webmaster@google.com"
         * 网络 "https://www.baidu.com", 短信(使用sms:或者smsto:) "sms:4155551212"
         * 彩信*使用mms:或者mmsto:) "mms:4155551212", 地图 "geo:38.899533,-77.036476"
         *
         * @param url url地址
         */
        public StringBuilder url(String url) {
            mSource.setSpan(new URLSpan(url), mStartIndex, mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        /**
         * 设置字体大小, 参数表示为默认字体宽度的多少倍
         *
         * @param proportion 倍数
         */
        public StringBuilder scaleX(float proportion) {
            mSource.setSpan(new ScaleXSpan(proportion), mStartIndex, mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        public void into(TextView textView) {
            textView.setText(mSource);
            mContext = null;
        }

        public SpannableString get() {
            mContext = null;
            return mSource;
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         */
        private int sp2px(float spValue) {
            final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        }

        private int dp2px(float dpValue) {
            final float scale = mContext.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
    }

}
