package br.com.borsoitech.pdv.layout.tableclient;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import br.com.borsoitech.pdv.layout.login.LoginControlador;
import br.com.borsoitech.pdv.layout.login.SessionManager;
import br.com.borsoitech.pdv.layout.util.Log;
import br.com.borsoitech.pdv.model.type.Cliente;
import br.com.borsoitech.pdv.model.type.LoginResponse;
import br.com.borsoitech.pdv.model.type.Pagina;

public class ClienteTabelaControlador extends javax.swing.JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private Pagina<Cliente> pagina;
    private int pageNum;
    private final ScheduledExecutorService scheduler;
    private LoginResponse loginResponse;
    private String pesquisarNome = "";
    private IClienteSelecionadoListener IClienteSelecionadoListener;
    private ClienteTabelaModelo modelo;

    public ClienteTabelaControlador(Component component) {
        initComponents();
        verificarEstadoLogin();
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::carregarTabela, 0, 5, TimeUnit.MINUTES);
        setLocationRelativeTo(component);
        setVisible(true);

        tabelaClientes.setRowHeight(29);
        txtPesquisar.requestFocus();
        txtPesquisar.addKeyListener(keyPressed());

        tabelaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClienteSelecionado(tabelaClientes.rowAtPoint(e.getPoint()));
            }
        });

        btAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaAnterior();
            }
        });

        btProximo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                proximaPagina();
            }
        });

        btPesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pesquisar();
            }
        });
    }

    private void verificarEstadoLogin() {
        loginResponse = SessionManager.getInstance().getLoginResponse();
        if (loginResponse == null || loginResponse.getAccess_token() == null) {
            JOptionPane.showMessageDialog(this, "Usuário não está autenticado. Favor fazer o login.");
            new LoginControlador().setVisible(true);
        }
    }

    private KeyAdapter keyPressed() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        pesquisar();
                        break;
                    case KeyEvent.VK_LEFT:
                        paginaAnterior();
                        break;
                    case KeyEvent.VK_RIGHT:
                        proximaPagina();
                        break;
                }
            }
        };
    }

    public void paginaAnterior() {
        if (btAnterior.isEnabled() && !pagina.isFirst()) {
            pageNum = pagina.getNumber() - 1;
            carregarTabela();
        }
    }

    public void proximaPagina() {
        if (btProximo.isEnabled() && !pagina.isLast()) {
            pageNum = pagina.getNumber() + 1;
            carregarTabela();
        }
    }

    public void pesquisar() {
        pesquisarNome = txtPesquisar.getText();
        carregarTabela();
    }

    public void addClienteSelecionadoListener(IClienteSelecionadoListener IClienteSelecionadoListener) {
        this.IClienteSelecionadoListener = IClienteSelecionadoListener;
    }

    private void setClienteSelecionado(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < tabelaClientes.getRowCount()) {
            String id = tabelaClientes.getValueAt(rowIndex, 0).toString();
            String nome = tabelaClientes.getValueAt(rowIndex, 1).toString();
            String cpf = tabelaClientes.getValueAt(rowIndex, 2).toString();
            String telefone = tabelaClientes.getValueAt(rowIndex, 3).toString();

            Cliente cliente = new Cliente(Long.valueOf(id), nome, telefone, cpf);

            if (IClienteSelecionadoListener != null) {
                IClienteSelecionadoListener.clienteSelecionado(cliente);
            }

            dispose();
        }
    }

    private void carregarTabela() {
        ClienteService service = new ClienteService();
        service.getAllClientesPaginados(pesquisarNome, loginResponse.getAccess_token(), pageNum, new IClienteCallback() {
            @Override
            public void onPaginaLoaded(Pagina<Cliente> pagina1) {
                ClienteTabelaModelo modelo = new ClienteTabelaModelo(pagina1.getContent());
                tabelaClientes.setModel(modelo);
                pagina = pagina1;
                txtNumPagina.setText(pagina.getNumber() + 1 + "/" + pagina.getTotalPages());
                btAnterior.setText("←");
                btAnterior.setEnabled(!pagina.isFirst());
                btProximo.setText("→");
                btProximo.setEnabled(!pagina.isLast());
            }

            @Override
            public void onFailure(String errorMessage) {
                JOptionPane.showMessageDialog(ClienteTabelaControlador.this, errorMessage);
            }
        });
    }


    @Override
    public void dispose() {
        scheduler.shutdown();
        super.dispose();
    }

    private void initComponents() {

        background = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btAnterior = new javax.swing.JButton();
        txtNumPagina = new javax.swing.JTextField();
        btProximo = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        background.setBackground(new java.awt.Color(51, 51, 51));
        background.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtPesquisar.setToolTipText("");
        txtPesquisar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btPesquisar.setBackground(new java.awt.Color(0, 0, 102));
        btPesquisar.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12)); // NOI18N
        btPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisar.setText("Pesquisar (Enter)");
        btPesquisar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(txtPesquisar))
                .addContainerGap())
        );

        tabelaClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(tabelaClientes);
        if (tabelaClientes.getColumnModel().getColumnCount() > 0) {
            tabelaClientes.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaClientes.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        btAnterior.setBackground(new java.awt.Color(0, 0, 102));
        btAnterior.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btAnterior.setForeground(new java.awt.Color(255, 255, 255));
        btAnterior.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtNumPagina.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNumPagina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumPagina.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNumPagina.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNumPagina.setEnabled(false);

        btProximo.setBackground(new java.awt.Color(0, 0, 102));
        btProximo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btProximo.setForeground(new java.awt.Color(255, 255, 255));
        btProximo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btProximo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumPagina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btProximo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }

    private javax.swing.JPanel background;
    private javax.swing.JButton btAnterior;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JToggleButton btProximo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaClientes;
    private javax.swing.JTextField txtNumPagina;
    private javax.swing.JTextField txtPesquisar;
}
