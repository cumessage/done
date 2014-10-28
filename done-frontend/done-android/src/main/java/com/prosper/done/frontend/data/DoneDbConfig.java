package com.prosper.done.frontend.data;

import android.provider.BaseColumns;

public class DoneDbConfig {
	
	public static final String DATABASE_NAME = "done";
	public static final int DATABASE_VERSION = 1;

	public DoneDbConfig() {}

	public static abstract class TaskConfig implements BaseColumns {

		public static final String TABLE_NAME = "task";
		public static final String COLUMN_NAME_DESC = "desc";
		public static final String COLUMN_NAME_MEMO = "memo";
		public static final String COLUMN_NAME_LOCATION = "fk_location";
		public static final String COLUMN_NAME_START_TIME = "start_time";
		public static final String COLUMN_NAME_END_TIME = "end_time";
		public static final String COLUMN_NAME_IS_REPEAT = "is_repeat";
		public static final String COLUMN_NAME_MAX_COUNT = "max_count";
		public static final String COLUMN_NAME_FINISH_COUNT = "finish_count";
		public static final String COLUMN_NAME_CREATE_TIME = "create_time";
		public static final String COLUMN_NAME_UPDATE_TIME = "update_time";

		public static final String SQL_CREATE =
				"CREATE TABLE " + TaskConfig.TABLE_NAME + " (" +
						TaskConfig._ID + " INTEGER PRIMARY KEY," +
						TaskConfig.COLUMN_NAME_DESC + " TEXT," +
						TaskConfig.COLUMN_NAME_MEMO + " TEXT," +
						TaskConfig.COLUMN_NAME_LOCATION + " INTEGER," +
						TaskConfig.COLUMN_NAME_START_TIME + " INTEGER," +
						TaskConfig.COLUMN_NAME_END_TIME + " INTEGER," +
						TaskConfig.COLUMN_NAME_IS_REPEAT + " INTEGER," +
						TaskConfig.COLUMN_NAME_MAX_COUNT + " INTEGER," +
						TaskConfig.COLUMN_NAME_FINISH_COUNT + " INTEGER," +
						TaskConfig.COLUMN_NAME_CREATE_TIME + " INTEGER," +
						TaskConfig.COLUMN_NAME_UPDATE_TIME + " INTEGER" +
						" )";

		public static final String SQL_DELETE =
				"DROP TABLE IF EXISTS " + TaskConfig.TABLE_NAME;

	}

	public static abstract class LocationConfig implements BaseColumns {
		public static final String TABLE_NAME = "location";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_CREATE_TIME = "create_time";
		public static final String COLUMN_NAME_UPDATE_TIME = "update_time";
		
		public static final String SQL_CREATE =
				"CREATE TABLE " + LocationConfig.TABLE_NAME + " (" +
						LocationConfig._ID + " INTEGER PRIMARY KEY," +
						LocationConfig.COLUMN_NAME_NAME + " TEXT," +
						LocationConfig.COLUMN_NAME_CREATE_TIME + " INTEGER," +
						LocationConfig.COLUMN_NAME_UPDATE_TIME + " INTEGER" +
						" )";

		public static final String SQL_DELETE =
				"DROP TABLE IF EXISTS " + TaskConfig.TABLE_NAME;
	}
	
	public static abstract class HistoryConfig implements BaseColumns {

		public static final String TABLE_NAME = "history";
		public static final String COLUMN_NAME_DESC = "desc";
		public static final String COLUMN_NAME_MEMO = "memo";
		public static final String COLUMN_NAME_STATUS = "status";
		public static final String COLUMN_NAME_LOCATION = "location";
		public static final String COLUMN_NAME_FINISH_TIME = "finish_time";
		public static final String COLUMN_NAME_START_TIME = "start_time";
		public static final String COLUMN_NAME_END_TIME = "end_time";
		public static final String COLUMN_NAME_IS_REPEAT = "is_repeat";
		public static final String COLUMN_NAME_MAX_COUNT = "max_count";
		public static final String COLUMN_NAME_FINISH_COUNT = "finish_count";
		public static final String COLUMN_NAME_CREATE_TIME = "create_time";

		public static final String SQL_CREATE =
				"CREATE TABLE " + HistoryConfig.TABLE_NAME + " (" +
						HistoryConfig._ID + " INTEGER PRIMARY KEY," +
						HistoryConfig.COLUMN_NAME_DESC + " TEXT," +
						HistoryConfig.COLUMN_NAME_MEMO + " TEXT," +
						HistoryConfig.COLUMN_NAME_STATUS + " INTEGER," +
						HistoryConfig.COLUMN_NAME_LOCATION + " TEXT," +
						HistoryConfig.COLUMN_NAME_FINISH_TIME + " INTEGER," +
						HistoryConfig.COLUMN_NAME_START_TIME + " INTEGER," +
						HistoryConfig.COLUMN_NAME_END_TIME + " INTEGER," +
						HistoryConfig.COLUMN_NAME_IS_REPEAT + " INTEGER," +
						HistoryConfig.COLUMN_NAME_MAX_COUNT + " INTEGER," +
						HistoryConfig.COLUMN_NAME_FINISH_COUNT + " INTEGER," +
						HistoryConfig.COLUMN_NAME_CREATE_TIME + " INTEGER" +
						" )";

		public static final String SQL_DELETE =
				"DROP TABLE IF EXISTS " + HistoryConfig.TABLE_NAME;

	}


}
