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
        Questionnaire questionnaire = new Questionnaire("doc1");
        String json = data.Utils.serialiseField(questionnaire);
        System.out.println(json);
    }
}
