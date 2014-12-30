
import Classes.Armazem;
import Classes.Encomenda;
import Classes.Produto;
import Classes.Veiculo;
import ClassesDAO.ArmazemDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ClassesDAO.ConexaoBD;
import ClassesDAO.EncomendaDAO;
import ClassesDAO.ProdutoDAO;
import ClassesDAO.RotaDAO;
import ClassesDAO.VeiculoDAO;
import java.sql.PreparedStatement;

public class main {

   
    public static void main(String[] args) throws SQLException {
        
        // Connect to Oracle Database
        ConexaoBD.iniciarConexao();
        
        

        //ArmazemDAO teste = new ArmazemDAO();
        //ProdutoDAO teste = new ProdutoDAO();
        //RotaDAO teste = new RotaDAO();
        //EncomendaDAO c1 = new EncomendaDAO(4);
        
        /* VeiculoDAO TESTE */
        VeiculoDAO v = new VeiculoDAO(1); // 1 do armazem
        int size = v.size();
        boolean emp = v.isEmpty();
        boolean contains = v.containsKey(3);
        String veic = v.get(10).toString();
        //Veiculo v2 = new Veiculo(0,"Ford Transit","99-ZZ-99",100,1);
        //v.put(2, v2);
        String novo = v.get(22).toString();
        
        v.remove(22);
        v.remove(21);
        v.remove(23);
        
        //boolean emp = teste.isEmpty();
        //boolean tam = teste.isEmpty();
        //boolean tam = teste.containsKey(424);
        //Encomenda e1 = c1.get(4);
        //c1.put(2, e1);
        //Produto p1 = teste.get(1);
        //Produto p2 = teste.get(2);
        
        //System.out.println(p1.toString());
        //System.out.println(p2.toString());
        
        //teste.put(1000, p1);
        //teste.put(1001, p2);
        
        //teste.remove(441);
        //teste.remove(442);
        
            
        //ResultSet rs2 = statement.executeQuery("SELECT count(*) FROM cliente");
        
       // if(rs2.next())
            //System.out.println("Numero de Clientes : "+rs2.getString(1));
        
        //rs2.close();
        
        System.out.println("Empty : "+emp);
        System.out.println("Size : "+size);
        System.out.println("Empty : "+contains);
        System.out.println(veic);
        System.out.println(novo);
        
        ConexaoBD.terminarConexao();
    }
}
