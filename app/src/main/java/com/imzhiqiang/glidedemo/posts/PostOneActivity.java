package com.imzhiqiang.glidedemo.posts;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imzhiqiang.glidedemo.R;

public class PostOneActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_one);

        ImageView targetImageView = (ImageView) findViewById(R.id.imageView);
        String internetUrl =
                "http://img1.ph.126.net/gGRsUgEni_P9xFrirRs2Ww==/6630801683887325749.jpg";
        Glide.with(this).asDrawable().load(internetUrl).into(targetImageView);
    }
}
