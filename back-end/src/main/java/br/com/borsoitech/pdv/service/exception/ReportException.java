package br.com.borsoitech.pdv.service.exception;

import java.io.Serial;

public class ReportException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ReportException(String message) {
        super(message);
    }
}
