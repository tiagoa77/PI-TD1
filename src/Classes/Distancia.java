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
public class Distancia {
    private int origem;
    private int destino; // idrota
    private int distancia;
    
    public Distancia(){
        this.origem=0;
        this.destino=0;
        this.distancia=0;
    }
    
    public Distancia(int origem, int destino, int distancia){
        this.origem=origem;
        this.destino=destino;
        this.distancia=distancia;
    }
    
    
    public Distancia(Distancia r){
        this.origem=r.getOrigem();
        this.destino=r.getDestino();
        this.distancia=r.getDistancia();
    }

    public int getOrigem() {
        return origem;
    }

    public int getDestino() {
        return destino;
    }

    public int getDistancia() {
        return distancia;
    }

    
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Distancia aux = (Distancia) obj;
        return this.origem == aux.getOrigem();
    }
    
    public Distancia clone(){
        return new Distancia(this);
    }
}
