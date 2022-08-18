package view;

import lib.XUtil;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLabelFormat extends JFormattedTextField.AbstractFormatter {
    private String datePattern = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return sdf.parseObject(text);
    }
    public String valueToString(Object value) throws ParseException {
        if(value != null) {
            Calendar c = (Calendar) value;
            return sdf.format(c.getTime());
        }
        return XUtil.convertDateToString(new Date());
    }
}
