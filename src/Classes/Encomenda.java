/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.ProdutoDAO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tiago
 */
public class Encomenda {
    private int id_encomenda;
    private int factura;
    private int banheiras; //numero total de banheiras
    private int activa;
    private int cliente_id_cliente;
    private Map<Integer,Produto> produtos;
    
    public Encomenda(){
        this.id_encomenda=0;
        this.factura=0;
        this.banheiras=0;
        this.activa=0;
        this.cliente_id_cliente=0;
        this.produtos=new ProdutoDAO(this.id_encomenda);
    }
    
    public Encomenda(int id, int factura, int banheiras, int activa, int id_cliente){
        this.id_encomenda=0;
        this.factura=0;
        this.banheiras=0;
        this.activa=0;
        this.cliente_id_cliente=0;
        this.produtos=new ProdutoDAO(this.id_encomenda);
    }
    
    public Encomenda(Encomenda e){
        this.id_encomenda=e.getId_encomenda();
        this.factura=e.getFactura();
        this.banheiras=e.getBanheiras();
        this.activa=e.getActiva();
        this.cliente_id_cliente=e.getCliente_id_cliente();
        this.produtos=e.getProdutos();
    }

    public int getId_encomenda() {
        return id_encomenda;
    }

    public int getFactura() {
        return factura;
    }

    public int getBanheiras() {
        return banheiras;
    }

    public int getActiva() {
        return activa;
    }

    public int getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public HashMap<Integer, Produto> getProdutos() {
        HashMap<Integer, Produto> aux = new HashMap<>();
        
        for(Produto p: this.produtos.values())
            aux.put(p.getId_produto(), p.clone());
        return aux;
    }
   
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Encomenda aux = (Encomenda) obj;
        return this.id_encomenda == aux.getId_encomenda();
    }
    
    public Encomenda clone(){
        return new Encomenda(this);
    }
}
