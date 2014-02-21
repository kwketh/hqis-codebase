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
    public Question(String _id, String _questionText)
    {
        super(_id, getFields());

        Text id = lookupField("id");
        id.setValue(_id);

        Text questionText = lookupField("question");
        questionText.setValue(_questionText);
    }

    protected static ArrayList<Field> getFields()
    {
        ArrayList<Field> ret = new ArrayList<Field>();
        ret.add(new data.fields.Text("id"));
        ret.add(new data.fields.Text("question"));
        ret.add(new data.fields.List<Answer>("answers"));
        return ret;
    }

    public void addAnswer(Answer answer)
    {
        List<Answer> answers = lookupField("answers");
        answers.add(answer);
    }

    public void addAnswer(String answerId, String answerText)
    {
        addAnswer(new Answer(answerId, answerText));
    }
}
