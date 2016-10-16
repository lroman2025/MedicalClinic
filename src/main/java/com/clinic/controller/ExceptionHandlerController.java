package com.clinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clinic.exception.EmailAlreadyInUseException;
import com.clinic.exception.PeselAlreadyInUseException;


@ControllerAdvice
public class ExceptionHandlerController {

	@ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = EmailAlreadyInUseException.class)
    public String emailAlreadyInUseExceptionHandler(EmailAlreadyInUseException e) {
        return e.getMessage();
    }
	
	@ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = PeselAlreadyInUseException.class)
    public String peselAlreadyInUseExceptionHandler(PeselAlreadyInUseException e) {
        return e.getMessage();
    }
}
