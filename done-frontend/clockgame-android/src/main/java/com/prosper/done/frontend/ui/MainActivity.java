package com.prosper.done.frontend.ui;

import java.util.ArrayList;
import java.util.List;

import com.prosper.done.frontend.R;
import com.prosper.done.frontend.data.DoneDb;
import com.prosper.done.frontend.data.DoneDbConfig.Task;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(
				this, android.R.layout.simple_expandable_list_item_1, getData()));
		setContentView(listView);
//		setContentView(R.layout.activity_main);
	}

	private List<String> getData() {
		List<String> dataList = new ArrayList<String>();
		Cursor c = getTaskList();
		while(!c.isLast()) {
			Log.d("donelog", "name: " + c.getColumnIndex(Task.COLUMN_NAME_DESC));
			dataList.add(c.getString(1));
			c.moveToNext();
		}
		return dataList;
	}

	private Cursor getTaskList() {
		DoneDb mDbHelper = new DoneDb(getBaseContext());
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		String[] projection = {
				Task._ID,
				Task.COLUMN_NAME_DESC
		};

		String sortOrder = Task._ID + " DESC";

		Cursor c = db.query(
				Task.TABLE_NAME,  // The table to query
				projection,                               // The columns to return
				null,                                // The columns for the WHERE clause
				null,                            // The values for the WHERE clause
				null,                                     // don't group the rows
				null,                                     // don't filter by row groups
				sortOrder                                 // The sort order
				);

		c.moveToFirst();
		return c;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_actionbar_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_add:
			openAdd();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void openAdd() {
		Intent intent = new Intent(this, CreateActivity.class);
		startActivity(intent);
	}

	private void openSettings() {

	}

}
