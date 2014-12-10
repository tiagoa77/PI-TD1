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
    private int id;
    private String localidade;
    private String coordenadas;
    
    private HashMap<Integer,Funcionario> funcionarios;
    private HashMap<Integer,Veiculo> veiculos;
    private HashMap<Integer,Produto> produtos;
    
    public Armazem(){
        this.id=0;
        this.localidade="";
        this.coordenadas="";
        this.funcionarios=new HashMap<>(); // quando tiver os dao vai ficar new FuncionarioDAO()
        this.veiculos=new HashMap<>();
        this.produtos=new HashMap<>();
    }
    
    public Armazem(Armazem a){
        this.id=a.getId();
        this.localidade=a.getLocalidade();
        this.coordenadas=a.getCoordenadas();
        this.funcionarios=a.getFuncionarios();
        
    }

    public int getId() {
        return id;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public HashMap<Integer, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public HashMap<Integer, Veiculo> getVeiculos() {
        return veiculos;
    }

    public HashMap<Integer, Produto> getProdutos() {
        return produtos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setFuncionarios(HashMap<Integer, Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setVeiculos(HashMap<Integer, Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void setProdutos(HashMap<Integer, Produto> produtos) {
        this.produtos = produtos;
    }
    
    
    
}
