package test.cba.weather.gen.map;

public class MapUtil {
	public static MapImagesGrid getImageGrid(double latitude, double longitude) throws Exception {
		A1Grid a1Grid;
		A2Grid a2Grid;
		B1Grid b1Grid;
		B2Grid b2Grid;
		C1Grid c1Grid;
		C2Grid c2Grid;
		D1Grid d1Grid;
		D2Grid d2Grid;
		
		if((a1Grid = new A1Grid()).isGrid(latitude, longitude)) 
			return a1Grid;
		else if((a2Grid = new A2Grid()).isGrid(latitude, longitude))
			return a2Grid;
		else if((b1Grid = new B1Grid()).isGrid(latitude, longitude))
			return b1Grid;
		else if((b2Grid = new B2Grid()).isGrid(latitude, longitude))
			return b2Grid;
		else if((c1Grid = new C1Grid()).isGrid(latitude, longitude))
			return c1Grid;
		else if((c2Grid = new C2Grid()).isGrid(latitude, longitude))
			return c2Grid;
		else if((d1Grid = new D1Grid()).isGrid(latitude, longitude))
			return d1Grid;
		else if((d2Grid = new D2Grid()).isGrid(latitude, longitude))
			return d2Grid;
		else
			throw new Exception("Invalid latitude or longitude" + latitude + ":" + longitude );
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getImageGrid(-88.3455, 80.7888));
	}
}

