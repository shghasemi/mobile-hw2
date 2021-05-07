package com.example.mobile_hw2.ui.bookmark;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bookmark.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "bookmark";
    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME +
            " (title VARCHAR(150) NOT NULL, " + " latitude FLOAT NOT NULL, " +
            " longitude FLOAT NOT NULL, " + " PRIMARY KEY (`title`));";

    public static final String INSERT_BOOKMARK_PATTERN = "REPLACE INTO " + TABLE_NAME + " " +
            "(title, latitude, longitude) VALUES (\"%s\", %f, %f);";

    public static final String DELETE_BOOKMARK_PATTERN = "DELETE FROM " + TABLE_NAME + " " +
            "WHERE title = \"%s\";";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public BookmarkDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
        // TODO: add migrations if needed.
    }

    public void truncate() {
        getWritableDatabase().execSQL(DROP_TABLE);
        getWritableDatabase().execSQL(CREATE_TABLE_QUERY);
    }

    public void delete(Bookmark bookmark) {
        String query = String.format(DELETE_BOOKMARK_PATTERN, bookmark.getTitle());
        getWritableDatabase().execSQL(query);
    }

    public void insert(Bookmark bookmark) {
        String query = String.format(INSERT_BOOKMARK_PATTERN, bookmark.getTitle(), bookmark.getLatitude(),
                bookmark.getLongitude());
        getWritableDatabase().execSQL(query);
    }

    public List<Bookmark> getAllMatched(String title) {
        Cursor response;
        if (title == null || title.isEmpty()) {
            response = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        } else {
            response = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME
                    + " WHERE title LIKE \"%" + title + "%\";", null);
        }
        return parseResults(response);

    }

    private List<Bookmark> parseResults(Cursor response) {
        List<Bookmark> bookmarks = new ArrayList<>();
        int titleIndex = response.getColumnIndex("title");
        int latitudeIndex = response.getColumnIndex("latitude");
        int longitudeIndex = response.getColumnIndex("longitude");
        while (response.moveToNext()) {
            bookmarks.add(new Bookmark(response.getString(titleIndex), response.getDouble(longitudeIndex),
                    response.getDouble(latitudeIndex)));
        }
        return bookmarks;
    }
}
