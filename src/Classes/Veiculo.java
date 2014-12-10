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
    private int armazem_id_armazem;

    public Veiculo(){
        this.id_veiculo=0;
        this.marca="";
        this.matricula="";
        this.armazem_id_armazem=0;
    }
    
    public Veiculo(Veiculo v){
        this.id_veiculo=v.getId_veiculo();
        this.marca=v.getMarca();
        this.matricula=v.getMatricula();
        this.armazem_id_armazem=v.getArmazem_id_armazem();
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
}
