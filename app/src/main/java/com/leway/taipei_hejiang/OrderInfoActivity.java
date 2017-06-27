package com.leway.taipei_hejiang;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.leway.taipei_hejiang.mail.SendMailTask;
import com.leway.taipei_hejiang.model.CustomerOrderInfo;
import com.leway.taipei_hejiang.utils.ConnectionUtil;
import com.leway.taipei_hejiang.utils.MailStringUtils;
import com.leway.taipei_hejiang.utils.SaveManager;
import com.leway.taipei_hejiang.utils.SaveUtils;
import com.leway.taipei_hejiang.utils.ShowUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/18.
 */

public class OrderInfoActivity extends AppCompatActivity implements SendMailTask.OnTaskListener {

    private ProgressDialog statusDialog;

    // 讀取函式庫
    static {
        System.loadLibrary("mail");
    }

    public native String getFromEmail();

    public native String getFromEmailP();

    public native String getToEmail();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_info_activity);
        initToolbar();
        initDataLayout();
    }

    private void initDataLayout() {
        EditText companyEditText = (EditText) findViewById(R.id.orderInfoActivity_companyEditText);
        companyEditText.setText(SaveUtils.getCompanyName());

        EditText nameEditText = (EditText) findViewById(R.id.orderInfoActivity_nameEditText);
        nameEditText.setText(SaveUtils.getName());

        EditText phoneEditText = (EditText) findViewById(R.id.orderInfoActivity_phoneEditText);
        phoneEditText.setText(SaveUtils.getPhone());

        RadioButton selfRadioButton = (RadioButton) findViewById(R.id.orderInfoActivity_takeSelfRadioButton);
        RadioButton otherRadioButton = (RadioButton) findViewById(R.id.orderInfoActivity_takeOtherRadioButton);
        if(SaveUtils.isSelfTake()){
            selfRadioButton.setChecked(true);
        }else{
            otherRadioButton.setChecked(true);
        }

        EditText addressEditText = (EditText) findViewById(R.id.orderInfoActivity_addressEditText);
        addressEditText.setText(SaveUtils.getAddress());
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.orderInfoActivity_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onDateClick(final View textView) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String format = setDateFormat(year, month, day);
                ((TextView) textView).setText(format);
            }

        }, mYear, mMonth, mDay);
        dialog.getDatePicker().setMinDate(new Date().getTime());
        dialog.show();
    }

    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {
        return String.valueOf(year) + "/"
                + String.valueOf(monthOfYear + 1) + "/"
                + String.valueOf(dayOfMonth);
    }

    public void onTimeClick(final View textView) {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        ((TextView) textView).setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        tpd.show();
    }

    private String getCompany() {
        return ((EditText) findViewById(R.id.orderInfoActivity_companyEditText)).getText().toString().trim();
    }

    private String getName() {
        return ((EditText) findViewById(R.id.orderInfoActivity_nameEditText)).getText().toString().trim();
    }

    private String getPhone() {
        return ((EditText) findViewById(R.id.orderInfoActivity_phoneEditText)).getText().toString().trim();
    }

    private boolean hasRadioButtonCheck() {
        return ((RadioGroup) findViewById(R.id.orderInfoActivity_radioGroup)).getCheckedRadioButtonId() != -1;
    }

    private boolean isTakeOtherSelected() {
        return ((RadioButton) findViewById(R.id.orderInfoActivity_takeOtherRadioButton)).isChecked();
    }

    private String getAddress() {
        return ((EditText) findViewById(R.id.orderInfoActivity_addressEditText)).getText().toString().trim();
    }

    private String getDate() {
        return ((TextView) findViewById(R.id.orderInfoActivity_dateTextView)).getText().toString().trim();
    }

    private String getTime() {
        return ((TextView) findViewById(R.id.orderInfoActivity_timeTextView)).getText().toString().trim();
    }

    public void onSendClick(View view) {
        if (!ConnectionUtil.isNetworkAvailable(this)) {
            ShowUtils.showToast(this, getString(R.string.open_connection));
            return;
        }

        String error = getErrorMessage();
        if (!TextUtils.isEmpty(error)) {
            showErrorDialog(error);
            return;
        }

        saveInfo();
        CustomerOrderInfo info = generateCustomerOrderInfo();
        Log.e("", "CustomerOrderInfo = " + new Gson().toJson(info));
        requestSendMailTask(info);
    }

    private void saveInfo() {
        SaveUtils.saveCompanyName(getCompany());
        SaveUtils.saveName(getName());
        SaveUtils.savePhone(getPhone());
        SaveUtils.saveFunction(!isTakeOtherSelected());
        SaveUtils.saveAddress(getAddress());
    }

    private void showLoading() {
        if (statusDialog == null) {
            statusDialog = new ProgressDialog(this);
            statusDialog.setMessage(getString(R.string.please_wait));
            statusDialog.setIndeterminate(false);
            statusDialog.setCancelable(false);
        }
        statusDialog.show();
    }

    private void requestSendMailTask(CustomerOrderInfo info) {
        showLoading();

        List<String> toMailList = new ArrayList<>();
        toMailList.add(getToEmail());

        SendMailTask task = new SendMailTask(this
                , getFromEmail()
                , getFromEmailP()
                , toMailList
                , getString(R.string.order_title)
                , MailStringUtils.createMailBody(this, info), this);
        task.execute();
    }

    @Override
    public void onTaskSuccess() {
        statusDialog.dismiss();
        showThankDialog();
    }

    @Override
    public void onTaskFailed(String body) {
        statusDialog.dismiss();
        startMailIntent(body);
    }

    private void showThankDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(R.string.thanks)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SaveManager.clearAllProducts(OrderInfoActivity.this);
                        dialog.dismiss();
                        finish();
                    }
                });
        builder.create();
        builder.show();
    }

    private void startMailIntent(String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getToEmail(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_title));
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private CustomerOrderInfo generateCustomerOrderInfo() {
        CustomerOrderInfo info = new CustomerOrderInfo();
        info.setCompany(getCompany());
        info.setName(getName());
        info.setPhone(getPhone());
        info.setMethod(getString(isTakeOtherSelected() ? R.string.take_other : R.string.take_self));
        info.setAddress(getAddress());
        info.setDate(getDate());
        info.setTime(getTime());
        return info;
    }

    private String getErrorMessage() {
        String write = getString(R.string.write);
        if (TextUtils.isEmpty(getCompany())) {
            return write + getString(R.string.company);
        }

        if (TextUtils.isEmpty(getName())) {
            return write + getString(R.string.order_name);
        }

        if (TextUtils.isEmpty(getPhone())) {
            return write + getString(R.string.order_phone);
        }

        if (!hasRadioButtonCheck()) {
            return getString(R.string.please_select_method);
        }

        if (isTakeOtherSelected() && TextUtils.isEmpty(getAddress())) {
            return write + getString(R.string.take_other_address);
        }

        if (TextUtils.isEmpty(getDate())) {
            return write + getString(R.string.date);
        }

        if (TextUtils.isEmpty(getTime())) {
            return write + getString(R.string.time);
        }

        return "";
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    @Override
    public void onBackPressed() {
        showBackWarning();
    }

    private void showBackWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.is_finish_this_page)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

}
