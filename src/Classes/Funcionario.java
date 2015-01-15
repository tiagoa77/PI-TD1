package Classes;

import sun.nio.cs.ext.TIS_620;

/**
 *
 * @author Tiago
 */
public class Funcionario {
    private int id_funcionario;
    private String nome;
    private String data_nasc;
    private String morada;
    private int contacto;
    private String funcao;
    private int activo;
    private int armazem_id_armazem;

    public Funcionario(){
        this.id_funcionario=0;
        this.nome="";
        this.data_nasc="";
        this.morada="";
        this.contacto=0;
        this.funcao="";
        this.activo=0;
        this.armazem_id_armazem=0;
    }
    
    public Funcionario(int id_funcionario,String nome, String data,String morada, int contacto, String funcao, int activo,int id_armazem){
        this.id_funcionario=id_funcionario;
        this.nome=nome;
        this.data_nasc=data;
        this.morada=morada;
        this.contacto=contacto;
        this.funcao=funcao;
        this.activo=activo;
        this.armazem_id_armazem=id_armazem;
    }
    
    public Funcionario(int id_funcionario,String nome, String data,String morada, int contacto, String funcao){
        this.id_funcionario=id_funcionario;
        this.nome=nome;
        this.data_nasc=data;
        this.morada=morada;
        this.contacto=contacto;
        this.funcao=funcao;
    }
    
    
    public Funcionario(Funcionario f){
        this.id_funcionario=f.getId_funcionario();
        this.nome=f.getNome();
        this.data_nasc=f.getData_nasc();
        this.morada=f.getMorada();
        this.contacto=f.getContacto();
        this.funcao=f.getFuncao();
        this.activo=f.getActivo();
        this.armazem_id_armazem=f.getArmazem_id_armazem();
    }

    public String getMorada() {
        return morada;
    }

    public String getFuncao() {
        return funcao;
    }

    public int getActivo() {
        return activo;
    }
    
    public int getId_funcionario() {
        return id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public int getContacto() {
        return contacto;
    }

    public int getArmazem_id_armazem() {
        return armazem_id_armazem;
    }
    
    public boolean equals(Object obj){
        if(this==obj) return true;
        if((obj==null) || (this.getClass() != obj.getClass()))
            return false;
        Funcionario aux = (Funcionario) obj;
        return this.id_funcionario == aux.getId_funcionario();
    }
    
    public Funcionario clone(){
        return new Funcionario(this);
    }
    
    
}
