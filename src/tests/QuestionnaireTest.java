package tests;

import app.documents.Questionnaire;
import junit.framework.TestCase;

import java.io.IOException;

public class QuestionnaireTest extends TestCase
{
    /**
     * Test if questionnaire can be serialised to JSON format.
     */
    public void testSerialiseQuestionnaire()
    {
        Questionnaire questionnaire = new Questionnaire("sample1");
        questionnaire.setName("A sample questionnaire");

        String json = data.Utils.serialiseField(questionnaire);

        assertEquals(questionnaire.getId(), "sample1");
        assertEquals(questionnaire.getName(), "A sample questionnaire");

        System.out.println(json);
    }

}
