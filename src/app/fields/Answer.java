package app.fields;

import core.base.Field;
import core.fields.Text;

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
class Answer extends Text
{
    Answer(String id)
    {
        super(id);
    }

    Answer(String id, String answerText)
    {
        super(id, answerText);
    }
}
