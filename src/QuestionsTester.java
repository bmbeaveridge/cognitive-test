import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.quantumoctopus.magiceatball.JSONWorker;
import com.quantumoctopus.magiceatball.Question;
import com.quantumoctopus.magiceatball.QuestionBuilder;


public class QuestionsTester {

	@Test
	public void testQuestionBuilder() throws Exception {
		String questionJsonString = new String();
		questionJsonString = JSONWorker.readFile("questions.json");
		
		Question q;
		int i=0;
		JsonElement jse = JSONWorker.getJSONElementFromString(questionJsonString);
		JsonElement tempjse = jse.getAsJsonObject().get("questions").getAsJsonObject().get("category").getAsJsonArray().get(i);
		QuestionBuilder qb = new QuestionBuilder();
		q = qb.getQuestion(tempjse);
		
		System.out.println(q.getText());
		System.out.println(q.getCount());
		System.out.println(q.getAnswer(i));
		System.out.println(q.getAnswerEffect(q.getAnswer(i)));
		Assert.assertEquals("\"Barbeque?\"", q.getText());
	}

}
