package cn.tf.cxf;

public class WeatherInterfaceImpl implements WeatherInterface{

	public String queryWeather(String cityName) {
		
		System.out.println("城市名称:"+cityName);
		String result="晴";
		return result;
	}

}
