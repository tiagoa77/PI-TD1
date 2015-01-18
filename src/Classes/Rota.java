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
public class Rota {
    private int id_rota;
    private Date data_hora;   
    private String listaClientes;
    private Map<Integer,Encomenda> encomendas;
    private Map<Integer,Cliente> clientes;
    
    public Rota(){
        this.id_rota=0;
        this.data_hora= new Date();
        this.listaClientes="";
        this.encomendas=new EncomendaDAO();
        this.clientes=new ClienteDAO();
    }
    
    public Rota(int id, Date data_hora,String listaClientes){
        this.id_rota=id;
        this.data_hora=data_hora;
        this.listaClientes=listaClientes;
        this.clientes=new ClienteDAO();
    }
    
    
    public Rota(Rota r){
        this.id_rota=r.getId_rota();
        this.data_hora=r.getData_hora();
        this.encomendas=r.getEncomendas();
        this.clientes=r.getClientes();
    }

    public String getListaClientes() {
        return listaClientes;
    }

    public int getId_rota() {
        return id_rota;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public HashMap<Integer, Encomenda> getEncomendas() {
        HashMap<Integer, Encomenda> aux = new HashMap<>();
        for(Encomenda e: this.encomendas.values())
            aux.put(e.getId_encomenda(), e.clone());
        return aux;
    }

    public HashMap<Integer, Cliente> getClientes() {
        HashMap<Integer, Cliente> aux = new HashMap<>();
        for(Cliente c : this.clientes.values())
            aux.put(c.getId_cliente(), c.clone());
        return aux;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Rota aux = (Rota) obj;
        return this.id_rota == aux.getId_rota();
    }
    
    public Rota clone(){
        return new Rota(this);
    }
}
