package model;

import java.util.Date;

public class Trip {
	
	private String trip_id;
	private String driver_id;
	private Date start_ts;
	private Date end_ts;
	
	public Trip(String trip_id, String driver_id, Date start_ts, Date end_ts) {
		super();
		this.trip_id = trip_id;
		this.driver_id = driver_id;
		this.start_ts = start_ts;
		this.end_ts = end_ts;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public Date getStart_ts() {
		return start_ts;
	}

	public void setStart_ts(Date start_ts) {
		this.start_ts = start_ts;
	}

	public Date getEnd_ts() {
		return end_ts;
	}

	public void setEnd_ts(Date end_ts) {
		this.end_ts = end_ts;
	}
	
	@Override
	public String toString() {
		return trip_id + ";" + driver_id + ";" + start_ts + ";" + end_ts;
	}
	
	

}
