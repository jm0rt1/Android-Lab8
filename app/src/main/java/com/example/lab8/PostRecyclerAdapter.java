package com.example.lab8;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostViewHolder>{
    public static String TAG = "PostRecyclerAdapter";
    ArrayList<String[]> data;
    Context c;

    public PostRecyclerAdapter(Context c, ArrayList<String[]> list){
        data = list;
        this.c = c;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.post_card,parent,false);
        PostViewHolder postViewHolder = new PostViewHolder(v);
        return postViewHolder;

    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(data.get(position)[0]);
        holder.content.setText(data.get(position)[1]);
        holder.id = data.get(position)[2];
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                askToDelete(v, holder);

            }
        });
    }

    private void askToDelete(View v, PostViewHolder holder) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Intent intent = new Intent(v.getContext(),MainActivity.class);

                        ServerInterface.Posts.deletePost(holder.id);
                        v.getContext().startActivity(intent);

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
