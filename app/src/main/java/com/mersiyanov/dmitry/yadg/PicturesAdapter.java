package com.mersiyanov.dmitry.yadg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> {


    private List<ResponseFileList.Item> itemList;
    private OnPictureClickListener onPictureClickListener;

    public PicturesAdapter(List<ResponseFileList.Item> itemList, OnPictureClickListener onPictureClickListener) {
        this.itemList = itemList;
        this.onPictureClickListener = onPictureClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(layoutInflater.inflate(R.layout.picture_item_view, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResponseFileList.Item image = itemList.get(position);

        Picasso.get().load(image.getFile()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPictureClickListener != null) {
                    onPictureClickListener.onPictureClick(image);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }

    public interface OnPictureClickListener {
        void onPictureClick(ResponseFileList.Item image);
    }

}
