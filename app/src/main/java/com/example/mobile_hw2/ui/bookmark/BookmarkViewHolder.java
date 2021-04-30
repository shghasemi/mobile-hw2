package com.example.mobile_hw2.ui.bookmark;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_hw2.R;

public class BookmarkViewHolder extends RecyclerView.ViewHolder {
    public final TextView title;
    public final TextView latitude;
    public final TextView longitude;

    public BookmarkViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.bookmark_title);
        latitude = itemView.findViewById(R.id.latitude);
        longitude = itemView.findViewById(R.id.longitude);
    }
}
