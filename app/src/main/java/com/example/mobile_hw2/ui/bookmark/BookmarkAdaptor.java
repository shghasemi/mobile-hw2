package com.example.mobile_hw2.ui.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_hw2.R;
import java.util.ArrayList;
import java.util.List;

public class BookmarkAdaptor extends RecyclerView.Adapter<BookmarkViewHolder> {

    private final List<Bookmark> marks;

    public BookmarkAdaptor() {
        marks = new ArrayList<Bookmark>() {{
            add(new Bookmark("Home", 35.11, 41.11f));
            add(new Bookmark("Work", 35.11, 41.11f));
            add(new Bookmark("School", 35.11, 41.11f));
            }};
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_item_layout, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark bookmark = marks.get(position);
        holder.longitude.setText(String.valueOf(bookmark.getLongitude()));
        holder.latitude.setText(String.valueOf(bookmark.getLatitude()));
        holder.title.setText(bookmark.getTitle());
    }

    @Override
    public int getItemCount() {
        return marks.size();
    }
}
