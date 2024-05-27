package br.com.borsoitech.pdv.layout.tableproduct;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.borsoitech.pdv.layout.util.FormatarUtil;
import br.com.borsoitech.pdv.model.type.Produto;

public class ProdutoTabelaModelo extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private final List<Produto> produtos;
    private final String[] colunas = {"Código", "Descrição", "Valor Unit"};

    public ProdutoTabelaModelo(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0 : return produto.getId();
            case 1 : return produto.getDescricao();
            case 2 : return FormatarUtil.valorParaBR(produto.getValorUnit());
            default : return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
