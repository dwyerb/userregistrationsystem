package net.muroc.Exception;

/**
 * Created by Brendan on 12/16/2018.
 */

import java.awt.TrayIcon.MessageType;

public class FieldValidationError {
    private String filed;
    private String message;
    private MessageType type;

    public String getFiled()
    {
        return this.filed;
    }
    public void setFiled(String filed)
    {
        this.filed = filed;
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
