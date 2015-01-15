/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.ArmazemDAO;
import ClassesDAO.ConexaoBD;
import ClassesDAO.RotaDAO;
import ClassesDAO.LoginDAO;
import ClassesDAO.ProdutoDAO;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class Sistema {

    private Map<Integer, Armazem> armazens;
    private Map<Integer, Rota> rotas;
    private Map<Integer, Login> logins;
    private Map<Integer, Produto> produtos;
    private String activo;

    public Sistema() {
        this.armazens = new ArmazemDAO();
        this.rotas = new RotaDAO();
        this.logins = new LoginDAO();
        this.produtos=new ProdutoDAO();
        this.activo = null;
        ConexaoBD.iniciarConexao();
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
    
    public int addProduto(int id,Produto p){
        for (Integer i : this.produtos.keySet()) {
            if (this.produtos.get(i).equals(p)) {
                return -1;
            }
        }
        this.produtos.put(id, p);
        return 1;
    }
    
    
    
    public String getActivo(){
        return this.activo;
        
    }
    
    public void setActivo(String user){
        this.activo=user;
    }
    
    public int keyLogin(String login) {
        int key = 0;
        for (int l : this.logins.keySet()) {
            System.out.println("Keyset: "+login);
            if (this.logins.get(l).getUser().equals(login)) {
                System.out.println("LoginPar:"+login);
                System.out.println("LoginBD:"+this.logins.get(key));
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
