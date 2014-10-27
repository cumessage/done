package com.prosper.done.frontend.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.prosper.done.frontend.bean.Location;
import com.prosper.done.frontend.bean.Task;
import com.prosper.done.frontend.data.DoneDbConfig.LocationConfig;
import com.prosper.done.frontend.data.DoneDbConfig.TaskConfig;

public class TaskDao {

	private DoneDbHelper dbHelper;

	public TaskDao(DoneDbHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public long insert(String desc, String memo, int location, long startTime, 
			long endTime, int repeatCount) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TaskConfig.COLUMN_NAME_DESC, desc);
		values.put(TaskConfig.COLUMN_NAME_MEMO, memo);
		values.put(TaskConfig.COLUMN_NAME_LOCATION, location);
		values.put(TaskConfig.COLUMN_NAME_START_TIME, startTime);
		values.put(TaskConfig.COLUMN_NAME_END_TIME, endTime);
		values.put(TaskConfig.COLUMN_NAME_REPEAT_COUNT, repeatCount);
		values.put(TaskConfig.COLUMN_NAME_CREATE_TIME, System.currentTimeMillis());
		values.put(TaskConfig.COLUMN_NAME_UPDATE_TIME, System.currentTimeMillis());

		long id = db.insert(TaskConfig.TABLE_NAME, TaskConfig.COLUMN_NAME_DESC, values);
		return id;
	}

	public List<Task> getList() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = db.rawQuery(
				"SELECT " + 
						"task." + TaskConfig._ID + "," +
						"task." + TaskConfig.COLUMN_NAME_DESC + "," +
						"task." + TaskConfig.COLUMN_NAME_MEMO + "," +
						"task." + TaskConfig.COLUMN_NAME_LOCATION + "," +
						"task." + TaskConfig.COLUMN_NAME_START_TIME + "," +
						"task." + TaskConfig.COLUMN_NAME_END_TIME + "," +
						"task." + TaskConfig.COLUMN_NAME_REPEAT_COUNT + "," +
						"task." + TaskConfig.COLUMN_NAME_CREATE_TIME + "," +
						"task." + TaskConfig.COLUMN_NAME_UPDATE_TIME + "," +
						"location." + LocationConfig._ID + "," +
						"location." + LocationConfig.COLUMN_NAME_NAME
						+ " FROM " 
						+ TaskConfig.TABLE_NAME + " AS task, " + LocationConfig.TABLE_NAME + " AS location"
						+ " WHERE task." + TaskConfig.COLUMN_NAME_LOCATION + " = location." + LocationConfig._ID
						+ " ORDER BY task." + TaskConfig._ID + " desc",
						null);


		List<Task> taskList = new ArrayList<Task>();
		if (c.moveToFirst() != false) {
			while(!c.isLast()) {
				Log.d("donelog", "name: " + c.getColumnIndex(TaskConfig.COLUMN_NAME_DESC));
				Location location = new Location();
				Task task = new Task();

				location.setId(c.getLong(8));
				location.setName(c.getString(9));

				task.setId(c.getLong(0));
				task.setDesc(c.getString(1));
				task.setMemo(c.getString(2));
				task.setLocation(location);
				task.setStartTime(c.getLong(3));;
				task.setEndTime(c.getLong(4));;
				task.setRepeatCount(c.getInt(5));;
				task.setCreateTime(c.getLong(6));;
				task.setUpdateTime(c.getLong(7));;
				taskList.add(task);
				c.moveToNext();
			}
		}
		return taskList;
	}




}
