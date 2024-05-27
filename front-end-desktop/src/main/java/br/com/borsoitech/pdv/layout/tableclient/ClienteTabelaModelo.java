package br.com.borsoitech.pdv.layout.tableclient;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.borsoitech.pdv.model.type.Cliente;

public class ClienteTabelaModelo extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private final List<Cliente> clientes;
    private final String[] colunas = {"Código", "Nome", "Telefone", "CPF"};

    public ClienteTabelaModelo(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0 : return cliente.getId();
            case 1 : return cliente.getNome();
            case 2 : return cliente.getTelefone();
            case 3 : return cliente.getCpf();
            default : return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
