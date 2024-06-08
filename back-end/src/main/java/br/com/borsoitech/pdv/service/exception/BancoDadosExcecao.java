package br.com.borsoitech.pdv.service.exception;

import java.io.Serial;

public class BancoDadosExcecao extends Exception {
	@Serial
    private static final long serialVersionUID = 1L;

	public BancoDadosExcecao(String msg) {
        super(msg);
    }
}
