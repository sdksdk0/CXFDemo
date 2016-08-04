package cn.tf.cxf;

import javax.jws.WebService;

@WebService
public interface  WeatherInterface {
	
	String queryWeather(String cityName);

}
