package app.fields;

import data.fields.Group;

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
    /**
     * The actual question text visible to the user.
     */
    private data.fields.Text m_question;

    /**
     * A list of answers the question has.
     */
    private data.fields.List<Answer> m_answers;

    public Question(String id, String questionText)
    {
        super(id);
        m_question.setValue(questionText);
    }

    @Override
    protected void setupFields()
    {
        addField(new data.fields.Text("id", getId()));

        m_question = new data.fields.Text("question");
        addField(m_question);

        m_answers = new data.fields.List<Answer>("answers");
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
