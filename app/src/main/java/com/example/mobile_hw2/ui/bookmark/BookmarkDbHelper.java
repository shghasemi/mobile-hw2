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
            " (title VARCHAR(150) NOT NULL, " + " longitude FLOAT NOT NULL, " +
            " latitude FLOAT NOT NULL, " + " PRIMARY KEY (`title`));";

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
        // TODO: remove these initial locations
        this.insert(new Bookmark("Home", 35.11, 41.11f));
        this.insert(new Bookmark("Work", 36.11, 40.11f));
        this.insert(new Bookmark("Ali", 37.11, 39.11f));
        this.insert(new Bookmark("Reza", 38.11, 38.11f));
        this.insert(new Bookmark("Hasan", 39.11, 37.11f));
        this.insert(new Bookmark("Sina", 40.11, 36.11f));
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
            bookmarks.add(new Bookmark(response.getString(titleIndex), response.getDouble(latitudeIndex),
                    response.getDouble(longitudeIndex)));
        }
        return bookmarks;
    }
}
