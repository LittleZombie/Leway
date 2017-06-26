package com.leway.taipei_hejiang;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leway.taipei_hejiang.model.Product;
import com.leway.taipei_hejiang.utils.CalculateUtil;
import com.leway.taipei_hejiang.utils.SaveManager;

import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/15.
 */

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener, CartAdapter.OnClearProductListener {

    private MenuItem clearAllMenuItem;
    private List<Product> products;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppint_cart_activity);
        initToolBar();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.shoppingCartActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart, menu);

        clearAllMenuItem = menu.findItem(R.id.menu_clearAllItem);
        List<Product> products = SaveManager.revertProducts(this);
        boolean isEmpty = products == null || products.size() <= 0;
        layoutMenuItem(isEmpty);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_clearAllItem:
                onClearAllClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        products = SaveManager.revertProducts(this);
        if (products == null || products.size() <= 0) {
            layoutShowContentView(true);
            return;
        }

        layoutProductListView(products);
        layoutTotalPrice(products);
        layoutShowContentView(false);
    }

    private void layoutShowContentView(boolean isEmpty) {
        findViewById(R.id.shoppingCartActivity_contentLayout).setVisibility(isEmpty ? View.GONE : View.VISIBLE);

        View emptyView = findViewById(R.id.shoppingCartActivity_emptyTextView);
        emptyView.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        emptyView.setOnClickListener(this);

        layoutMenuItem(isEmpty);
    }

    private void layoutMenuItem(boolean isEmpty) {
        if (clearAllMenuItem != null) {
            clearAllMenuItem.setVisible(!isEmpty);
        }
    }

    private void layoutProductListView(List<Product> products) {
        Log.e("", "onResume products = " + new Gson().toJson(products));
        CartAdapter adapter = new CartAdapter(products, this, this);
        ListView listView = (ListView) findViewById(R.id.shoppingCartActivity_listView);
        listView.setAdapter(adapter);
    }

    private void layoutTotalPrice(List<Product> products) {
        int totalPrice = CalculateUtil.totalProductPrice(products);
        String total = getString(R.string.calculate_total) + String.valueOf(totalPrice);
        TextView totalTextView = (TextView) findViewById(R.id.shoppingCartActivity_totalTextView);
        totalTextView.setText(total);

        findViewById(R.id.shoppingCartActivity_confirmTextView).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shoppingCartActivity_emptyTextView:
                onBackPressed();
                break;
            case R.id.shoppingCartActivity_confirmTextView:
                onConfirmButtonClick();
                break;
        }
    }

    private void onConfirmButtonClick() {
        Intent intent = new Intent(this, OrderInfoActivity.class);
        startActivity(intent);
    }

    private void onClearAllClick() {
        new AlertDialog.Builder(this).setMessage(R.string.is_clear_all)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveManager.clearAllProducts(ShoppingCartActivity.this);
                        dialog.dismiss();
                        onResume();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                }).show();

    }

    @Override
    public void onClearProductClick(Product product) {
        products.remove(product);
        SaveManager.saveProductList(this, products);
        onResume();
    }
}
