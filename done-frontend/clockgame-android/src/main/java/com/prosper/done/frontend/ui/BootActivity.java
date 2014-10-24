package com.prosper.done.frontend.ui;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.SherlockActivity;
import com.prosper.done.frontend.R;
import com.prosper.done.frontend.bean.Game;
import com.prosper.done.frontend.bean.GameResource;

public class BootActivity extends SherlockActivity {

	@Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_boot);
        
        InputStream config = getResources().openRawResource(R.raw.config);
        JSONArray jsonArray = null;
        try {
			// jsonArray = new JSONArray(config.toString());
			// todo load config
			Thread.sleep(500);
			
			
		} catch (Exception e) {
			System.out.println("load config failed");
			e.printStackTrace();
			return;
		}
        
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
