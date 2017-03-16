package cba.weather.gen.season;

import java.util.ArrayList;
import java.util.List;

public class Equator extends Season {
	public List<Integer> getHotterMonths() {
		return new ArrayList<Integer>() {
			{
				add(Month.MARCH);
				add(Month.APRIL);
				add(Month.MAY);
				add(Month.JUNE);
				add(Month.JULY);
				add(Month.AUGUST);
				add(Month.SEPTEMBER);
				add(Month.OCTOBER);
				add(Month.NOVEMBER);
			}
		};
	}
	
	public List<Integer> getColderMonths() {
		return new ArrayList<Integer>() {
			{
				add(Month.DECEMBER);
				add(Month.JANUARY);
				add(Month.FEBRUARY);
			}
		};
	}
}
