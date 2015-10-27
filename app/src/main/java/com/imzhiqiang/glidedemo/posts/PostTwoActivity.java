package com.imzhiqiang.glidedemo.posts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.imzhiqiang.glidedemo.R;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostTwoActivity extends BaseActivity {

    private RequestManager requestManager;

    @Bind(R.id.imageView2)
    ImageView imageView;

    @Bind(R.id.current_source)TextView currentSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_two);

        ButterKnife.bind(this);

        requestManager = Glide.with(this);

    }

    @OnClick(R.id.btn_intent)
    public void loadIntent() {
        requestManager.load("http://img0.ph.126.net/mXB8X0xkfgebar2kOH0WGw==/6631309658258301075.jpg").into(imageView);
        currentSource.setText("From Intent");
    }

    @OnClick(R.id.btn_uri)
    public void loadUri() {
        requestManager.load(resourceIdToUri(this, R.drawable.glide_logo)).into(imageView);
        currentSource.setText("From Uri");
    }

    @OnClick(R.id.btn_file)
    public void loadFile() {
        requestManager.load(new File(getFilesDir(),"dog.jpg")).into(imageView);
        currentSource.setText("From File");
    }

    @OnClick(R.id.btn_res)
    public void loadRes() {
        requestManager.load(R.mipmap.ic_launcher).into(imageView);
        currentSource.setText("From Res");
    }

    @OnClick(R.id.btn_gallery)
    public void loadGallery() {
        startActivity(new Intent(this,SimpleGalleryActivity.class));
    }

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
