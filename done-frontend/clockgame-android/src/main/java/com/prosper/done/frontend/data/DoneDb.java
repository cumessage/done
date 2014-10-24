package com.prosper.done.frontend.data;

import com.prosper.done.frontend.data.DoneDbConfig.Location;
import com.prosper.done.frontend.data.DoneDbConfig.Task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoneDb extends SQLiteOpenHelper {
	
	public DoneDb(Context context) {
		super(context, DoneDbConfig.DATABASE_NAME, null, DoneDbConfig.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Task.SQL_CREATE);
		db.execSQL(Location.SQL_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(Task.SQL_DELETE);
		db.execSQL(Location.SQL_DELETE);
        onCreate(db);
	}

}
