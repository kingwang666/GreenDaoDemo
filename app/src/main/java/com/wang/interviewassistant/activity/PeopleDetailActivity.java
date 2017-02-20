package com.wang.interviewassistant.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wang.interviewassistant.R;
import com.wang.interviewassistant.model.People;
import com.wang.interviewassistant.model.UserInfo;
import com.wang.interviewassistant.presenter.PeopleActivityPresenter;
import com.wang.interviewassistant.util.InputTypeCheck;
import com.wang.interviewassistant.util.SMSUtil;
import com.wang.interviewassistant.util.StringUtil;
import com.wang.interviewassistant.util.UserHelper;
import com.wang.interviewassistant.widget.IOSSinglePickDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
public class PeopleDetailActivity extends BaseKWActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, PeopleActivityPresenter.IView {

    private static final int NAME = 100;
    private static final int PHONE = 101;
    private static final int DESC = 102;

    @BindView(R.id.interview_time_tv)
    AppCompatTextView mInterviewTimeTv;
    @BindView(R.id.callback_time_tv)
    AppCompatTextView mCallbackTimeTv;
    @BindView(R.id.save_btn)
    AppCompatButton mSaveBtn;
    @BindView(R.id.name_tv)
    AppCompatTextView mNameTV;
    @BindView(R.id.phone_tv)
    AppCompatTextView mPhoneTV;
    @BindView(R.id.technology_tv)
    AppCompatTextView mTechnologyTV;
    @BindView(R.id.study_tv)
    AppCompatTextView mStudyTV;
    @BindView(R.id.fit_tv)
    AppCompatTextView mFitTV;
    @BindView(R.id.desc_tv)
    AppCompatTextView mDescTV;

    private PeopleActivityPresenter mPresenter;

    private People mPeople;

    private int mType;

    private String mTime;

    public static Intent getCallingIntent(Context context, int type, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra("type", type);
        if (people != null) {
            intent.putExtra("people", people);
        }
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_people_detail;
    }

    @Override
    protected void initListener() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.phone_menu:
                        PeopleDetailActivityPermissionsDispatcher.callWithCheck(PeopleDetailActivity.this, mPeople);
                        break;
                    case R.id.sms_menu:
                        PeopleDetailActivityPermissionsDispatcher.sendSMSWithCheck(PeopleDetailActivity.this, mPeople);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData(Intent intent) {
        mType = intent.getIntExtra("type", 0);
        if (mType == 0) {
            mPeople = new People(null, 0, "", "", "", "", 0, 0, 0, "");
        } else {
            mPeople = intent.getParcelableExtra("people");
        }
    }

