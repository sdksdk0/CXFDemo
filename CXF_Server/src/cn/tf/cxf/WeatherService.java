package cn.tf.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class WeatherService {
	public static void main(String[] args) {
		//创建一个JaxWsServerFactoryBean对象
		JaxWsServerFactoryBean  factoryBean=new JaxWsServerFactoryBean();
		
		//4.2、设置服务的发布地址，是一个httpurl
		factoryBean.setAddress("http://127.0.0.1:11111/weather");
				
		//4.3、设置SEI接口
		factoryBean.setServiceClass(WeatherInterface.class);

		//4.4、设置SEI实现类对象
		factoryBean.setServiceBean(new WeatherInterfaceImpl());

		//4.5、调用create方法发布服务。
		factoryBean.create();
		
	}

}
