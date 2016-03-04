package com.imzhiqiang.glidedemo.posts;

import android.content.Context;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

/**
 * Created by Zech on 2015/11/1 Email: dfshizhiqiang@gmail.com
 */
public class MyUrlLoader extends BaseGlideUrlLoader<MyDataModel> {
    public MyUrlLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(MyDataModel model, int width, int height) {
        return model.buidUrl(width, height);
    }
}
