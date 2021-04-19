package writers;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import Controller.AppSingleton;
import Controller.Util;
import model.Trip;
import model.TripCoordinate;

public class TextFileWriterImpl implements AbstractWriter {
	
	@Override
	public void generateTrips(char[][] mat, String outputFilePath) {
		
		File f = new File(outputFilePath);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		}
		catch(Exception ee) {
			ee.printStackTrace();
		}
		
		for (int i=0; i<mat.length; i++) {
			String line = "";
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j] != 0) 
					line += mat[i][j];
				else
					line +=" ";
				System.out.print(mat[i][j]);
			}
			pw.println(line);
			System.out.println();
		}
		
		pw.close();
	}

}
