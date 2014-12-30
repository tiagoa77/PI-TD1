/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import ClassesDAO.FuncionarioDAO;
import ClassesDAO.ProdutoDAO;
import ClassesDAO.VeiculoDAO;
import java.util.HashMap;
import java.util.Map;

/** ESTÁ
 *
 * @author Tiago
 */
public class Armazem {
    private int id_armazem;
    private String localidade;
    private String coordenadas;
    
    private Map<Integer,Funcionario> funcionarios;
    private Map<Integer,Veiculo> veiculos;
    private Map<Integer,Produto> produtos;
    
    public Armazem(){
        this.id_armazem=0;
        this.localidade="";
        this.coordenadas="";
        this.funcionarios=new FuncionarioDAO(this.id_armazem); // quando tiver os dao vai ficar new FuncionarioDAO()
        this.veiculos=new VeiculoDAO(this.id_armazem);
        //this.produtos=new ProdutoDAO(this.id_armazem);
    }
    
    public Armazem(int id, String loc,String coord){
        this.id_armazem=id;
        this.localidade=loc;
        this.coordenadas=coord;
        this.funcionarios=new FuncionarioDAO(this.id_armazem); // quando tiver os dao vai ficar new FuncionarioDAO()
        this.veiculos=new VeiculoDAO(this.id_armazem);
        //this.produtos=new ProdutoDAO(this.id_armazem);
    }

    public Armazem(Armazem a){
        this.id_armazem=a.getId_armazem();
        this.localidade=a.getLocalidade();
        this.coordenadas=a.getCoordenadas();
        this.funcionarios=a.getFuncionarios();
        this.funcionarios=new FuncionarioDAO(this.id_armazem); // quando tiver os dao vai ficar new FuncionarioDAO()
        this.veiculos=new VeiculoDAO(this.id_armazem);
        //this.produtos=new ProdutoDAO(this.id_armazem);
        
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
            aux.put(f.getId_funcionario(),f.clone()); // tirar os clones
        return aux;
    }

    public HashMap<Integer, Veiculo> getVeiculos() {
        HashMap<Integer, Veiculo> aux = new HashMap<>();
        for(Veiculo v : this.veiculos.values())
            aux.put(v.getId_veiculo(), v.clone());
        return aux;
    }

    public HashMap<Integer, Produto> getProdutos() {
        HashMap<Integer, Produto> aux = new HashMap<>();
        for(Produto p : this.produtos.values())
            aux.put(p.getId_produto(), p.clone());
        return aux;
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
