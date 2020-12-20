package vezmar.boris.spring.boot.demo.controller;

import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import vezmar.boris.spring.boot.demo.model.response.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {IllegalArgumentException.class})
	protected ResponseEntity<ErrorResponse> handleIllegalArgumentEx(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {NoSuchElementException.class})
	protected ResponseEntity<ErrorResponse> handleNoSuchElementEx(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = {DataAccessException.class})
	protected ResponseEntity<ErrorResponse> handleDataAccessEx(RuntimeException ex, WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
