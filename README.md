Weather Generator
------

This project will simulate weather conditions across the globe.

Assumptions
------

- The temperature at the Equator does not vary much through out the year, falls in the range 35 to 50 degree Celsius.
- The temperature at sea level decreases 3 degree Fahrenheit ever 300 mile from the equator either in the north or south direction
- The temperature decreases by 3 degree Fahrenheit every 1000 feet, moving above the sea level
- Atmospheric pressure always fall in the range 1000hPa to 1080hPa

Usage
----

Download the following images and the rename them as follows:

|Image Location|Rename To|
|--------------|---------|
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_A1_grey_geo.tif | A1.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_A2_grey_geo.tif | A2.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_B1_grey_geo.tif | B1.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_B2_grey_geo.tif | B2.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_C1_grey_geo.tif | C1.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_C2_grey_geo.tif | C2.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_D1_grey_geo.tif | D1.tif |
|http://eoimages.gsfc.nasa.gov/images/imagerecords/73000/73934/gebco_08_rev_elev_D2_grey_geo.tif | D2.tif |

Put all these renamed files into a directory.

This is a Maven project, the steps for setup can be found here:
https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

The `main` function can be found here cba.weather.gen.Driver class.

The code expects two runtime arguments:
- map: Folder path of the map images mentioned above
- dest: Folder path of the destination weather data file that is generated

References
----------

- https://en.wikipedia.org/wiki/Equator
- http://www.bobthealien.co.uk/earth/year.htm
- http://landterms.com/Articles_and_FAQ_s/Conservation_and_Ecology_Articles_and_FAQ_s/Latitude_Elevation_and_Temperature/The_Effects_of_Latitude_and_Elevation_on_Temperature_1206.html
- https://www.thoughtco.com/degree-of-latitude-and-longitude-distance-4070616
