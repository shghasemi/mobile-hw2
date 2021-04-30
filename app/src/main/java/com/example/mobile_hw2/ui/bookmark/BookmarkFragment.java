package com.example.mobile_hw2.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_hw2.R;

public class BookmarkFragment extends Fragment {

    private RecyclerView marksView;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        marksView = view.findViewById(R.id.bookmarks);
        searchView = view.findViewById(R.id.search);
        marksView.setAdapter(new BookmarkAdaptor());
        marksView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        marksView.setHasFixedSize(false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        return view;
    }
}