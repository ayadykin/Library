package com.ayadykin.ggstars.test.library.exception;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ayadykin.ggstars.test.library.dto.ErrorDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorAdvice {

	// Application error
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(LibraryException.class)
	public ErrorDto libraryError(LibraryException e) {
		log.error("-> libraryError: {}", e.getMessage());
		return new ErrorDto(e.getMessage());
	}

	// Constraint violation exception
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDto validationError(MethodArgumentNotValidException e) {
		log.error("-> validationError error: {}", e.getMessage());
		ErrorDto errorDto = new ErrorDto(LibraryException.SERVICE_ERROR);

		e.getBindingResult().getFieldErrors().stream().forEach(error -> {
			errorDto.addError(error.getDefaultMessage());
		});
		return errorDto;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorDto validationError(ConstraintViolationException e) {
		log.error("-> validationError error: {}", e.getMessage());
		ErrorDto errorDto = new ErrorDto(LibraryException.SERVICE_ERROR);

		e.getConstraintViolations().stream().forEach(error -> {
			errorDto.addError(error.getMessage());
		});
		return errorDto;
	}

	// ServletException
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ServletException.class)
	public ErrorDto mediaTypeError(ServletException e) {
		log.error("-> mediaTypeError error: {}", e.getMessage());
		return new ErrorDto(e.getMessage());
	}

	// Json Mapping errors
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonProcessingException.class)
	public ErrorDto jsonError(JsonProcessingException e) {
		log.error("-> jsonError error: {}", e.getMessage());
		return new ErrorDto(e.getMessage());
	}

	// Spring Json Mapping errors
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorDto springError(HttpMessageNotReadableException e) {
		log.error("-> springError error: {}", e.getMessage());
		return new ErrorDto(e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public ErrorDto runtimeError(RuntimeException e) {
		log.error("-> runtimeError error: {}", e);
		return new ErrorDto(LibraryException.SERVICE_ERROR);
	}
}
