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
public class Local {
    private int id_local;
    private String morada;
    
    public Local(){
        this.id_local=0;
        this.morada="";
    }
    
    public Local(Local l){
        this.id_local=l.getId_local();
    }

    public int getId_local() {
        return id_local;
    }

    public String getMorada() {
        return morada;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Local aux = (Local) obj;
        return this.id_local == aux.getId_local();  
    }
    
    public Local clone(){
        return new Local(this);
    }
    
    
}
