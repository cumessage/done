package com.prosper.done.frontend.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prosper.done.frontend.bean.Location;
import com.prosper.done.frontend.data.DoneDbConfig.LocationConfig;
import com.prosper.done.frontend.data.DoneDbConfig.TaskConfig;

public class LocationDao {

	private DoneDbHelper dbHelper;

	public LocationDao(DoneDbHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public long insert(String name) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(LocationConfig.COLUMN_NAME_NAME, name);
		values.put(LocationConfig.COLUMN_NAME_CREATE_TIME, System.currentTimeMillis());
		values.put(LocationConfig.COLUMN_NAME_UPDATE_TIME, System.currentTimeMillis());

		long id = db.insert(LocationConfig.TABLE_NAME, LocationConfig.COLUMN_NAME_NAME, values);
		return id;
	}

	public Location getByName(String name) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String[] projection = {
				LocationConfig._ID,
				LocationConfig.COLUMN_NAME_NAME,
				LocationConfig.COLUMN_NAME_CREATE_TIME,
				LocationConfig.COLUMN_NAME_UPDATE_TIME
		};
		String where = LocationConfig.COLUMN_NAME_NAME + " = ?";
		String[] whereArg = new String[]{name}; 

		Cursor c = db.query(LocationConfig.TABLE_NAME, projection, where, whereArg, null, null, null, null);
		if (c.moveToFirst() != false) {
			Location location = new Location();
			location.setId(Integer.parseInt(c.getString(0)));
			location.setName(c.getString(1));
			location.setCreateTime(Long.parseLong(c.getString(2)));
			location.setUpdateTime(Long.parseLong(c.getString(3)));
			return location;
		} else {
			return null;
		}
	}


}
