package cn.tf.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import cn.tf.cxf.WeatherInterface;


//使用cxf工厂调用webservice
public class WeatherClient {
	
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
		factory.setAddress("http://127.0.0.1:11111/weather?wsdl");
		factory.setServiceClass(WeatherInterface.class);
		WeatherInterface portType=(WeatherInterface) factory.create();
		
		String result=portType.queryWeather("衡阳");
		System.out.println(result);
		
	}

}
