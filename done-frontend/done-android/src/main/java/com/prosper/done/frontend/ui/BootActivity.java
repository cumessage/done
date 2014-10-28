package com.prosper.done.frontend.ui;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.prosper.done.frontend.R;

public class BootActivity extends SherlockActivity {

	@Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_boot);
        
        try {
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
