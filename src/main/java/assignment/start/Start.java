package assignment.start;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import assignment.pojo.TempratureInfo;
import assignment.ui.CityTemprature;

public class Start {

	public static void main(String[] args) throws IOException {
		HashMap<String, TempratureInfo> TempratureUI=CityTemprature.getTemprature();
		HashMap<String, TempratureInfo> TempratureAPI=assignment.api.CityTemprature.getTemprature(TempratureUI);
		System.out.println("UI--"+TempratureUI.size());
		System.out.println("API--"+TempratureAPI.size());
		System.out.println("***********************UI****************************");
		for (Entry<String, TempratureInfo> entry : TempratureUI.entrySet())  
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " +entry.getValue().getTempratureInDegree() +","+entry.getValue().getHumidity()); 
		System.out.println("***********************UI****************************");
		for (Entry<String, TempratureInfo> entry : TempratureAPI.entrySet())  
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " +entry.getValue().getTempratureInDegree() +","+entry.getValue().getHumidity()); 

		
	}
}
