package gui;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.swing.JFormattedTextField.AbstractFormatter;
 
/**
 * Klasa <code>DateLabelFormatter</code> nasledjuje AbstractFormatter.
 * Konvertuje iz objekta u string i obrnuto (radi sa stringom u obliku datuma)
 * @see AbstractFormatter
 * @see SimpleDateFormat
 *
 */

@SuppressWarnings("serial")
public class DateLabelFormatter extends AbstractFormatter {
 
   
	private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
     
    /**
     * Konvertuje string u tip {@code SimpleDateFormat}
     * @param text
     * @throws ParseException
     * @return SimpleDateFormat
     */
    
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
    
    /**
     * Formatira {@code Date} u date/time string
     * @param value
     * @throws ParseException
     * @return String
     */

 
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
         
        return "";
    }
 
}