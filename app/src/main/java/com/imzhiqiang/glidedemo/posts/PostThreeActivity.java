package com.imzhiqiang.glidedemo.posts;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imzhiqiang.glidedemo.R;

public class PostThreeActivity extends BaseActivity {

    private String url = "http://ww3.sinaimg.cn/bmiddle/005AUQMNjw1f1jxncznq8g30b4069kjl.gif";
    private String thumbnailUrl =
            "http://img0.ph.126.net/ZRtqC7-0gXMd4Pk6pXifdQ==/6598146188600193111.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_three);

        ImageView imageView = (ImageView) findViewById(R.id.imageView3);

        Glide.with(this).load(url).thumbnail(Glide.with(this).load(thumbnailUrl)).into(imageView);
    }
}
