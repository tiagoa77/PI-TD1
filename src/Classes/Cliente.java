/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.EncomendaDAO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class Cliente {
    private int id_cliente;
    private String nome_farmacia;
    private String nome_farmaceutico;
    private int contacto;
    private int nif;
    private int activo;
    private int local_id_local;
    private Map<Integer,Encomenda> encomendas;
    
    public Cliente(){
        this.id_cliente=0;
        this.nome_farmacia="";
        this.nome_farmaceutico="";
        this.contacto=0;
        this.nif=0;
        this.activo=0;
        this.local_id_local=0;
        this.encomendas=new EncomendaDAO();
    }
    
    public Cliente(int id, String nome_far, String nome_resp, int contac, int nif, int activo, int id_local){
        this.id_cliente=id;
        this.nome_farmacia=nome_far;
        this.nome_farmaceutico=nome_resp;
        this.contacto=contac;
        this.nif=nif;
        this.activo=activo;
        this.local_id_local=id_local;
        this.encomendas=new EncomendaDAO();
    }
    
    public Cliente(int id, String nome_far, String nome_resp, int contac, int nif){
        this.id_cliente=id;
        this.nome_farmacia=nome_far;
        this.nome_farmaceutico=nome_resp;
        this.contacto=contac;
        this.nif=nif;
    }
    
    public Cliente(Cliente c){
        this.id_cliente=c.getId_cliente();
        this.nome_farmacia=c.getNome_farmacia();
        this.nome_farmaceutico=c.getNome_farmaceutico();
        this.contacto=c.getContacto();
        this.nif=c.getNif();
        this.activo=c.getActivo();
        this.local_id_local=c.getLocal_id_local();
        this.encomendas=c.getEncomendas();
        
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getNome_farmacia() {
        return nome_farmacia;
    }

    public String getNome_farmaceutico() {
        return nome_farmaceutico;
    }

    public int getContacto() {
        return contacto;
    }

    public int getNif() {
        return nif;
    }

    public int getActivo() {
        return activo;
    }

    public int getLocal_id_local() {
        return local_id_local;
    }

    public HashMap<Integer, Encomenda> getEncomendas() {
        HashMap<Integer, Encomenda> aux = new HashMap<Integer, Encomenda>();
        for(Encomenda e : this.encomendas.values())
            aux.put(e.getId_encomenda(), e.clone());
        return aux;
    }
    
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Cliente aux = (Cliente) obj;
        return this.id_cliente == aux.getId_cliente();
    }
    
    public Cliente clone(){
        return new Cliente(this);
    }
}
