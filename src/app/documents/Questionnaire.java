package app.documents;

import app.fields.Answer;
import app.fields.Question;
import data.base.Field;
import data.fields.List;

import java.util.ArrayList;
import java.util.Observable;

/**
 * documents.Questionnaire class.
 *
 * The questionnaire class extends Document to allow
 * allow both loading and storing the questionnaires
 * on the HQIS server.
 */
public class Questionnaire extends data.base.Document
{
    public static final String typeName = "questionnaire";

    /**
     * Constructor.
     */
    public Questionnaire()
    {
        this(data.Utils.newId());
    }

    /**
     * Constructor.
     *
     * @param id
     *   the questionnaire id
     */
    public Questionnaire(String id)
    {
        super(id, typeName, getFields());
    }

    public void addQuestion(Question question)
    {
        List<Question> questions = lookupField("questions");
        questions.add(question);
    }

    public String getQuestionText(String questionId)
    {
        List<Question> questions = lookupField("questions");
        return questions.getById(questionId).getQuestionText();
    }

    public String getAnswerText(String questionId, String answerId)
    {
        List<Question> questions = lookupField("questions");
        Question question = questions.getById(questionId);
        List<Answer> answers = question.lookupField("answers");
        return answers.getById(answerId).getAnswerText();
    }

    static protected ArrayList<Field> getFields()
    {
        List<Question> questions = new List<Question>("questions", Question.class);
        ArrayList<Field> ret = data.base.Document.getFields();
        ret.add(questions);
        return ret;
    }

    public String toString()
    {
        if (getName() == null || getName().isEmpty())
            return "Untitled questionnaire (last modified " + getDate() + ")";
        else
            return getName() + " (last modified " + getDate() + ")";
    }
}
