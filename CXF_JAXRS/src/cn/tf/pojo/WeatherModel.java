package cn.tf.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WeatherModel {
	
	private String info;
	private int maxTemp;
	private int minTemp;
	private Date date;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}
	public int getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String toString() {
		return "WeatherModel [info=" + info + ", maxTemp=" + maxTemp
				+ ", minTemp=" + minTemp + ", date=" + date + "]";
	}
	public WeatherModel(String info, int maxTemp, int minTemp, Date date) {
		super();
		this.info = info;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
		this.date = date;
	}
	public WeatherModel() {
		super();
	}
	
	
	

}

