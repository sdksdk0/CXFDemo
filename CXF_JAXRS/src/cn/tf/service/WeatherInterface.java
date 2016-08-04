package cn.tf.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.tf.pojo.WeatherModel;


//窄化请求映射
@Path("/weather")
public interface WeatherInterface {
	
	//方法的请求路径{}中的内容就是参数，许哟对应@PathParam注解参数
	@Path("/city/{cityName}")
	//请求的方法
	@GET
	//返回结果的数据类型，可以是xml也可以是json
	@Produces({MediaType.APPLICATION_JSON+";charset=utf-8", MediaType.APPLICATION_XML})
	List<WeatherModel> queryWeather(@PathParam(value="cityName")String cityName);

}
