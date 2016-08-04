# CXFDemo
一个关于CXF实现jax-ws规范的webservice
#CXF框架
Apache CXF=Celtix+Xfire.是一个开源的一个webservice，可以与spring无缝集成。支持soap1.1、1.2、RESTtful或者CORBA。



http://cxf.apache.org

##使用CXF实现jax-ws规范的webservice

服务端：

- 1、创建java工程，把cxf的jar包导入工程中
- 2、编写SEI，在SEI上添加@WebService注解
- 3、编写SEI实现类，需要实现SEI接口
- 4、发布服务

		4.1、创建一个JaxWsServerFactoryBean对象
		4.2、设置服务的发布地址，是一个httpurl
		4.3、设置SEI接口
		4.4、设置SEI实现类对象
		4.5、调用create方法发布服务。

-

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

客户端：
wsdl2java.bat或者wsimport自动生成客户端调用代码。需要配置java_home,cxf_home,在path中配置%CXF_HOME%\bin

Wsdl2java -d :指定java代码生成的路径
wsdl的url

    Wsdl2java -d . http://127.0.0.1:11111/weather?wsdl



- 1、使用cxf提供的工厂类调用
- 2、设置服务的url，服务端地址
- 3、设置SEI（portType)类型
- 4、调用create方法获得portType对象
- 5、调用服务端方法


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


###CXF发布rest服务

Rest是一种软件架构的风格。


服务端：

- 第一步：创建一个pojo，返回值的类型。需要在pojo上添加@XmlRootElement。
- 第二步：创建一个SEI。也就是一个接口。需要用到的注解
	- 1、@Path：标注请求url
	- 2、@GET、@POST、@PUT、@DELETE：标注操作的方法
	- 3、@Produce：指定返回结果的数据类型，xml或者json等。
- 第三步：创建SEI的实现类。可以不使用任何注解。
- 第四步：发布rest服务。
	- 1、使用JAXRsServerFactoryBean对象发服务。
	- 2、设置服务发布的地址。设置url
	- 3、设置SEI实现类对象。
	- 4、发布服务，create方法。



1、创建一个pojo



	private String info;
	private int maxTemp;
	private int minTemp;
	private Date date;

添加get\set方法，添加@XmlRootElement注解。

2、SEI
    
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


3、实现SEI的接口

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

4、发布服务

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

xml格式的数据：

http://127.0.0.1:11111/rest/weather/city/%E8%A1%A1%E9%98%B3?_type=xml



json格式的数据:

http://127.0.0.1:11111/rest/weather/city/%E8%A1%A1%E9%98%B3?_type=json
