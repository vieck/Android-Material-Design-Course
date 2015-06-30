package edu.purdue.vieck.htmlcolorwheel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vieck on 6/28/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "saved_colors";
    private static final String TABLE_COLORS = "colors";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME_LABEL = "label";
    private static final String COLUMN_NAME_HEX = "hex";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COLOR_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COLORS + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_LABEL + " TEXT," + COLUMN_NAME_HEX + " TEXT );";
        db.execSQL(CREATE_COLOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORS);
        onCreate(db);
    }

    public void addColor(CustomColor color) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, color.getID());
        contentValues.put(COLUMN_NAME_LABEL, color.getName());
        contentValues.put(COLUMN_NAME_HEX, color.getHexValue());

        database.insert(TABLE_COLORS, null, contentValues);
        database.close();
    }

    public ArrayList<CustomColor> getAllColors() {
        ArrayList<CustomColor> mDataset = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_COLORS;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CustomColor color = new CustomColor();
                color.setID(cursor.getLong(0));
                color.setName(cursor.getString(1));
                color.setHexValue(cursor.getString(2));
                mDataset.add(color);
            } while (cursor.moveToNext());
        }
        return mDataset;
    }

    public void delete(CustomColor color) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_COLORS, COLUMN_ID + " = " + color.getID(), null);
    }

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_COLORS, null, null);
    }

}
