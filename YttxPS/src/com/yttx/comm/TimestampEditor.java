package com.yttx.comm;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class TimestampEditor extends PropertyEditorSupport {  
  
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");  
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
  
    private DateFormat dateFormat;  
    private boolean allowEmpty = true;  
  
    public TimestampEditor() {
    }  
  
    public TimestampEditor(DateFormat dateFormat) {  
        this.dateFormat = dateFormat;  
    }  
  
    public TimestampEditor(DateFormat dateFormat, boolean allowEmpty) {  
        this.dateFormat = dateFormat;  
        this.allowEmpty = allowEmpty;  
    }  
  
    /** 
     * Parse the Date from the given text, using the specified DateFormat. 
     */  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (this.allowEmpty && !StringUtils.hasText(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        } else {  
            try {  
                if(this.dateFormat != null)  
                    setValue(new Timestamp(this.dateFormat.parse(text).getTime()));  
                else {  
                    if(text.contains(":"))  
                        setValue(new Timestamp(TIMEFORMAT.parse(text).getTime()));  
                    else  
                        setValue(new Timestamp(DATEFORMAT.parse(text).getTime()));  
                }  
            } catch (ParseException ex) {  
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);  
            }  
        }  
    }  
  
    /** 
     * Format the Date as String, using the specified DateFormat. 
     */  
    @Override  
    public String getAsText() {  
        Date value = (Date) getValue();  
        DateFormat dateFormat = this.dateFormat;  
        if(dateFormat == null)  
            dateFormat = TIMEFORMAT;  
        return (value != null ? dateFormat.format(value) : "");  
    }  
}  