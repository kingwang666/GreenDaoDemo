package com.wang.interviewassistant.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.model.UserInfo;
import com.wang.interviewassistant.util.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wang
 * on 2017/2/16
 */

public class UserInfoActivity extends BaseKWActivity {

    private static final int COMPANY = 100;
    private static final int NAME = 101;
    private static final int PHONE = 102;
    private static final int SITE = 103;

    @BindView(R.id.company_tv)
    AppCompatTextView mCompanyTV;
    @BindView(R.id.name_tv)
    AppCompatTextView mNameTV;
    @BindView(R.id.phone_tv)
    AppCompatTextView mPhoneTV;
    @BindView(R.id.site_tv)
    AppCompatTextView mSiteTV;

    private UserInfo mUserInfo;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, UserInfoActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                UserHelper.saveUserInfo(UserInfoActivity.this, mUserInfo);
                finish();
                return true;
            }
        });
    }

    @Override
    protected void initData(Intent intent) {
        mUserInfo = UserHelper.readUserInfo(this);
        if (mUserInfo == null) {
            mUserInfo = new UserInfo();
        }
    }

    @Override
    protected void afterView() {
        mCompanyTV.setText(String.format("公司: %s", mUserInfo.Company));
        mSiteTV.setText(String.format("公司地点: %s", mUserInfo.Site));
        mNameTV.setText(String.format("联系人: %s", mUserInfo.Name));
        mPhoneTV.setText(String.format("联系电话: %s", mUserInfo.Phone));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        String text = data.getStringExtra("text");
        switch (requestCode) {
            case COMPANY:
                mCompanyTV.setText(String.format("公司: %s", text));
                mUserInfo.Company = text;
                break;
            case SITE:
                mSiteTV.setText(String.format("公司地点: %s", text));
                mUserInfo.Site = text;
                break;
            case NAME:
                mNameTV.setText(String.format("联系人: %s", text));
                mUserInfo.Name = text;
                break;
            case PHONE:
                mPhoneTV.setText(String.format("联系电话: %s", text));
                mUserInfo.Phone = text;
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_info, menu);
        return true;
    }

    @OnClick({R.id.company_tv, R.id.name_tv, R.id.phone_tv, R.id.site_tv})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.company_tv:
                intent = TextInputActivity.getCallingIntent(this, "公司", mUserInfo.Company, false);
                startActivityForResult(intent, COMPANY);
                break;
            case R.id.name_tv:
                intent = TextInputActivity.getCallingIntent(this, "联系人", mUserInfo.Name, false);
                startActivityForResult(intent, NAME);
                break;
            case R.id.phone_tv:
                intent = TextInputActivity.getCallingIntent(this, "联系电话", mUserInfo.Phone, true);
                startActivityForResult(intent, PHONE);
                break;
            case R.id.site_tv:
                intent = TextInputActivity.getCallingIntent(this, "公司地点", mUserInfo.Site, false);
                startActivityForResult(intent, SITE);
                break;
        }
    }

}
