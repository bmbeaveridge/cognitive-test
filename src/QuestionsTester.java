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
		try {
			questionJsonString = JSONWorker.getJSONString("https://magic-eat-ball.googlecode.com/hg/questions.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Question q;
		int i=0;
		JsonElement jse = JSONWorker.getJSONElementFromString(questionJsonString);
		JsonElement tempjse = jse.getAsJsonObject().get("questions").getAsJsonObject().get("category").getAsJsonArray().get(i);
		q = QuestionBuilder.getQuestion(tempjse);
		
		System.out.println(q.getText());
		System.out.println(q.getCount());
		System.out.println(q.getAnswer(i));
		System.out.println(q.getAnswerEffect(q.getAnswer(i)));
		Assert.assertEquals("\"Barbeque?\"", q.getText());
	}

}
