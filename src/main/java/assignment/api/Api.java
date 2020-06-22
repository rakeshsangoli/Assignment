package assignment.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

/**
 * This interface is used by all API requests classes
 * @author rsangoli
 *
 */
public interface Api {
public void execute() throws UnsupportedEncodingException, ClientProtocolException, IOException;
public String getResponse();
public int getStatusCode();
}
