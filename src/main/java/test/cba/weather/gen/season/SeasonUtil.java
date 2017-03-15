package test.cba.weather.gen.season;

public class SeasonUtil {
	public static Season getSeasonArea(double latitude) {
		if(latitude > 0)
			return new NorthernHemisphere();
		else if(latitude < 0)
			return new SouthernHemisphere();
		else
			return new Equator();
	}
}
