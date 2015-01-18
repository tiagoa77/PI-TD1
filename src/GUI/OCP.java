/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Cliente;
import Classes.Encomenda;
import Classes.Funcionario;
import Classes.Local;
import Classes.Produto;
import Classes.Sistema;
import ClassesDAO.ConexaoBD;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tiago
 */
public final class OCP extends javax.swing.JFrame {

    private final Sistema sistema;
    private int[][] clientesRotas;

    public OCP(Sistema s, String login) {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tiago\\Documents\\NetBeansProjects\\PI-TD1\\logo.png"));
        this.setTitle("OCP Portugal");
        this.sistema = s;
        this.jTextPaneSessao.setText(login);
        this.jTextPaneSessao1.setText(login);
        this.jTextPaneSessao4.setText(login);
        this.jTextPaneSessao5.setText(login);
        this.jTextPaneSessao7.setText(login);
        listaProdutos();
        listaFuncionarios();
        listaClientes();
        listaEncomendas();
        listaRotas();
        ClientesRotas();

        this.jButtonGuardarFuncionario.setVisible(false);
        this.jButtonGuardarClientes.setVisible(false);

        this.jTextPaneAprovacao.setEditable(false);
        this.jTextPaneDataHora.setEditable(false);
        this.jTextPaneNumeroBanheirasEncomendas.setEditable(false);
        this.jTextPaneDataNascimento.setEditable(false);
        this.jTextPaneClienteEncomenda.setEditable(false);
        this.jTextPaneFuncao.setEditable(false);
        this.jTextPaneMoradaCliente.setEditable(false);
        this.jTextPaneMoradaFuncionario.setEditable(false);
        this.jTextPaneNIF.setEditable(false);
        this.jTextPaneNomeCliente.setEditable(false);
        this.jTextPaneNomeFarmaceutico.setEditable(false);
        this.jTextPaneNomeFuncionario.setEditable(false);
        this.jTextPaneObservacoes.setEditable(false);
        this.jTextPanePreco.setEditable(false);
        this.jTextPaneProduto.setEditable(false);
        this.jTextPaneRotaID.setEditable(false);
        this.jTextPaneSessao.setEditable(false);
        this.jTextPaneSessao1.setEditable(false);
        this.jTextPaneSessao4.setEditable(false);
        this.jTextPaneSessao5.setEditable(false);
        this.jTextPaneSessao7.setEditable(false);
        this.jTextPaneTelefoneCliente.setEditable(false);
        this.jTextPaneTelefoneFuncionario.setEditable(false);
        this.jTextPaneTipo.setEditable(false);
    }

    private Map<Integer, String[]> parseClientes() { // idRota-> Clientes
        Map<Integer, String[]> clientesVisitados = new HashMap<>();
        String lista;
        String delim = "[-]";

        for (int k : this.sistema.getRotas().keySet()) {
            lista = this.sistema.getRotas().get(k).getListaClientes();
            String[] tokens = lista.split(delim);
            clientesVisitados.put(k, tokens);

        }
        return clientesVisitados;
    }

    private int[][] ClientesRotas() {
        int clientes = this.sistema.getClientes().size();
        int rotas = this.sistema.getRotas().size();
        this.clientesRotas = new int[clientes][rotas];
        Map<Integer, String[]> clientesV = parseClientes();

        for (int k : clientesV.keySet()) {
            for (int l = 0; l < clientesV.get(k).length; l++) {
                if (this.sistema.getClientes().containsKey(Integer.parseInt(clientesV.get(k)[l]))) {
                    this.clientesRotas[Integer.parseInt(clientesV.get(k)[l])][k-1] = 1;
                }
            }
        }

        for (int i = 0; i < clientes; i++) {
            for (int j = 0; j < rotas; j++) {
                System.out.print(this.clientesRotas[i][j] + " ");
            }
            System.out.print("\n");
        }

        return clientesRotas;
    }

    private void updateListaFuncionarios() {
        if (jCheckBoxMotorista.isSelected() && jCheckBoxOperador.isSelected()) {
            listaFuncionarios();
        } else if (jCheckBoxMotorista.isSelected()) {
            listaFuncionariosMotoristas();
        } else if (jCheckBoxOperador.isSelected()) {
            listaFuncionariosOperadores();
        } else {
            listaFuncionarios();
        }
    }

    private void updateListaClientes() {
        if (jCheckBoxActivosClientes.isSelected() && jCheckBoxInativosClientes.isSelected()) {
            listaClientes();
        } else if (jCheckBoxActivosClientes.isSelected()) {
            listaClientes();
        } else if (jCheckBoxInativosClientes.isSelected()) {
            listaClientesInativos();
        } else {
            listaClientes();
        }
    }

    private void atualizaCliente(int key, Cliente c) {
        try {
            String sql = "UPDATE Cliente SET"
                    + " NomeFarmacia = '" + c.getNome_farmacia()
                    + "',NomeFarmaceutico= '" + c.getNome_farmaceutico()
                    + "',Contacto = " + c.getContacto()
                    + " ,Nif = " + c.getNif()
                    + " WHERE id_cliente =" + key;
            PreparedStatement stm = ConexaoBD.getConexao().prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
        }
    }

    private void atualizaLocal(int key, Local l) {
        try {
            String sql = "UPDATE Local SET"
                    + " Morada = '" + l.getMorada()
                    + "',Concelho= '" + l.getConcelho()
                    + " WHERE id_local=" + key;
            PreparedStatement stm = ConexaoBD.getConexao().prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
        }
    }

