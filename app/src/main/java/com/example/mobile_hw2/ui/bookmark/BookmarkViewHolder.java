package com.example.mobile_hw2.ui.bookmark;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_hw2.R;

public class BookmarkViewHolder extends RecyclerView.ViewHolder {
    public final TextView title;
    public final TextView latitude;
    public final TextView longitude;
    public final ImageButton deleteButton;
    public final ImageView locationView;
    public final View view;
    public BookmarkViewHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        title = itemView.findViewById(R.id.bookmark_title);
        latitude = itemView.findViewById(R.id.latitude);
        longitude = itemView.findViewById(R.id.longitude);
        deleteButton = itemView.findViewById(R.id.deleteButton);
        locationView = itemView.findViewById(R.id.location_image);
    }
}
