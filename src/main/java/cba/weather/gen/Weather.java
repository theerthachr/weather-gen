package cba.weather.gen;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import cba.weather.gen.util.Util;

public class Weather {
	enum WeatherCondition {
		sunny, rain, snow
	}
	
	public Weather(String location, double latitude, double longitude, double elevation, DateTime dateTime,
	    double temperature, double pressure, int humidity) {
		super();
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		this.dateTime = dateTime;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		
		if(humidity > 90 && temperature > 10) {
			this.condition = WeatherCondition.rain;
		}
		else if(humidity > 90 && temperature < 10) {
			this.condition = WeatherCondition.snow;
		}
		else {
			this.condition = WeatherCondition.sunny;
		}
	}

	private String location;
	private double latitude;
	private double longitude;
	private double elevation;
	private DateTime dateTime;
	private WeatherCondition condition;
	private double temperature;
	private double pressure;
	private int humidity;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public WeatherCondition getCondition() {
		return condition;
	}

	public void setCondition(WeatherCondition condition) {
		this.condition = condition;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	public String pipeFormatted() {
		StringBuffer str = new StringBuffer();
		
		StringBuffer position = new StringBuffer();
		double latRounded = Util.round(latitude, 2);
		double longRounded = Util.round(longitude, 2);
		int elevation = (int) this.elevation;
		position.append(latRounded).append(',').append(longRounded).append(',').append(elevation);
		
		String formmatedDateTime = dateTime.withZone(DateTimeZone.UTC).toString();
		
		double tempRounded = Util.round(temperature, 1);
		double pressureRounded = Util.round(pressure, 1);
		
		str.append(location).append('|').append(position)
		.append('|').append(formmatedDateTime).append('|')
		.append(condition).append('|').append(tempRounded).append('|')
		.append(pressureRounded).append('|').append(humidity);
		
		return str.toString();
	}

}
