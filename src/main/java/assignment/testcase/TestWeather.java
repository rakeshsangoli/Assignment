package assignment.testcase;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import assignment.common.Common;
import assignment.pojo.TempratureInfo;
import assignment.ui.CityTemprature;

public class TestWeather {
	static HashMap<String, TempratureInfo> TempratureUI=null;
	static HashMap<String, TempratureInfo> TempratureAPI=null;

	@BeforeClass
	public static void loadCityWeatherInformation() throws IOException{
		TempratureUI=CityTemprature.getTemprature();
		TempratureAPI=assignment.api.CityTemprature.getTemprature(TempratureUI);
	}
	
	@Test
	public static void testWeatherReport(){
		String city="Bengaluru";
		TempratureInfo WeatherInfoUI=TempratureUI.get(city);
		TempratureInfo WeatherInfoAPI=TempratureAPI.get(city);
		System.out.println("UI----");
		System.out.println(WeatherInfoUI.getHumidity());
		System.out.println(WeatherInfoUI.getTempratureInDegree());
		System.out.println("API----");
		System.out.println(WeatherInfoAPI.getHumidity());
		System.out.println(WeatherInfoAPI.getTempratureInDegree());
		if((Common.compareTemprature(Float.parseFloat(WeatherInfoUI.getTempratureInDegree()), 
									Float.parseFloat(WeatherInfoAPI.getTempratureInDegree())))
				|| (Common.compareHumidity(Integer.parseInt(WeatherInfoAPI.getHumidity()), 
										  Integer.parseInt(WeatherInfoUI.getHumidity())))
		  ){
			System.out.println("Passed");
		}else{
			System.out.println("Failed");
			Assert.fail("Did not met expected values");
		}
			
	}
	public static void main(String[] args) throws IOException {
		TestWeather.loadCityWeatherInformation();
		TestWeather.testWeatherReport();
	}
}
