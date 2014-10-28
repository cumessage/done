package com.prosper.done.frontend.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prosper.done.frontend.bean.History;
import com.prosper.done.frontend.data.DoneDbConfig.HistoryConfig;

public class HistoryDao {

	private DoneDbHelper dbHelper;

	public HistoryDao(DoneDbHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public long insert(
			String desc, 
			String memo, 
			int status, 
			String location, 
			long finishTime, 
			long startTime, 
			long endTime, 
			int isRepeat, 
			int maxCount,
			int finishCount) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(HistoryConfig.COLUMN_NAME_DESC, desc);
		values.put(HistoryConfig.COLUMN_NAME_MEMO, memo);
		values.put(HistoryConfig.COLUMN_NAME_STATUS, status);
		values.put(HistoryConfig.COLUMN_NAME_LOCATION, location);
		values.put(HistoryConfig.COLUMN_NAME_FINISH_TIME, finishTime);
		values.put(HistoryConfig.COLUMN_NAME_START_TIME, startTime);
		values.put(HistoryConfig.COLUMN_NAME_END_TIME, endTime);
		values.put(HistoryConfig.COLUMN_NAME_IS_REPEAT, isRepeat);
		values.put(HistoryConfig.COLUMN_NAME_MAX_COUNT, maxCount);
		values.put(HistoryConfig.COLUMN_NAME_FINISH_COUNT, finishCount);
		values.put(HistoryConfig.COLUMN_NAME_CREATE_TIME, System.currentTimeMillis());

		long id = db.insert(HistoryConfig.TABLE_NAME, HistoryConfig.COLUMN_NAME_DESC, values);
		return id;
	}

	public List<History> getList() {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = db.rawQuery(
				"SELECT " + 
						"history." + HistoryConfig._ID + "," +
						"history." + HistoryConfig.COLUMN_NAME_DESC + "," +
						"history." + HistoryConfig.COLUMN_NAME_MEMO + "," +
						"history." + HistoryConfig.COLUMN_NAME_STATUS + "," +
						"history." + HistoryConfig.COLUMN_NAME_LOCATION + "," +
						"history." + HistoryConfig.COLUMN_NAME_FINISH_TIME + "," +
						"history." + HistoryConfig.COLUMN_NAME_START_TIME + "," +
						"history." + HistoryConfig.COLUMN_NAME_END_TIME + "," +
						"history." + HistoryConfig.COLUMN_NAME_IS_REPEAT + "," +
						"history." + HistoryConfig.COLUMN_NAME_MAX_COUNT + "," +
						"history." + HistoryConfig.COLUMN_NAME_FINISH_COUNT + "," +
						"history." + HistoryConfig.COLUMN_NAME_CREATE_TIME + "," +
						" FROM " + HistoryConfig.TABLE_NAME + 
						" ORDER BY task." + HistoryConfig._ID + " desc",
						null);


		List<History> historyList = new ArrayList<History>();
		if (c.moveToFirst() != false) {
			while(!c.isAfterLast()) {
				History history = new History();
				history.setId(c.getLong(0));
				history.setDesc(c.getString(1));
				history.setMemo(c.getString(2));
				history.setStatus(c.getInt(3));
				history.setLocation(c.getString(4));
				history.setFinishTime(c.getLong(5));
				history.setStartTime(c.getLong(6));
				history.setEndTime(c.getLong(7));
				history.setIsRepeat(c.getInt(8));
				history.setMaxCount(c.getInt(9));
				history.setFinishCount(c.getInt(10));
				history.setCreateTime(c.getLong(11));
				historyList.add(history);
				c.moveToNext();
			}
		}
		return historyList;
	}




}
