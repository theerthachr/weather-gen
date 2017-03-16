package cba.weather.gen;

import cba.weather.gen.util.Range;

public class Constants {
	public static final int MAX_ELEVATION_IN_FT = 29035; // Height of Mount everest in feet
	public static final double ONE_DEG_LATITUDE_IN_MILES = 69.0; // One degree change in latitude is equivalent to 69 miles
	public static final int TEMP_CHANGE_PER_MILES = 300; // temperature decreases ever 300 miles from the equator
	public static final double TEMP_CHANGE_PER_300_MILE = 3.0; // the temperate change in fahrenheit per 300 miles from equator
	public static final Range EQUATOR_TEMP_RANGE_F = new Range(95, 122); // assumed temperature range along the equator in fahrenheit
	
	public static final Range ATMOSPHERIC_PRESSURE_RANGE = new Range(1000, 1080); // assumption made on the basis of normal pressure at sea level
	public static final double ATMOSPHERIC_PRESSURE_DEC_PER_FOOT = 0.02627; // assumption made on the basis of height of mount everest
	
	public static final Range HUMIDITY_RANGE = new Range(0, 100); 
	
	public static final double TEMP_DEC_FACTOR_LATITUDE_SUMMER = 1.0; 
	public static final double TEMP_DEC_FACTOR_LATITUDE_WINTER = 1.2;
	
	public static final double ELEVATION_PER_COLOR_INTESITY = 29035.0 / 255.0; // Highest level above sea level divided by the total number of color intensity in color channel 
	public static final double TEMP_DEC_PER_1000_FEET = 3.0;
}
