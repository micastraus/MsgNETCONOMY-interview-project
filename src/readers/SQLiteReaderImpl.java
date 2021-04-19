package readers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Controller.AppSingleton;
import model.Trip;
import model.TripCoordinate;

public class SQLiteReaderImpl implements AbstractReader {
	
	
	@Override
	public ArrayList<Trip> getTrips(String driver_id) {
		Statement statement;
		ArrayList<Trip> trips = new ArrayList<>();
		try {
			statement = AppSingleton.getConnection().createStatement();
			ResultSet res = null;
			res = statement.executeQuery("SELECT * from TRIPS WHERE driver_id = '" + driver_id +"'");
			if (res == null) return null;
			
			
			// prvi put res.next() ce preskociti prvi red u tabeli, koji je u stvari heder (red sa nazivima kolona)
			while (res.next()) {
				String trip_id = res.getString("trip_id");
				Date start_ts = null;
				Date end_ts = null;
				try {
					start_ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("start_ts"));
					end_ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("end_ts"));
					trips.add(new Trip(trip_id, driver_id, start_ts, end_ts));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return trips;
	}
	
	@Override
	public ArrayList<TripCoordinate> getCoordinates(String trip_id) {
		Statement statement;
		ArrayList<TripCoordinate> tripCoords =  new ArrayList<>();
		
		try {
			statement = AppSingleton.getConnection().createStatement();
			ResultSet res = null;
			res = statement.executeQuery("SELECT * from POINTS WHERE trip_id = '" + trip_id +"'");
			if (res == null) return null;
			
			// prvi put res.next() ce preskociti prvi red u tabeli, koji je u stvari heder (red sa nazivima kolona)
			while (res.next()) {
				int id = res.getInt("id");
				int x = res.getInt("x");
				int y = res.getInt("y");
				
				Date ts = null;
				try {
					ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("ts"));
					tripCoords.add(new TripCoordinate(id, trip_id, x, y, ts));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (TripCoordinate coo : tripCoords) 
    		System.out.println(coo);
		
		return tripCoords;
	}
	
}
