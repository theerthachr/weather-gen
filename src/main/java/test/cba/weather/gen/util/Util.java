package test.cba.weather.gen.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import org.joda.time.DateTime;

import test.cba.weather.gen.Constants;
import test.cba.weather.gen.Weather;
import test.cba.weather.gen.map.MapImagesGrid;
import test.cba.weather.gen.map.MapUtil;
import test.cba.weather.gen.season.Equator;
import test.cba.weather.gen.season.Season;

public class Util {
	public static int generateIntegerNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	public static double generateDoubleNumber(double min, double max, int precision) {		
		return round(ThreadLocalRandom.current().nextDouble(min, max), precision);
	}
	
	public static double round(double value, int precision) {
		double rounder = 10.0;
		for(int incr = 1; incr <= precision; incr++) {
			rounder = rounder * (double)incr;
		}
		
		return Math.round(value * rounder) / rounder;
	}
	
	public static double getTemperatureAtEquator(DateTime datetime) {
		return generateDoubleNumber(Constants.EQUATOR_TEMP_RANGE_F.getMin(), Constants.EQUATOR_TEMP_RANGE_F.getMax(), 1);
	}
	
	public static double getTempAtLatLongAtSeaLevel(double latitude, DateTime datetime) {
		double tempAtEquator = getTemperatureAtEquator(datetime);
		System.out.println(" tempAtEquator" +tempAtEquator+" " +Converter.celToFah(tempAtEquator));
		double milesFromEquator = Math.abs(latitude) * Constants.ONE_DEG_LATITUDE_IN_MILES;
		
		System.out.println(" milesFromEquator" + milesFromEquator);
		double tempChangePerMileWithSeason = Constants.TEMP_CHANGE_PER_300_MILE * getFactorOfTemperatureChange(latitude, datetime); 
		double tempDifference = (milesFromEquator / Constants.TEMP_CHANGE_PER_MILES) * tempChangePerMileWithSeason;
		System.out.println("tempDifference::" + tempDifference);
		
		return (tempAtEquator - tempDifference);
	}
	
	
	public static double getFactorOfTemperatureChange(double latitude, DateTime datetime) {
		Season season = test.cba.weather.gen.season.SeasonUtil.getSeasonArea(latitude);
		
		if(season instanceof Equator)
			return 0.0;
		else if(season.isHotMonth(datetime.getMonthOfYear()))
			return Constants.TEMP_DEC_FACTOR_LATITUDE_SUMMER;
		else
			return Constants.TEMP_DEC_FACTOR_LATITUDE_WINTER;
	}
	
	public static double getElevation(String imagePath, double latitude, double longitude) throws Exception {
		MapImagesGrid mapGrid = MapUtil.getImageGrid(latitude, longitude);
		
		File file = new File(imagePath, mapGrid.getGridFileName());
		
		System.out.println(file);
		
		BufferedImage img = ImageIO.read(file);
		
		if(img.getWidth() != img.getHeight())
			throw new Exception("The pixel width and height of image" + file + "are different, please load the required image");
		
		int imageDimension = img.getWidth();


		double latChange = 90.0 / imageDimension;
		double longChange = 90.0 / imageDimension;
		
		int mapLatPixel = imageDimension - Math.abs((int)(latitude / latChange));
		int mapLonPixel = imageDimension - Math.abs((int)((longitude - mapGrid.getEndLongitude()) / longChange));
		
		Color color = new Color(img.getRGB(mapLonPixel, mapLatPixel));
		
		System.out.println(mapLonPixel +" -- "+ mapLatPixel);
		int colorIntensity = color.getRed();
		
		return colorIntensity * Constants.ELEVATION_PER_COLOR_INTESITY;
	}
	
	public static Weather getWeather(double latitude, double longitude, DateTime time, String mapPath) throws Exception {
		double tempAtSeaLevel = Util.getTempAtLatLongAtSeaLevel(latitude, time);
		double atmosphericPressureAtSeaLevel = Util.generateDoubleNumber(Constants.ATMOSPHERIC_PRESSURE_RANGE.getMin(), Constants.ATMOSPHERIC_PRESSURE_RANGE.getMax(), 1);
		double elevation = Util.getElevation(mapPath, latitude, longitude);
		double atmosphericPressureAtElevation = atmosphericPressureAtSeaLevel - (elevation * Constants.ATMOSPHERIC_PRESSURE_DEC_PER_FOOT);
		
		System.out.println("atmosphericPressureAtElevation" + atmosphericPressureAtElevation);
		
		int humidity = Util.generateIntegerNumber(Constants.HUMIDITY_RANGE.getMin(), Constants.HUMIDITY_RANGE.getMax());
		
		System.out.println(Converter.celToFah(tempAtSeaLevel));
		double tempDecFromSeaLevel = (elevation / 1000) * Constants.TEMP_DEC_PER_1000_FOOT;
		double tempAtElevation = Converter.celToFah(tempAtSeaLevel - tempDecFromSeaLevel);
		System.out.println(tempAtElevation);
		
		return new Weather("Sydney", latitude, longitude, Converter.feetToMetre(elevation), time, tempAtElevation, atmosphericPressureAtElevation, humidity);
	}
	
}
