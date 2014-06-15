package org.xfl.chat.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{
	private static String TAG = DBHelper.class.getName();
	private static String DB_name = "chatapp.db";
	private static int DATABASE_VERSION = 1;
	public DBHelper(Context context) {
		super(context, DB_name, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "onCreate");
		StringBuffer sqlbuf = new StringBuffer();
		sqlbuf.append("create table IF NOT EXISTS chatmsg (msgid integer primary key autoincrement, ")
			  .append("content_time date, name varchar(30), content varchar(140), isleft integer)");
		db.execSQL(sqlbuf.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "onUpgrade:[oldVersion:"+oldVersion+",newVersion:"+newVersion+"]");
		db.execSQL("alter table chatmsg");
	}
}
