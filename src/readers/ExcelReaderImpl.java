package readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Trip;
import model.TripCoordinate;

public class ExcelReaderImpl implements AbstractReader {
	
	FileInputStream fis = null;
    XSSFWorkbook book = null;
    
    // lista sheet-ova (tabela) u Excel fajlu
    ArrayList<XSSFSheet> sheetList;
	
	public ExcelReaderImpl(String inputFilePath) {
		File f = new File(inputFilePath);
        try {
        	fis = new FileInputStream(f);
        	book = new XSSFWorkbook(fis);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        
        sheetList = new ArrayList<>();
        
        // Tacno sam dva sheet-a dodao u listu jer vise od toga u trenutnoj aplikaciji necu koristiti
        sheetList.add(book.getSheetAt(0));
        sheetList.add(book.getSheetAt(1));
	}
	
	
	@Override
	public ArrayList<Trip> getTrips(String driver_id) {
		XSSFSheet tripSheet = sheetList.get(0);
    	Iterator<Row> rowIter = tripSheet.rowIterator();
    	
    	// kreiram listu trip-ova, da bih posle za svaki trip u drugoj tabeli nasao koordinate
    	ArrayList<Trip> tripRows = new ArrayList<>();
    	
    	// Prvi red (nazive kolona) preskacemo, ukoliko postoji barem jedan red u tom sheet-u
    	if (tripSheet.getPhysicalNumberOfRows() > 0) {
    		rowIter.next();
    	}

    	while (rowIter.hasNext()) {
        	Row row = rowIter.next();
        	// pretpostavka da ce kolona 1 (B kolona u excell-u) u trips tabeli uvek biti "driver_id"
        	if (row.getCell(1).toString().equals(driver_id)) {
        		String trip_id = row.getCell(0).toString();
        		// row.getCell(1) je driver_id, to vec imam kao prosledjeni argument getTrips(String driver_id) metode
        		Date start_ts = row.getCell(2).getDateCellValue();
        		Date end_ts = row.getCell(3).getDateCellValue();
				tripRows.add(new Trip(trip_id, driver_id, start_ts, end_ts));
        	}
        }
        
//        try {
//			book.close();
//			fis.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
        
		return tripRows;
	}
	
	@Override
	public ArrayList<TripCoordinate> getCoordinates(String trip_id) {
		ArrayList<TripCoordinate> tripCoords = new ArrayList<>();
		XSSFSheet tripSheet = sheetList.get(1);
		
		Iterator<Row> rowIter = tripSheet.rowIterator();
    	
    	// Prvi red (nazive kolona) preskacemo, ukoliko postoji barem jedan red u tom sheet-u
    	if (tripSheet.getPhysicalNumberOfRows() > 0) {
    		rowIter.next();
    	}

    	while (rowIter.hasNext()) {
        	Row row = rowIter.next();
        	// pretpostavka da ce kolona 1 (B kolona u excell-u) u trip_coordinates tabeli uvek biti "trip_id"
        	if (row.getCell(1).toString().equals(trip_id)) {
        		String stringId = row.getCell(0).toString();
        		int id = (int)Float.parseFloat(stringId); // cita vrednosti iz excell-a kao decimalni zapis, pa nisam smeo
        												  // da parsiram u Integer
        		
        		String stringX = row.getCell(2).toString();
        		int x = (int)Float.parseFloat(stringX);
        		
        		String stringY = row.getCell(3).toString();
        		int y = (int)Float.parseFloat(stringY);
        		
        		Date ts = row.getCell(4).getDateCellValue();
        		
        		tripCoords.add(new TripCoordinate(id, trip_id, x, y, ts));
        	}
        }
    	
		return tripCoords;
	}

}
