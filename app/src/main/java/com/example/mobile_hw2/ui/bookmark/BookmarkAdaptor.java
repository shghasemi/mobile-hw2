package com.example.mobile_hw2.ui.bookmark;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_hw2.R;
import com.example.mobile_hw2.ui.map.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class BookmarkAdaptor extends RecyclerView.Adapter<BookmarkViewHolder> {

    private List<Bookmark> marks;
    private final BookMarkViewHandler bookMarkViewHandler;
    private final FragmentManager fragmentManager;
    private final Activity activity;

    public BookmarkAdaptor(List<Bookmark> marks, BookMarkViewHandler bookMarkViewHandler,
                           FragmentManager supportFragmentManager, Activity activity) {
        this.marks = marks;
        this.fragmentManager = supportFragmentManager;
        this.bookMarkViewHandler = bookMarkViewHandler;
        this.activity = activity;
        bookMarkViewHandler.setAdaptor(this);
    }

    public void updateMarks(List<Bookmark> marks) {
        this.marks = marks;
        notifyDataSetChanged();
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
        final Bookmark bookmark = marks.get(position);
        holder.longitude.setText(String.valueOf(bookmark.getLongitude()));
        holder.latitude.setText(String.valueOf(bookmark.getLatitude()));
        holder.title.setText(bookmark.getTitle());

        holder.itemView.setOnClickListener(v -> {
            SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putFloat("latitude", (float) bookmark.getLatitude());
            edit.putFloat("longitude", (float) bookmark.getLongitude());
            edit.apply();
            ((BottomNavigationView)activity.findViewById(R.id.nav_view))
                    .setSelectedItemId(R.id.navigation_map);
        });
        holder.deleteButton.setOnClickListener(v -> {
            Message message = new Message();
            message.what = BookMarkViewHandler.DELETE_DATA;
            message.obj = bookmark;
            bookMarkViewHandler.sendMessage(message);
            marks.remove(position);
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return marks.size();
    }
}
