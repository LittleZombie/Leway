package com.leway.taipei_hejiang.mail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/20.
 */

public class SendMailTask extends AsyncTask<Void, Void, Boolean> {

    private Activity activity;
    private String fromEmail;
    private String fromPassword;
    private List<String> toEmailList;
    private String emailSubject;
    private String emailBody;

    private OnTaskListener listener;

    public SendMailTask(Activity activity, String fromEmail, String fromPassword
            , List<String> toEmailList, String emailSubject, String emailBody
            , OnTaskListener listener) {
        this.activity = activity;
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            GMail androidEmail = new GMail(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
            androidEmail.createEmailMessage();
            androidEmail.sendEmail();
            Log.i("SendMailTask", "Mail Sent.");
            return true;
        } catch (Exception e) {
            Log.e("", "Error Message: " + e.getMessage());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean){
            listener.onTaskSuccess();
        }else{
            listener.onTaskFailed(emailBody);
        }
    }

    public interface OnTaskListener{
        void onTaskSuccess();
        void onTaskFailed(String body);
    }
}
