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
		public static final String COLUMN_NAME_REPEAT_COUNT = "repeat_count";
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
						TaskConfig.COLUMN_NAME_REPEAT_COUNT + " INTEGER," +
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


}
