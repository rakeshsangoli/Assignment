package assignment.common;

public class Common {
	final static int variance=2;
	final static int humidity=10;
	public static boolean compareTemprature(float Temprature1, float Temprature2){
		int difference=(int) ((int) Temprature1-Temprature2);
		if(difference>variance)
			return false;
		return true;
	}
	public static boolean compareHumidity(int Humidity1, int Humidity2){
		int difference=Humidity1-Humidity2;
		if(difference>humidity)
			return false;
		return true;
	}
	
}
