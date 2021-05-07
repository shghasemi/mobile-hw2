package com.example.mobile_hw2.ui.bookmark;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_hw2.R;

public class BookmarkFragment extends Fragment {

    private RecyclerView marksView;
    private SearchView searchView;
    private BookmarkDbHelper dbHelper;
    private BookMarkViewHandler handler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        dbHelper = new BookmarkDbHelper(getContext());

        dbHelper.insert(new Bookmark("Home", 35.11, 41.11f));
        dbHelper.insert(new Bookmark("Work", 36.11, 40.11f));

        handler = new BookMarkViewHandler(dbHelper);
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        marksView = view.findViewById(R.id.bookmarks);
        searchView = view.findViewById(R.id.search);
        BookmarkAdaptor bookmarkAdaptor = new BookmarkAdaptor(dbHelper.getAllMatched(null), handler);
        marksView.setAdapter(bookmarkAdaptor);
        marksView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        marksView.setHasFixedSize(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                updateData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateData(newText);
                return false;
            }

            private void updateData(String query) {
                Message message = new Message();
                message.what = BookMarkViewHandler.UPDATE_DATA;
                message.obj = query;
                handler.sendMessage(message);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return view;
    }
}