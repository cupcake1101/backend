package com.app.exc_handler;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.app.custom_excs.IssuesNotFoundException;
import com.app.custom_excs.PersonNotFoundException;
import com.app.custom_excs.ProjectMappingNotFoundException;
import com.app.custom_excs.ProjectsNotFoundException;
import com.app.dto.ErrorResponse;

@ControllerAdvice // stereotype annotation . Class level annotation .
//Supplies common advice to all controllers  n rest controllers.
public class GlobalExceptionHandler {//extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ProjectsNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ProjectsNotFoundException exc, WebRequest request) {
		System.out.println("in handle res not found");
		ErrorResponse errResp = new ErrorResponse(exc.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
	}
	
		@ExceptionHandler(ProjectMappingNotFoundException.class)
		public ResponseEntity<?> handleResourceNotFoundException1(ProjectMappingNotFoundException exc, WebRequest request) {
			System.out.println("in handle res not found");
			ErrorResponse errResp = new ErrorResponse(exc.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
		}
		@ExceptionHandler(IssuesNotFoundException.class)
		public ResponseEntity<?> handleResourceNotFoundException2(IssuesNotFoundException exc, WebRequest request) {
			System.out.println("in handle res not found");
			ErrorResponse errResp = new ErrorResponse(exc.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
		}
		@ExceptionHandler(PersonNotFoundException.class)
		public ResponseEntity<?> handleResourceNotFoundException3(PersonNotFoundException exc, WebRequest request) {
			System.out.println("in handle res not found");
			ErrorResponse errResp = new ErrorResponse(exc.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
		}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationExc(ConstraintViolationException e, WebRequest request) {
		System.out.println("in exc 2!!!");
		ErrorResponse errResp = new ErrorResponse(e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
	}

	// This Exception is thrown when a method argument fails validation typically
	// due to @Valid style validation
	// or request body content is required.
	//@Override
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("exc 3");
		
		/*
		 * List<String> validationErrs = ex.getBindingResult().getFieldErrors().stream()
		 * .map(fieldErr -> fieldErr.getDefaultMessage()).collect(Collectors.toList());
		 */	
		List<String> validationErrs=new ArrayList<>();
		//BindingResult : i/f : represents results of binding between request data n pojo properties
		for(FieldError err : ex.getBindingResult().getFieldErrors())
			validationErrs.add(err.getDefaultMessage());
		
		ErrorResponse errResp=new ErrorResponse("Validation Failed", validationErrs.toString());
		return new ResponseEntity<Object>(errResp, HttpStatus.BAD_REQUEST);

	}

	// handle ANY exception -- equivalent to catch-all
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAnyException(Exception exc, WebRequest request) {
		System.out.println("in handle exc");
		ErrorResponse errResp = new ErrorResponse(exc.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
