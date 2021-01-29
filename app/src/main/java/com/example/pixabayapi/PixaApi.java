package com.example.pixabayapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PixaApi extends RecyclerView.Adapter<PixaApi.HighlightViewHolder> {
    Context context;
    List<Hit> contentList = new ArrayList<>();


    public PixaApi(Context context, List<Hit> contentList) {
        this.context = context;
        this.contentList = contentList;

    }

    @NonNull
    @Override
    public HighlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item,parent,false);
        final HighlightViewHolder holder =new HighlightViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightViewHolder holder, int position) {
        holder.tvname.setText(contentList.get(position).getUser());
        String Likes = String.valueOf(contentList.get(position).getLikes());
        holder.tvlikes.setText(Likes);
        Glide.with(context).load(contentList.get(position).getWebformatURL()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public  static final class HighlightViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvname,tvlikes;

        public HighlightViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            tvname=itemView.findViewById(R.id.name);
            tvlikes=itemView.findViewById(R.id.likes);
        }
    }
}