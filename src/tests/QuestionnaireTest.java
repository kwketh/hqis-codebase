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
        StringWriter json = new StringWriter();
        JSONWriter writer = new JSONWriter(json);
        try {
            questionnaire.toJSON(writer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(json);

    }
}
