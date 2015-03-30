package com.cmput301w15t15travelclaimsapp.osmdroid;
//https://code.google.com/p/osmbonuspack/source/browse/trunk/OSMBonusPack/src/org/osmdroid/bonuspack/overlays/MapEventsReceiver.java
import org.osmdroid.util.GeoPoint;

/**
 * Interface for objects that need to handle map events thrown by a MapEventsOverlay.
 * @see MapEventsOverlay
 * @author M.Kergall
 */
public interface MapEventsReceiver {

        /**
         * @param p the position where the event occurred.
         * @return true if the event has been "consumed" and should not be handled by other objects.
         */
        boolean singleTapConfirmedHelper(GeoPoint p);
       
        /**
         * @param p the position where the event occurred.
         * @return true if the event has been "consumed" and should not be handled by other objects.
         */
        boolean longPressHelper(GeoPoint p);    
}
