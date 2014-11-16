package app.buusk15.quiz02_55410488;

import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.HashMap;

import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

public class control488DB extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "mydatabase488";
	private static final String TABLE_MEMBER = "member";
	private static final String TABLE_SCORE = "score";
	private static final int DATABASE_VERSION = 1;
	private static final String Create_default = "CREATE TABLE android_metadata (locale TEXT DEFAULT 'en_US');";

	public control488DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_MEMBER
				+ "(Userid INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Username TEXT(100)," + "Password TEXT(100),"
				+ "Position TEXT(10));");
		db.execSQL("CREATE TABLE "
				+ TABLE_SCORE
				+ "(Scoreid INTEGER PRIMARY KEY AUTOINCREMENT, Userid VARCHAR(10),Score CHAR(1));");
		Log.d("CREATE TABLE", "Create Table Successfully");
		db.execSQL("INSERT INTO member(Username,Password,Position) VALUES ('admin','0000','admin')");
	}

	// Insert DataAdmin
	public long InsertDataAdmin(String strUsername, String strPassword,
			String strPosition) {

		try {

			SQLiteDatabase db;

			db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put("Username", strUsername);
			values.put("Password", strPassword);
			values.put("Position", strPosition);

			long l = db.insert(TABLE_MEMBER, null, values);

			db.close();

			return l;

		} catch (Exception e) {

			return -1;

		}

	}

	// Insert DataMember
	public long InsertDataMember(String strUsername, String strPassword) {
		String strStatus = "member";
		try {

			SQLiteDatabase db;

			db = this.getWritableDatabase();

			ContentValues values = new ContentValues();

			values.put("Username", strUsername);

			values.put("Password", strPassword);

			values.put("Position", strStatus);

			long l = db.insert(TABLE_MEMBER, null, values);

			db.close();

			return l;

		} catch (Exception e) {

			return -1;

		}

	}

	// Insert Score
	public long InsertScore(String strScoreid, String strresult) {

		try {

			SQLiteDatabase db;
			db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put("Userid", strScoreid);
			values.put("Score", strresult);
			long l = db.insert(TABLE_SCORE, null, values);

			db.close();

			return l;

		} catch (Exception e) {

			return -1;

		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS" + TABLE_MEMBER);
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_SCORE);

		onCreate(db);

	}

	// check login
	public String checkLogin(String username) {
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_MEMBER, null, "Username" + "='"
				+ username.trim() + "'", null, null, null, null);

		if (cursor == null || cursor.getCount() == 0)
			return "";
		cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("Password"));
		return password;
	}

	// check position
	public String checkPosition(String username) {
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_MEMBER, null, "Username" + "='"
				+ username.trim() + "'", null, null, null, null);

		if (cursor == null || cursor.getCount() == 0)
			return "";
		cursor.moveToFirst();
		String userid = cursor.getString(cursor.getColumnIndex("Position"));
		return userid;
	}

	// check Userid
	public String checkId(String username) {
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_MEMBER, null, "Username" + "='"
				+ username.trim() + "'", null, null, null, null);

		if (cursor == null || cursor.getCount() == 0)
			return "";
		cursor.moveToFirst();
		String position = cursor.getString(cursor.getColumnIndex("Userid"));
		return position;
	}

	// Check Data

	public String[] CheckData(String Username, String Password) {

		try {

			String arrData[] = null;

			SQLiteDatabase db;

			db = this.getReadableDatabase();

			String strSQL = "SELECT Username,Password FROM " + TABLE_MEMBER
					+ " WHERE " + Username + " AND " + Password;

			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor != null) {

				if (cursor.moveToFirst()) {

					arrData = new String[cursor.getColumnCount()];

					arrData[0] = cursor.getString(0);

					arrData[1] = cursor.getString(1);

					arrData[2] = cursor.getString(2);

					arrData[3] = cursor.getString(3);

				}

			}

			cursor.close();

			db.close();

			return arrData;

		} catch (Exception e) {

			return null;

		}

	}

	// Select All

	public ArrayList<HashMap<String, String>> SelectAllData() {

		try {

			ArrayList<HashMap<String, String>> MyArrayList = new ArrayList<HashMap<String, String>>();

			HashMap<String, String> map;

			SQLiteDatabase db;

			db = this.getReadableDatabase();

			String strSQL = "SELECT * FROM " + TABLE_MEMBER;

			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor != null) {

				if (cursor.moveToFirst()) {

					do {

						map = new HashMap<String, String>();

						map.put("Userid", cursor.getString(0));

						map.put("Username", cursor.getString(1));

						map.put("Password", cursor.getString(2));

						map.put("Position", cursor.getString(3));

						MyArrayList.add(map);

					} while (cursor.moveToNext());

				}

			}

			cursor.close();

			db.close();

			return MyArrayList;

		} catch (Exception e) {

			return null;

		}

	}

}
