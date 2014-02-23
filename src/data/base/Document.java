package data.base;

import data.fields.Group;
import data.fields.Text;

import java.util.ArrayList;

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
     * Constructor.
     *
     * @param _id
     *   the document id
     *
     * @param _type
     *   the document type
     */
    protected Document(String _id, String _type, ArrayList<Field> _fields)
    {
        super(_id, _fields);

        Text id = lookupField("id");
        id.setValue(_id);

        Text type = lookupField("type");
        type.setValue(_type);
    }

    static protected ArrayList<Field> getFields()
    {
        ArrayList<Field> ret = new ArrayList<Field>();
        ret.add(new Text("id"));
        ret.add(new Text("type"));
        ret.add(new Text("name"));
        return ret;
    }

    /**
     * Returns document name.
     *
     * @return {string}
     *   the document name
     */
    public String getName()
    {
        Text name = lookupField("name");
        return name.getValue();
    }

    /**
     * Sets the document name.
     *
     * @param _name
     *   the new document name
     */
    public void setName(String _name)
    {
        Text name = lookupField("name");
        name.setValue(_name);
    }

}
