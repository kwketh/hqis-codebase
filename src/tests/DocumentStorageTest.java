package tests;

import app.documents.Questionnaire;
import app.fields.Question;
import junit.framework.TestCase;

public class DocumentStorageTest extends TestCase
{
    public void testDocumentStorageSync()
    {
        /* Create basic questionnaire */

        Questionnaire questionnaire = new Questionnaire("basic");
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

        /* Add the questionnaire document to the document storage */
        data.storage.DocumentStorage.add(questionnaire);

        /* Sync the document storage both locally and remotely */
        data.storage.DocumentStorage.syncAll();
    }
}
