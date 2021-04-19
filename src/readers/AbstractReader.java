package readers;

import java.util.ArrayList;

import model.Trip;
import model.TripCoordinate;

public interface AbstractReader {

	ArrayList<Trip> getTrips(String driver_id);
	ArrayList<TripCoordinate> getCoordinates(String trip_id);
	
}
