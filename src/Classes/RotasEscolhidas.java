/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.ClienteDAO;
import ClassesDAO.EncomendaDAO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class RotasEscolhidas {
    private int id_rota_escolhida;
    private int escolhida; // idrota
    
    public RotasEscolhidas(){
        this.id_rota_escolhida=0;
        this.escolhida=0;
    }
    
    public RotasEscolhidas(int id, int escolhida){
        this.id_rota_escolhida=id;
        this.escolhida=escolhida;
    }
    
    
    public RotasEscolhidas(RotasEscolhidas r){
        this.id_rota_escolhida=r.getEscolhida();
        this.escolhida=r.getEscolhida();
    }

    public int getId_rota_escolhida() {
        return id_rota_escolhida;
    }

    public int getEscolhida() {
        return escolhida;
    }

    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        RotasEscolhidas aux = (RotasEscolhidas) obj;
        return this.id_rota_escolhida == aux.getId_rota_escolhida();
    }
    
    public RotasEscolhidas clone(){
        return new RotasEscolhidas(this);
    }
}
