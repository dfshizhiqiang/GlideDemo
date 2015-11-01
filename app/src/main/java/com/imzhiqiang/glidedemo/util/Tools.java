package com.imzhiqiang.glidedemo.util;

import android.content.Context;
import android.net.Uri;

/**
 * Created by Zech on 2015/11/1
 * Email: dfshizhiqiang@gmail.com
 */
public class Tools {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
