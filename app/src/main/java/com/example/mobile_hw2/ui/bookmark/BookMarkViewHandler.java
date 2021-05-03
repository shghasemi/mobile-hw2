package com.example.mobile_hw2.ui.bookmark;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/**
 * View Handler for manipulating db.
 */
public class BookMarkViewHandler extends Handler {

    public static final int UPDATE_DATA = 0;
    public static final int DELETE_DATA = 1;
    public static final int DELETE_ALL_DATA = 2;

    private final WeakReference<BookmarkDbHelper> helper;
    private WeakReference<BookmarkAdaptor> adaptor;

    public BookMarkViewHandler(BookmarkDbHelper db) {
        super(Looper.myLooper());
        this.helper = new WeakReference<>(db);
    }

    public void setAdaptor(BookmarkAdaptor adaptor) {
        this.adaptor = new WeakReference<>(adaptor);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        BookmarkDbHelper helper = this.helper.get();
        BookmarkAdaptor adaptor = this.adaptor.get();
        if (helper == null || adaptor == null) {
            return;
        }
        switch (msg.what) {
            case UPDATE_DATA:
                adaptor.updateMarks(helper.getAllMatched((String) msg.obj));
                adaptor.notifyDataSetChanged();
                break;
            case DELETE_DATA:
                helper.delete((Bookmark) msg.obj);
                adaptor.notifyDataSetChanged();
                break;
            case DELETE_ALL_DATA:
                helper.truncate();
                break;
            default:
                Log.e("Handler", "Unknown Message");
        }
    }
}
