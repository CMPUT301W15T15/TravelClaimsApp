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
package com.cmput301w15t15.travelclaimsapp;

import java.net.InetAddress;

import android.content.Context;
import android.net.ConnectivityManager;


/**
 * Used to check connectivity to the internet through different methods
 *
 */
public class InternetController {
	/**
	 * Checks for internet connection by checking for IP Address. Needs to be run in its own thread.
	 * @return boolean
	 */
	public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("http://cmput301.softwareprocess.es:8080/cmput301w15t15/");
//            InetAddress ipAddr = InetAddress.getByName("https://www.google.ca/webhp?hl=en");

            if (ipAddr.equals("")) {
                return false;
//            	return "no ip";
            } else {
                return true;
//            	return "good";
            }

        } catch (Exception e) {
            return false;
//        	return "else";
        }

    }


	/**
	 * Checks for internet connection by checking network activity. Needs activity context to be run.
	 * @param context
	 * @return boolean
	 */
	public static boolean isInternetAvailable2(Context context) {
		return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
	}
}
