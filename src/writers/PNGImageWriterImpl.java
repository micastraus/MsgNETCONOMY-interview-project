package writers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Controller.AppSingleton;
import Controller.Util;
import model.Trip;
import model.TripCoordinate;

public class PNGImageWriterImpl implements AbstractWriter {
	
	@Override
	public void generateTrips(char[][] mat, String outputFilePath) {
		// Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setPaint (Color.WHITE);
        g2d.fillRect ( 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight() );
        
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				
				// Ako kroz tu tacku niko do sada nije prosao, onda samo postavljam oznaku trenutnog puta, 'A' na primer
				if (mat[i][j] != Util.NULL_CHARACTER) {
					int rgbColor;
					if (mat[i][j] != Util.TRIP_INTERSECTION_CHAR) {
						Color color = Util.TRIP_COLOR_VALUES[mat[i][j] - 'A']; // mat[i][j] - 'A', jer je 'A' = 65 u decimalnom zapisu 
						rgbColor = color.getRGB();
					}
					else {
						Color color = Util.TRIP_INTERSECTION_COLOR;
						rgbColor = color.getRGB();
					}
					bufferedImage.setRGB(j, i, rgbColor);
				}
				// else, ostaje bela tacka
			}
		}
        
     // Disposes of this graphics context and releases any system resources that it is using. 
        g2d.dispose();
        
        File file = new File(outputFilePath);
        try {
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
