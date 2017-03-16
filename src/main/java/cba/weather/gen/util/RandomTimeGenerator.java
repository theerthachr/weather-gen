package cba.weather.gen.util;

import java.util.concurrent.ThreadLocalRandom;

import org.joda.time.DateTime;

public class RandomTimeGenerator {
	public static DateTime generateDateTime() {

		int year = ThreadLocalRandom.current().nextInt(1950, 2018);
		int month = ThreadLocalRandom.current().nextInt(1, 13);
		int day = ThreadLocalRandom.current().nextInt(1, maxDaysInMonth(month) + 1);

		int hour = ThreadLocalRandom.current().nextInt(0, 24);
		int minute = ThreadLocalRandom.current().nextInt(0, 60);
		int second = ThreadLocalRandom.current().nextInt(0, 60);

		return new DateTime(year, month, day, hour, minute, second);
	}

	public static int maxDaysInMonth(int monthIndex) {
		switch (monthIndex) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 2:
			return 28; // Not considering leap years right now
		default:
			return 30;
		}
	}
}
