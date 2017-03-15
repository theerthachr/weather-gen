package test.cba.weather.gen;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import test.cba.weather.gen.util.Converter;
import test.cba.weather.gen.util.Util;

public class Driver {

	/**
	 * @param args Expects runtime arguments to get following: 
	 *              -map Folder path of the elevation tiff images
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		CommandLine parsedArgs  = parseRunTimeArguments(args);
		String mapPath = parsedArgs.getParsedOptionValue("map").toString();
		
		DateTime time = DateTime.parse("2016-12-01 13:00:08", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
		double latitude = -33.857302;
		double longitude = 151.182861;
		
		
				
		System.out.println(Util.getWeather(latitude, longitude, time, mapPath).pipeFormatted());
		
	}
	
	private static CommandLine parseRunTimeArguments(String[] args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		Option mapPath = Option.builder("map").argName("map")
				.desc("Folder path of the elevation map tiff files")
				.hasArg()
				.required(true)
				.build();
		
		Options options = new Options();
		options.addOption(mapPath);
		
		return parser.parse(options, args);
	}

}
