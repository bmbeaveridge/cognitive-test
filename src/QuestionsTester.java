import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
		QuestionBuilder qb = new QuestionBuilder();
		qb.loadQuestions(questionJsonString);
		ArrayList<Question> qlist = new ArrayList<Question>();
		qlist = qb.getArrayListOfQuestions("category");
		q=qlist.get(i);
		
		System.out.println(q.getText());
		System.out.println(q.getCount());
		System.out.println(q.getAnswer(i));
		System.out.println(q.getAnswerEffect(q.getAnswer(i)));
		Assert.assertEquals("Barbeque?", q.getText());
	}

}
