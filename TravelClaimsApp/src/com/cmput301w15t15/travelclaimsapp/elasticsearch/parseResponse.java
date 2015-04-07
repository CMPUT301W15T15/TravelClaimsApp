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

/**
 * Handles parsing related to http responses in Elastic Search.
 */
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
	 * @return SearchHit<ClaimList>
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
	 * @return SearchHit<User>
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
