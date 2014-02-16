package base;

import java.util.ArrayList;

/**
 * base.Document class.
 *
 * The intention for this class is to be able to
 * both load and store data that is stored on the
 * HQIS server application.
 *
 * A document explicitly declares a set of fields
 * it consists of and that should be stored on the
 * server. Since there may be different types of
 * document, each document must also explicitly
 * set its type in the constructor.
 */
abstract public class Document
{
    /**
     * The document id.
     */
    private String m_id;

    /**
     * The document type.
     */
    private String m_type;

    /**
     * The document name.
     */
    private String m_name;

    /**
     * List of fields the document contains.
     */
    protected ArrayList<Field> m_fields;

    /**
     * Constructor.
     *
     * @param id
     *   the document id
     *
     * @param type
     *   the document type
     */
    protected Document(String id, String type)
    {
        m_id = id;
        m_type = type;
    }

    /**
     * Returns document type.
     *
     * @return {string}
     *   type of the document
     */
    String getType()
    {
        return m_type;
    }
}
