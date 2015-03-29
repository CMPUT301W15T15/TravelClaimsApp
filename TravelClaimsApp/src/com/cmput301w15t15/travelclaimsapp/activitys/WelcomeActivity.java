package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class WelcomeActivity extends Activity {
	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		MediaPlayer ourSong=MediaPlayer.create(WelcomeActivity.this, R.raw.sound);
		ourSong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(2500);
				}catch (InterruptedException e){
					e.printStackTrace();
				}finally {
					Intent intent =new Intent(WelcomeActivity.this, MainMenuActivity.class);
					startActivity(intent);
				}
			}
		};
		timer.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 5c9718f590da865f1bc5672bc3a7974b65489834
