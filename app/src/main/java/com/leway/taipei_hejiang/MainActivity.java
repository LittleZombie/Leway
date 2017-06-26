package com.leway.taipei_hejiang;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.leway.taipei_hejiang.task.CheckVersionTask;
import com.leway.taipei_hejiang.utils.ConnectionUtil;
import com.leway.taipei_hejiang.utils.ShowUtils;

public class MainActivity extends AppCompatActivity implements CheckVersionTask.CheckVersionListener {

    private ProgressDialog statusDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();

        layoutViewPager();

        if (!ConnectionUtil.isNetworkAvailable(this)) {
            ShowUtils.showToast(this, getString(R.string.open_connection));
        } else {
            showLoading();
            requestCheckVersionTask();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cartItem:
                startShoppingCartActivity();
                return true;
            case R.id.menu_contactUsItem:
                startContactUsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    private void requestCheckVersionTask() {
        String packageName = getPackageName();
        CheckVersionTask task = new CheckVersionTask(packageName, this);
        task.execute();
    }

    @Override
    public void onUpdateVersionDone(String result) {
        statusDialog.dismiss();
        if (TextUtils.isEmpty(result)) {
            return;
        }

        String currentVersion = "";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            currentVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (!currentVersion.equals(result)) {
            showUpdateDialog();
        }
    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.is_update));
        builder.setPositiveButton(getString(R.string.update), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startGooglePlay();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.show();
    }

    private void startGooglePlay() {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        } catch (Exception e) {
            ShowUtils.showToast(this, "");
        }
    }

    private void startShoppingCartActivity() {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    private void startContactUsActivity() {
        Intent intent = new Intent(this, ContactUsActivity.class);
        startActivity(intent);
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainActivity_toolbar);
        toolbar.setLogo(R.drawable.leway);
        setSupportActionBar(toolbar);
    }

    private void layoutViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.mainActivity_viewPager);
        DrinkListPagerAdapter adapter = new DrinkListPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.mainActivity_tabLayout);
        initTabs(tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(adapter);
    }

    private void initTabs(TabLayout tabLayout) {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_original)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_special)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_fruit)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_fake_milk_tea)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_true_milk_tea)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_milk)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_high_mountain)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_winner_special)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_sweet_food)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.leway_tea_pasture)));
    }

    private class DrinkListPagerAdapter extends FragmentPagerAdapter {

        public DrinkListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                default:
                    return DrinkListFragment.getFragmentPage(ProductEnum.ORIGINAL);
                case 1:
                    return DrinkListFragment.getFragmentPage(ProductEnum.SPECIAL);
                case 2:
                    return DrinkListFragment.getFragmentPage(ProductEnum.FRUIT);
                case 3:
                    return DrinkListFragment.getFragmentPage(ProductEnum.FAKE_MILK);
                case 4:
                    return DrinkListFragment.getFragmentPage(ProductEnum.TRUE_MILK);
                case 5:
                    return DrinkListFragment.getFragmentPage(ProductEnum.MILK);
                case 6:
                    return DrinkListFragment.getFragmentPage(ProductEnum.MOUNTAIN_TEA);
                case 7:
                    return DrinkListFragment.getFragmentPage(ProductEnum.WINNER);
                case 8:
                    return DrinkListFragment.getFragmentPage(ProductEnum.SWEET);
                case 9:
                    return DrinkListFragment.getFragmentPage(ProductEnum.PASTURE);
            }
        }

        @Override
        public int getCount() {
            return 10;
        }

    }
}
