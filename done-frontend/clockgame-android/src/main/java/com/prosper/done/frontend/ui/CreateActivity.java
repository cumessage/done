package com.prosper.done.frontend.ui;

import com.prosper.done.frontend.R;
import com.prosper.done.frontend.bean.Location;
import com.prosper.done.frontend.data.DoneDbHelper;
import com.prosper.done.frontend.data.LocationDao;
import com.prosper.done.frontend.data.DoneDbConfig.TaskConfig;
import com.prosper.done.frontend.data.TaskDao;

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
		DoneDbHelper dbHelper = new DoneDbHelper(getBaseContext());
		LocationDao locationDao = new LocationDao(dbHelper);
		TaskDao taskDao = new TaskDao(dbHelper);

		String locationVal = ((EditText) findViewById(R.id.create_input_location)).getText().toString();
		Location location = locationDao.getByName(locationVal);
		long locationId;

		if (location != null) {
			locationId = location.getId();
		} else {
			locationId = locationDao.insert(locationVal);
			if (locationId == -1) {
				throw new RuntimeException("location is not inserted!");
			}
		}

		long taskId = taskDao.insert(
				((EditText) findViewById(R.id.create_input_desc)).getText().toString(),
				((EditText) findViewById(R.id.create_input_memo)).getText().toString(),
				Integer.parseInt(((EditText) findViewById(R.id.create_input_location)).getText().toString()),
				Long.parseLong(((EditText) findViewById(R.id.create_input_start_time)).getText().toString()),
				Long.parseLong(((EditText) findViewById(R.id.create_input_end_time)).getText().toString()),
				Integer.parseInt(((EditText) findViewById(R.id.create_input_repeat_count)).getText().toString())
				);

		if (taskId == -1) {
			throw new RuntimeException("insert task failed");
		} else {
			System.out.println("insert task success, id:" + taskId);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}
}
