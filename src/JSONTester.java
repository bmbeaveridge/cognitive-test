import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
			ResultsWorker rw = new ResultsWorker();
			Result r = rw.buildResult(jsa.get(i));

			Assert.assertNotNull(r);
		}
	}

	@Test
	public void testNumResults() {
		String testURL = "http://api.yelp.com/v2/search?term=mexican+restaurant,food&location=93101&radius_filter=10000";
		String signedURL = YelpURLSigner.getURLString(testURL);
		String numResults = null;
		numResults = ResultsWorker.getNumberOfResults(signedURL);
		System.out.println(numResults);
		Assert.assertNotNull(numResults);
	}
}
