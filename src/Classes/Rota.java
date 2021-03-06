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
    private String aprovacao;
    private int r_id_funcionario;
    private int r_id_veiculo;
    private Map<Integer,Encomenda> encomendas;
    private Map<Integer,Cliente> clientes;
    
    public Rota(){
        this.id_rota=0;
        this.data_hora= new Date();
        this.listaClientes="";
        this.aprovacao="Nao Aprovado";
        this.r_id_funcionario=0;
        this.r_id_veiculo=0;
        this.encomendas=new EncomendaDAO();
        this.clientes=new ClienteDAO();
    }
    
    public Rota(int id, Date data_hora,String listaClientes,String estado){
        this.id_rota=id;
        this.data_hora=data_hora;
        this.listaClientes=listaClientes;
        this.aprovacao=estado;
        this.clientes=new ClienteDAO();
    }
    
    public Rota(int id, Date data_hora,String listaClientes,int id_funcionario,int id_veiculo,String estado){
        this.id_rota=id;
        this.data_hora=data_hora;
        this.listaClientes=listaClientes;
        this.aprovacao=estado;
        this.r_id_funcionario=id_funcionario;
        this.r_id_veiculo=id_veiculo;
        this.clientes=new ClienteDAO();
    }
    
    public Rota(Rota r){
        this.id_rota=r.getId_rota();
        this.data_hora=r.getData_hora();
        this.encomendas=r.getEncomendas();
        this.clientes=r.getClientes();
        this.aprovacao=r.getAprovacao();
    }

    public String getAprovacao() {
        return aprovacao;
    }

    public int getR_id_funcionario() {
        return r_id_funcionario;
    }

    public int getR_id_veiculo() {
        return r_id_veiculo;
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
