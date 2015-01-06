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
public class Login {
    private int id_login;
    private String user;
    private String pass;
    
    public Login(){
        this.id_login=0;
        this.user="";
        this.pass="";
    }
    
    public Login(String user, String pass){

        this.user=user;
        this.pass=pass;
    }
    
    public Login(Login l){
        this.id_login=l.getId_login();
        this.user=l.getUser();
        this.pass=l.getPass();
    }
    
    

    public int getId_login() {
        return id_login;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Login aux = (Login) obj;
        return this.id_login == aux.getId_login();
    }
    
    public Login clone(){
        return new Login(this);
    }
    
}
