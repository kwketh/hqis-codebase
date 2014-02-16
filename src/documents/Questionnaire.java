package documents;

import base.Document;
import base.Field;

import java.util.ArrayList;

/**
 * documents.Questionnaire class.
 *
 * The questionnaire class extends Document to allow
 * allow both loading and storing the questionnaires
 * on the HQIS server.
 */
class Questionnaire extends Document
{
    final protected ArrayList<Field> m_fields = new ArrayList<Field>() {{
        add(new fields.Question("name", "Enter your name"));
        add(new fields.Question("surname", "Enter your surname"));
        add(new fields.Question("age", "Enter your age"));
    }};

    /**
     * Constructor.
     *
     * @param id
     *   the questionnaire id
     */
    Questionnaire(String id)
    {
        super(id, "questionnaire");
    }


}
