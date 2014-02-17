package app.documents;

import core.base.Document;
import app.fields.Question;

/**
 * documents.Questionnaire class.
 *
 * The questionnaire class extends Document to allow
 * allow both loading and storing the questionnaires
 * on the HQIS server.
 */
public class Questionnaire extends core.base.Document
{
    Question q1, q2, q3;

    /**
     * Constructor.
     *
     * @param id
     *   the questionnaire id
     */
    public Questionnaire(String id)
    {
        super(id, "questionnaire");
    }

    protected void setupFields()
    {
        super.setupFields();

        q1 = new Question("sex", "Are you a male or female?");
        q1.addAnswer("male", "Male");
        q1.addAnswer("female", "Female");
        addField(q1);

        q2 = new Question("age1630", "Are you aged between 16 and 30?");
        q2.addAnswer("yes", "Yes");
        q2.addAnswer("no", "No");
        addField(q2);

        q3 = new Question("marketingField", "What is your preferred search engine?");
        q3.addAnswer("altavista", "Altavista");
        q3.addAnswer("dogpile", "Dogpile");
        q3.addAnswer("google", "Google");
        q3.addAnswer("yahoo", "yahoo");
        addField(q3);
    }

}
