package cba.weather.gen.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.joda.time.DateTime;

import cba.weather.gen.Constants;
import cba.weather.gen.Weather;
import cba.weather.gen.map.Coordinates;
import cba.weather.gen.map.MapUtil;
import cba.weather.gen.season.Equator;
import cba.weather.gen.season.Season;

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
		double milesFromEquator = Math.abs(latitude) * Constants.ONE_DEG_LATITUDE_IN_MILES;
		
		double tempChangePerMileWithSeason = Constants.TEMP_CHANGE_PER_300_MILE * getFactorOfTemperatureChange(latitude, datetime); 
		double tempDifference = (milesFromEquator / Constants.TEMP_CHANGE_PER_MILES) * tempChangePerMileWithSeason;
		
		return (tempAtEquator - tempDifference);
	}
	
	
	public static double getFactorOfTemperatureChange(double latitude, DateTime datetime) {
		Season season = cba.weather.gen.season.SeasonUtil.getSeasonArea(latitude);
		
		if(season instanceof Equator)
			return 0.0;
		else if(season.isHotMonth(datetime.getMonthOfYear()))
			return Constants.TEMP_DEC_FACTOR_LATITUDE_SUMMER;
		else
			return Constants.TEMP_DEC_FACTOR_LATITUDE_WINTER;
	}
	
	public static double getElevation(String imagePath, double latitude, double longitude) throws Exception {
		return MapUtil.getColorIntensity(latitude, longitude, imagePath) * Constants.ELEVATION_PER_COLOR_INTESITY;
	}
	
	public static Weather getWeather(String location, Coordinates coordinates, DateTime time, String mapPath) throws Exception {
		double tempAtSeaLevel = Util.getTempAtLatLongAtSeaLevel(coordinates.getLatitude(), time);
		double atmosphericPressureAtSeaLevel = Util.generateDoubleNumber(Constants.ATMOSPHERIC_PRESSURE_RANGE.getMin(), Constants.ATMOSPHERIC_PRESSURE_RANGE.getMax(), 1);
		double elevation = Util.getElevation(mapPath, coordinates.getLatitude(), coordinates.getLongitude());
		double atmosphericPressureAtElevation = atmosphericPressureAtSeaLevel - (elevation * Constants.ATMOSPHERIC_PRESSURE_DEC_PER_FOOT);
		int humidity = Util.generateIntegerNumber(Constants.HUMIDITY_RANGE.getMin(), Constants.HUMIDITY_RANGE.getMax());
		double tempDecFromSeaLevel = (elevation / 1000) * Constants.TEMP_DEC_PER_1000_FEET;
		double tempAtElevation = Converter.fahToCel(tempAtSeaLevel - tempDecFromSeaLevel);
		
		return new Weather(location, coordinates.getLatitude(), coordinates.getLongitude(), Converter.feetToMetre(elevation), time, tempAtElevation, atmosphericPressureAtElevation, humidity);
	}

	public static void generateFile(Map<String, Coordinates> locations, String mapPath, String destPath, int numPerLocation) throws Exception {
		File file = new File(destPath, "weather.txt");
		
		if(file.exists())
			file.delete();
		
		BufferedWriter bufferWriter = null;
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(file);
			bufferWriter = new BufferedWriter(fileWriter);
			for(String location : locations.keySet()) {
				for(int index = 0; index < numPerLocation; index ++) {
					bufferWriter.write(getWeather(location, locations.get(location), RandomTimeGenerator.generateDateTime(), mapPath).pipeFormatted() + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferWriter != null)
					bufferWriter.close();
				if (fileWriter != null)
					fileWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
