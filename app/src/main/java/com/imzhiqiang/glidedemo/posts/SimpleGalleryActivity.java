package com.imzhiqiang.glidedemo.posts;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.imzhiqiang.glidedemo.R;

public class SimpleGalleryActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int THUMBNAIL_LOAD = 1101;

    private GridView mGallery;
    private CursorAdapter mAdapter;

    private MaterialDialog mLoadingDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_gallery);

        mGallery = (GridView) findViewById(R.id.gallery);
        mGallery.setNumColumns(4);
        mGallery.setHorizontalSpacing(5);
        mAdapter = new GalleryAdapter(this, null, false);
        mGallery.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(THUMBNAIL_LOAD, null, this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
        getLoaderManager().destroyLoader(THUMBNAIL_LOAD);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mLoadingDialog = new MaterialDialog.Builder(this)
                .content("Loading photo album...")
                .progress(true, 0)
                .build();
        mLoadingDialog.show();
        switch (id) {
            case THUMBNAIL_LOAD:
                final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID,
                        MediaStore.Images.ImageColumns.ORIENTATION};
                return new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (!isFinishing()) {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog = new MaterialDialog.Builder(this)
                    .content("Loading photo album...")
                    .progress(true, 0)
                    .build();
            mLoadingDialog.show();

        }
        mAdapter.changeCursor(null);
    }


    private class GalleryAdapter extends CursorAdapter {

        public GalleryAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View contentView = View.inflate(context, R.layout.gallery_item, null);

            ViewHolder holder = new ViewHolder();

            holder.picture = (ImageView) contentView.findViewById(R.id.pic);

            contentView.setTag(holder);

            return contentView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            if (cursor != null) {
                ViewHolder holder = (ViewHolder) view.getTag();
                if (holder != null) {
                    int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    if (dataColumnIndex < cursor.getColumnCount()) {
                        final String originalUrl = cursor.getString(dataColumnIndex);
                        final String url = "file://" + originalUrl;

                        Glide.with(SimpleGalleryActivity.this).load(url).into(holder.picture);
                    }
                }
            }
        }
    }

    private static class ViewHolder {
        public ImageView picture;
    }
}
