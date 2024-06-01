package br.com.borsoitech.pdv.layout.pdv;

public interface IVendaSalvaCallback {

    void onVendaSalva(Long id);
    void onFailure(String errorMessage);
}