    @Override
    protected void afterView() {
        mPresenter = new PeopleActivityPresenter();
        mPresenter.setView(this);
        mNameTV.setText(String.format("姓名: %s", mPeople.getName()));
        mPhoneTV.setText(String.format("电话: %s", mPeople.getPhone()));
        String source = String.format("技术\n%s", getLevel(mPeople.getTechnology()));
        mTechnologyTV.setText(getLevelAll(source));
        source = String.format("学习\n%s", getLevel(mPeople.getStudy()));
        mStudyTV.setText(getLevelAll(source));
        source = String.format("适合\n%s", getLevel(mPeople.getFit()));
        mFitTV.setText(getLevelAll(source));
        mInterviewTimeTv.setText(String.format("面试时间: %s".toLowerCase(), mPeople.getInterviewTime()));
        mCallbackTimeTv.setText(String.format("回复时间: %s".toLowerCase(), mPeople.getCallbackTime()));
        mDescTV.setText(String.format("个人印象:\n%s", mPeople.getDesc()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        String text = data.getStringExtra("text");
        switch (requestCode){
            case NAME:
                mPeople.setName(text);
                mNameTV.setText(String.format("姓名: %s", text));
                break;
            case PHONE:
                mPeople.setPhone(text);
                mPhoneTV.setText(String.format("电话: %s", text));
                break;
            case DESC:
                mPeople.setDesc(text);
                mDescTV.setText(String.format("个人印象:\n%s", text));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PeopleDetailActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_people_detail, menu);
        return true;
    }

    private CharSequence getLevelAll(String source){
        return StringUtil
                .with(this)
                .source(source)
                .startAndEnd(3, source.length())
                .sizeSp(16)
                .colorRes(R.color.blue300)
                .get();
    }

    private String getLevel(int level) {
        switch (level) {
            case 1:
                return "A+";
            case 2:
                return "A";
            case 3:
                return "A-";
            case 4:
                return "B+";
            case 5:
                return "B";
            case 6:
                return "B-";
            case 7:
                return "C+";
            case 8:
                return "C";
            case 9:
                return "C-";
            default:
                return "";
        }
    }

    @NeedsPermission(Manifest.permission.SEND_SMS)
    public void sendSMS(People people) {
        UserInfo user = UserHelper.readUserInfo(this);
        SMSUtil.sendMessageByIntent(this, people.getPhone(), String.format(getString(R.string.sms), user.Company, people.getInterviewTime(), user.Site, user.Name, user.Phone));
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

    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    public void showCallRationale(PermissionRequest request) {
        showRationaleDialog(R.string.rationale_take_photo, request);
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    public void callDenied() {
        showToast("无法拨打电话");
    }

    @OnClick({R.id.name_tv, R.id.phone_tv, R.id.desc_tv})
    public void onInputClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.name_tv:
                intent = TextInputActivity.getCallingIntent(this, "姓名", mPeople.getName(), false);
                startActivityForResult(intent, NAME);
                break;
            case R.id.phone_tv:
                intent = TextInputActivity.getCallingIntent(this, "电话", mPeople.getPhone(), true);
                startActivityForResult(intent, PHONE);
                break;
            case R.id.desc_tv:
                intent = TextInputActivity.getCallingIntent(this, "个人印象", mPeople.getDesc(), false);
                startActivityForResult(intent, DESC);
                break;
        }
    }

    @OnClick(R.id.interview_time_tv)
    public void onInterviewTime() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setVersion(DatePickerDialog.Version.VERSION_2);
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.callback_time_tv)
    public void onCallbackTime() {
        List<String> durations = new ArrayList<>();
        durations.add("一天内");
        durations.add("二天内");
        durations.add("三天内");
        durations.add("四天内");
        durations.add("五天内");
        durations.add("六天内");
        durations.add("一周内");
        durations.add("一周外");
        new IOSSinglePickDialog.Builder<String>()
                .setDatas(durations)
                .setDefaultData(mPeople.getCallbackTime())
                .setOnItemSelectListener(new IOSSinglePickDialog.OnItemSelectListener<String>() {
                    @Override
                    public void onDateSelected(int currentItem, String s) {
                        mPeople.setCallbackTime(s);
                        mCallbackTimeTv.setText(String.format("回复时间: %s".toLowerCase(), s));
                    }
                })
                .builder(this)
                .show();
    }

    @OnClick(R.id.technology_tv)
    public void onTechnology(){
        new IOSSinglePickDialog.Builder<String>()
                .setDatas(getLevels())
                .setDefaultPosition(mPeople.getTechnology())
                .setOnItemSelectListener(new IOSSinglePickDialog.OnItemSelectListener<String>() {
                    @Override
                    public void onDateSelected(int currentItem, String s) {
                        mPeople.setTechnology(currentItem);
                        String source = String.format("技术\n%s", s);
                        mTechnologyTV.setText(getLevelAll(source));
                    }
                })
                .builder(this)
                .show();
    }

    @OnClick(R.id.study_tv)
    public void onStudy(){
        new IOSSinglePickDialog.Builder<String>()
                .setDatas(getLevels())
                .setDefaultPosition(mPeople.getStudy())
                .setOnItemSelectListener(new IOSSinglePickDialog.OnItemSelectListener<String>() {
                    @Override
                    public void onDateSelected(int currentItem, String s) {
                        mPeople.setStudy(currentItem);
                        String source = String.format("学习\n%s", s);
                        mStudyTV.setText(getLevelAll(source));
                    }
                })
                .builder(this)
                .show();
    }

    @OnClick(R.id.fit_tv)
    public void onfit(){
        new IOSSinglePickDialog.Builder<String>()
                .setDatas(getLevels())
                .setDefaultPosition(mPeople.getFit())
                .setOnItemSelectListener(new IOSSinglePickDialog.OnItemSelectListener<String>() {
                    @Override
                    public void onDateSelected(int currentItem, String s) {
                        mPeople.setFit(currentItem);
                        String source = String.format("适合\n%s", s);
                        mFitTV.setText(getLevelAll(source));
                    }
                })
                .builder(this)
                .show();
    }

    @OnClick(R.id.save_btn)
    public void onSave() {
        mSaveBtn.setEnabled(false);
        if (mType == 0) {
            mPresenter.insert(mPeople);
        } else {
            mPresenter.update(mPeople);
        }
    }

    private List<String> getLevels(){
        List<String> level = new ArrayList<>();
        level.add("无");
        level.add("A+");
        level.add("A");
        level.add("A-");
        level.add("B+");
        level.add("B");
        level.add("B-");
        level.add("C+");
        level.add("C");
        level.add("C-");
        return level;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar week = Calendar.getInstance();
        week.set(year, monthOfYear, dayOfMonth);
        mTime = String.format("%d-%d-%d (%S)".toLowerCase(), year, monthOfYear, dayOfMonth, week.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
        TimePickerDialog tpd = TimePickerDialog.newInstance(this, 0, 0, true);
        tpd.setVersion(TimePickerDialog.Version.VERSION_2);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mTime = String.format("%s %d:%02d".toLowerCase(), mTime, hourOfDay, minute);
        mPeople.setInterviewTime(mTime);
        mInterviewTimeTv.setText(String.format("面试时间: %s".toLowerCase(), mTime));
    }

    @Override
    public void updateSuccess() {
        mSaveBtn.setEnabled(true);
        setResult(RESULT_OK);
        finish();
    }

}
