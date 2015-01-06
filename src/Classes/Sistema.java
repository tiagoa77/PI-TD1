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
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class Sistema {

    private Map<Integer, Armazem> armazens;
    private Map<Integer, Rota> rotas;
    private Map<Integer, Login> logins;

    public Sistema() {
        this.armazens = new ArmazemDAO();
        this.rotas = new RotaDAO();
        this.logins = new LoginDAO();
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

    public boolean validaLogin(String login, String password) {
        int key = keyLogin(login);
        
        return this.logins.get(key).getPass().equals(password);
    }

    public boolean ligaFuncionario(String login, String pass) {
        return validaLogin(login, pass);
    }
}
