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
    private String tipo;
    private String descricao;
    private int activo;
    private int armazem_id_armazem;
    
    public Produto(){
        this.id_produto=0;
        this.nome="";
        this.tipo="";
        this.descricao="";
        this.activo=0;
        this.armazem_id_armazem=0;
    }
    
    public Produto(Produto p){
        this.id_produto=p.getId_produto();
        this.nome=p.getNome();
        this.tipo=p.getTipo();
        this.descricao=p.getDescricao();
        this.activo=p.activo;
        this.armazem_id_armazem=p.getArmazem_id_armazem();
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
}
