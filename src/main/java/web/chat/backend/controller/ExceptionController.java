package web.chat.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import web.chat.backend.exception.NotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotFoundException.class)
	public String handleNotFoundException(NotFoundException e) {
		log.error("not found exception", e);
		return e.getMessage();
	}
}