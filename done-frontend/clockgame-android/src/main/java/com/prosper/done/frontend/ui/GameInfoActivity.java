package com.prosper.done.frontend.ui;

import com.actionbarsherlock.app.SherlockActivity;
import com.prosper.done.frontend.R;
import com.prosper.done.frontend.common.DefaultHandler;
import com.prosper.done.frontend.common.DefaultResponse;
import com.prosper.done.frontend.common.Global;
import com.prosper.done.frontend.restful.GameRestClient;

import android.os.Bundle;
import android.widget.TextView;

public class GameInfoActivity extends SherlockActivity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_game_info);
        
        Global global = (Global) getApplication();
		long gameId = global.getGameId();
		try {
			GameRestClient.getInfo(gameId, new DefaultHandler() {
				@Override
				public void doMessage (DefaultResponse response) {
					updateUI(response);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void updateUI(DefaultResponse response) {
		TextView gameIdView = (TextView) findViewById(R.id.game_id);
		gameIdView.setText(response.getJson().get("id").asText());
    	System.out.println("get result:" + response.getJson().toString());
	}
    

}
