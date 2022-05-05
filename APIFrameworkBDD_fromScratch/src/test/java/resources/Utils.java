package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Utils {

	//Making public static - so that this variable is shared across all the tests during the execution
	public static RequestSpecification reqSpec;
	
	public RequestSpecification requestSpecification () throws IOException {

		//Adding a condition so that the logging file doesnot overwrite the logs
		if(reqSpec==null) {
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseURL")).
				addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		return reqSpec;
		}
		return reqSpec;
		}

	public static String getGlobalValues(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\OM\\Desktop\\Edureka\\APIFrameworkBDD_fromScratch\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);

		return  prop.getProperty(key);


	}
	
	public String getJSONPath(Response response , String key ) {
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		
		return js.get(key).toString();
	}
	
	
	
}
