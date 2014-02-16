package fields;

import base.Field;
import java.util.ArrayList;

/**
 * fields.Question class.
 *
 * The question class consists of explicit declaration
 * of all possible fields an answer may have. Keep
 * in mind this is data definition layer not the
 * actual data a layer.
 *
 * The intention for this class is to represent any
 * additional fields such as "hint" or accessibility
 * fields such as "audio".
 *
 * It is important each question has a unique id per
 * questionnaire. An id will help to have a consistent
 * set of possible questions and to be able to represent
 * them in the data layer. Data layer is often
 * transferred over the network so it is important to
 * represent each answer its id.
 */
public class Question extends Field
{
    /**
     * The id of the question.
     */
    private String m_id;

    /**
     * The actual question text visible to the user.
     */
    private String m_questionText;

    /**
     * An array of answers the question has.
     */
    private ArrayList<Answer> m_answers;

    public Question(String id, String questionText)
    {
        m_id = id;
        m_questionText = questionText;
    }

    public void addAnswer(Answer answer)
    {
        m_answers.add(answer);
    }
}
