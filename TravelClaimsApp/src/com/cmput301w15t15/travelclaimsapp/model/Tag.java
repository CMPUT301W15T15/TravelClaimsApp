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
package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class Tag implements Listenable{
	protected String tagName;
	private transient ArrayList<Listener> listeners;
	
	public Tag(String tagName1){
		this.tagName=tagName1;
		this.listeners = new ArrayList<Listener>();
	}

	/**Set Tag Name
	 * @param tagName
	 */
	public void setName(String tagName){
		this.tagName=tagName;
		notifyListeners();
		
	}
	
	
	/**Return tagName to tagList
	 * @return tagName
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return this.tagName;
	}

	/**Changes tagName
	 * @param tagName2
	 */
	public void rename(String tagName2) {
		// TODO Auto-generated method stub
		this.tagName=tagName2;
		notifyListeners();
	}

	public String toString(){
		return tagName;
	}
	
	@Override
	public void notifyListeners() {
		for(Listener listener : listeners){
			listener.update();
		}
		
	}

	@Override
	public void addListener(Listener listener) {
		this.listeners.add(listener);
		
	}

	@Override
	public void setListeners() {
		this.listeners = new ArrayList<Listener>();
		
	}

	@Override
	public void deleteListener(Listener listener) {
		this.listeners.remove(listener);
		
	}
}