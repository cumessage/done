package com.prosper.done.frontend.data;

import com.prosper.done.frontend.data.DoneDbConfig.LocationConfig;
import com.prosper.done.frontend.data.DoneDbConfig.TaskConfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoneDbHelper extends SQLiteOpenHelper {
	
	public DoneDbHelper(Context context) {
		super(context, DoneDbConfig.DATABASE_NAME, null, DoneDbConfig.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TaskConfig.SQL_CREATE);
		db.execSQL(LocationConfig.SQL_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(TaskConfig.SQL_DELETE);
		db.execSQL(LocationConfig.SQL_DELETE);
        onCreate(db);
	}

}
