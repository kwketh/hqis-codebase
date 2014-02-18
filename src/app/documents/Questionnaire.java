package app.documents;

import app.fields.Question;
import data.fields.List;

/**
 * documents.Questionnaire class.
 *
 * The questionnaire class extends Document to allow
 * allow both loading and storing the questionnaires
 * on the HQIS server.
 */
public class Questionnaire extends data.base.Document
{
    List<Question> questions;

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

        questions = new List<Question>("questions");
        addField(questions);

        Question q1 = new Question("sex", "Are you a male or female?");
        q1.addAnswer("male", "Male");
        q1.addAnswer("female", "Female");
        questions.add(q1);

        Question q2 = new Question("age1630", "Are you aged between 16 and 30?");
        q2.addAnswer("yes", "Yes");
        q2.addAnswer("no", "No");
        questions.add(q2);

        Question q3 = new Question("prefferedEngine", "What is your preferred search engine?");
        q3.addAnswer("altavista", "Altavista");
        q3.addAnswer("dogpile", "Dogpile");
        q3.addAnswer("google", "Google");
        q3.addAnswer("yahoo", "yahoo");
        questions.add(q3);
    }

}
