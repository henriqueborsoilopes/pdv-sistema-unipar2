package br.com.borsoitech.pdv.layout.pdv.tabela;

import java.io.Serial;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.borsoitech.pdv.layout.util.FormatarUtil;
import br.com.borsoitech.pdv.model.type.Pagamento;
import br.com.borsoitech.pdv.model.type.enums.TipoPagamento;

public class VendaPagamentoTabelaModelo extends AbstractTableModel {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Pagamento> pagamentos;
    private final String[] colunas = {"Forma", "Parcela", "Valor Total"};

    public VendaPagamentoTabelaModelo(List<Pagamento> pagamentos) {
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
        return switch (columnIndex) {
            case 0 -> TipoPagamento.paraEnum(pagamento.getTipoPag()).getDescricao();
            case 1 -> pagamento.getParcelas();
            case 2 -> FormatarUtil.valorParaBR(pagamento.getValorPago());
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}
