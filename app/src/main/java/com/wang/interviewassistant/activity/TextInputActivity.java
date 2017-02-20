package com.wang.interviewassistant.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.text.TextUtils;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.util.InputTypeCheck;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wang
 * on 2017/2/16
 */

public class TextInputActivity extends BaseKWActivity {


    @BindView(R.id.text_til)
    TextInputLayout mTextTil;

    private boolean mIsPhone;


    public static Intent getCallingIntent(Context context, String title, String older, boolean isPhone) {
        Intent intent = new Intent(context, TextInputActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("older", older);
        intent.putExtra("is_phone", isPhone);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_text_input;
    }

    @Override
    protected void initData(Intent intent) {
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        mTextTil.setHint(title);
        mIsPhone = intent.getBooleanExtra("is_phone", false);
        if (mIsPhone) {
            mTextTil.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        } else {
            mTextTil.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        }
        String older = intent.getStringExtra("older");
        if (!TextUtils.isEmpty(older)) {
            mTextTil.getEditText().setText(older);
            mTextTil.getEditText().setSelection(older.length());
        }

    }

    @Override
    protected void afterView() {

    }

    @OnClick(R.id.save_btn)
    public void onSave() {
        String text = mTextTil.getEditText().getText().toString();
        if (mIsPhone && !InputTypeCheck.isPhoneNumber(text)) {
            showToast("请输入正确的手机号码");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("text", TextUtils.isEmpty(text) ? "" : text);
        setResult(RESULT_OK, intent);
        finish();
    }

}
