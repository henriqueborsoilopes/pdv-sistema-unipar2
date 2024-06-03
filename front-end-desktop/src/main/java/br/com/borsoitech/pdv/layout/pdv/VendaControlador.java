package br.com.borsoitech.pdv.layout.pdv;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import br.com.borsoitech.pdv.layout.login.SessionManager;
import br.com.borsoitech.pdv.layout.popup.NotificationPopup;
import br.com.borsoitech.pdv.layout.report.RelatorioControlador;
import br.com.borsoitech.pdv.layout.tableclient.ClienteTabelaControlador;
import br.com.borsoitech.pdv.layout.tablepayment.PagamentoTabelaControlador;
import br.com.borsoitech.pdv.layout.tableproduct.ProdutoTabelaControlador;
import br.com.borsoitech.pdv.layout.util.FormatarUtil;
import br.com.borsoitech.pdv.model.type.*;

public class VendaControlador extends javax.swing.JFrame {

    private Integer qtdProduto = 1;
    private Double descontoItem = 0.00;
    private Double descontoVenda = 0.00;
    private Produto produto;
    private Cliente cliente;
    private Venda venda;
    private LoginResponse loginResponse;

    public VendaControlador() {
        initComponents();
        verificarEstadoLogin();
        novaVenda();

        background.requestFocus();
        addGlobalKeyListeners(keyPressed());
        addButtonActionListeners();
        configureFocusListeners();
    }

    private void verificarEstadoLogin() {
        loginResponse = SessionManager.getInstance().getLoginResponse();
        if (loginResponse == null || loginResponse.getAccess_token() == null) {
            JOptionPane.showMessageDialog(this, "Usuário não está autenticado. Favor fazer o login.");
            // TODO Redirecionar para a tela de login ou encerrar a aplicação
        }
    }

    private void addGlobalKeyListeners(KeyAdapter keyAdapter) {
        background.addKeyListener(keyAdapter);
        btAdicionarProduto.addKeyListener(keyAdapter);
        btAumentarQtd.addKeyListener(keyAdapter);
        btDiminuirQtd.addKeyListener(keyAdapter);
        btNovaVenda.addKeyListener(keyAdapter);
        btNovoCliente.addKeyListener(keyAdapter);
        btNovoProduto.addKeyListener(keyAdapter);
        btSalvarVenda.addKeyListener(keyAdapter);
        btAdicionarPagamento.addKeyListener(keyAdapter);
        jButton6.addKeyListener(keyAdapter);
        tabelaItens.addKeyListener(keyAdapter);
        tabelaPagamentos.addKeyListener(keyAdapter);
        txtQtd.addKeyListener(keyAdapter);
        txtDescontoProduto.addKeyListener(keyAdapter);
    }

    private void addButtonActionListeners() {
        btAdicionarProduto.addActionListener((ActionEvent e) -> addProduto());
        btNovoCliente.addActionListener((ActionEvent e) -> abrirClienteTabelaControlador());
        btNovoProduto.addActionListener((ActionEvent e) -> abrirProdutoTabelaControlador());
        btNovaVenda.addActionListener((ActionEvent e) -> novaVenda());
        btSalvarVenda.addActionListener((ActionEvent e) -> salvarVenda());
        btDiminuirQtd.addActionListener((ActionEvent e) -> diminuirQtd());
        btAumentarQtd.addActionListener((ActionEvent e) -> aumentarQtd());
        btAdicionarPagamento.addActionListener((ActionEvent e) -> abrirPagamentoTabelaControlador());
    }

