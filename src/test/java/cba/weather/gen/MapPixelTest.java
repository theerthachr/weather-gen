package cba.weather.gen;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cba.weather.gen.map.MapImagesGrid;
import cba.weather.gen.map.MapUtil;
import cba.weather.gen.map.Pixel;

public class MapPixelTest {

	@Test
	public void checkCalibrationWithImage() throws Exception {
		MapImagesGrid mapGrid1 = MapUtil.getImageGrid(-33.868820, 151.209296);
		MapImagesGrid mapGrid2 = MapUtil.getImageGrid(27.987850, 86.925026);
		MapImagesGrid mapGrid3 = MapUtil.getImageGrid(-33.868820, 151.209296);
		
		Pixel pixel1 = MapUtil.getImagePixel(mapGrid1, -33.868820, 151.209296, 10800);
		Pixel pixel2 = MapUtil.getImagePixel(mapGrid2, 27.987850, 86.925026, 10800);
		Pixel pixel3 = MapUtil.getImagePixel(mapGrid3, 28.613939, 77.209021, 10800);
		
		assertEquals("Pixel x coordinate should match", 7345, pixel1.x);
		assertEquals("Pixel x coordinate should match", 10431, pixel2.x);
		assertEquals("Pixel x coordinate should match", 1534, pixel3.x);
		
		assertEquals("Pixel y coordinate should match", 4064, pixel1.y);
		assertEquals("Pixel y coordinate should match", 7442, pixel2.y);
		assertEquals("Pixel y coordinate should match", 7367, pixel3.y);
	}
}
