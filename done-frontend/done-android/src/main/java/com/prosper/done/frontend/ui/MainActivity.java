package com.prosper.done.frontend.ui;

import com.prosper.done.frontend.R;
import com.prosper.done.frontend.data.DoneDbHelper;
import com.prosper.done.frontend.service.TaskService;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		DoneDbHelper dbHelper = new DoneDbHelper(getBaseContext());
		TaskService taskService = new TaskService(dbHelper);
		taskService.checkOutdated();
		
		setListAdapter(new SimpleAdapter(
				this, taskService.getData(), R.layout.list_view_main, 
				new String[]{"detail"}, new int[]{R.id.detail}));
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
