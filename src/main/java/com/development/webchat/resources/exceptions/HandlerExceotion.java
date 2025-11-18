package com.development.webchat.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.development.webchat.services.exceptions.NotFoundObjectException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlerExceotion {

	@ExceptionHandler(NotFoundObjectException.class)
	public ResponseEntity<StandardError> notFound(NotFoundObjectException e, HttpServletRequest request){
		String error = "not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError stde = new StandardError(
				Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(stde);
	}
}
