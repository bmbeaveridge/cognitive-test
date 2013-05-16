import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.quantumoctopus.magiceatball.JSONWorker;
import com.quantumoctopus.magiceatball.Result;
import com.quantumoctopus.magiceatball.ResultsWorker;
import com.quantumoctopus.magiceatball.YelpURLSigner;

public class JSONTester {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testResultBuilder() {
		String testURL = "http://api.yelp.com/v2/search?term=mexican+restaurant,food&location=93101&radius_filter=10000";
		String signedURL = YelpURLSigner.getURLString(testURL);

		JsonArray jsa = ResultsWorker.getJsonArrayOfBusinesses(signedURL);

		for (int i = 0; i < jsa.size(); i++) {
			System.out.println(jsa.get(i).toString());
			Result r = ResultsWorker.buildResult(jsa.get(i));
			Assert.assertNotNull(r);
		}
	}

	@Test
	public void testNumResults() {
		String testURL = "http://api.yelp.com/v2/search?term=restaurant,food&ll=34.425958581828425,-119.88632478962093&sort=1&radius_filter=2000";
		String signedURL = YelpURLSigner.getURLString(testURL);
		String numResults = null;
		numResults = ResultsWorker.getNumberOfResults(signedURL);
		System.out.println(numResults);
		Assert.assertNotNull(numResults);
	}

	@Test
	public void testAppendJsonArray() {
		JsonArray tempjsa = new JsonArray();
		String testURL = "http://api.yelp.com/v2/search?term=mexican+restaurant,food&location=93101&radius_filter=10000";
		String signedURL = YelpURLSigner.getURLString(testURL);

		JsonArray jsa = ResultsWorker.getJsonArrayOfBusinesses(signedURL);
		JsonObject jso = new JsonObject();
		for (int i = 0; i < jsa.size(); i++) {
			Result r = ResultsWorker.buildResult(jsa.get(i));
			tempjsa = addToLog(tempjsa, r);
		}

		jso.add("log", tempjsa);
		System.out.println(jso.toString());
	}
	
	@Test
	public void testGetResultByID(){
		String id = "el-sitio-santa-barbara";
		Result r = ResultsWorker.getResultByID(id);
		System.out.println(r.getUrl());
		System.out.println(r.getName());
	}

	public JsonArray addToLog(JsonArray jsa, Result r) {
		JsonObject jso = new JsonObject();
		jso.addProperty("name", r.getName());
		jso.addProperty("id", r.getId());
		jsa.add(jso);
		return jsa;
	}
}