    private void atualizaFuncionario(int key, Funcionario f) {
        try {
            String sql = "UPDATE Funcionario SET"
                    + " Nome = '" + f.getNome()
                    + "',Data_Nasc = '" + f.getData_nasc()
                    + "',Morada= '" + f.getMorada()
                    + "',Contacto= " + f.getContacto()
                    + " ,Funcao= '" + f.getFuncao()
                    + "' WHERE id_funcionario=" + key;
            PreparedStatement stm = ConexaoBD.getConexao().prepareStatement(sql);
            stm.executeUpdate();
            stm.close();
        } catch (SQLException e) {
        }
    }

    public void listaProdutos() {
        DefaultListModel<String> str = new DefaultListModel<>();
        for (int i : this.sistema.getProdutos().keySet()) {
            str.addElement(this.sistema.getProdutos().get(i).getNome());
        }
        jListProdutos.setModel(str);
        jListProdutos.setSelectedIndex(0);
    }

    public void listaRotas() {
        DefaultListModel<Integer> str = new DefaultListModel<>();
        for (int i : this.sistema.getRotas().keySet()) {
            str.addElement(this.sistema.getRotas().get(i).getId_rota());
        }
        jListRotas.setModel(str);
        jListRotas.setSelectedIndex(0);
    }

    public void listaClientes() {
        DefaultListModel<String> str = new DefaultListModel<>();
        for (int i : this.sistema.getClientes().keySet()) {
            str.addElement(this.sistema.getClientes().get(i).getNome_farmacia());
        }
        jListClientes.setModel(str);
        jListClientes.setSelectedIndex(0);
    }

    public void listaEncomendas() {
        DefaultListModel<Integer> str = new DefaultListModel<>();
//        TreeSet<String> ordena = new TreeSet<>();
        Cliente c;
        //int id=0;
        for (int i : this.sistema.getEncomendas().keySet()) {
            str.addElement(this.sistema.getEncomendas().get(i).getId_encomenda());
            //c = this.sistema.getClientes().get(id);
            //ordena.add(c.getNome_farmacia());
        }
        /*
         Iterator<String> it = ordena.iterator();
         while(it.hasNext()){
         str.addElement(it.next());
         }
         */
        jListEncomendas.setModel(str);
        jListEncomendas.setSelectedIndex(0);
    }

