import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.quantumoctopus.magiceatball.QuizManager;
import com.quantumoctopus.magiceatball.Result;
import com.quantumoctopus.magiceatball.ResultsWorker;
import com.quantumoctopus.magiceatball.YelpURLSigner;

public class QuizTester {

	 @Test
	public void testQuizURLBuilder() {
		QuizManager q = new QuizManager();
		q.setLl("34.406113,-119.697332");
		q.setCategory_filter("burgers");
		q.setRadius_filter("2000");
		q.setSort("1");
		q.setSearchParameters();
		String URL = new String();
		URL = q.getURL();
		System.out.println(URL);

		String signedURL = YelpURLSigner.getURLString(URL);

		JsonArray jsa = ResultsWorker.getJsonArrayOfBusinesses(signedURL);
		String numResults = null;
		numResults = ResultsWorker.getNumberOfResults(signedURL);
		System.out.println(numResults);

		Vector<Result> results = new Vector<Result>();
		for (int i = 0; i < jsa.size(); i++) {
			Result r = ResultsWorker.buildResult(jsa.get(i));
			System.out.println(r.getName());
			results.add(r);
		}

		Assert.assertNotNull(results);
	}

	// @Test
	public void testRandom() {

		QuizManager q = new QuizManager();
		q.setLl("34.406113,-119.697332");
		Result r = q.getRandomResult();
		System.out.println(r.getName());

	}
}
