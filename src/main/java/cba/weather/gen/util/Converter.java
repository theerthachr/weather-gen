package cba.weather.gen.util;

public class Converter {
	public static double fahToCel(double tempFarenheit) {
		return (tempFarenheit - 32) * (5.0 / 9.0);
	}
	
	public static double feetToMetre(double feet) {
		return feet / 3.28;
	}
}
