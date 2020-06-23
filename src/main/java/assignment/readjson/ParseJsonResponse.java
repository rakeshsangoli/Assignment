package assignment.readjson;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
/**
 * 
 * @author rsangoli
 *
 */
public class ParseJsonResponse {
	
  public static String parseJson(String Response,String Expression){
	 try {
		 JsonPath path = JsonPath.compile(Expression); 
		 List<Object> books = path.read(Response);
		return books.toString().replace("\\", "");
	} catch (Exception e) {
		try {
			if(e.getMessage().contains("No results for path"))
				return "";
			JsonPath path = JsonPath.compile(Expression); 
			 Map<String,String> books = path.read(Response);
			 
			 Iterator it = books.entrySet().iterator();
			 String Json="{";
			 int i=0;
			 for (String key : books.keySet()) {
				 System.out.println(books.get(key));
				    String value = books.get(key).toString();
				    if((books.size()-1)==i)
				    	Json+="\""+key+"\""+":"+"\""+value+"\"";
				    else
				    	Json+="\""+key+"\""+":"+"\""+value+"\",";
				    i++;
				}
			 if((books.size())==i)
				 Json+="}";
			return Json;
		} catch (Exception e2) {
			try {
				JsonPath path = JsonPath.compile(Expression); 
				 String books = path.read(Response).toString();
				return books;
			} catch (Exception e3) {
				try {
					JsonPath path = JsonPath.compile(Expression); 
					 int books = path.read(Response);
					return String.valueOf(books);
				} catch (Exception e4) {
					try {
						JsonPath path = JsonPath.compile(Expression); 
						 Map<String,String> books = path.read(Response);
						 return books.toString();
					} catch (Exception e5) {
						try {
							JsonPath path = JsonPath.compile(Expression); 
							 boolean books = path.read(Response);
							 return String.valueOf(books);
						} catch (Exception e6) {
							return "";
						}
						
					}
				}
			}
		}
	}
	  
  }	
  public boolean isResultForPath(String Response,String Expression){
	  try {
			 JsonPath path = JsonPath.compile(Expression); 
			 path.read(Response);
			return true;
		} catch (Exception e) {
			if(e.getMessage().contains("No results for path"))
				return false;
			else
				return true;
		}
  }
  
}
