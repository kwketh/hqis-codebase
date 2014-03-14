package tests;

import app.documents.Questionnaire;
import app.fields.Question;
import data.loaders.DocumentLoader;
import junit.framework.TestCase;

import java.io.IOException;

public class DocumentSerialisationTest extends TestCase
{
    public void testQuestionnaireSerialisation()
    {
        Questionnaire questionnaire = new Questionnaire("basicquestionnaire");
        questionnaire.setName("Basic questionnaire");

        Question q1 = new Question("sex", "Are you a male or female?");
        q1.addAnswer("male", "Male");
        q1.addAnswer("female", "Female");
        questionnaire.addQuestion(q1);

        Question q2 = new Question("age1630", "Are you aged between 16 and 30?");
        q2.addAnswer("yes", "Yes");
        q2.addAnswer("no", "No");
        questionnaire.addQuestion(q2);

        Question q3 = new Question("prefferedEngine", "What is your preferred search engine?");
        q3.addAnswer("altavista", "Altavista");
        q3.addAnswer("dogpile", "Dogpile");
        q3.addAnswer("google", "Google");
        q3.addAnswer("yahoo", "yahoo");
        questionnaire.addQuestion(q3);

        String json = data.Utils.serialiseField(questionnaire);

        DocumentLoader loader = new DocumentLoader();
        loader.loadFromJson(json);

        Questionnaire questionnaireCopy = loader.constructDocument();

        String jsonCopy = data.Utils.serialiseField(questionnaireCopy);

        assertEquals("deserialising and serialising gives exact same json", json, jsonCopy);
    }
}
