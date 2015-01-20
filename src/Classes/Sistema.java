/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.ArmazemDAO;
import ClassesDAO.ClienteDAO;
import ClassesDAO.ConexaoBD;
import ClassesDAO.DistanciaDAO;
import ClassesDAO.EncomendaDAO;
import ClassesDAO.FuncionarioDAO;
import ClassesDAO.LocalDAO;
import ClassesDAO.RotaDAO;
import ClassesDAO.LoginDAO;
import ClassesDAO.ProdutoDAO;
import ClassesDAO.RotasEscolhidasDAO;
import ClassesDAO.VeiculoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class Sistema {

    private Map<Integer, Armazem> armazens;
    private Map<Integer, Rota> rotas;
    private Map<Integer, RotasEscolhidas> rotasEscolhidas;
    private Map<Integer, Login> logins;
    private Map<Integer, Produto> produtos;
    private Map<Integer, Funcionario> funcionarios;
    private Map<Integer, Local> locais;
    private Map<Integer, Cliente> clientes;
    private Map<Integer, Encomenda> encomendas;
    private Map<Integer, Distancia> distancias;
    private Map<Integer, Veiculo> veiculos;
    private int[][] clientesRotas;
    private String activo;

    public Sistema() {
        this.armazens = new ArmazemDAO();
        this.rotas = new RotaDAO();
        this.rotasEscolhidas = new RotasEscolhidasDAO();
        this.logins = new LoginDAO();
        this.produtos=new ProdutoDAO();
        this.funcionarios=new FuncionarioDAO();
        this.locais=new LocalDAO();
        this.clientes= new ClienteDAO();
        this.encomendas = new EncomendaDAO();
        this.veiculos=new VeiculoDAO();
        this.distancias=new DistanciaDAO();
        this.clientesRotas =null;
        this.activo = null;
        ConexaoBD.iniciarConexao();
    }

    public Map<Integer, Distancia> getDistancias() {
        return distancias;
    }

    public int devolve_distancias(int origem, int destino) {
        int tmp;
        if(origem>destino && origem!=destino){
            tmp=origem;
            origem=destino;
            destino=tmp;
        }
        else return 0;
        PreparedStatement ps = null;
        int distancia = 0;
        try {
            String sql = "select devolve_distancia(?,?) from dual";
            ps = ConexaoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, origem);
            ps.setInt(2, destino);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                distancia = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return distancia;
    }
    
    public Map<Integer, String[]> parseClientes() { // idRota-> Clientes
        Map<Integer, String[]> clientesVisitados = new HashMap<>();
        String lista;
        String delim = "[-]";

        for (int k : getRotas().keySet()) {
            lista = getRotas().get(k).getListaClientes();
            String[] tokens = lista.split(delim);
            clientesVisitados.put(k, tokens);

        }
        return clientesVisitados;
    }

    public int[][] ClientesRotas() {
        int clientes = getClientes().size();
        int rotas = getRotas().size();
        this.clientesRotas = new int[clientes][rotas];
        Map<Integer, String[]> clientesV = parseClientes();

        for (int k : clientesV.keySet()) {
            for (int l = 0; l < clientesV.get(k).length; l++) {
                if (getClientes().containsKey(Integer.parseInt(clientesV.get(k)[l]))) {
                    this.clientesRotas[Integer.parseInt(clientesV.get(k)[l])][k - 1] = 1;
                }
            }
        }
        /*
         for (int i = 0; i < clientes; i++) {
         for (int j = 0; j < rotas; j++) {
         System.out.print(this.clientesRotas[i][j] + " ");
         }
         System.out.print("\n");
         }
         */
        return clientesRotas;
    }
    
    
    
    
    
    public int[][] getClientesRotas() {
        return clientesRotas;
    }

    public Map<Integer, Veiculo> getVeiculos() {
        return veiculos;
    }

    
    public Map<Integer, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Map<Integer, Armazem> getArmazens() {
        return armazens;
    }

    public Map<Integer, Rota> getRotas() {
        return rotas;
    }

    public Map<Integer, Login> getLogins() {
        return logins;
    }

    public Map<Integer, Produto> getProdutos() {
        return produtos;
    }

    public Map<Integer, Local> getLocais() {
        return locais;
    }

    public Map<Integer, Cliente> getClientes() {
        return clientes;
    }
    
    public Map<Integer, Encomenda> getEncomendas(){
        return encomendas;
    }

    public Map<Integer, RotasEscolhidas> getRotasEscolhidas() {
        return rotasEscolhidas;
    }
    
    
    
    public int addProduto(int id,Produto p){
        for (Integer i : this.produtos.keySet()) {
            if (this.produtos.get(i).equals(p)) {
                return -1;
            }
        }
        this.produtos.put(id, p);
        return 1;
    }
    
    public int addFuncionario(int id,Funcionario f){
        for (Integer i : this.funcionarios.keySet()) {
            if (this.funcionarios.get(i).equals(f)) {
                return -1;
            }
        }
        this.funcionarios.put(id, f);
        return 1;
    }
    
    public int addLocal(int id,Local l){
        for (Integer i : this.locais.keySet()) {
            if (this.locais.get(i).equals(l)) {
                return -1;
            }
        }
        this.locais.put(id, l);
        return 1;
    }
    
    public int addCliente(int id,Cliente c){
        for (Integer i : this.clientes.keySet()) {
            if (this.clientes.get(i).equals(c)) {
                return -1;
            }
        }
        this.clientes.put(id, c);
        return 1;
    }
    
    
    public String getActivo(){
        return this.activo;
        
    }
    
    public void setActivo(String user){
        this.activo=user;
    }
    
    public Cliente getCliente(String nome) {
        for (int i : this.clientes.keySet()) {
            if (this.clientes.get(i).getNome_farmacia().equals(nome)) {
                return this.clientes.get(i);
            }
        }
        return null;
    }
    
    
    public Funcionario getFuncionario(String nome) {
        for (int i : this.funcionarios.keySet()) {
            if (this.funcionarios.get(i).getNome().equals(nome)) {
                return this.funcionarios.get(i);
            }
        }
        return null;
    }
    
    public Veiculo getVeiculo(String matricula) {
        for (int i : this.veiculos.keySet()) {
            if (this.veiculos.get(i).getMatricula().equals(matricula)) {
                return this.veiculos.get(i);
            }
        }
        return null;
    }
    
    public Produto getProduto(String nome) {
        for (int i : this.produtos.keySet()) {
            if (this.produtos.get(i).getNome().equals(nome)) {
                return this.produtos.get(i);
            }
        }
        return null;
    }

    
    public int keyLogin(String login) {
        int key = 0;
        for (int l : this.logins.keySet()) {
           // System.out.println("Keyset: "+login);
            if (this.logins.get(l).getUser().equals(login)) {
             //   System.out.println("LoginPar:"+login);
               // System.out.println("LoginBD:"+this.logins.get(key));
                key = l;
            }
        }
        return key;
    }
    
    public String userActivo(int key){
        return this.logins.get(key).getUser();
    }
    
    public boolean validaLogin(String login, String password) {
        int key = keyLogin(login);
        
        return this.logins.get(key).getPass().equals(password);
    }

    public boolean ligaFuncionario(String login, String pass) {
        
        return validaLogin(login, pass);
    }
}