    private void configureFocusListeners() {
        txtDescontoProduto.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtDescontoProduto.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String valor = txtDescontoProduto.getText();
                descontoItem = valor.isEmpty() ? descontoItem : FormatarUtil.valorParaDouble(valor);
                txtDescontoProduto.setText(FormatarUtil.valorParaBR(descontoItem));
            }
        });

        txtDescontoVenda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtDescontoVenda.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                atualizarDescontoVenda();
                atualizarVenda();
                atualizarCamposVendaAtual();
            }
        });

        txtQtd.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtQtd.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                String valor = txtQtd.getText();
                qtdProduto = valor.isEmpty() ? qtdProduto : Integer.valueOf(txtQtd.getText());
                txtQtd.setText(String.valueOf(qtdProduto));
            }
        });

        txtDescontoVenda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    atualizarDescontoVenda();
                    atualizarVenda();
                    atualizarCamposVendaAtual();
                }
            }
        });
    }

    private void salvarVenda() {
        VendaService vendaService = new VendaService();
        vendaService.salvarVenda(loginResponse.getAccess_token(), venda, new IVendaSalvaCallback() {
            @Override
            public void onVendaSalva(Long vendaId) {
                SwingUtilities.invokeLater(() -> {
                    showNotification("Venda salva com sucesso!");
                    int n = JOptionPane.showOptionDialog(null,
                            "Gostaria de imprimir o comprovante?",
                            "Comprovante", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Imprimir", "Não"}, "Não");
                    if (n == 0) {
                        new RelatorioControlador(loginResponse.getAccess_token(), vendaId);
                        showNotification("Comprovante gerado com sucesso!");
                    }
                    novaVenda();
                });
            }

            @Override
            public void onFailure(String errorMessage) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(VendaControlador.this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE, null);
                });
            }
        });
    }

    private void atualizaVenda() {
        VendaService vendaService = new VendaService();
        vendaService.atualizarVenda(loginResponse.getAccess_token(), venda, new IVendaAtualizaCallback() {
            @Override
            public void onVendaAtualizada(Venda vendaAtualizada) {
                // TODO Este método não é relevante para a operação de salvar uma nova venda
            }

            @Override
            public void onFailure(String errorMessage) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(VendaControlador.this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE, null);
                });
            }
        });
    }

    private void showNotification(String message) {
        NotificationPopup popup = new NotificationPopup(message);
        popup.showPopup();
    }

    private void novaVenda() {
        venda = new Venda(0.0, 0.0, 0.0, 0.0, false, null);
        atualizarCamposNovaVenda();
        atualizarItemVendaTabela();
        atualizarPagamentoTabela();
    }

    private void abrirClienteTabelaControlador() {
        ClienteTabelaControlador controlador = new ClienteTabelaControlador(this);
        controlador.addClienteSelecionadoListener((Cliente cliente1) -> {
            this.cliente = cliente1;
            exibirCliente();
            atualizarVenda();
            atualizarCamposVendaAtual();
            cliente = null;
            synchronized (VendaControlador.this) {
                VendaControlador.this.notify();
            }
        });
    }

    private void abrirProdutoTabelaControlador() {
        ProdutoTabelaControlador controlador = new ProdutoTabelaControlador(this);
        controlador.addProdutoSelecionadoListener((Produto produto1) -> {
            this.produto = produto1;
            exibirProduto();
            synchronized (VendaControlador.this) {
                VendaControlador.this.notify();
            }
        });
    }

    private void abrirPagamentoTabelaControlador() {
        if (venda.isVendaQuitada()) {
            return;
        }
        PagamentoTabelaControlador controlador = new PagamentoTabelaControlador(this, venda.getValorParcialPago());
        controlador.addPagamentoSelecionadoListener((Pagamento pagamento1) -> {
            venda.addPagamento(pagamento1);
            atualizaVenda();
            atualizarPagamentoTabela();
            synchronized (VendaControlador.this) {
                VendaControlador.this.notify();
            }
        });
    }

    private KeyAdapter keyPressed() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        aumentarQtd();
                        break;
                    case KeyEvent.VK_DOWN:
                        diminuirQtd();
                        break;
                    case KeyEvent.VK_F2:
                        abrirClienteTabelaControlador();
                        break;
                    case KeyEvent.VK_F1:
                        abrirProdutoTabelaControlador();
                        break;
                    case KeyEvent.VK_ENTER:
                        addProduto();
                        break;
                    case KeyEvent.VK_F9:
                        novaVenda();
                        break;
                    case KeyEvent.VK_F5:
                        salvarVenda();
                        break;
                    case KeyEvent.VK_F3:
                        abrirPagamentoTabelaControlador();
                        break;
                }
            }
        };
    }

    private void atualizarDescontoVenda() {
        String valor = txtDescontoVenda.getText();
        descontoVenda = valor.isEmpty() ? descontoVenda : FormatarUtil.valorParaDouble(valor);
    }

    private void atualizarCamposNovaVenda() {
        qtdProduto = 1;
        descontoItem = 0.00;
        descontoVenda = 0.00;
        txtValorTotalVenda.setText(FormatarUtil.valorParaBR(venda.getValorTotal()));
        txtDescontoVenda.setText(FormatarUtil.valorParaBR(descontoVenda));
        txtTotalPago.setText(FormatarUtil.valorParaBR(venda.getValorTotalPago()));
        txtSaldoFinal.setText(FormatarUtil.valorParaBR(venda.getValorParcialPago()));
        txtDescontoProduto.setText(FormatarUtil.valorParaBR(descontoItem));
        txtCodigoProduto.setText("");
        txtDescricaoProduto.setText("");
        txtQtd.setText(String.valueOf(qtdProduto));
        txtCodigoCliente.setText("");
        txtNomeCliente.setText("");
    }

    private void atualizarCamposVendaAtual() {
        qtdProduto = 1;
        descontoItem = 0.00;
        txtValorTotalVenda.setText(FormatarUtil.valorParaBR(venda.getValorTotal()));
        txtDescontoVenda.setText(FormatarUtil.valorParaBR(descontoVenda));
        txtTotalPago.setText(FormatarUtil.valorParaBR(venda.getValorTotalPago()));
        txtSaldoFinal.setText(FormatarUtil.valorParaBR(venda.getValorParcialPago()));
        txtDescontoProduto.setText(FormatarUtil.valorParaBR(descontoItem));
        txtCodigoProduto.setText("");
        txtDescricaoProduto.setText("");
        txtQtd.setText(String.valueOf(qtdProduto));
    }

    private void exibirProduto() {
        txtCodigoProduto.setText(produto.getId().toString());
        txtDescricaoProduto.setText(produto.getDescricao());
    }

    private void exibirCliente() {
        txtCodigoCliente.setText(cliente.getId().toString());
        txtNomeCliente.setText(cliente.getNome());
    }

    private void addProduto() {
        addItemVenda();
        atualizaVenda();
        atualizarItemVendaTabela();
        atualizarCamposVendaAtual();
    }

    private void addItemVenda() {
        if (produto != null) {
            for (ItemVenda item : venda.getItens()) {
                if (item.getProdutoId().equals(produto.getId())) {
                    item.setDesconto(item.getDesconto() + descontoItem);
                    item.setQuantidade(item.getQuantidade() + qtdProduto);
                    produto = null;
                    return;
                }
            }
            venda.addItem(new ItemVenda(0.0, produto.getDescricao(), qtdProduto, produto.getValorUnit(), descontoItem, produto.getId()));
            produto = null;
        }
    }

    private void aumentarQtd() {
        qtdProduto += 1;
        txtQtd.setText(String.valueOf(qtdProduto));
    }

    private void diminuirQtd() {
        if (qtdProduto > 1) {
            qtdProduto -= 1;
            txtQtd.setText(String.valueOf(qtdProduto));
        }
    }

    private void atualizarPagamentoTabela() {
        VendaPagamentoTabelaModelo modelo = new VendaPagamentoTabelaModelo(venda.getPagamentos());
        tabelaPagamentos.setModel(modelo);
        tabelaPagamentos.setRowHeight(30);
        atualizarCamposVendaAtual();
    }

    private void atualizarItemVendaTabela() {
        VendaItemVendaTabelaModelo modelo = new VendaItemVendaTabelaModelo(venda.getItens());
        tabelaItens.setModel(modelo);
        tabelaItens.setRowHeight(30);
    }

    private void atualizarVenda() {
        venda.setClienteId(cliente.getId());
        venda.setDesconto(descontoVenda);
    }

    @Override
    public void dispose() {
        int resposta = JOptionPane.showConfirmDialog(VendaControlador.this, "Deseja realmente sair do programa?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            super.dispose();
        }
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField3 = new javax.swing.JTextField();
        jMenuItem1 = new javax.swing.JMenuItem();
        background = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtDescricaoProduto = new javax.swing.JTextField();
        txtCodigoProduto = new javax.swing.JTextField();
        btAdicionarProduto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtDescontoProduto = new javax.swing.JTextField();
        btAumentarQtd = new javax.swing.JButton();
        btDiminuirQtd = new javax.swing.JButton();
        txtQtd = new javax.swing.JTextField();
        btNovoProduto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaItens = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btNovoCliente = new javax.swing.JButton();
        txtNomeCliente = new javax.swing.JTextField();
        txtCodigoCliente = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btAdicionarPagamento = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaPagamentos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btSalvarVenda = new javax.swing.JButton();
        btNovaVenda = new javax.swing.JButton();
        txtValorTotalVenda = new javax.swing.JTextField();
        txtTotalPago = new javax.swing.JTextField();
        txtSaldoFinal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDescontoVenda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Descrição", "QTD", "Valor Unit", "Desconto", "Total", "Editar", "Exclir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(2).setMinWidth(60);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setMinWidth(70);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(4).setMinWidth(70);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(5).setMinWidth(70);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(70);
            jTable1.getColumnModel().getColumn(6).setMinWidth(50);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(7).setMinWidth(50);
            jTable1.getColumnModel().getColumn(7).setMaxWidth(50);
        }

        jTextField3.setText("00,00");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PDV");
        setBackground(new java.awt.Color(0, 102, 102));
        setName("main"); // NOI18N
        setSize(new java.awt.Dimension(1200, 800));
        setLocationRelativeTo(null);
        setExtendedState(VendaControlador.NORMAL);

        background.setBackground(new java.awt.Color(51, 51, 51));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtDescricaoProduto.setToolTipText("");
        txtDescricaoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDescricaoProduto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescricaoProduto.setEnabled(false);

        txtCodigoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCodigoProduto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigoProduto.setEnabled(false);

        btAdicionarProduto.setBackground(new java.awt.Color(0, 102, 0));
        btAdicionarProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btAdicionarProduto.setForeground(new java.awt.Color(255, 255, 255));
        btAdicionarProduto.setText("Adicionar (Enter)");
        btAdicionarProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Desconto");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtDescontoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescontoProduto.setText("0.00");
        txtDescontoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btAumentarQtd.setBackground(new java.awt.Color(0, 0, 102));
        btAumentarQtd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btAumentarQtd.setForeground(new java.awt.Color(255, 255, 255));
        btAumentarQtd.setText("+ (↑)");
        btAumentarQtd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btDiminuirQtd.setBackground(new java.awt.Color(153, 0, 0));
        btDiminuirQtd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btDiminuirQtd.setForeground(new java.awt.Color(255, 255, 255));
        btDiminuirQtd.setText("-  (↓)");
        btDiminuirQtd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btDiminuirQtd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtQtd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtQtd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQtd.setText("1");
        txtQtd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtQtd.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btNovoProduto.setBackground(new java.awt.Color(0, 0, 102));
        btNovoProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNovoProduto.setForeground(new java.awt.Color(255, 255, 255));
        btNovoProduto.setText("+ Novo produto (F1)");
        btNovoProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricaoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btDiminuirQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAumentarQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescontoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btAdicionarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoProduto)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addComponent(btAumentarQtd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescontoProduto)
                            .addComponent(btDiminuirQtd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtQtd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDescricaoProduto)
                    .addComponent(btNovoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabelaItens.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaItens.setForeground(new java.awt.Color(102, 102, 102));
        tabelaItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Valor Unit", "Qtd", "Desc", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaItens.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tabelaItens);
        tabelaItens.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabelaItens.getColumnModel().getColumnCount() > 0) {
            tabelaItens.getColumnModel().getColumn(0).setMinWidth(60);
            tabelaItens.getColumnModel().getColumn(0).setMaxWidth(60);
            tabelaItens.getColumnModel().getColumn(2).setMinWidth(75);
            tabelaItens.getColumnModel().getColumn(2).setMaxWidth(75);
            tabelaItens.getColumnModel().getColumn(3).setMinWidth(60);
            tabelaItens.getColumnModel().getColumn(3).setMaxWidth(60);
            tabelaItens.getColumnModel().getColumn(4).setMinWidth(75);
            tabelaItens.getColumnModel().getColumn(4).setMaxWidth(75);
            tabelaItens.getColumnModel().getColumn(5).setMinWidth(75);
            tabelaItens.getColumnModel().getColumn(5).setMaxWidth(75);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btNovoCliente.setBackground(new java.awt.Color(0, 0, 102));
        btNovoCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNovoCliente.setForeground(new java.awt.Color(255, 255, 255));
        btNovoCliente.setText("+ Novo cliente (F2)");
        btNovoCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNomeCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNomeCliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNomeCliente.setEnabled(false);

        txtCodigoCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCodigoCliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigoCliente.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNovoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeCliente)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btNovoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtCodigoCliente))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btAdicionarPagamento.setBackground(new java.awt.Color(0, 0, 102));
        btAdicionarPagamento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btAdicionarPagamento.setForeground(new java.awt.Color(255, 255, 255));
        btAdicionarPagamento.setText("+ Novo pagamento (F3)");
        btAdicionarPagamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabelaPagamentos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Forma", "Parcela", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabelaPagamentos);
        if (tabelaPagamentos.getColumnModel().getColumnCount() > 0) {
            tabelaPagamentos.getColumnModel().getColumn(1).setMinWidth(60);
            tabelaPagamentos.getColumnModel().getColumn(1).setMaxWidth(60);
            tabelaPagamentos.getColumnModel().getColumn(2).setMinWidth(80);
            tabelaPagamentos.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                    .addComponent(btAdicionarPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdicionarPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btSalvarVenda.setBackground(new java.awt.Color(0, 102, 0));
        btSalvarVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btSalvarVenda.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarVenda.setText("Finalizar venda (F5)");
        btSalvarVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btNovaVenda.setBackground(new java.awt.Color(0, 0, 102));
        btNovaVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btNovaVenda.setForeground(new java.awt.Color(255, 255, 255));
        btNovaVenda.setText("+ Nova venda (F9)");
        btNovaVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtValorTotalVenda.setEditable(false);
        txtValorTotalVenda.setBackground(new java.awt.Color(255, 255, 255));
        txtValorTotalVenda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtValorTotalVenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorTotalVenda.setText("0,00");
        txtValorTotalVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtValorTotalVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorTotalVenda.setEnabled(false);

        txtTotalPago.setEditable(false);
        txtTotalPago.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalPago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalPago.setText("0,00");
        txtTotalPago.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtTotalPago.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotalPago.setEnabled(false);

        txtSaldoFinal.setEditable(false);
        txtSaldoFinal.setBackground(new java.awt.Color(255, 255, 255));
        txtSaldoFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSaldoFinal.setText("0,00");
        txtSaldoFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtSaldoFinal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSaldoFinal.setEnabled(false);

        jLabel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total geral");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total pago");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Saldo final");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtDescontoVenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescontoVenda.setText("0,00");
        txtDescontoVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDescontoVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Desconto");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButton6.setBackground(new java.awt.Color(204, 102, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Aguardar (F7)");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSalvarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalPago)
                            .addComponent(txtSaldoFinal)
                            .addComponent(txtDescontoVenda, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtValorTotalVenda))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescontoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSaldoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(btSalvarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(102, 102, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private javax.swing.JPanel background;
    private javax.swing.JButton btAdicionarPagamento;
    private javax.swing.JButton btAdicionarProduto;
    private javax.swing.JButton btAumentarQtd;
    private javax.swing.JButton btDiminuirQtd;
    private javax.swing.JButton btNovaVenda;
    private javax.swing.JButton btNovoCliente;
    private javax.swing.JButton btNovoProduto;
    private javax.swing.JButton btSalvarVenda;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tabelaItens;
    private javax.swing.JTable tabelaPagamentos;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtCodigoProduto;
    private javax.swing.JTextField txtDescontoProduto;
    private javax.swing.JTextField txtDescontoVenda;
    private javax.swing.JTextField txtDescricaoProduto;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtQtd;
    private javax.swing.JTextField txtSaldoFinal;
    private javax.swing.JTextField txtTotalPago;
    private javax.swing.JTextField txtValorTotalVenda;
}
