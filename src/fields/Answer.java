package fields;

import base.Field;

/**
 * fields.Answer class.
 *
 * The answer class consists of explicit declaration
 * of all possible fields an answer may have. Keep
 * in mind this is data definition layer not the
 * actual data a layer.
 *
 * The intention for this class is to represent any
 * additional fields such as "hint" or accessibility
 * fields such as "audio".
 *
 * It is important each answer has a unique id per
 * question. An id will help to have a consistent
 * set of possible answers and to be able to
 * represent them in the data layer. Data layer is
 * often transferred over the network so it is
 * important to represent each answer its id.
 */
class Answer extends Field
{
    Answer(String id, String answerText)
    {
        m_id = id;
        m_answerText = answerText;
    }

    /**
     * The id of the answer.
     */
    private String m_id;

    /**
     * The actual answer text visible to the user.
     */
    private String m_answerText;
}
