/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.ArmazemDAO;
import ClassesDAO.RotaDAO;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class Sistema {
    
    private Map<Integer,Armazem> armazens;
    private Map<Integer,Rota> rotas;
    
    public Sistema(){
        this.armazens = new ArmazemDAO();
        this.rotas = new RotaDAO();
    }
    
}
