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

	public long insert(Task task) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TaskConfig.COLUMN_NAME_DESC, task.getDesc());
		values.put(TaskConfig.COLUMN_NAME_MEMO, task.getMemo());
		values.put(TaskConfig.COLUMN_NAME_LOCATION, task.getLocation().getId());
		values.put(TaskConfig.COLUMN_NAME_START_TIME, task.getStartTime());
		values.put(TaskConfig.COLUMN_NAME_END_TIME, task.getEndTime());
		values.put(TaskConfig.COLUMN_NAME_IS_REPEAT, task.getIsRepeat());
		values.put(TaskConfig.COLUMN_NAME_MAX_COUNT, task.getMaxCount());
		values.put(TaskConfig.COLUMN_NAME_FINISH_COUNT, task.getFinishCount());
		values.put(TaskConfig.COLUMN_NAME_CREATE_TIME, System.currentTimeMillis());
		values.put(TaskConfig.COLUMN_NAME_UPDATE_TIME, System.currentTimeMillis());

		long id = db.insert(TaskConfig.TABLE_NAME, TaskConfig.COLUMN_NAME_DESC, values);
		return id;
	}
	
	public void delete(long id) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		String where = TaskConfig._ID + " = ?";
		String[] whereArgs = new String[]{Long.toString(id)};
		
		db.delete(TaskConfig.TABLE_NAME, where, whereArgs);
	}
	
	public long update(Task task) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TaskConfig.COLUMN_NAME_DESC, task.getDesc());
		values.put(TaskConfig.COLUMN_NAME_MEMO, task.getMemo());
		values.put(TaskConfig.COLUMN_NAME_LOCATION, task.getLocation().getId());
		values.put(TaskConfig.COLUMN_NAME_START_TIME, task.getStartTime());
		values.put(TaskConfig.COLUMN_NAME_END_TIME, task.getEndTime());
		values.put(TaskConfig.COLUMN_NAME_IS_REPEAT, task.getIsRepeat());
		values.put(TaskConfig.COLUMN_NAME_MAX_COUNT, task.getMaxCount());
		values.put(TaskConfig.COLUMN_NAME_FINISH_COUNT, task.getFinishCount());
		values.put(TaskConfig.COLUMN_NAME_UPDATE_TIME, System.currentTimeMillis());
		
		String where = TaskConfig._ID + " = ?";
		String[] whereArgs = new String[]{Long.toString(task.getId())};

		long id = db.update(TaskConfig.TABLE_NAME, values, where, whereArgs);
		return id;
	}

	

	public List<Task> getList() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = db.rawQuery(
				"SELECT " + 
						"task." + TaskConfig._ID + "," +
						"task." + TaskConfig.COLUMN_NAME_DESC + "," +
						"task." + TaskConfig.COLUMN_NAME_MEMO + "," +
						"task." + TaskConfig.COLUMN_NAME_START_TIME + "," +
						"task." + TaskConfig.COLUMN_NAME_END_TIME + "," +
						"task." + TaskConfig.COLUMN_NAME_IS_REPEAT + "," +
						"task." + TaskConfig.COLUMN_NAME_MAX_COUNT + "," +
						"task." + TaskConfig.COLUMN_NAME_FINISH_COUNT + "," +
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
		Log.i("done", "count:" + taskList.size());
		if (c.moveToFirst() != false) {
			while(!c.isAfterLast()) {
				Log.d("donelog", "name: " + c.getColumnIndex(TaskConfig.COLUMN_NAME_DESC));
				Location location = new Location();
				Task task = new Task();

				location.setId(c.getLong(10));
				location.setName(c.getString(11));

				task.setId(c.getLong(0));
				task.setDesc(c.getString(1));
				task.setMemo(c.getString(2));
				task.setLocation(location);
				task.setStartTime(c.getLong(3));
				task.setEndTime(c.getLong(4));
				task.setIsRepeat(c.getInt(5));
				task.setMaxCount(c.getInt(6));
				task.setFinishCount(c.getInt(7));
				task.setCreateTime(c.getLong(8));
				task.setUpdateTime(c.getLong(9));
				taskList.add(task);
				c.moveToNext();
			}
		}
		return taskList;
	}
	
	public List<Task> getListByEndtime(long endTime, boolean before) {
		String symbol = "<";
		if (!before) symbol = ">";
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String sql = "SELECT " + 
				"task." + TaskConfig._ID + "," +
				"task." + TaskConfig.COLUMN_NAME_DESC + "," +
				"task." + TaskConfig.COLUMN_NAME_MEMO + "," +
				"task." + TaskConfig.COLUMN_NAME_START_TIME + "," +
				"task." + TaskConfig.COLUMN_NAME_END_TIME + "," +
				"task." + TaskConfig.COLUMN_NAME_IS_REPEAT + "," +
				"task." + TaskConfig.COLUMN_NAME_MAX_COUNT + "," +
				"task." + TaskConfig.COLUMN_NAME_FINISH_COUNT + "," +
				"task." + TaskConfig.COLUMN_NAME_CREATE_TIME + "," +
				"task." + TaskConfig.COLUMN_NAME_UPDATE_TIME + "," +
				"location." + LocationConfig._ID + "," +
				"location." + LocationConfig.COLUMN_NAME_NAME
				+ " FROM " 
				+ TaskConfig.TABLE_NAME + " AS task, " + LocationConfig.TABLE_NAME + " AS location"
				+ " WHERE task." + TaskConfig.COLUMN_NAME_LOCATION + " = location." + LocationConfig._ID
				+ " AND task." + TaskConfig.COLUMN_NAME_END_TIME + symbol + endTime
				+ " ORDER BY task." + TaskConfig._ID + " desc";
		
		Cursor c = db.rawQuery(sql, null);

		List<Task> taskList = new ArrayList<Task>();
		if (c.moveToFirst() != false) {
			while(!c.isAfterLast()) {
				Log.d("donelog", "name: " + c.getColumnIndex(TaskConfig.COLUMN_NAME_DESC));
				Location location = new Location();
				Task task = new Task();

				location.setId(c.getLong(10));
				location.setName(c.getString(11));

				task.setId(c.getLong(0));
				task.setDesc(c.getString(1));
				task.setMemo(c.getString(2));
				task.setLocation(location);
				task.setStartTime(c.getLong(3));
				task.setEndTime(c.getLong(4));
				task.setIsRepeat(c.getInt(5));
				task.setMaxCount(c.getInt(6));
				task.setFinishCount(c.getInt(7));
				task.setCreateTime(c.getLong(8));
				task.setUpdateTime(c.getLong(9));
				taskList.add(task);
				c.moveToNext();
			}
		}
		return taskList;
	}


}
