package com.imzhiqiang.glidedemo.posts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.imzhiqiang.glidedemo.R;

public class GalleryUseRecyclerViewActivity extends BaseGalleryActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int THUMBNAIL_LOAD = 1101;

    private RecyclerView mGallery;
    private CursorRecyclerViewAdapter mAdapter;

    private GridLayoutManager mGridLayoutManager;

    private ContentLoadingProgressBar mLoadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_use_recyclerview);

        mLoadingProgressBar = (ContentLoadingProgressBar) findViewById(R.id.content_progress_bar);

        mGallery = (RecyclerView) findViewById(R.id.gallery);

        mGridLayoutManager = new GridLayoutManager(this, 3);

        mGallery.setLayoutManager(mGridLayoutManager);

        mAdapter = new GalleryAdapter(this, null, false);

        mGallery.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(THUMBNAIL_LOAD, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoadingProgressBar.hide();
        getLoaderManager().destroyLoader(THUMBNAIL_LOAD);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mLoadingProgressBar.show();
        switch (id) {
            case THUMBNAIL_LOAD:
                final String[] columns = {
                        MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID,
                        MediaStore.Images.ImageColumns.ORIENTATION
                };
                return new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
                        null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        mLoadingProgressBar.hide();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (!isFinishing()) {
            mLoadingProgressBar.hide();
        }
        mAdapter.changeCursor(null);
    }

    private class GalleryAdapter
            extends CursorRecyclerViewAdapter<GalleryUseRecyclerViewActivity.ViewHolder> {

        private Context context;
        private final boolean isStagger;

        public GalleryAdapter(Context context, Cursor c, boolean isStagger) {
            super(context, c);
            this.context = context;
            this.isStagger = isStagger;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View contentView = View.inflate(context,
                    isStagger ? R.layout.stagger_gallery_item : R.layout.gallery_item, null);
            ViewHolder holder = new ViewHolder(contentView);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
            if (cursor != null) {
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                if (dataColumnIndex < cursor.getColumnCount()) {
                    final String originalUrl = cursor.getString(dataColumnIndex);
                    final String url = "file://" + originalUrl;

                    Glide.with(GalleryUseRecyclerViewActivity.this)
                            .load(url)
                            .centerCrop()
                            .into(viewHolder.picture);
                }
            }
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);

            picture = (ImageView) itemView.findViewById(R.id.pic);
        }
    }
}
