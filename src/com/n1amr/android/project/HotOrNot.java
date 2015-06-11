package com.n1amr.android.project;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";

    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private static class DbHelper extends SQLiteOpenHelper {

	public DbHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
		    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
		    + " TEXT NOT NULL, " + KEY_HOTNESS + " TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	    onCreate(db);
	}

    }

    public HotOrNot(Context context) {
	mContext = context;

    }

    public HotOrNot open() throws SQLException {
	mDbHelper = new DbHelper(mContext);
	mSQLiteDatabase = mDbHelper.getWritableDatabase();
	return this;
    }

    public void close() {
	mDbHelper.close();
    }

    public long createEntry(String sName, String sAge) {

	ContentValues mContentValues = new ContentValues();
	mContentValues.put(KEY_NAME, sName);
	mContentValues.put(KEY_HOTNESS, sAge);

	return mSQLiteDatabase.insert(DATABASE_TABLE, null, mContentValues);
    }

    String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_HOTNESS };

    public String getData() {
	Cursor mCursor = mSQLiteDatabase.query(DATABASE_TABLE, columns, null,
		null, null, null, null);
	String result = "";
	int iRow = mCursor.getColumnIndex(KEY_ROWID);
	int iName = mCursor.getColumnIndex(KEY_NAME);
	int iAge = mCursor.getColumnIndex(KEY_HOTNESS);

	for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor
		.moveToNext())
	    result = result + mCursor.getString(iRow) + " "
		    + mCursor.getString(iName) + " " + mCursor.getString(iAge)
		    + "\n";
	return result;
    }

    public String getName(long l) {
	Cursor mCursor = mSQLiteDatabase.query(DATABASE_TABLE, columns,
		KEY_ROWID + "=" + l, null, null, null, null);
	if (mCursor != null) {
	    mCursor.moveToFirst();
	    return mCursor.getString(1);
	}
	return null;
    }

    public String getAge(long l) {
	Cursor mCursor = mSQLiteDatabase.query(DATABASE_TABLE, columns,
		KEY_ROWID + "=" + l, null, null, null, null);
	if (mCursor != null) {
	    mCursor.moveToFirst();
	    return mCursor.getString(2);
	}
	return null;
    }

    public void updateEntry(long lRow, String sName, String sAge) {
	ContentValues mContentValues = new ContentValues();
	mContentValues.put(KEY_NAME, sName);
	mContentValues.put(KEY_HOTNESS, sAge);

	mSQLiteDatabase.update(DATABASE_TABLE, mContentValues, KEY_ROWID + "="
		+ lRow, null);
    }

    public void deleteEntry(long lRow) {
	mSQLiteDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow, null);

    }

    public List<String> listAllNames() {
	List<String> result = new ArrayList<String>();

	Cursor mCursor = mSQLiteDatabase.query(DATABASE_TABLE, columns, null,
		null, null, null, null);

	for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor
		.moveToNext())
	    result.add(mCursor.getString(1));
	return result;
    }
}