package assignment.api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Class to execute the get request and get status code and response body
 * @author rsangoli
 *
 */
public class Get implements Api{
	private DefaultHttpClient httpClient=null;
	private HttpGet getRequest=null;
	private HttpResponse response=null;
	private Map<String,String> params = new HashMap();
	/**
	 * Initialize the parameters
	 * @param Params
	 */
	public Get(Map Params){
		super();
		this.params=Params;
		this.httpClient = new DefaultHttpClient();
	}
	/**
	 * Execute the get request
	 */
	@Override
	public void execute() {
		try {
			getRequest= new HttpGet(this.params.get("uri"));
			prepareHeader();
			response = httpClient.execute(getRequest);
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Prepare the headers
	 */
	private void prepareHeader(){
		String[] header=this.params.get("header").split(",");
		for (int i = 0; i < header.length; i++) {
			getRequest.setHeader(header[i].split(":")[0], header[i].split(":")[1]);
		}
	}
	/**
	 * Get the response body
	 */
	@Override
	public String getResponse() {
		BufferedReader br;
		try {
			br = new BufferedReader(
			new InputStreamReader((response.getEntity().getContent())));
			String Response="";
			String thisLine;
//			System.out.println(br.lines().);
			 while ((thisLine = br.readLine()) != null) {
//				System.out.println(br.readLine());
				Response+=thisLine;
			}
			return Response;
		} 
		catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Get the status code
	 */
	@Override
	public int getStatusCode() {
		return response.getStatusLine().getStatusCode();
	}

}
