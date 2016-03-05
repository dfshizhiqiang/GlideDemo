package com.imzhiqiang.glidedemo.util;


import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

public class MyGlideModel implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        registry.append(MyDataModel.class, InputStream.class, new MyModelLoader.Factory());
    }
}
