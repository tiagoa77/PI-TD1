package Classes;

/**
 *
 * @author Tiago
 */
public class Funcionario {
    private int id_funcionario;
    private String nome;
    private String data_nasc;
    private int contacto;
    private int armazem_id_armazem;

    public Funcionario(){
        this.id_funcionario=0;
        this.nome="";
        this.data_nasc="";
        this.contacto=0;
        this.armazem_id_armazem=0;
    }
    
    public Funcionario(Funcionario f){
        this.id_funcionario=f.getId_funcionario();
        this.nome=f.getNome();
        this.data_nasc=f.getData_nasc();
        this.contacto=f.getContacto();
        this.armazem_id_armazem=f.getArmazem_id_armazem();
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
