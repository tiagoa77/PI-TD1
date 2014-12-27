/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Tiago
 */
public class Produto {
    private int id_produto;
    private String nome;
    private double preco;
    private String tipo;
    private String descricao;
    private int activo;
    private int armazem_id_armazem;
    private int encomenda_id_encomenda;
    
    public Produto(){
        this.id_produto=0;
        this.nome="";
        this.preco=0;
        this.tipo="";
        this.descricao="";
        this.activo=0;
        this.armazem_id_armazem=0;
        this.encomenda_id_encomenda=0;
    }
    
    public Produto(int id_produto, String nome, double preco, String tipo, String descricao, int activo, int id_armazem,int id_encomenda){
        this.id_produto=id_produto;
        this.nome=nome;
        this.preco=preco;
        this.tipo=tipo;
        this.descricao=descricao;
        this.activo=activo;
        this.armazem_id_armazem=id_armazem;
        this.encomenda_id_encomenda=id_encomenda;
    }
    
    public Produto(Produto p){
        this.id_produto=p.getId_produto();
        this.nome=p.getNome();
        this.preco=p.getPreco();
        this.tipo=p.getTipo();
        this.descricao=p.getDescricao();
        this.activo=p.activo;
        this.armazem_id_armazem=p.getArmazem_id_armazem();
        this.encomenda_id_encomenda=p.getEncomenda_id_encomenda();
    }

    public double getPreco() {
        return preco;
    }

    public int getEncomenda_id_encomenda() {
        return encomenda_id_encomenda;
    }

    public int getId_produto() {
        return id_produto;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getActivo() {
        return activo;
    }

    public int getArmazem_id_armazem() {
        return armazem_id_armazem;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Produto aux = (Produto) obj;
        return this.id_produto == aux.getId_produto();
    }
    
    public Produto clone(){
        return new Produto(this);
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder("------ Produto---------\n");
        s.append("ID: " + id_produto + "\n");
        s.append("Nome: " + nome + "\n");
        s.append("Preco: " + preco + "\n");
        s.append("Tipo: " + tipo + "\n");
        s.append("Descricao: " + descricao + "\n");
        s.append("Activo: " + activo + "\n");
        s.append("ID_Armazem: " + armazem_id_armazem + "\n");
        s.append("ID_Encomenda: " + encomenda_id_encomenda + "\n");
        
        return s.toString();
    }
}
