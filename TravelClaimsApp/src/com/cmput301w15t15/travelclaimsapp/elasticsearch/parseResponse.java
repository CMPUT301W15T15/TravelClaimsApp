package com.cmput301w15t15.travelclaimsapp.elasticsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;

import android.util.Log;

import com.cmput301w15t15.travelclaimsapp.model.ClaimList;
import com.cmput301w15t15.travelclaimsapp.model.User;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public class parseResponse {

	private static Gson gson = new Gson();
	private static final String TAG = "Petat";
	
	// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/ESMovieManager.java
	/**
	 * Gets content from an HTTP response
	 */
	public static String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/ESMovieManager.java
	/**
	 * Gets rid of elastic search header and returns saved claimlist.
	 * @param response
	 * @return
	 */
	public static SearchHit<ClaimList> claimListHit(HttpResponse response) {
		
		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<SearchHit<ClaimList>>() {}.getType();
			
			SearchHit<ClaimList> sr = gson.fromJson(json, searchHitType);
			return sr;
		} 
		catch (IOException e) {
			Log.d(TAG, "parseClaimListHit did not work: " + e.getMessage());
		}
		
		return null;
	}
	
	// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/ESMovieManager.java
	/**
	 * Gets rid of elastic search header and returns saved user.
	 * @param response
	 * @return
	 */
	public static SearchHit<User> userHit(HttpResponse response) {

		try {
			String json = getEntityContent(response);
			Type searchHitType = new TypeToken<SearchHit<User>>() {}.getType();
			
			SearchHit<User> sr = gson.fromJson(json, searchHitType);
			return sr;
		} 
		catch (IOException e) {
			Log.d(TAG, "parseUseHit could not work: " + e.getMessage());
		}
		
		return null;
	}
}
