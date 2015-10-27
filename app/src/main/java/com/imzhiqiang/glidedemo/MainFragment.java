package com.imzhiqiang.glidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.imzhiqiang.glidedemo.adapters.PostAdapter;
import com.imzhiqiang.glidedemo.posts.PostOneActivity;
import com.imzhiqiang.glidedemo.posts.PostTwoActivity;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends ListFragment {

    private List<Post> posts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        posts = new ArrayList<>();
        posts.add(new Post("Post 1", "Hello,Glide"));
        posts.add(new Post("Post 2", "Load images from different data sources"));

        setListAdapter(new PostAdapter(getActivity(), posts));
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position) {
            case 0:
                startActivity(PostOneActivity.class);
                break;
            case 1:
                startActivity(PostTwoActivity.class);
            default:
                break;
        }

    }

    private void startActivity(Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    public static class Post {
        private String title;
        private String subtitle;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public Post(String title, String subtitle) {
            this.title = title;
            this.subtitle = subtitle;
        }

        @Override
        public String toString() {
            return title + subtitle;
        }
    }

}
