package br.com.borsoitech.pdv.layout.tableclient;

import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import br.com.borsoitech.pdv.model.type.Cliente;

public class ClienteTabelaModelo extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private final List<Cliente> clientes;
    private final String[] colunas = {"Código", "Nome", "Telefone", "CPF"};
    private final Class<?>[] types = {Integer.class, String.class, String.class, String.class};
    private final boolean[] canEdit = {false, false, false, false};

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
        return switch (columnIndex) {
            case 0 -> cliente.getId();
            case 1 -> cliente.getNome();
            case 2 -> cliente.getTelefone();
            case 3 -> cliente.getCpf();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public JTable setColumnSize(JTable tabelaClientes) {
        if (tabelaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaClientes.getColumnModel().getColumn(0).setMinWidth(20);
            tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(100);
            tabelaClientes.getColumnModel().getColumn(2).setMinWidth(50);
            tabelaClientes.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        return tabelaClientes;
    }

    public JTable setColumnAlignment(JTable tabelaClientes) {
        for (int columnIndex = 0; columnIndex < tabelaClientes.getColumnCount(); columnIndex++) {
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            tabelaClientes.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);
        }
        return tabelaClientes;
    }
}
