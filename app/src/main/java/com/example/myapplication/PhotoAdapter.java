package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<PhotoItem> photoItemList;

    public PhotoAdapter(List<PhotoItem> photoItemList) {
        this.photoItemList = photoItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PhotoItem photoItem = photoItemList.get(position);
        holder.photoView.setImageResource(photoItem.getPhotoResourceId());
    }

    @Override
    public int getItemCount() {
        return photoItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoView;

        public ViewHolder(View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.photo_view);
        }
    }
}


