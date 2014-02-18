package tests;

import app.documents.Questionnaire;
import junit.framework.TestCase;
import java.io.StringWriter;
import org.json.JSONException;
import org.json.JSONWriter;

public class QuestionnaireTest extends TestCase
{
    /**
     * Test if questionnaire can be serialised.
     */
    public void testQuestionnaire()
    {
        Questionnaire questionnaire = new Questionnaire("sample1");
        questionnaire.setName("A sample questionnaire");

        String json = data.Utils.serialiseField(questionnaire);

        System.out.println(json);
    }
}
