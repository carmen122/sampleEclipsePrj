import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class GSonParseExample {
	
	public static void main(String [] args) {
		//String json = "{\"brand\" : \"Toyota\", \"doors\" : 5}";

		String inline = "";
		String deneme = "denemebirki";

		try {
			
			URL url = new URL("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=69c79776bf39010000db49d54b8a");
			//Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//Set the request to GET or POST as per the requirements
			conn.setRequestMethod("GET");
			//Use the connect method to create the connection bridge
			conn.connect();
			//Get the response status of the Rest API
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is: " +responsecode);
			
			//Iterating condition to if response code is not 200 then throw a runtime exception
			//else continue the actual process of getting the JSON data
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
				    BufferedReader in = new BufferedReader(
				    new InputStreamReader(url.openStream()));

				    String inputLine;
				    while ((inputLine = in.readLine()) != null)
				        inline += inputLine;
				    in.close();
			}
			
			JsonReader jsonReader = new JsonReader(new StringReader(inline));
			Gson gson = new GsonBuilder()
					  .setPrettyPrinting()
					  .create();
			
			
		    while(true){
		        JsonToken nextToken = jsonReader.peek();
		        System.out.println(nextToken);

		        if(JsonToken.BEGIN_OBJECT.equals(nextToken)){

		        	//System.out.println("---BEGIN_OBJECT---");
		            jsonReader.beginObject();

		        } else if(JsonToken.NAME.equals(nextToken)){

		        	//System.out.println("---NAME---");
		            String name  =  jsonReader.nextName();
		            System.out.println(name);

		        } else if(JsonToken.STRING.equals(nextToken)){

		        	//System.out.println("---STRING---");
		            String value =  jsonReader.nextString();
		            System.out.println(value);

		        } else if(JsonToken.NUMBER.equals(nextToken)){

		        	//System.out.println("---NUMBER---");
		        	double val = jsonReader.nextDouble();
		        
		        	System.out.println(val);
		        	
		        	Number num = new Number(val);
		        		        	 
		        	System.out.println(gson.toJson(num));
		        	
		        	System.out.println("JSON CREATED ***************");;
		        		
		        }else if(JsonToken.BEGIN_ARRAY.equals(nextToken)) {
		        	
		        	jsonReader.beginArray();
		        	readArray(jsonReader);
		        
		        }else if(JsonToken.END_ARRAY.equals(nextToken)) {
		        	
		            jsonReader.endArray();		            
		            
		        }else if(JsonToken.END_OBJECT.equals(nextToken)) {
		        	
		        	jsonReader.skipValue();
		        	
		        }else if(JsonToken.END_DOCUMENT.equals(nextToken)) {
		        	
		        	break;
		        }
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void readArray(JsonReader jsonReader) throws IOException {
		
		System.out.println("------------------ARRAY BEGIN-----------------");
		
		while(true) {
			JsonToken nextToken = jsonReader.peek();
	        System.out.println(nextToken);
	
	        if(JsonToken.BEGIN_OBJECT.equals(nextToken)){
	
	        	//System.out.println("---BEGIN_OBJECT---");
	            jsonReader.beginObject();
	
	        } else if(JsonToken.NAME.equals(nextToken)){
	
	        	//System.out.println("---NAME---");
	            String name  =  jsonReader.nextName();
	            System.out.println(name);
	
	        } else if(JsonToken.STRING.equals(nextToken)){
	
	        	//System.out.println("---STRING---");
	            String value =  jsonReader.nextString();
	            System.out.println(value);
	
	        } else if(JsonToken.NUMBER.equals(nextToken)){
	
	        	//System.out.println("---NUMBER---");
	        	double val = jsonReader.nextDouble();
	        	System.out.println(val);
	        		
	        }else if(JsonToken.BEGIN_ARRAY.equals(nextToken)) {
	        	
	        	jsonReader.beginArray();
	        	readArray(jsonReader);
	        
	        }else if(JsonToken.END_ARRAY.equals(nextToken)) {
	        	
	        	System.out.println("-------------------ARRAY END--------------------");
	            jsonReader.endArray();           
	            
	            return;
	            
	        }else if(JsonToken.END_OBJECT.equals(nextToken)) {
	        	
	        	jsonReader.skipValue();        	
	        	
	        }
		}
		
	}

}
