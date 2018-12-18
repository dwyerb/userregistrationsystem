package net.muroc.Exception;

/**
 * Created by Brendan on 12/16/2018.
 */

import java.awt.TrayIcon.MessageType;

public class FieldValidationError {

    public enum MessageType
    {
        SUCCESS,INFO,WARNING,ERROR
    }
    private String field;
    private String message;
    private MessageType type;

    public String getField()
    {
        return this.field;
    }
    public void setFiled(String field)
    {
        this.field = field;
    }
    public String getMessage()
    {
        return this.message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public MessageType getType()
    {
        return this.type;
    }
    public void setType(MessageType type)
    {
        this.type = type;
    }

}
