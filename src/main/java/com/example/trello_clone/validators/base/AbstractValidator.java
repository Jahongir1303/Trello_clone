package com.example.trello_clone.validators.base;



import com.example.trello_clone.dto.base.BaseGenericDto;
import com.example.trello_clone.dto.base.GenericDto;
import com.example.trello_clone.exceptions.ValidationException;

import java.io.Serializable;

public abstract class AbstractValidator<
        CD extends BaseGenericDto,
        UD extends GenericDto,
        K extends Serializable> implements BaseGenericValidator {


    public abstract void validateKey(K id) throws ValidationException;

    public abstract void validOnCreate(CD dto) throws ValidationException;

    public abstract void validOnUpdate(UD dto) throws ValidationException;

}
