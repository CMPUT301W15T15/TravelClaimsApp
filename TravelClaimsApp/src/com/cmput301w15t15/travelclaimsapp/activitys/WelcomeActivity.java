/*
 *TravelClaimsApp
 *Copyright (C) 2015 Jon Machinski, Bo Zhou, Henry Ha, Chris Wang, Sean Scheideman
 *
 *This program is free software: you can redistribute it and/or modify
 *it under the terms of the GNU General Public License as published by
 *the Free Software Foundation, either version 3 of the License, or
 *(at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.cmput301w15t15.travelclaimsapp.activitys;

import com.cmput301w15t15.travelclaimsapp.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

/**
<<<<<<< HEAD
 * 
 * used to show the welcome image and activity
 * @author
 *
=======
 * Shows intro picture and plays intro song.
>>>>>>> 6b000230bd4005c748c7fbcd91df358479d9e7fe
 */
public class WelcomeActivity extends Activity {
	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
					Intent intent =new Intent(WelcomeActivity.this, LoginActivity.class);
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
}