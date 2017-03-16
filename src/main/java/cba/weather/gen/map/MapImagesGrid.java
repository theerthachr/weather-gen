package cba.weather.gen.map;

/**
 * These classes provide a mapping of the map grid images from
 *     https://visibleearth.nasa.gov/view.php?id=73934
 * to the maximum latitude and longitude represented in the image grid 
 *
 */

public abstract class MapImagesGrid {
	abstract public double getStartLongitude();

	abstract public double getEndLongitude();

	abstract public double getStartLatitude();

	abstract public double getEndLatitude();

	/*
	 * Checks if a particular latitude - longitude belongs to the image grid  
	 */
	public boolean isGrid(double latitude, double longitude) {
		return (latitude >= getStartLatitude()) && (latitude <= getEndLatitude()) && (longitude >= getStartLongitude())
				&& (longitude <= getEndLongitude());
	}
	
	abstract public String getGridFileName();
}

class A1Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return -180;
	}

	public double getEndLongitude() {
		return -90;
	}

	public double getStartLatitude() {
		return 0;
	}

	public double getEndLatitude() {
		return 90;
	}

	public String getGridFileName() {
		return "A1.tif";
	}
}

class A2Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return -180;
	}

	public double getEndLongitude() {
		return -90;
	}

	public double getStartLatitude() {
		return -90;
	}

	public double getEndLatitude() {
		return 0;
	}

	public String getGridFileName() {
		return "A2.tif";
	}
}

class B1Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return -90;
	}

	public double getEndLongitude() {
		return 0;
	}

	public double getStartLatitude() {
		return 0;
	}

	public double getEndLatitude() {
		return 90;
	}

	public String getGridFileName() {
		return "B1.tif";
	}
}

class B2Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return -90;
	}

	public double getEndLongitude() {
		return 0;
	}

	public double getStartLatitude() {
		return -90;
	}

	public double getEndLatitude() {
		return 0;
	}

	public String getGridFileName() {
		return "B2.tif";
	}
}

class C1Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return 0;
	}

	public double getEndLongitude() {
		return 90;
	}

	public double getStartLatitude() {
		return 0;
	}

	public double getEndLatitude() {
		return 90;
	}

	public String getGridFileName() {
		return "C1.tif";
	}
}

class C2Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return 0;
	}

	public double getEndLongitude() {
		return 90;
	}

	public double getStartLatitude() {
		return -90;
	}

	public double getEndLatitude() {
		return 0;
	}

	public String getGridFileName() {
		return "C2.tif";
	}
}

class D1Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return 90;
	}

	public double getEndLongitude() {
		return 180;
	}

	public double getStartLatitude() {
		return 0;
	}

	public double getEndLatitude() {
		return 90;
	}

	public String getGridFileName() {
		return "D1.tif";
	}
}

class D2Grid extends MapImagesGrid {
	public double getStartLongitude() {
		return 90;
	}

	public double getEndLongitude() {
		return 180;
	}

	public double getStartLatitude() {
		return -90;
	}

	public double getEndLatitude() {
		return 0;
	}

	public String getGridFileName() {
		return "D2.tif";
	}
}