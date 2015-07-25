package edu.purdue.vieck.budgetapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vieck on 7/22/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "budget_database";
    private static final String TABLE_DATA = "data";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_NOTE = "note";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COLOR_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_DATA + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_AMOUNT + " REAL,"
                + COLUMN_TYPE + " INTEGER,"
                + COLUMN_DAY + " INTEGER,"
                + COLUMN_MONTH + " INTEGER,"
                + COLUMN_YEAR + " INTEGER"
                + COLUMN_NOTE + " TEXT" + ");";
        db.execSQL(CREATE_COLOR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }

    public void addData(BudgetElement budgetElement) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COLUMN_ID, color.getID());
        contentValues.put(COLUMN_CATEGORY, budgetElement.getCategory());
        contentValues.put(COLUMN_AMOUNT, budgetElement.getAmount());
        contentValues.put(COLUMN_TYPE, budgetElement.isType());
        contentValues.put(COLUMN_DAY, budgetElement.getDay());
        contentValues.put(COLUMN_MONTH, budgetElement.getMonth());
        contentValues.put(COLUMN_YEAR, budgetElement.getYear());
        contentValues.put(COLUMN_NOTE, budgetElement.getNote());

        database.insert(TABLE_DATA, null, contentValues);
        database.close();
    }

    public ArrayList<BudgetElement> getAllColors() {
        ArrayList<BudgetElement> mDataset = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BudgetElement budgetElement = new BudgetElement();
                //budgetElement.setID(cursor.getLong(0));
                budgetElement.setCategory(cursor.getString(1));
                budgetElement.setAmount(cursor.getFloat(2));
                if (cursor.getInt(3) == 0) {
                    budgetElement.setType(false);
                } else {
                    budgetElement.setType(true);
                }
                budgetElement.setDay(cursor.getInt(4));
                budgetElement.setMonth(cursor.getInt(5));
                budgetElement.setYear(cursor.getInt(6));

                mDataset.add(budgetElement);
            } while (cursor.moveToNext());
        }
        return mDataset;
    }

    public void delete(BudgetElement budgetElement) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_DATA, COLUMN_ID + " = " + budgetElement.getCategory(), null);
    }

    public void deleteAll() {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_DATA, null, null);
    }
}
