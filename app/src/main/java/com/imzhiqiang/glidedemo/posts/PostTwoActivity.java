package com.imzhiqiang.glidedemo.posts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.imzhiqiang.glidedemo.R;
import com.imzhiqiang.glidedemo.util.Tools;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.imzhiqiang.glidedemo.util.Constants.IMG_NAME;

public class PostTwoActivity extends BaseActivity {

    private RequestManager requestManager;

    private String url1 = "http://img0.ph.126.net/SSM85Ibp3g8hOTa2tkjnBw==/6631268976328190694.jpg";
    private String url2 = "http://img1.ph.126.net/tAn2tp-lzI1waAzc1NiN-g==/6631224995863078981.jpg";

    @Bind(R.id.imageView2)
    ImageView imageView;

    @Bind(R.id.current_source)
    TextView currentSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_two);

        ButterKnife.bind(this);

        requestManager = Glide.with(this);

    }

    @OnClick(R.id.btn_intent)
    public void loadIntent() {
        String url = "http://img0.ph.126.net/mXB8X0xkfgebar2kOH0WGw==/6631309658258301075.jpg";
        requestManager
                .load(url)
                .into(imageView);
        currentSource.setText("From Intent");
    }

    @OnClick(R.id.btn_uri)
    public void loadUri() {
        requestManager
                .load(Tools.resourceIdToUri(this, R.drawable.glide_logo))
                .into(imageView);
        currentSource.setText("From Uri");
    }

    @OnClick(R.id.btn_file)
    public void loadFile() {
        requestManager
                .load(new File(getFilesDir(), IMG_NAME))
                .into(imageView);
        currentSource.setText("From File");
    }

    @OnClick(R.id.btn_res)
    public void loadRes() {
        requestManager
                .load(R.mipmap.ic_launcher)
                .into(imageView);
        currentSource.setText("From Res");
    }

    @OnClick(R.id.btn_custom)
    public void loadCustom() {
        requestManager
                .using(new MyUrlLoader(this))
                .load(new MyDataModel() {//用两张不同的图片模拟不同尺寸大小
                    @Override
                    public String buidUrl(int width, int height) {
                        if (width >= 600) {//在1080p的手机上看到url1
                            return url1;
                        } else {//在720p的手机上看到url2
                            return url2;
                        }
                    }
                })
                .into(imageView);
        currentSource.setText("From Custom Source");
    }

    @OnClick(R.id.btn_gallery)
    public void loadGallery() {
        startActivity(new Intent(this, SimpleGalleryActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
