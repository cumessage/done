package com.prosper.done.frontend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.util.Log;

import com.prosper.done.frontend.bean.Task;
import com.prosper.done.frontend.common.HistoryStatus;
import com.prosper.done.frontend.data.DoneDbHelper;
import com.prosper.done.frontend.data.HistoryDao;
import com.prosper.done.frontend.data.TaskDao;

public class TaskService {

	private DoneDbHelper dbHelper;

	public TaskService(DoneDbHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public void checkOutdated() { 
		TaskDao taskDao = new TaskDao(dbHelper);
		HistoryDao historyDao = new HistoryDao(dbHelper);

		long currentTime = System.currentTimeMillis();
		List<Task> outDatedTaskList = taskDao.getListByEndtime(System.currentTimeMillis(), true);

		for(Task task: outDatedTaskList) {
			if (task.getEndTime() > currentTime) continue;

			if (task.getIsRepeat() == 1) {
				task.setStartTime(task.getEndTime());
				task.setEndTime(task.getEndTime() + (task.getEndTime() - task.getStartTime()));
				taskDao.update(task);
			}

			int status = HistoryStatus.UNFINISHED;
			if (task.getMaxCount() > 0) {
				if (task.getFinishCount() > task.getMaxCount()) {
					status = HistoryStatus.FINISHED;
				}
			}

			historyDao.insert(
					task.getDesc(), 
					task.getMemo(), 
					status,
					task.getLocation().getName(), 
					currentTime, 
					task.getStartTime(), 
					task.getEndTime(), 
					task.getIsRepeat(), 
					task.getMaxCount(),
					task.getFinishCount()
					);

			taskDao.delete(task.getId());
		}
	}

	public List<Map<String, String>> getData() {
		TaskDao taskDao = new TaskDao(this.dbHelper);
		List<Task> taskList = taskDao.getList();

		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA); 
		for (Task task : taskList) {
			Map<String, String> dataMap = new HashMap<String, String>();
			
			dataMap.put(
					"detail", 
					"desc: " + task.getDesc() + " \n " + 
							"memo: " + task.getMemo() + " \n " + 
							"location id: " + task.getLocation().getId() + " \n " +
							"location name: " + task.getLocation().getName() + " \n " +
							"startTime: " + time.format(new Date(task.getStartTime())) + " \n " +
							"endTime: " + time.format(new Date(task.getEndTime())) + " \n " +
							"isRepeat: " + task.getIsRepeat() + " \n " +
							"maxCount: " + task.getMaxCount() + " \n " +
							"createTime: " + time.format(new Date(task.getCreateTime())) + " \n " +
							"updateTime: " + time.format(new Date(task.getUpdateTime()))
					);
			dataList.add(dataMap);
		}
		return dataList;
	}


}
