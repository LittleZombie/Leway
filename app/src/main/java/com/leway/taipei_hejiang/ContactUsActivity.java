package com.leway.taipei_hejiang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.leway.taipei_hejiang.utils.ShowUtils;

/**
 * Created by Li Chia-en on 2017/5/22.
 */

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_activity);
        initToolBar();

        layoutContentView();
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

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.contactUsActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void layoutContentView() {
        findViewById(R.id.contactUsActivity_locationImageView).setOnClickListener(this);
    }

    public void onPhoneClick(View view) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0225015959"));
            startActivity(intent);
        }catch (Exception e){
            ShowUtils.showToast(this, getString(R.string.can_not_call));
        }
    }

    public void startLewayLocationWithMap() {
        try {
            Double myLatitude = 25.059928;
            Double myLongitude = 121.539292;
            String labelLocation = getString(R.string.app_name);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + myLatitude + ">,<" + myLongitude + ">?q=<" + myLatitude + ">,<" + myLongitude + ">(" + labelLocation + ")"));
            startActivity(intent);
        } catch (Exception e) {
            ShowUtils.showToast(this, "Please install a maps application");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactUsActivity_locationImageView:
                startLewayLocationWithMap();
                break;
        }
    }

    public void onFaceBookClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/leway011/"));
        startActivity(intent);
    }
}
