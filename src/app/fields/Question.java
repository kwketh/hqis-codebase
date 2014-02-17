package app.fields;

import core.fields.Group;
import core.fields.Text;

/**
 * fields.Question class.
 *
 * The class is a Group to describe a group of fields.
 *
 * The class intention is to represent any additional
 * fields such as "hint" or accessibility fields such
 * as "audio".
 *
 * @see core.fields.Group
 */
public class Question extends Group
{
    /**
     * The actual question text visible to the user.
     */
    private core.fields.Text m_question;

    /**
     * A list of answers the question has.
     */
    private core.fields.List<Answer> m_answers;

    public Question(String id, String questionText)
    {
        super(id);
        m_question.setValue(questionText);
    }

    @Override
    protected void setupFields()
    {
        m_question = new core.fields.Text("question");
        addField(m_question);

        m_answers = new core.fields.List<Answer>("answers");
        addField(m_answers);
    }

    public void addAnswer(Answer answer)
    {
        m_answers.add(answer);
    }

    public void addAnswer(String answerId, String answerText)
    {
        m_answers.add(new Answer(answerId, answerText));
    }
}
