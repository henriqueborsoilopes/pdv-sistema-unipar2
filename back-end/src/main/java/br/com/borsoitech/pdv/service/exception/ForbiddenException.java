package br.com.borsoitech.pdv.service.exception;

public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException(String message) {
        super(message);
    }
}