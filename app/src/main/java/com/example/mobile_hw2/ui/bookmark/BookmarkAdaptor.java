package com.example.mobile_hw2.ui.bookmark;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile_hw2.R;
import java.util.List;

public class BookmarkAdaptor extends RecyclerView.Adapter<BookmarkViewHolder> {

    private List<Bookmark> marks;
    private final BookMarkViewHandler bookMarkViewHandler;

    public BookmarkAdaptor(List<Bookmark> marks, BookMarkViewHandler bookMarkViewHandler) {
        this.marks = marks;
        this.bookMarkViewHandler = bookMarkViewHandler;
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
