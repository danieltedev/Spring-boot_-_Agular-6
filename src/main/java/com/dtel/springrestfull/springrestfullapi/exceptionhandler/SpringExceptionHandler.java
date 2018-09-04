package com.dtel.springrestfull.springrestfullapi.exceptionhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * SpringExceptionHandler
 */
@ControllerAdvice
public class SpringExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, "Mensagem enviada é inválida.", headers, HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, "Dados não encontrados.", headers, HttpStatus.NOT_FOUND, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream().map(f -> {
            Map<String, String> ret = new HashMap<String, String>();
            ret.put(f.getField(), f.getDefaultMessage());
            return ret;
        }).collect(Collectors.toList());

		return handleExceptionInternal(ex, errors, headers, status, request);
    }
    
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Recurso não encontrado.", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
}