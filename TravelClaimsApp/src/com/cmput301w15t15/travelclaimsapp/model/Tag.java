package com.cmput301w15t15.travelclaimsapp.model;

import java.util.ArrayList;

public class Tag implements Listenable{
	protected String tagName;
	private transient ArrayList<Listener> listeners;
	
	public Tag(String tagName1){
		this.tagName=tagName1;
		this.listeners = new ArrayList<Listener>();
	}

	public void setName(String tagName){
		this.tagName=tagName;
		notifyListeners();
		
	}
	
	
	public String getName() {
		// TODO Auto-generated method stub
		return this.tagName;
	}

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