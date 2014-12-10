/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.HashMap;

/**
 *
 * @author Tiago
 */
public class Armazem {
    private int id_armazem;
    private String localidade;
    private String coordenadas;
    
    private HashMap<Integer,Funcionario> funcionarios;
    private HashMap<Integer,Veiculo> veiculos;
    private HashMap<Integer,Produto> produtos;
    
    public Armazem(){
        this.id_armazem=0;
        this.localidade="";
        this.coordenadas="";
        this.funcionarios=new HashMap<>(); // quando tiver os dao vai ficar new FuncionarioDAO()
        this.veiculos=new HashMap<>();
        this.produtos=new HashMap<>();
    }
    
    public Armazem(Armazem a){
        this.id_armazem=a.getId_armazem();
        this.localidade=a.getLocalidade();
        this.coordenadas=a.getCoordenadas();
        this.funcionarios=a.getFuncionarios();
        
    }

    public int getId_armazem() {
        return id_armazem;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public HashMap<Integer, Funcionario> getFuncionarios() {
        HashMap<Integer,Funcionario> aux = new HashMap<>();
        
        for(Funcionario f : this.funcionarios.values())
                aux.put(f.getId_funcionario(),f.clone());
        
        return aux;
    }

    public HashMap<Integer, Veiculo> getVeiculos() {
        return veiculos;
    }

    public HashMap<Integer, Produto> getProdutos() {
        return produtos;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Armazem aux = (Armazem) obj;
        return this.id_armazem == aux.getId_armazem() && this.funcionarios.equals(aux.getFuncionarios());
    }
    
    public Armazem clone(){
        return new Armazem(this);
    }
    
    
}
