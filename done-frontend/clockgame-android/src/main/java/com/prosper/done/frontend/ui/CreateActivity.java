package com.prosper.done.frontend.ui;

import com.prosper.done.frontend.R;
import com.prosper.done.frontend.data.DoneDb;
import com.prosper.done.frontend.data.DoneDbConfig.Task;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_create);
    }
	
	public void creatTask(View view) {
		DoneDb mDbHelper = new DoneDb(getBaseContext());
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		EditText desc = (EditText) findViewById(R.id.create_input_desc);
		EditText memo = (EditText) findViewById(R.id.create_input_memo);
		EditText location = (EditText) findViewById(R.id.create_input_location);
		EditText startTime = (EditText) findViewById(R.id.create_input_start_time);
		EditText lastTime = (EditText) findViewById(R.id.create_input_last_time);
		EditText repeatCount = (EditText) findViewById(R.id.create_input_repeat_count);
		
		ContentValues values = new ContentValues();
		values.put(Task.COLUMN_NAME_DESC, desc.getText().toString());
		values.put(Task.COLUMN_NAME_MEMO, memo.getText().toString());
		values.put(Task.COLUMN_NAME_LOCATION, location.getText().toString());
		values.put(Task.COLUMN_NAME_START_TIME, startTime.getText().toString());
		values.put(Task.COLUMN_NAME_LAST_TIME, lastTime.getText().toString());
		values.put(Task.COLUMN_NAME_REPEAT_COUNT, repeatCount.getText().toString());
		values.put(Task.COLUMN_NAME_CREATE_TIME, System.currentTimeMillis());
		values.put(Task.COLUMN_NAME_UPDATE_TIME, System.currentTimeMillis());

		long id = db.insert(Task.TABLE_NAME, Task.COLUMN_NAME_DESC, values);
		
		if (id == -1) {
			System.out.println("insert task failed");
		} else {
			System.out.println("insert task success, id:" + id);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
}
