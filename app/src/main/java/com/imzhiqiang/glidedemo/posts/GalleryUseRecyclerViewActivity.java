package com.imzhiqiang.glidedemo.posts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.imzhiqiang.glidedemo.R;

public class GalleryUseRecyclerViewActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int THUMBNAIL_LOAD = 1101;

    private RecyclerView mGallery;
    private CursorRecyclerViewAdapter mAdapter;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    private MaterialDialog mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_use_recyclerview);

        mGallery = (RecyclerView) findViewById(R.id.gallery);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mStaggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        mGallery.setLayoutManager(mGridLayoutManager);

        mAdapter = new GalleryAdapter(this, null);
        mGallery.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(THUMBNAIL_LOAD, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_linear:
                mGallery.setLayoutManager(mLinearLayoutManager);
                break;
            case R.id.action_grid:
                mGallery.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.action_stagger:
                mGallery.setLayoutManager(mStaggeredGridLayoutManager);
                break;
        }
        return true;
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
        mLoadingDialog = new MaterialDialog.Builder(this).content("Loading photo album...")
                .progress(true, 0)
                .build();
        mLoadingDialog.show();
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
            mLoadingDialog = new MaterialDialog.Builder(this).content("Loading photo album...")
                    .progress(true, 0)
                    .build();
            mLoadingDialog.show();
        }
        mAdapter.changeCursor(null);
    }

    private class GalleryAdapter
            extends CursorRecyclerViewAdapter<GalleryUseRecyclerViewActivity.ViewHolder> {

        private Context context;

        public GalleryAdapter(Context context, Cursor c) {
            super(context, c);
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View contentView = View.inflate(context, R.layout.gallery_item, null);

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
