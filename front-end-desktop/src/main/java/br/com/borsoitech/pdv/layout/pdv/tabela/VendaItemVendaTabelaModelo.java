package br.com.borsoitech.pdv.layout.pdv.tabela;

import java.io.Serial;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.borsoitech.pdv.layout.util.FormatarUtil;
import br.com.borsoitech.pdv.model.type.ItemVenda;

public class VendaItemVendaTabelaModelo extends AbstractTableModel {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<ItemVenda> itens;
    private final String[] colunas = {"Código", "Descrição", "Valor Unit", "Qtd", "Desc", "Valor Total"};

    public VendaItemVendaTabelaModelo(List<ItemVenda> itens) {
        this.itens = itens;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemVenda item = itens.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> item.getProdutoId();
            case 1 -> item.getDescricao();
            case 2 -> FormatarUtil.valorParaBR(item.getValorUnit());
            case 3 -> item.getQuantidade();
            case 4 -> FormatarUtil.valorParaBR(item.getDesconto());
            case 5 -> FormatarUtil.valorParaBR(item.getValorTotalItem());
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
