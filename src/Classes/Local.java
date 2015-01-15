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
    private String concelho;
    
    public Local(){
        this.id_local=0;
        this.morada="";
        this.concelho="";
    }
    
    public Local(int id,String m, String c){
        this.id_local=id;
        this.morada=m;
        this.concelho=c;
    }
    
    public Local(Local l){
        this.id_local=l.getId_local();
        this.morada=l.getMorada();
        this.concelho=l.getConcelho();
    }

    public String getConcelho() {
        return concelho;
    }
    
    public int getId_local() {
        return id_local;
    }

    public String getMorada() {
        return morada;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
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
