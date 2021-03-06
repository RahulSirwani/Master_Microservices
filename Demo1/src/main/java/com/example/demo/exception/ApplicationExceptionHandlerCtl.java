package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ExceptionResponce;

/**
 * Handles application propagated exceptions
 * 
 * @author DELL
 *
 *A convenient base class for @ControllerAdvice classesthat wish to provide 
 *centralized exception handling across all @RequestMapping methods through 
 *@ExceptionHandler methods. 
 *This base class provides an @ExceptionHandler method for handlinginternal 
 *Spring MVC exceptions. This method returns a ResponseEntityfor writing to 
 *the response with a message converter,in contrast to 
 *DefaultHandlerExceptionResolver which returns a ModelAndView. 
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandlerCtl extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity handleRuntimeException(RuntimeException e) {
		
		ExceptionResponce responce = new ExceptionResponce(new Date(), e.getMessage(), "details");
		return new ResponseEntity(responce,HttpStatus.INTERNAL_SERVER_ERROR);
//		return e.getMessage();
	}
	
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity userNotFound(CustomException e) {
		
		ExceptionResponce responce = new ExceptionResponce(new Date(), e.getMessage(), "details");
		return new ResponseEntity(responce,HttpStatus.NOT_FOUND);
//		return e.getMessage();
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//		ExceptionResponce responce = new ExceptionResponce(new Date(), ex.getMessage(),ex.getBindingResult().toString());
		
		String msg = null;
		
		
		
		for (Object object : ex.getBindingResult().getAllErrors()) {
		    if(object instanceof FieldError) {
		        FieldError fieldError = (FieldError) object;

		        System.out.println(fieldError.getDefaultMessage());
		        msg=fieldError.getDefaultMessage();
		    }

//		    if(object instanceof ObjectError) {
//		        ObjectError objectError = (ObjectError) object;
//
//		        System.out.println(objectError.getDefaultMessage());
//		    }
		}
		ExceptionResponce responce = new ExceptionResponce(new Date(), "Validation Failed",msg);
		
		return new ResponseEntity(responce,HttpStatus.BAD_REQUEST);
	}

}
/*
 * Thank you for your patience as we perform routine site maintenance. 
 * We expect to be back shortly.
 * 
 */