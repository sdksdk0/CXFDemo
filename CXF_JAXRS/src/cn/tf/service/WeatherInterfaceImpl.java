package cn.tf.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.tf.pojo.WeatherModel;



public class WeatherInterfaceImpl implements WeatherInterface{

	@Override
	public List<WeatherModel> queryWeather(String cityName) {
		
		List<WeatherModel> weatherInfo = getWeatherInfo(cityName);
		
		return weatherInfo;
	}
	
	private List<WeatherModel> getWeatherInfo(String cityName) {
		List<WeatherModel> weatherList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		//第一天
		WeatherModel model1 = new WeatherModel();
		model1.setInfo("雷阵雨");
		model1.setMaxTemp(31);
		model1.setMinTemp(22);
		model1.setDate(calendar.getTime());
		weatherList.add(model1);
		//第二天
		WeatherModel model2 = new WeatherModel();
		model2.setInfo("多云");
		model2.setMaxTemp(33);
		model2.setMinTemp(25);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
		model2.setDate(calendar.getTime());
		weatherList.add(model2);
		//第三天
		WeatherModel model3 = new WeatherModel();
		model3.setInfo("多云");
		model3.setMaxTemp(35);
		model3.setMinTemp(25);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
		model3.setDate(calendar.getTime());
		weatherList.add(model3);
		
		return weatherList;
	}

}
