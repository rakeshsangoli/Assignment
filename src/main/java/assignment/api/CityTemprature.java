package assignment.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.vocera.abx.configuration.ConfigOperation;

import assignment.pojo.TempratureInfo;
import assignment.readjson.ParseJsonResponse;
import net.minidev.json.JSONObject;
/**
 * Read the city temprature from the rest end point
 * @author rsangoli
 *
 */
public class CityTemprature {
	/**
	 * Convert temprature from 
	 * @param Temp
	 * @return
	 */
	private static HashMap<String, TempratureInfo> cityTempratureAPI = 
            new HashMap<String, TempratureInfo>();
	
	public static String TempInDegree(String Temp){
		return String.valueOf((float) (Float.parseFloat(Temp)-273.15));
	}
	public static synchronized HashMap<String, TempratureInfo> getTemprature(HashMap<String, TempratureInfo> cityTempratureUI) throws IOException{
		if(cityTempratureAPI.size()>0)
			return cityTempratureAPI;
		Map properties=ConfigOperation.getProperties();
		//Read temprature from API
		Map<String,String> param = new HashMap();	
		String URI=(String) properties.get("restendpoint");
		String Header="Accept:application/json,Content-type:application/json";
		String Body="";
		param.put("header", Header);
		param.put("body",Body);
		//Create Get Rest object
		for (String name : cityTempratureUI.keySet()){
			try {
				param.put("uri", URI.replace("@city", name.replace(" ", "%20")));
				Get get=new Get(param);
				//Execute get request
				get.execute();
				//Get response of request
				String Response=get.getResponse();
//				ParseJsonResponse jsonExp= new ParseJsonResponse();
				System.out.println(Response);
				String Temprature=(ParseJsonResponse.parseJson(Response, "$.main.temp")).toString();
				String Humidity=ParseJsonResponse.parseJson(Response, "$.main.humidity");
				cityTempratureAPI.put(ParseJsonResponse.parseJson(Response, "$.name"), 
						new TempratureInfo(Humidity, TempInDegree(Temprature)));
			} catch (Exception e) {
				System.out.println("Exception occured on API call, Exception="+e);
			}
		}
		return cityTempratureAPI;
	}
	public static void main(String[] args) throws IOException {
		Map properties=ConfigOperation.getProperties();
		//Prepare get parameter
		Map<String,String> param = new HashMap();	
		String URI=(String) properties.get("restendpoint");
		String Header="Accept:application/json,Content-type:application/json";
		String Body="";
		param.put("uri", URI.replace("@city", "Bengaluru"));
		param.put("header", Header);
		param.put("body",Body);
		//Create Get Rest object
		Get get=new Get(param);
		//Execute get request
		get.execute();
		//Get response of request
		String Response=get.getResponse();
//		ParseJsonResponse jsonExp= new ParseJsonResponse();
		String Temprature=(ParseJsonResponse.parseJson(Response, "$.main.temp")).toString();
		System.out.println(TempInDegree(Temprature));;
		String Humidity=ParseJsonResponse.parseJson(Response, "$.main.humidity");
			
		System.out.println(Response);
	}
}
