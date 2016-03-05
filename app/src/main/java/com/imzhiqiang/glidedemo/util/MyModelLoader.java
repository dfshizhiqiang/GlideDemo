package com.imzhiqiang.glidedemo.util;


import android.content.Context;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

public class MyModelLoader extends BaseGlideUrlLoader<MyDataModel> {

    public MyModelLoader(ModelLoader<GlideUrl, InputStream> urlLoader) {
        super(urlLoader);
    }

    public static class Factory implements ModelLoaderFactory<MyDataModel, InputStream> {


        @Override
        public ModelLoader<MyDataModel, InputStream> build(Context context,
                                                           MultiModelLoaderFactory multiFactory) {
            return new MyModelLoader(multiFactory.build(GlideUrl.class, InputStream.class));
        }

        @Override
        public void teardown() {
            // Do nothing.
        }
    }

    @Override
    protected String getUrl(MyDataModel myDataModel, int width, int height, Options options) {
        return myDataModel.buildUrl(width, height);
    }

    @Override
    public boolean handles(MyDataModel myDataModel) {
        return true;
    }
}
