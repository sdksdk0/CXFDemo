package cn.tf;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import cn.tf.service.WeatherInterfaceImpl;

public class WeatherServer {
	
	public static void main(String[] args) {
		//创建一个JAXRSServerFactoryBean对象
				JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
				//设置服务发布的地址
				factoryBean.setAddress("http://127.0.0.1:11111/rest");
				//设置实现类对象
				factoryBean.setServiceBean(new WeatherInterfaceImpl());
				//发布服务
				factoryBean.create();
	}

}
