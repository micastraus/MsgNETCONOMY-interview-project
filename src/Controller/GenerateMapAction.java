package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Trip;
import model.TripCoordinate;
import writers.PNGImageWriterImpl;
import writers.TextFileWriterImpl;

public class GenerateMapAction implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String driver_id = AppSingleton.getInstance().getWindow().getFieldDriver().getText().trim();
		ArrayList<Trip> trips = AppSingleton.getInstance().getReader().getTrips(driver_id);
		
		if (trips == null || trips.size() == 0) {
			System.out.println("Ne postoje predjeni putevi za unetog vozaca.");
			return;
		}
		
		
		char mat[][] = new char[100][100];
		
		// Svaki WRITER ce dobijati prosledjenu matricu sa karakterima, i na osnovu toga ce dalje odradjivati svoj deo
		int idx = 0;
		for (Trip t : trips) {
			ArrayList<TripCoordinate> tripCoords = AppSingleton.getInstance().getReader().getCoordinates(t.getTrip_id());
			for (TripCoordinate coord : tripCoords) {
				int x = coord.getX();
				int y = coord.getY();
				
				// Ako kroz tu tacku niko do sada nije prosao, onda samo postavljam oznaku trenutnog puta, 'A' na primer
				if (mat[y][x] == Util.NULL_CHARACTER) {
					mat[y][x] = Util.TRIP_CHAR_VALUES.charAt(idx);
				}
				// u suprotnom, vec je tuda vozac prosao, obelezi tu tacku sa 'X'
				else {
					mat[y][x] = Util.TRIP_INTERSECTION_CHAR;
				}
			}
			idx++;
		}
		
		String outputFileName = AppSingleton.getInstance().getWindow().getFieldOutputFile().getText().trim();
		if (outputFileName.length() == 0) {
			System.out.println("Fali naziv izlaznog fajla.");
			return;
		}
		
		String outputFilePath = AppSingleton.getSecondCommandLineArgument() + File.separator + outputFileName;
		
		int outputTypeIndex = AppSingleton.getInstance().getWindow().getListTypes().getSelectedIndex();
		// index = 0, TXT
		if (outputTypeIndex == 0) {
			outputFilePath += ".txt";
			AppSingleton.getInstance().setWriter(new TextFileWriterImpl());
		}
		// index = 1, PNG
		else if (outputTypeIndex == 1) {
			outputFilePath += ".png";
			AppSingleton.getInstance().setWriter(new PNGImageWriterImpl());
		}
		
		AppSingleton.getInstance().getWriter().generateTrips(mat, outputFilePath);
		
	}
}