package com.bonuskids.moimaster.feed;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bonuskids.moimaster.R;
import com.bonuskids.moimaster.data.Order;
import com.bonuskids.moimaster.databinding.OrderDetailActBinding;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OrderDetailActBinding binding = DataBindingUtil.setContentView(this, R.layout.order_detail_act);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Order order = getIntent().getParcelableExtra("selectedOrder");
        binding.setOrder(order);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
