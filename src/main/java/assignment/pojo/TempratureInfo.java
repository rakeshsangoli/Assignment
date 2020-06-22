package assignment.pojo;

public class TempratureInfo {
	private String humidity;
	private String tempratureInDegree;
	
	public TempratureInfo(String humidity, String tempratureInDegree){
		this.humidity=humidity;
		this.tempratureInDegree= tempratureInDegree;
	}
	
	public String getHumidity(){
		return this.humidity;
	}
	public String getTempratureInDegree(){
		return this.tempratureInDegree;
	}
	
	
}
