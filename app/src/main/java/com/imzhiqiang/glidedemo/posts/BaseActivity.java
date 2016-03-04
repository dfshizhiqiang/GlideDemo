package com.imzhiqiang.glidedemo.posts;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.imzhiqiang.glidedemo.R;

public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        setupToolbar();
    }

    private void setupToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            mToolbar.setTitle(getClass().getSimpleName());
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
