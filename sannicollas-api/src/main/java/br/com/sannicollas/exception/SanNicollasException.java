package br.com.sannicollas.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SanNicollasException extends RuntimeException{

    private HttpStatus status;

    private static final long serialVersionUID = 1L;

    public SanNicollasException(String message) {
        super(message);
    }

}
