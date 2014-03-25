package app.fields;

import data.base.Field;
import data.fields.Group;
import data.fields.Text;

import java.util.ArrayList;

/**
 * fields.Answer class.
 *
 * The intention for this class is to represent any
 * additional fields such as "hint" or accessibility
 * fields such as "audio".
 *
 * Currently the Answer class is just a Text field,
 * therefore representing the actual answer text.
 */
public class Answer extends Group
{
    public Answer()
    {
        this(data.Utils.newId());
    }

    public Answer(String _id)
    {
        super(_id, getFields());
        setAnswerId(_id);
    }

    void setAnswerId(String id)
    {
        Text answerText = lookupField("id");
        answerText.setValue(id);

        setChanged();
        notifyObservers();
    }

    public void setAnswerText(String text)
    {
        Text answerText = lookupField("answerText");
        answerText.setValue(text);

        setChanged();
        notifyObservers();
    }

    public String getAnswerText()
    {
        Text answerText = lookupField("answerText");
        return answerText.getValue();
    }

    protected static ArrayList<Field> getFields()
    {
        ArrayList<Field> ret = new ArrayList<Field>();
        ret.add(data.Factory.makeField(Text.class, "id"));
        ret.add(data.Factory.makeField(Text.class, "answerText"));
        return ret;
    }

    public String toString()
    {
        return getAnswerText();
    }
}
