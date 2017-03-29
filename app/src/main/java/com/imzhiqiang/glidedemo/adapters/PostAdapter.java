package com.imzhiqiang.glidedemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.imzhiqiang.glidedemo.MainFragment;
import com.imzhiqiang.glidedemo.R;
import java.util.List;

public class PostAdapter extends BaseAdapter {

    private Context context;
    private List<MainFragment.Post> postList;

    public PostAdapter(Context context, List<MainFragment.Post> posts) {
        this.context = context;
        this.postList = posts;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(postList.get(position).getTitle());
        holder.subtitle.setText(postList.get(position).getSubtitle());

        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.subtitle)
        TextView subtitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
