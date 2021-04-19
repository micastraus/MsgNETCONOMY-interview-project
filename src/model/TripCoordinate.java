package model;

import java.util.Date;

public class TripCoordinate {
	
	private int id;
	private String trip_id;
	private int x;
	private int y;
	private Date ts;
	
	public TripCoordinate(int id, String trip_id, int x, int y, Date ts) {
		super();
		this.id = id;
		this.trip_id = trip_id;
		this.x = x;
		this.y = y;
		this.ts = ts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
	
	@Override
	public String toString() {
		return id + ";" + trip_id + ";" + x + ";" + y + ";" + ts;
	}
	
	

}
