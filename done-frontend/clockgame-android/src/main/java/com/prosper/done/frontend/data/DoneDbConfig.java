package com.prosper.done.frontend.data;

import android.provider.BaseColumns;

public class DoneDbConfig {
	
	public static final String DATABASE_NAME = "done";
	public static final int DATABASE_VERSION = 1;

	public DoneDbConfig() {}

	public static abstract class Task implements BaseColumns {

		public static final String TABLE_NAME = "Task";
		public static final String COLUMN_NAME_DESC = "desc";
		public static final String COLUMN_NAME_MEMO = "memo";
		public static final String COLUMN_NAME_LOCATION = "fk_location";
		public static final String COLUMN_NAME_START_TIME = "start_time";
		public static final String COLUMN_NAME_LAST_TIME = "last_time";
		public static final String COLUMN_NAME_REPEAT_COUNT = "repeat_count";
		public static final String COLUMN_NAME_CREATE_TIME = "create_time";
		public static final String COLUMN_NAME_UPDATE_TIME = "update_time";

		public static final String SQL_CREATE =
				"CREATE TABLE " + Task.TABLE_NAME + " (" +
						Task._ID + " INTEGER PRIMARY KEY," +
						Task.COLUMN_NAME_DESC + " TEXT," +
						Task.COLUMN_NAME_MEMO + " TEXT," +
						Task.COLUMN_NAME_LOCATION + " INTEGER," +
						Task.COLUMN_NAME_START_TIME + " INTEGER," +
						Task.COLUMN_NAME_LAST_TIME + " INTEGER," +
						Task.COLUMN_NAME_REPEAT_COUNT + " INTEGER," +
						Task.COLUMN_NAME_CREATE_TIME + " INTEGER," +
						Task.COLUMN_NAME_UPDATE_TIME + " INTEGER" +
						" )";

		public static final String SQL_DELETE =
				"DROP TABLE IF EXISTS " + Task.TABLE_NAME;

	}

	public static abstract class Location implements BaseColumns {
		public static final String TABLE_NAME = "location";
		public static final String COLUMN_NAME_LOCATION_ID = "pk_id";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_CREATE_TIME = "create_time";
		public static final String COLUMN_NAME_UPDATE_TIME = "update_time";
		
		public static final String SQL_CREATE =
				"CREATE TABLE " + Location.TABLE_NAME + " (" +
						Location._ID + " INTEGER PRIMARY KEY," +
						Location.COLUMN_NAME_NAME + " TEXT," +
						Location.COLUMN_NAME_CREATE_TIME + " INTEGER," +
						Location.COLUMN_NAME_UPDATE_TIME + " INTEGER" +
						" )";

		public static final String SQL_DELETE =
				"DROP TABLE IF EXISTS " + Task.TABLE_NAME;
	}


}
