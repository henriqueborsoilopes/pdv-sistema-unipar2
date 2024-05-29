package br.com.borsoitech.pdv.layout.pdv;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.borsoitech.pdv.layout.util.FormatarUtil;
import br.com.borsoitech.pdv.model.type.Pagamento;

public class MainPagamentoTabelaModelo extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private final List<Pagamento> pagamentos;
    private final String[] colunas = {"Forma", "Parcela", "Valor Total"};

    public MainPagamentoTabelaModelo(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public int getRowCount() {
        return pagamentos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pagamento pagamento = pagamentos.get(rowIndex);
        switch (columnIndex) {
            case 0 : return pagamento.getTipoPagamento().getDescricao();
            case 1 : return pagamento.getParcelas();
            case 2 : return FormatarUtil.valorParaBR(pagamento.getValorPago());
            default : return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
