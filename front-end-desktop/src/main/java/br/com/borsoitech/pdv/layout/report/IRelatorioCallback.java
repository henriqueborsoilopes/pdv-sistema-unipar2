package br.com.borsoitech.pdv.layout.report;

public interface IRelatorioCallback {

    void onRelatorioGerado();
    void onFailure(String errorMessage);
}
