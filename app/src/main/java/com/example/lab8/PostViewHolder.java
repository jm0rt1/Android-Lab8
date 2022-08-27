package com.example.lab8;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {


    TextView title;
    TextView content;
    View view;
    String id;

    public PostViewHolder(View view)
    {
        super(view);
        title = view.findViewById(R.id.post_title);
        content = view.findViewById(R.id.post_content);
        this.view = view;
    }


}
