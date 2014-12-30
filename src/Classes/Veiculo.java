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
public class Veiculo {
    private int id_veiculo;
    private String marca;
    private String matricula;
    private int capacidade;
    private int armazem_id_armazem;

    public Veiculo(){
        this.id_veiculo=0;
        this.marca="";
        this.matricula="";
        this.capacidade=0;
        this.armazem_id_armazem=0;
    }
    
    public Veiculo(int id, String marca, String matricula, int capacidade,int id_armazem){
        this.id_veiculo=id;
        this.marca=marca;
        this.matricula=matricula;
        this.capacidade=capacidade;
        this.armazem_id_armazem=id_armazem;
    }
    
    public Veiculo(Veiculo v){
        this.id_veiculo=v.getId_veiculo();
        this.marca=v.getMarca();
        this.matricula=v.getMatricula();
        this.capacidade=v.getCapacidade();
        this.armazem_id_armazem=v.getArmazem_id_armazem();
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    
    public int getId_veiculo() {
        return id_veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getArmazem_id_armazem() {
        return armazem_id_armazem;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Veiculo aux = (Veiculo) obj;
        return this.id_veiculo == aux.getId_veiculo();
    }
    
    public Veiculo clone(){
        return new Veiculo(this);
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder("------ Veiculo---------\n");
        s.append("ID: " + id_veiculo + "\n");
        s.append("Marca: " + marca + "\n");
        s.append("Matricula: " + matricula + "\n");
        s.append("Capacidade: " + capacidade + "\n");
        s.append("ID_ARMAZEM: " + armazem_id_armazem + "\n");
       
        return s.toString();
    }
}
