
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

// https://github.com/joshua2ua/AndroidElasticSearch/blob/master/src/ca/ualberta/ssrg/movies/es/data/SearchHit.java

package com.cmput301w15t15.travelclaimsapp.elasticsearch;

public class SearchHit<T> {
	private T _source;


	public T getSource() {
		return _source;
	}
	
}
