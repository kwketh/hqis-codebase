package app.documents;

import app.fields.Question;
import data.base.Field;
import data.fields.Date;
import data.fields.Group;
import data.fields.List;
import data.fields.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class PatientResult extends data.base.Document
{
    static final String typeName = "patient_result";

    /**
     * Constructor.
     */
    public PatientResult()
    {
        this(data.Utils.newId());
    }

    public PatientResult(String id)
    {
        super(id, typeName, getFields());
    }

    public void setQuestionnaireId(String id)
    {
        Text questionnaireIdField = lookupField("questionnaireId");
        questionnaireIdField.setValue(id);
    }

    public String getPatientName()
    {
        Text patientNameField = lookupField("patientName");
        return patientNameField.getValue();
    }

    public String getPatientDateOfBirth()
    {
        Date patientDateOfBirthField = lookupField("patientDateOfBirth");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(patientDateOfBirthField.getValue());
    }

    public void setPatientName(String patientName)
    {
        Text patientNameField = lookupField("patientName");
        patientNameField.setValue(patientName);
    }

    public void setPatientDateOfBirth(Integer day, Integer month, Integer year)
    {
        Date patientDateOfBirthField = lookupField("patientDateOfBirth");
        Calendar calendarTimeInstance = Calendar.getInstance();
        calendarTimeInstance.set(year, month, day, 0, 0, 0);
        patientDateOfBirthField.setValue(calendarTimeInstance.getTime());
    }

    public void setAnswer(String questionId, String answerId)
    {
        Group answers = lookupField("answers");
        Text existingAnswerId = answers.lookupField(questionId);
        if (existingAnswerId != null) {
            existingAnswerId.setValue(answerId);
        } else {
            Text newAnswerId = new Text(questionId);
            newAnswerId.setValue(answerId);
            answers.addField(newAnswerId);
        }
    }

    public String getAnswerId(String questionId)
    {
        Group answers = lookupField("answers");
        Text answerIdField = answers.lookupField(questionId);
        if (answerIdField != null)
            return answerIdField.getValue();
        return null;
    }

    static protected ArrayList<Field> getFields()
    {
        Group answers = new Group("answers", new ArrayList<Field>());
        ArrayList<Field> ret = data.base.Document.getFields();
        ret.add(answers);

        Text questionnaireId = new Text("questionnaireId");
        ret.add(questionnaireId);

        Text patientName = new Text("patientName");
        ret.add(patientName);

        Date patientDateOfBirth = new Date("patientDateOfBirth");
        ret.add(patientDateOfBirth);

        return ret;
    }

    public String toString()
    {
        return getPatientName() + " (date of birth: " + getPatientDateOfBirth() + ")";
    }
}
