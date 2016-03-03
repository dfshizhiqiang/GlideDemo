package com.imzhiqiang.glidedemo.posts;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.imzhiqiang.glidedemo.R;

public class PostThreeActivity extends BaseActivity {

    private String url = "http://ww3.sinaimg.cn/bmiddle/005AUQMNjw1f1jxncznq8g30b4069kjl.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_three);

        ImageView imageView = (ImageView) findViewById(R.id.imageView3);

        Glide
                .with(this)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.3F)
                .into(imageView);
    }
}
