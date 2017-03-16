package cba.weather.gen;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cba.weather.gen.map.Coordinates;
import cba.weather.gen.util.Util;

public class Driver {

	/**
	 * @param args Expects runtime arguments to get following: 
	 *              -map Folder path of the elevation tiff images
	 *              -dest Folder path for writing the generated file
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		CommandLine parsedArgs  = parseRunTimeArguments(args);
		String mapPath = parsedArgs.getParsedOptionValue("map").toString();
		String destPath = parsedArgs.getParsedOptionValue("dest").toString();
		
		Map<String, Coordinates> locations = new HashMap<String, Coordinates>();
		
		locations.put("Sydney", new Coordinates(-33.868820, 151.209296));
		locations.put("Mount Everest", new Coordinates(27.987850, 86.925026));
		locations.put("New Delhi", new Coordinates(28.613939, 77.209021));
		locations.put("Chennai", new Coordinates(13.082680, 80.270718));
		locations.put("London", new Coordinates(51.507351, -0.127758));
		locations.put("New York", new Coordinates(40.712784, -74.005941));
		
		Util.generateFile(locations, mapPath, destPath, 2);
	}
	
	private static CommandLine parseRunTimeArguments(String[] args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		Option mapPath = Option.builder("map").argName("map")
				.desc("Folder path of the elevation map tiff files")
				.hasArg()
				.required(true)
				.build();
		Option destPath = Option.builder("dest").argName("dest")
				.desc("Folder path for writing the generated file")
				.hasArg()
				.required(true)
				.build();
		
		Options options = new Options();
		options.addOption(mapPath);
		options.addOption(destPath);
		
		return parser.parse(options, args);
	}

}
