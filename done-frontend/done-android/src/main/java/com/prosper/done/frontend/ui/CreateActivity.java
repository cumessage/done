package com.prosper.done.frontend.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.prosper.done.frontend.R;
import com.prosper.done.frontend.bean.Location;
import com.prosper.done.frontend.bean.Task;
import com.prosper.done.frontend.data.DoneDbHelper;
import com.prosper.done.frontend.data.LocationDao;
import com.prosper.done.frontend.data.TaskDao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

public class CreateActivity extends FragmentActivity {

//	TimeInputFragment timeInputFragment = null;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_create);
	}

	public void creatTask(View view) throws ParseException {
		DoneDbHelper dbHelper = new DoneDbHelper(getBaseContext());
		LocationDao locationDao = new LocationDao(dbHelper);
		TaskDao taskDao = new TaskDao(dbHelper);

		String locationVal = ((EditText) findViewById(R.id.create_input_location)).getText().toString();
		Location location = locationDao.getByName(locationVal);

		if (location == null) {
			long locationId = locationDao.insert(locationVal);
			if (locationId == -1) {
				throw new RuntimeException("location is not inserted!");
			}
			location = locationDao.getByName(locationVal);
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);  
		Date startTime = simpleDateFormat.parse(((EditText) findViewById(R.id.create_input_start_time)).getText().toString());  
		Date endTime = simpleDateFormat.parse(((EditText) findViewById(R.id.create_input_end_time)).getText().toString());
		
		Task task = new Task();
		task.setDesc(((EditText) findViewById(R.id.create_input_desc)).getText().toString());
		task.setMemo(((EditText) findViewById(R.id.create_input_memo)).getText().toString());
		task.setLocation(location);
		task.setStartTime(startTime.getTime());
		task.setEndTime(endTime.getTime());
		task.setIsRepeat(Integer.parseInt(((EditText) findViewById(R.id.create_input_is_repeat)).getText().toString()));
		task.setMaxCount(Integer.parseInt(((EditText) findViewById(R.id.create_input_max_count)).getText().toString()));
		
		
		long taskId = taskDao.insert(task);

		if (taskId == -1) {
			throw new RuntimeException("insert task failed");
		} else {
			System.out.println("insert task success, id:" + taskId);
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}

	public void inputStartTime(View view) {
		inputTime(R.id.create_input_start_time);
	}

	public void inputEndTime(View view) {
		inputTime(R.id.create_input_end_time);
	}

	private void inputTime(int fieldId) {
		TimeInputFragment timeInputFragment = new TimeInputFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("fieldId", fieldId);
		timeInputFragment.setArguments(new Bundle(bundle));
		timeInputFragment.show(getSupportFragmentManager(), "add");
	}

}





















