package net.muroc.Exception;

/**
 * Created by Brendan on 12/16/2018.
 */

import net.muroc.dto.UserDTO;

import net.muroc.repository.UserJPARepository;

public class CustomErrorType extends UserDTO
{
    private String errorMessage;
    public CustomErrorType(final String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    //@Override
    public String getErrorMessage()
    {
        return errorMessage;
    }
}
