package cba.weather.gen.map;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MapUtil {
	public static MapImagesGrid getImageGrid(double latitude, double longitude) throws Exception {
		A1Grid a1Grid;
		A2Grid a2Grid;
		B1Grid b1Grid;
		B2Grid b2Grid;
		C1Grid c1Grid;
		C2Grid c2Grid;
		D1Grid d1Grid;
		D2Grid d2Grid;
		
		if((a1Grid = new A1Grid()).isGrid(latitude, longitude)) 
			return a1Grid;
		else if((a2Grid = new A2Grid()).isGrid(latitude, longitude))
			return a2Grid;
		else if((b1Grid = new B1Grid()).isGrid(latitude, longitude))
			return b1Grid;
		else if((b2Grid = new B2Grid()).isGrid(latitude, longitude))
			return b2Grid;
		else if((c1Grid = new C1Grid()).isGrid(latitude, longitude))
			return c1Grid;
		else if((c2Grid = new C2Grid()).isGrid(latitude, longitude))
			return c2Grid;
		else if((d1Grid = new D1Grid()).isGrid(latitude, longitude))
			return d1Grid;
		else if((d2Grid = new D2Grid()).isGrid(latitude, longitude))
			return d2Grid;
		else
			throw new Exception("Invalid latitude or longitude" + latitude + ":" + longitude );
	}
	
	public static int getColorIntensity(double latitude, double longitude, String imagePath) throws Exception {
		MapImagesGrid mapGrid = getImageGrid(latitude, longitude);
		
		File file = new File(imagePath, mapGrid.getGridFileName());
		BufferedImage img = ImageIO.read(file);
		
		if(img.getWidth() != img.getHeight())
			throw new Exception("The pixel width and height of image" + file + "are different, please load the required image");
		
		int imageDimension = img.getWidth();

		Pixel imagePixel = getImagePixel(mapGrid, latitude, longitude, imageDimension);
		
		Color color = new Color(img.getRGB(imagePixel.x, imagePixel.y));
		
		return color.getRed();
	}
	
	public static Pixel getImagePixel(MapImagesGrid mapGrid, double latitude, double longitude, int imageDimension) {
		double latChange = 90.0 / imageDimension;
		double longChange = 90.0 / imageDimension;
		
		Pixel imagePixel = null;
		
		if(longitude < 0 && latitude > 0) 
			imagePixel = new Pixel(imageDimension - Math.abs((int)((longitude - mapGrid.getEndLongitude()) / longChange)), imageDimension - Math.abs((int)(latitude / latChange)));
		else if(longitude > 0  && latitude > 0)
			imagePixel = new Pixel(Math.abs((int)((longitude - mapGrid.getStartLongitude()) / longChange)), imageDimension - Math.abs((int)(latitude / latChange)));
		else if(longitude < 0 && latitude < 0)
			imagePixel = new Pixel(imageDimension - Math.abs((int)((longitude - mapGrid.getEndLongitude()) / longChange)), Math.abs((int)(latitude / latChange)));
		else
			imagePixel = new Pixel(Math.abs((int)((longitude - mapGrid.getStartLongitude()) / longChange)), Math.abs((int)(latitude / latChange)));
		
		return imagePixel;
	}
}