    public Set<Integer> keysetFuncionariosMotoristas() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Funcionario WHERE activo = 1 and funcao='Motorista'";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                res.add(rs.getInt(1));
            }

            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    public Set<Integer> keysetFuncionariosOperadores() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Funcionario WHERE activo = 1 and funcao='Operador'";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                res.add(rs.getInt(1));
            }

            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    public Set<Integer> keysetClientesInativos() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Cliente WHERE activo = 0";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                res.add(rs.getInt(1));
            }

            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    public Set<Integer> keysetProdutosEncomendas(int id) {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT * FROM Produto WHERE activo = 1 and E_Id_Encomenda=" + id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                res.add(rs.getInt(1));
            }
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    public void listaFuncionarios() {
        DefaultListModel<String> str = new DefaultListModel<String>();
        for (int i : this.sistema.getFuncionarios().keySet()) {
            str.addElement(this.sistema.getFuncionarios().get(i).getNome());
        }
        jListFuncionarios.setModel(str);
        jListFuncionarios.setSelectedIndex(0);
    }

    public void listaProdutosEncomendas(int id) {
        DefaultListModel<String> str = new DefaultListModel<String>();
        for (int i : keysetProdutosEncomendas(id)) {
            str.addElement(this.sistema.getProdutos().get(i).getNome());
        }
        jListProdutosEncomendas.setModel(str);
    }

    public void listaFuncionariosMotoristas() {
        DefaultListModel<String> str = new DefaultListModel<String>();
        for (int i : keysetFuncionariosMotoristas()) {
            str.addElement(this.sistema.getFuncionarios().get(i).getNome());
        }
        jListFuncionarios.setModel(str);
        jListFuncionarios.setSelectedIndex(0);
    }

    public void listaFuncionariosOperadores() {
        DefaultListModel<String> str = new DefaultListModel<String>();
        for (int i : keysetFuncionariosOperadores()) {
            str.addElement(this.sistema.getFuncionarios().get(i).getNome());
        }
        jListFuncionarios.setModel(str);
        jListFuncionarios.setSelectedIndex(0);
    }

    public void listaClientesInativos() {
        DefaultListModel<String> str = new DefaultListModel<String>();
        for (int i : keysetClientesInativos()) {
            str.addElement(this.sistema.getClientes().get(i).getNome_farmacia());
        }
        jListClientes.setModel(str);
        jListClientes.setSelectedIndex(0);
    }

    public String seleccionaProduto() {
        String s = null;
        if (jListProdutos.getSelectedIndex() != -1) {
            s = jListProdutos.getSelectedValue().toString();
        }
        return s;
    }

    public String seleccionaFuncionario() {
        String s = null;
        if (jListFuncionarios.getSelectedIndex() != -1) {
            s = jListFuncionarios.getSelectedValue().toString();
        }
        return s;
    }

    public String seleccionaCliente() {
        String s = null;
        if (jListClientes.getSelectedIndex() != -1) {
            s = jListClientes.getSelectedValue().toString();
        }
        return s;
    }

    public String seleccionaEncomenda() {
        String s = null;
        if (jListEncomendas.getSelectedIndex() != -1) {
            s = jListEncomendas.getSelectedValue().toString();
        }
        return s;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tabs = new javax.swing.JTabbedPane();
        Rotas = new javax.swing.JPanel();
        jLabelRotas = new javax.swing.JLabel();
        jScrollPaneRotas = new javax.swing.JScrollPane();
        jListRotas = new javax.swing.JList();
        jLabelImagem = new javax.swing.JLabel();
        jLabelSessao = new javax.swing.JLabel();
        jTextPaneSessao = new javax.swing.JTextPane();
        jLabelRota = new javax.swing.JLabel();
        jLabelDataHora = new javax.swing.JLabel();
        jTextPaneDataHora = new javax.swing.JTextPane();
        jTextPaneRotaID = new javax.swing.JTextPane();
        jLabelMotorista = new javax.swing.JLabel();
        jComboBoxMotorista = new javax.swing.JComboBox();
        jLabelVeiculo = new javax.swing.JLabel();
        jComboBoxVeiculo = new javax.swing.JComboBox();
        jScrollPaneLocais = new javax.swing.JScrollPane();
        jListLocais = new javax.swing.JList();
        jLabelLocais = new javax.swing.JLabel();
        jButtonValidar = new javax.swing.JButton();
        jTextPaneAprovacao = new javax.swing.JTextPane();
        Encomendas = new javax.swing.JPanel();
        jLabelEncomendas = new javax.swing.JLabel();
        jLabelImagem1 = new javax.swing.JLabel();
        jLabelSessao1 = new javax.swing.JLabel();
        jTextPaneSessao1 = new javax.swing.JTextPane();
        jLabelEncomenda = new javax.swing.JLabel();
        jTextPaneClienteEncomenda = new javax.swing.JTextPane();
        jScrollPaneEncomendas = new javax.swing.JScrollPane();
        jListEncomendas = new javax.swing.JList();
        jLabelNumeroBanheirasEncomendas = new javax.swing.JLabel();
        jTextPaneNumeroBanheirasEncomendas = new javax.swing.JTextPane();
        jScrollPaneProdutosEncomendas = new javax.swing.JScrollPane();
        jListProdutosEncomendas = new javax.swing.JList();
        jLabelProdutosEncomendas = new javax.swing.JLabel();
        jLabelClienteEncomenda = new javax.swing.JLabel();
        jTextPaneFatura = new javax.swing.JTextPane();
        Clientes = new javax.swing.JPanel();
        jLabelSessao4 = new javax.swing.JLabel();
        jTextPaneSessao4 = new javax.swing.JTextPane();
        jLabelImagem4 = new javax.swing.JLabel();
        jLabelClientes = new javax.swing.JLabel();
        jScrollPaneClientes = new javax.swing.JScrollPane();
        jListClientes = new javax.swing.JList();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jLabelInformacao = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelNIF = new javax.swing.JLabel();
        jTextPaneNomeCliente = new javax.swing.JTextPane();
        jTextPaneMoradaCliente = new javax.swing.JTextPane();
        jLabelNomeFarmaceutico = new javax.swing.JLabel();
        jTextPaneNomeFarmaceutico = new javax.swing.JTextPane();
        jTextPaneNIF = new javax.swing.JTextPane();
        jLabelNomeTelefoneCliente = new javax.swing.JLabel();
        jTextPaneTelefoneCliente = new javax.swing.JTextPane();
        jLabelNomeMorada = new javax.swing.JLabel();
        jButtonEditarClientes = new javax.swing.JButton();
        jButtonGuardarClientes = new javax.swing.JButton();
        jTextPaneConcelho = new javax.swing.JTextPane();
        jLabelNomeMorada1 = new javax.swing.JLabel();
        jCheckBoxActivosClientes = new javax.swing.JCheckBox();
        jCheckBoxInativosClientes = new javax.swing.JCheckBox();
        Funcionarios = new javax.swing.JPanel();
        jLabelSessao5 = new javax.swing.JLabel();
        jTextPaneSessao5 = new javax.swing.JTextPane();
        jLabelImagem5 = new javax.swing.JLabel();
        jScrollPaneFuncionarios = new javax.swing.JScrollPane();
        jListFuncionarios = new javax.swing.JList();
        jLabelFuncionarios = new javax.swing.JLabel();
        jButtonAdicionarFuncionario = new javax.swing.JButton();
        jButtonRemoverFuncionario = new javax.swing.JButton();
        jLabelInformacao1 = new javax.swing.JLabel();
        jLabelNomeFuncionario = new javax.swing.JLabel();
        jLabelDataNascimento = new javax.swing.JLabel();
        jTextPaneNomeFuncionario = new javax.swing.JTextPane();
        jTextPaneMoradaFuncionario = new javax.swing.JTextPane();
        jLabelFuncao = new javax.swing.JLabel();
        jTextPaneFuncao = new javax.swing.JTextPane();
        jTextPaneDataNascimento = new javax.swing.JTextPane();
        jLabelNomeTelefoneFuncionario = new javax.swing.JLabel();
        jTextPaneTelefoneFuncionario = new javax.swing.JTextPane();
        jLabelNomeMoradaFuncionario = new javax.swing.JLabel();
        jButtonEditarFuncionario = new javax.swing.JButton();
        jButtonGuardarFuncionario = new javax.swing.JButton();
        jCheckBoxMotorista = new javax.swing.JCheckBox();
        jCheckBoxOperador = new javax.swing.JCheckBox();
        Produtos = new javax.swing.JPanel();
        jLabelProdutos = new javax.swing.JLabel();
        jScrollPaneProdutos = new javax.swing.JScrollPane();
        jListProdutos = new javax.swing.JList();
        jLabelImagem7 = new javax.swing.JLabel();
        jLabelSessao7 = new javax.swing.JLabel();
        jTextPaneSessao7 = new javax.swing.JTextPane();
        jLabelProduto = new javax.swing.JLabel();
        jLabelDataTipo = new javax.swing.JLabel();
        jTextPaneTipo = new javax.swing.JTextPane();
        jTextPaneProduto = new javax.swing.JTextPane();
        jLabelPreco = new javax.swing.JLabel();
        jLabelObservacoes = new javax.swing.JLabel();
        jTextPaneObservacoes = new javax.swing.JTextPane();
        jTextPanePreco = new javax.swing.JTextPane();
        jButtonAdicionarProduto = new javax.swing.JButton();
        jButtonRemoverProduto = new javax.swing.JButton();
        Menu = new javax.swing.JMenuBar();
        MenuOpcoes = new javax.swing.JMenu();
        Sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabelRotas.setText("Rotas");

        jScrollPaneRotas.setViewportView(jListRotas);

        jLabelImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jLabelSessao.setText("Sessão activa para:");

        jLabelRota.setText("Rota:");

        jLabelDataHora.setText("Data/Hora:");

        jLabelMotorista.setText("Motorista:");

        jComboBoxMotorista.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMotoristaActionPerformed(evt);
            }
        });

        jLabelVeiculo.setText("Veículo:");

        jComboBoxVeiculo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVeiculoActionPerformed(evt);
            }
        });

        jScrollPaneLocais.setViewportView(jListLocais);

        jLabelLocais.setText("Locais:");

        jButtonValidar.setText("Validar Rota");

        javax.swing.GroupLayout RotasLayout = new javax.swing.GroupLayout(Rotas);
        Rotas.setLayout(RotasLayout);
        RotasLayout.setHorizontalGroup(
            RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RotasLayout.createSequentialGroup()
                        .addComponent(jScrollPaneRotas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jScrollPaneLocais, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPaneAprovacao, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jLabelLocais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(RotasLayout.createSequentialGroup()
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRotas, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addComponent(jLabelSessao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextPaneSessao, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addComponent(jLabelRota, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jTextPaneRotaID, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabelImagem))
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelMotorista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelDataHora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(jLabelVeiculo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPaneDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(19, Short.MAX_VALUE))))
        );
        RotasLayout.setVerticalGroup(
            RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RotasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSessao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPaneSessao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabelRotas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(RotasLayout.createSequentialGroup()
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPaneRotaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRota, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPaneDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLocais, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneLocais, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RotasLayout.createSequentialGroup()
                                .addComponent(jButtonValidar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextPaneAprovacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPaneRotas))
                .addGap(0, 65, Short.MAX_VALUE))
        );

        Tabs.addTab("Rotas", Rotas);

        jLabelEncomendas.setText("Encomendas");

        jLabelImagem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jLabelSessao1.setText("Sessão activa para:");

        jLabelEncomenda.setText("Factura:");

        jListEncomendas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListEncomendasValueChanged(evt);
            }
        });
        jScrollPaneEncomendas.setViewportView(jListEncomendas);

        jLabelNumeroBanheirasEncomendas.setText("Nº Banheiras:");

        jScrollPaneProdutosEncomendas.setViewportView(jListProdutosEncomendas);

        jLabelProdutosEncomendas.setText("Produtos:");

        jLabelClienteEncomenda.setText("Cliente:");

        javax.swing.GroupLayout EncomendasLayout = new javax.swing.GroupLayout(Encomendas);
        Encomendas.setLayout(EncomendasLayout);
        EncomendasLayout.setHorizontalGroup(
            EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncomendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EncomendasLayout.createSequentialGroup()
                        .addComponent(jScrollPaneEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EncomendasLayout.createSequentialGroup()
                                .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelNumeroBanheirasEncomendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelEncomenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelProdutosEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelClienteEncomenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPaneNumeroBanheirasEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextPaneFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextPaneClienteEncomenda, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPaneProdutosEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(EncomendasLayout.createSequentialGroup()
                        .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelEncomendas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSessao1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextPaneSessao1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(jLabelImagem1)
                        .addGap(19, 19, 19))))
        );
        EncomendasLayout.setVerticalGroup(
            EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EncomendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSessao1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPaneSessao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelImagem1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabelEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(EncomendasLayout.createSequentialGroup()
                        .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelClienteEncomenda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPaneClienteEncomenda, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPaneFatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelEncomenda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EncomendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextPaneNumeroBanheirasEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNumeroBanheirasEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelProdutosEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPaneProdutosEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneEncomendas, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        Tabs.addTab("Encomendas", Encomendas);

        jLabelSessao4.setText("Sessão activa para:");

        jLabelImagem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jLabelClientes.setText("Clientes");

        jListClientes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListClientesValueChanged(evt);
            }
        });
        jScrollPaneClientes.setViewportView(jListClientes);

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonRemover.setText("Remover");

        jLabelInformacao.setText("Informação Geral");

        jLabelNome.setText("Farmácia:");

        jLabelNIF.setText("NIF:");

        jLabelNomeFarmaceutico.setText("Nome Farmacêutico:");

        jLabelNomeTelefoneCliente.setText("Telefone:");

        jLabelNomeMorada.setText("Morada:");

        jButtonEditarClientes.setText("Editar");
        jButtonEditarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarClientesActionPerformed(evt);
            }
        });

        jButtonGuardarClientes.setText("Guardar");
        jButtonGuardarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarClientesActionPerformed(evt);
            }
        });

        jLabelNomeMorada1.setText("Concelho");

        jCheckBoxActivosClientes.setText("Activo");
        jCheckBoxActivosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActivosClientesActionPerformed(evt);
            }
        });

        jCheckBoxInativosClientes.setText("Inactivos");
        jCheckBoxInativosClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxInativosClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ClientesLayout = new javax.swing.GroupLayout(Clientes);
        Clientes.setLayout(ClientesLayout);
        ClientesLayout.setHorizontalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addComponent(jLabelSessao4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextPaneSessao4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabelImagem4))
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addComponent(jButtonAdicionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemover))
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(jCheckBoxActivosClientes)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxInativosClientes)))
                        .addGap(78, 78, 78)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInformacao)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPaneNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPaneNomeFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNomeFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(jButtonEditarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonGuardarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextPaneNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextPaneMoradaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNomeMorada, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ClientesLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabelNomeTelefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ClientesLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelNomeMorada1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextPaneTelefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextPaneConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        ClientesLayout.setVerticalGroup(
            ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImagem4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSessao4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPaneSessao4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNomeFarmaceutico, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextPaneNomeFarmaceutico, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                            .addComponent(jTextPaneNomeCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNomeTelefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(jTextPaneNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNomeMorada, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ClientesLayout.createSequentialGroup()
                                .addComponent(jTextPaneTelefoneCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNomeMorada1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPaneConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPaneMoradaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonEditarClientes)
                            .addComponent(jButtonGuardarClientes))
                        .addGap(37, 37, 37))
                    .addGroup(ClientesLayout.createSequentialGroup()
                        .addComponent(jScrollPaneClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxActivosClientes)
                            .addComponent(jCheckBoxInativosClientes))
                        .addGap(18, 18, 18)))
                .addGroup(ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionar)
                    .addComponent(jButtonRemover))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        Tabs.addTab("Clientes", Clientes);

        jLabelSessao5.setText("Sessão activa para:");

        jLabelImagem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jListFuncionarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListFuncionariosValueChanged(evt);
            }
        });
        jScrollPaneFuncionarios.setViewportView(jListFuncionarios);

        jLabelFuncionarios.setText("Funcionários");

        jButtonAdicionarFuncionario.setText("Adicionar");
        jButtonAdicionarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarFuncionarioActionPerformed(evt);
            }
        });

        jButtonRemoverFuncionario.setText("Remover");
        jButtonRemoverFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverFuncionarioActionPerformed(evt);
            }
        });

        jLabelInformacao1.setText("Informação Geral");

        jLabelNomeFuncionario.setText("Nome:");

        jLabelDataNascimento.setText("Data Nascimento:");

        jLabelFuncao.setText("Função");

        jLabelNomeTelefoneFuncionario.setText("Telefone:");

        jLabelNomeMoradaFuncionario.setText("Morada:");

        jButtonEditarFuncionario.setText("Editar");
        jButtonEditarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarFuncionarioActionPerformed(evt);
            }
        });

        jButtonGuardarFuncionario.setText("Guardar");
        jButtonGuardarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarFuncionarioActionPerformed(evt);
            }
        });

        jCheckBoxMotorista.setText("Motorista");
        jCheckBoxMotorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMotoristaActionPerformed(evt);
            }
        });

        jCheckBoxOperador.setText("Operador");
        jCheckBoxOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxOperadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FuncionariosLayout = new javax.swing.GroupLayout(Funcionarios);
        Funcionarios.setLayout(FuncionariosLayout);
        FuncionariosLayout.setHorizontalGroup(
            FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FuncionariosLayout.createSequentialGroup()
                        .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(FuncionariosLayout.createSequentialGroup()
                                .addComponent(jLabelSessao5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextPaneSessao5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addGroup(FuncionariosLayout.createSequentialGroup()
                                .addComponent(jScrollPaneFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonEditarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelImagem5)
                            .addComponent(jButtonGuardarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(FuncionariosLayout.createSequentialGroup()
                        .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxMotorista)
                            .addComponent(jButtonAdicionarFuncionario))
                        .addGap(18, 18, 18)
                        .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonRemoverFuncionario)
                            .addComponent(jCheckBoxOperador))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FuncionariosLayout.createSequentialGroup()
                    .addGap(252, 252, 252)
                    .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelInformacao1)
                        .addGroup(FuncionariosLayout.createSequentialGroup()
                            .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextPaneNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextPaneFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(FuncionariosLayout.createSequentialGroup()
                            .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextPaneDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelDataNascimento))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextPaneTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelNomeTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jTextPaneMoradaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelNomeMoradaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(63, Short.MAX_VALUE)))
        );
        FuncionariosLayout.setVerticalGroup(
            FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FuncionariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImagem5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSessao5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPaneSessao5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FuncionariosLayout.createSequentialGroup()
                        .addComponent(jScrollPaneFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FuncionariosLayout.createSequentialGroup()
                        .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonGuardarFuncionario)
                            .addComponent(jButtonEditarFuncionario))
                        .addGap(46, 46, 46)))
                .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMotorista)
                    .addComponent(jCheckBoxOperador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemoverFuncionario)
                    .addComponent(jButtonAdicionarFuncionario))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FuncionariosLayout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(jLabelInformacao1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(FuncionariosLayout.createSequentialGroup()
                            .addComponent(jLabelNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextPaneNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(FuncionariosLayout.createSequentialGroup()
                            .addComponent(jLabelFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextPaneFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(FuncionariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(FuncionariosLayout.createSequentialGroup()
                            .addComponent(jLabelDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextPaneDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(FuncionariosLayout.createSequentialGroup()
                            .addComponent(jLabelNomeTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextPaneTelefoneFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabelNomeMoradaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextPaneMoradaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );

        Tabs.addTab("Funcionários", Funcionarios);

        jLabelProdutos.setText("Produtos");

        jListProdutos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListProdutosValueChanged(evt);
            }
        });
        jScrollPaneProdutos.setViewportView(jListProdutos);

        jLabelImagem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N

        jLabelSessao7.setText("Sessão activa para:");

        jLabelProduto.setText("Produto:");

        jLabelDataTipo.setText("Tipo:");

        jLabelPreco.setText("Preço:");

        jLabelObservacoes.setText("Observações:");

        jButtonAdicionarProduto.setText("Adicionar");
        jButtonAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarProdutoActionPerformed(evt);
            }
        });

        jButtonRemoverProduto.setText("Remover");
        jButtonRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProdutosLayout = new javax.swing.GroupLayout(Produtos);
        Produtos.setLayout(ProdutosLayout);
        ProdutosLayout.setHorizontalGroup(
            ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addComponent(jScrollPaneProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProdutosLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ProdutosLayout.createSequentialGroup()
                                        .addComponent(jLabelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextPaneProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ProdutosLayout.createSequentialGroup()
                                        .addComponent(jLabelDataTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextPaneTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ProdutosLayout.createSequentialGroup()
                                        .addComponent(jLabelPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextPanePreco, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(ProdutosLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPaneObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addComponent(jLabelSessao7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextPaneSessao7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabelImagem7))
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addComponent(jButtonAdicionarProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoverProduto))
                    .addComponent(jLabelProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        ProdutosLayout.setVerticalGroup(
            ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProdutosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImagem7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSessao7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPaneSessao7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabelProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ProdutosLayout.createSequentialGroup()
                        .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextPaneProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDataTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPaneTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPanePreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextPaneObservacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneProdutos))
                .addGap(18, 18, 18)
                .addGroup(ProdutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionarProduto)
                    .addComponent(jButtonRemoverProduto))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        Tabs.addTab("Produtos", Produtos);

        MenuOpcoes.setText("Menu");

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        MenuOpcoes.add(Sair);

        Menu.add(MenuOpcoes);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_SairActionPerformed

    private void jComboBoxMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMotoristaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMotoristaActionPerformed

    private void jComboBoxVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVeiculoActionPerformed

    private void jButtonAdicionarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarFuncionarioActionPerformed
        // TODO add your handling code here:
        new AdicionarFuncionario(sistema).setVisible(true);
        listaFuncionarios();
    }//GEN-LAST:event_jButtonAdicionarFuncionarioActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        // TODO add your handling code here:
        new AdicionarCliente(sistema).setVisible(true);
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarProdutoActionPerformed
        new AdicionarProduto(sistema).setVisible(true);
        listaProdutos();
    }//GEN-LAST:event_jButtonAdicionarProdutoActionPerformed

    private void jListProdutosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListProdutosValueChanged
        // TODO add your handling code here:
        String aux = seleccionaProduto();
        Produto v = null;
        for (int i : this.sistema.getProdutos().keySet()) {
            if (this.sistema.getProdutos().get(i).getNome().equals(aux)) {
                v = this.sistema.getProdutos().get(i);
            }
        }
        if (aux != null && v != null) {
            this.jTextPaneProduto.setText(v.getNome());
            this.jTextPaneTipo.setText(v.getTipo());
            StringBuilder sb = new StringBuilder();
            String preco = Double.toString(v.getPreco());
            sb.append(preco);
            sb.append("€");
            this.jTextPanePreco.setText(sb.toString());
            this.jTextPaneObservacoes.setText(v.getDescricao());
        }

    }//GEN-LAST:event_jListProdutosValueChanged

    private void jButtonRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverProdutoActionPerformed
        // TODO add your handling code here:
        int key = 0;
        for (int i : this.sistema.getProdutos().keySet()) {
            if (this.sistema.getProdutos().get(i).getNome().equals(seleccionaProduto())) {
                key = i;
            }
        }
        this.sistema.getProdutos().remove(key);
        jListProdutos.clearSelection();
        listaProdutos();
        this.jTextPaneProduto.setText(null);
        this.jTextPaneTipo.setText(null);
        this.jTextPanePreco.setText(null);
        this.jTextPaneObservacoes.setText(null);

    }//GEN-LAST:event_jButtonRemoverProdutoActionPerformed

    private void jButtonRemoverFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverFuncionarioActionPerformed
        // TODO add your handling code here:
        int key = 0;
        for (int i : this.sistema.getFuncionarios().keySet()) {
            if (this.sistema.getFuncionarios().get(i).getNome().equals(seleccionaFuncionario())) {
                key = i;
            }
        }
        //System.out.println("KEY : " + key);
        this.sistema.getFuncionarios().remove(key);
        jListFuncionarios.clearSelection();
        listaFuncionarios();
        this.jTextPaneNomeFuncionario.setText(null);
        this.jTextPaneMoradaFuncionario.setText(null);
        this.jTextPaneTelefoneFuncionario.setText(null);
        this.jTextPaneFuncao.setText(null);
        this.jTextPaneDataNascimento.setText(null);
    }//GEN-LAST:event_jButtonRemoverFuncionarioActionPerformed

    private void jListFuncionariosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListFuncionariosValueChanged
        String aux = seleccionaFuncionario();
        Funcionario f = null;

        for (int i : this.sistema.getFuncionarios().keySet()) {
            if (this.sistema.getFuncionarios().get(i).getNome().equals(aux)) {
                f = this.sistema.getFuncionarios().get(i);
            }
        }
        if (aux != null && f != null) {
            this.jTextPaneNomeFuncionario.setText(f.getNome());
            this.jTextPaneMoradaFuncionario.setText(f.getMorada());
            String tlf = Integer.toString(f.getContacto());
            this.jTextPaneTelefoneFuncionario.setText(tlf);
            this.jTextPaneFuncao.setText(f.getFuncao());
            this.jTextPaneDataNascimento.setText(f.getData_nasc());
        }
    }//GEN-LAST:event_jListFuncionariosValueChanged

    private void jCheckBoxMotoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMotoristaActionPerformed
        updateListaFuncionarios();
    }//GEN-LAST:event_jCheckBoxMotoristaActionPerformed

    private void jCheckBoxOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxOperadorActionPerformed
        updateListaFuncionarios();
    }//GEN-LAST:event_jCheckBoxOperadorActionPerformed

    private void jListClientesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListClientesValueChanged
        String aux = seleccionaCliente();
        Cliente c = null;
        Local l = null;
        jButtonGuardarClientes.setVisible(false);
        for (int i : this.sistema.getClientes().keySet()) {
            if (this.sistema.getClientes().get(i).getNome_farmacia().equals(aux)) {
                c = this.sistema.getClientes().get(i);
                l = this.sistema.getLocais().get(c.getLocal_id_local());
            }
        }

        if (aux != null && c != null && l != null) {
            this.jTextPaneNomeCliente.setText(c.getNome_farmacia());
            this.jTextPaneNomeFarmaceutico.setText(c.getNome_farmaceutico());
            String tlf = Integer.toString(c.getContacto());
            this.jTextPaneTelefoneCliente.setText(tlf);
            String nif = Integer.toString(c.getNif());
            this.jTextPaneNIF.setText(nif);
            this.jTextPaneMoradaCliente.setText(l.getMorada());
            this.jTextPaneConcelho.setText(l.getConcelho());
        }
    }//GEN-LAST:event_jListClientesValueChanged

    private void jButtonEditarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarFuncionarioActionPerformed

        jButtonEditarFuncionario.setVisible(false);
        jButtonGuardarFuncionario.setVisible(true);

        jTextPaneNomeFuncionario.setEditable(true);
        jTextPaneFuncao.setEditable(true);
        jTextPaneDataNascimento.setEditable(true);
        jTextPaneTelefoneFuncionario.setEditable(true);
        jTextPaneMoradaFuncionario.setEditable(true);
    }//GEN-LAST:event_jButtonEditarFuncionarioActionPerformed

    private void jButtonGuardarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarFuncionarioActionPerformed
        jButtonEditarFuncionario.setVisible(true);
        jButtonGuardarFuncionario.setVisible(false);

        jTextPaneNomeFuncionario.setEditable(false);
        jTextPaneFuncao.setEditable(false);
        jTextPaneDataNascimento.setEditable(false);
        jTextPaneTelefoneFuncionario.setEditable(false);
        jTextPaneMoradaFuncionario.setEditable(false);

        String aux = seleccionaFuncionario();
        int id = this.sistema.getFuncionario(aux).getId_funcionario();

        String nome = jTextPaneNomeFuncionario.getText();
        String funcao = jTextPaneFuncao.getText();
        String data = jTextPaneDataNascimento.getText();
        String tlf = jTextPaneTelefoneFuncionario.getText();
        int tlfInt = Integer.parseInt(tlf);
        String morada = jTextPaneMoradaFuncionario.getText();

        Funcionario f = new Funcionario(id, nome, data, morada, tlfInt, funcao);
        atualizaFuncionario(id, f);
        listaFuncionarios();
    }//GEN-LAST:event_jButtonGuardarFuncionarioActionPerformed

    private void jButtonEditarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarClientesActionPerformed
        this.jButtonGuardarClientes.setVisible(true);
        this.jButtonEditarClientes.setVisible(false);

        jTextPaneNomeCliente.setEditable(true);
        jTextPaneNomeFarmaceutico.setEditable(true);
        jTextPaneNIF.setEditable(true);
        jTextPaneTelefoneCliente.setEditable(true);
        jTextPaneMoradaCliente.setEditable(true);
        jTextPaneConcelho.setEditable(true);

    }//GEN-LAST:event_jButtonEditarClientesActionPerformed

    private void jButtonGuardarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarClientesActionPerformed
        this.jButtonEditarClientes.setVisible(true);
        this.jButtonGuardarClientes.setVisible(false);

        jTextPaneNomeCliente.setEditable(false);
        jTextPaneNomeFarmaceutico.setEditable(false);
        jTextPaneNIF.setEditable(false);
        jTextPaneTelefoneCliente.setEditable(false);
        jTextPaneMoradaCliente.setEditable(false);
        jTextPaneConcelho.setEditable(false);

        String aux = seleccionaCliente();
        int idC = this.sistema.getCliente(aux).getId_cliente();
        int idL = this.sistema.getCliente(aux).getLocal_id_local();

        String nome = jTextPaneNomeCliente.getText();
        String nomeFarm = jTextPaneNomeFarmaceutico.getText();
        String nif = jTextPaneNIF.getText();
        int nifInt = Integer.parseInt(nif);
        String tlf = jTextPaneTelefoneCliente.getText();
        int tlfInt = Integer.parseInt(tlf);
        String morada = jTextPaneMoradaFuncionario.getText();
        String concelho = jTextPaneMoradaFuncionario.getText();

        Local l = new Local(idL, morada, concelho);
        Cliente c = new Cliente(idC, nome, nomeFarm, tlfInt, nifInt);

        atualizaLocal(idL, l);
        atualizaCliente(idC, c);
        listaClientes();

    }//GEN-LAST:event_jButtonGuardarClientesActionPerformed

    private void jCheckBoxActivosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivosClientesActionPerformed
        updateListaClientes();
    }//GEN-LAST:event_jCheckBoxActivosClientesActionPerformed

    private void jCheckBoxInativosClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInativosClientesActionPerformed
        updateListaClientes();
    }//GEN-LAST:event_jCheckBoxInativosClientesActionPerformed

    private void jListEncomendasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListEncomendasValueChanged
        String aux = seleccionaEncomenda();
        int id = Integer.parseInt(aux);
        int idCliente = this.sistema.getEncomendas().get(id).getCliente_id_cliente();
        Cliente c = this.sistema.getClientes().get(idCliente);
        Encomenda e = null;
        for (int i : this.sistema.getEncomendas().keySet()) {
            if (this.sistema.getEncomendas().get(i).getId_encomenda() == id) {
                e = this.sistema.getEncomendas().get(i);
            }
        }

        if (aux != null && e != null) {
            String fatura = Integer.toString(e.getFactura());
            this.jTextPaneFatura.setText(fatura);
            String banheiras = Integer.toString(e.getBanheiras());
            this.jTextPaneNumeroBanheirasEncomendas.setText(banheiras);
            this.jTextPaneClienteEncomenda.setText(c.getNome_farmacia());
            listaProdutosEncomendas(id);
        }


    }//GEN-LAST:event_jListEncomendasValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clientes;
    private javax.swing.JPanel Encomendas;
    private javax.swing.JPanel Funcionarios;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuOpcoes;
    private javax.swing.JPanel Produtos;
    private javax.swing.JPanel Rotas;
    private javax.swing.JMenuItem Sair;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonAdicionarFuncionario;
    private javax.swing.JButton jButtonAdicionarProduto;
    private javax.swing.JButton jButtonEditarClientes;
    private javax.swing.JButton jButtonEditarFuncionario;
    private javax.swing.JButton jButtonGuardarClientes;
    private javax.swing.JButton jButtonGuardarFuncionario;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonRemoverFuncionario;
    private javax.swing.JButton jButtonRemoverProduto;
    private javax.swing.JButton jButtonValidar;
    private javax.swing.JCheckBox jCheckBoxActivosClientes;
    private javax.swing.JCheckBox jCheckBoxInativosClientes;
    private javax.swing.JCheckBox jCheckBoxMotorista;
    private javax.swing.JCheckBox jCheckBoxOperador;
    private javax.swing.JComboBox jComboBoxMotorista;
    private javax.swing.JComboBox jComboBoxVeiculo;
    private javax.swing.JLabel jLabelClienteEncomenda;
    private javax.swing.JLabel jLabelClientes;
    private javax.swing.JLabel jLabelDataHora;
    private javax.swing.JLabel jLabelDataNascimento;
    private javax.swing.JLabel jLabelDataTipo;
    private javax.swing.JLabel jLabelEncomenda;
    private javax.swing.JLabel jLabelEncomendas;
    private javax.swing.JLabel jLabelFuncao;
    private javax.swing.JLabel jLabelFuncionarios;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelImagem1;
    private javax.swing.JLabel jLabelImagem4;
    private javax.swing.JLabel jLabelImagem5;
    private javax.swing.JLabel jLabelImagem7;
    private javax.swing.JLabel jLabelInformacao;
    private javax.swing.JLabel jLabelInformacao1;
    private javax.swing.JLabel jLabelLocais;
    private javax.swing.JLabel jLabelMotorista;
    private javax.swing.JLabel jLabelNIF;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeFarmaceutico;
    private javax.swing.JLabel jLabelNomeFuncionario;
    private javax.swing.JLabel jLabelNomeMorada;
    private javax.swing.JLabel jLabelNomeMorada1;
    private javax.swing.JLabel jLabelNomeMoradaFuncionario;
    private javax.swing.JLabel jLabelNomeTelefoneCliente;
    private javax.swing.JLabel jLabelNomeTelefoneFuncionario;
    private javax.swing.JLabel jLabelNumeroBanheirasEncomendas;
    private javax.swing.JLabel jLabelObservacoes;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelProduto;
    private javax.swing.JLabel jLabelProdutos;
    private javax.swing.JLabel jLabelProdutosEncomendas;
    private javax.swing.JLabel jLabelRota;
    private javax.swing.JLabel jLabelRotas;
    private javax.swing.JLabel jLabelSessao;
    private javax.swing.JLabel jLabelSessao1;
    private javax.swing.JLabel jLabelSessao4;
    private javax.swing.JLabel jLabelSessao5;
    private javax.swing.JLabel jLabelSessao7;
    private javax.swing.JLabel jLabelVeiculo;
    private javax.swing.JList jListClientes;
    private javax.swing.JList jListEncomendas;
    private javax.swing.JList jListFuncionarios;
    private javax.swing.JList jListLocais;
    private javax.swing.JList jListProdutos;
    private javax.swing.JList jListProdutosEncomendas;
    private javax.swing.JList jListRotas;
    private javax.swing.JScrollPane jScrollPaneClientes;
    private javax.swing.JScrollPane jScrollPaneEncomendas;
    private javax.swing.JScrollPane jScrollPaneFuncionarios;
    private javax.swing.JScrollPane jScrollPaneLocais;
    private javax.swing.JScrollPane jScrollPaneProdutos;
    private javax.swing.JScrollPane jScrollPaneProdutosEncomendas;
    private javax.swing.JScrollPane jScrollPaneRotas;
    private javax.swing.JTextPane jTextPaneAprovacao;
    private javax.swing.JTextPane jTextPaneClienteEncomenda;
    private javax.swing.JTextPane jTextPaneConcelho;
    private javax.swing.JTextPane jTextPaneDataHora;
    private javax.swing.JTextPane jTextPaneDataNascimento;
    private javax.swing.JTextPane jTextPaneFatura;
    private javax.swing.JTextPane jTextPaneFuncao;
    private javax.swing.JTextPane jTextPaneMoradaCliente;
    private javax.swing.JTextPane jTextPaneMoradaFuncionario;
    private javax.swing.JTextPane jTextPaneNIF;
    private javax.swing.JTextPane jTextPaneNomeCliente;
    private javax.swing.JTextPane jTextPaneNomeFarmaceutico;
    private javax.swing.JTextPane jTextPaneNomeFuncionario;
    private javax.swing.JTextPane jTextPaneNumeroBanheirasEncomendas;
    private javax.swing.JTextPane jTextPaneObservacoes;
    private javax.swing.JTextPane jTextPanePreco;
    private javax.swing.JTextPane jTextPaneProduto;
    private javax.swing.JTextPane jTextPaneRotaID;
    private javax.swing.JTextPane jTextPaneSessao;
    private javax.swing.JTextPane jTextPaneSessao1;
    private javax.swing.JTextPane jTextPaneSessao4;
    private javax.swing.JTextPane jTextPaneSessao5;
    private javax.swing.JTextPane jTextPaneSessao7;
    private javax.swing.JTextPane jTextPaneTelefoneCliente;
    private javax.swing.JTextPane jTextPaneTelefoneFuncionario;
    private javax.swing.JTextPane jTextPaneTipo;
    // End of variables declaration//GEN-END:variables
}
