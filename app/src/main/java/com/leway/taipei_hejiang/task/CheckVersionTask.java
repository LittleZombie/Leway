package com.leway.taipei_hejiang.task;

import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by Li Chia-en on 2017/5/25.
 */

public class CheckVersionTask extends AsyncTask<Void, Void, String> {

    private String packageName;
    private CheckVersionListener listener;

    public CheckVersionTask(String packageName, CheckVersionListener listener) {
        this.packageName = packageName;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String newVersion = "";
        try {
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + packageName + "&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div[itemprop=softwareVersion]")
                    .first()
                    .ownText();
        } catch (Exception e) {
            Log.e("CheckVersionTask", "Exception: " + e.getMessage());
            e.printStackTrace();
        }

        return newVersion;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        listener.onUpdateVersionDone(result);
    }

    public interface CheckVersionListener{
        void onUpdateVersionDone(String result);
    }
}
