package com.cmput301w15t15.travelclaimsapp;

import java.net.InetAddress;

import android.content.Context;
import android.net.ConnectivityManager;


public class InternetController {
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


	public static boolean isInternetAvailable2(Context context) {
		return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
	}
}
