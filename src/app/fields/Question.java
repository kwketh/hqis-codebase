package app.fields;

import data.base.Field;
import data.fields.Group;
import data.fields.List;
import data.fields.Text;

import java.util.ArrayList;


/**
 * fields.Question class.
 *
 * The class is a Group to describe a group of fields.
 *
 * The class intention is to represent any additional
 * fields such as "hint" or accessibility fields such
 * as "audio".
 *
 * @see data.fields.Group
 */
public class Question extends Group
{
    public Question()
    {
        this(data.Utils.newId());
    }

    public Question(String id)
    {
        super(id, getFields());
    }

    public Question(String id, String questionText)
    {
        super(id, getFields());
        setQuestionText(questionText);
    }

    public void setQuestionText(String questionText)
    {
        Text questionTextField = lookupField("question");
        questionTextField.setValue(questionText);

        setChanged();
        notifyObservers();
    }

    public String getQuestionText()
    {
        Text questionTextField = lookupField("question");
        return questionTextField.getValue();
    }

    protected static ArrayList<Field> getFields()
    {
        ArrayList<Field> ret = new ArrayList<Field>();
        ret.add(new data.fields.Text("id"));
        ret.add(new data.fields.Text("question"));
        ret.add(new data.fields.List<Answer>("answers", Answer.class));
        return ret;
    }

    public void addAnswer(Answer answer)
    {
        List<Answer> answers = lookupField("answers");
        answers.add(answer);
    }

    public void addAnswer(String answerId, String answerText)
    {
        Answer answer = new Answer(answerId);
        answer.setAnswerText(answerText);
        addAnswer(answer);
    }

    public String toString()
    {
        if (getQuestionText() == null || getQuestionText().isEmpty())
            return " ";
        else
            return getQuestionText();
    }
}
