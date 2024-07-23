package com.fiap.tc.core.domain.exception.handler;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.IntegrationException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.adapter.web.response.DefaultResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<DefaultResponse> defaultExceptionHandler(Exception e) {

		log.error(e.getMessage(), e);

		DefaultResponse response = new DefaultResponse();
		response.setStatus("ERROR");
		response.setMessage("Ocorreu um erro inesperado!");

		return status(INTERNAL_SERVER_ERROR).body(response);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());

		DefaultResponse response = new DefaultResponse();
		response.setStatus(BAD_REQUEST.name());
		response.setMessage(mensagemUsuario);

		return status(BAD_REQUEST).body(response);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.warn(e.getMessage(), e);

		List<String> messages = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(toList());

		DefaultResponse response = new DefaultResponse();
		response.setStatus(BAD_REQUEST.name());
		response.getMessages().addAll(messages);

		return status(BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<DefaultResponse> notFoundExceptionHandler(NotFoundException e) {

		log.warn(e.getMessage(), e);

		DefaultResponse response = new DefaultResponse();
		response.setStatus(NOT_FOUND.name());
		response.setMessage(e.getMessage());

		return status(NOT_FOUND).body(response);
	}

	@ExceptionHandler(value = { BadRequestException.class, IllegalStateException.class, IllegalArgumentException.class,
			NoSuchElementException.class, IntegrationException.class })
	public ResponseEntity<DefaultResponse> badRequestExceptionHandler(RuntimeException e) {

		log.warn(e.getMessage(), e);

		DefaultResponse response = new DefaultResponse();
		response.setStatus(BAD_REQUEST.name());
		response.setMessage(e.getMessage());

		return status(BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<DefaultResponse> acccessDeniedExceptionHandler(AccessDeniedException e) {

		log.warn(e.getMessage(), e);

		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null,
				LocaleContextHolder.getLocale());

		DefaultResponse response = new DefaultResponse();
		response.setStatus(UNAUTHORIZED.name());
		response.setMessage(mensagemUsuario);

		return status(UNAUTHORIZED).body(response);
	}

}
