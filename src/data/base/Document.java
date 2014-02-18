package data.base;

import data.fields.Group;
import data.fields.Text;

/**
 * base.Document class.
 *
 * The class intention is to be able to both load
 * from and store documents on the HQIS server.
 *
 * The class responsibility is to allow different
 * document types such as Questionnaire to derive
 * from this class, ensure consistency of all
 * documents as well as validate program
 * correctness.
 *
 * This class explicitly defines all the required
 * information such as meta data, the document
 * type as well as the actual fields.
 */
abstract public class Document extends Group
{
    /**
     * The document type.
     */
    private Text m_type;

    /**
     * The document name.
     */
    private Text m_name;

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
        super(id);
        m_type.setValue(type);
    }

    protected void setupFields()
    {
        addField(new Text("id", getId()));

        m_type = new Text("type");
        addField(m_type);

        m_name = new Text("name");
        addField(m_name);
    }

    /**
     * Returns document type.
     *
     * @return {string}
     *   type of the document
     */
    public String getType()
    {
        return m_type.getValue();
    }

    public void setName(String name)
    {
        m_name.setValue(name);
    }

}
