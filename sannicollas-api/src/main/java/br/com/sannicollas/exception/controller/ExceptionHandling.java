package br.com.sannicollas.exception.controller;

import br.com.sannicollas.exception.ObjectNotFoundException;
import br.com.sannicollas.exception.SanNicollasException;
import br.com.sannicollas.exception.model.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<Object> handleObjectNotFoundExceptionException(ObjectNotFoundException exception) {
        LOGGER.error("SanNicollas Exception: ", exception);
        if (exception.getStatus() == null) {
            exception.setStatus(HttpStatus.NOT_FOUND);
        }

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(exception.getMessage());
        apiErrorResponse.setStatus(exception.getStatus());

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(SanNicollasException.class)
    ResponseEntity<Object> handleSanNicollasException(SanNicollasException exception) {
        LOGGER.error("Gesthosp Exception: ", exception);
        if (exception.getStatus() == null) exception.setStatus(HttpStatus.BAD_REQUEST);

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(exception.getMessage());
        apiErrorResponse.setStatus(exception.getStatus());

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        LOGGER.error("MethodArgumentNotValidException: ", exception);

        List<String> errorMessages = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage("Argumentos inválidos");
        apiErrorResponse.setStatus(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setErrors(errorMessages);

        return buildResponseEntity(apiErrorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiErrorResponse, headers, apiErrorResponse.getStatus());
    }

}
