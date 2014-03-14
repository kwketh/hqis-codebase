package data.base;

import data.fields.Date;
import data.fields.Group;
import data.fields.Text;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
abstract public class Document extends Group implements Observer
{
    /**
     * Constructor.
     *
     * @param id
     *   the document id
     *
     * @param type
     *   the document type
     */
    protected Document(String id, String type, ArrayList<Field> fields)
    {
        super(id, fields);

        Text typeField = lookupField("type");
        typeField.setValue(type);
    }

    @Override
    public void addField(Field field)
    {
        super.addField(field);
        field.addObserver(this);
    }

    static protected ArrayList<Field> getFields()
    {
        ArrayList<Field> ret = new ArrayList<Field>();
        ret.add(data.Factory.makeField(Text.class, "id"));
        ret.add(data.Factory.makeField(Text.class, "type"));
        ret.add(data.Factory.makeField(Text.class, "name"));
        ret.add(data.Factory.makeField(Date.class, "date"));
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

    public String getType()
    {
        Text type = this.lookupField("type");
        return type.getValue();
    }

    /**
     * Sets the document name.
     *
     * @param name
     *   the new document name
     */
    public void setName(String name)
    {
        Text nameField = lookupField("name");
        nameField.setValue(name);
    }

    public java.util.Date getDate()
    {
        Date dateField = lookupField("date");
        return dateField.getValue();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Date dateField = lookupField("date");
        dateField.setValue(new java.util.Date());
    }
}
