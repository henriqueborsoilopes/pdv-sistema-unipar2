package br.com.borsoitech.pdv.service.exception;

import java.io.Serial;

public class ControllerNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ControllerNotFoundException(String message) {
        super(message);
    }
}
