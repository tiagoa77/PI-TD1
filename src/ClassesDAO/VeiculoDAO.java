/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Veiculo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Tiago
 */
public class VeiculoDAO implements Map<Integer,Veiculo>{
    private int idarmazem;

    public VeiculoDAO() {
    }
    
    
    public VeiculoDAO(int id_armazem) {
        this.idarmazem=id_armazem;
    }

    @Override
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Veiculo";
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next())
                res++;
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        boolean res = false;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Veiculo";
            ResultSet rs = stm.executeQuery(sql);
            if(!rs.next())
                res=true;
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean res = false;
        try {
            int id = (Integer) key;
            String sql = "SELECT * FROM Veiculo WHERE id_veiculo = " + id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Veiculo get(Object key) {
        Veiculo v = null;
        try {
            int id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM Veiculo WHERE id_veiculo=" +id;
            ResultSet rs = stm.executeQuery(sql);
            
            if(rs.next()) {
                int id_veiculo = rs.getInt(1);
                String marca = rs.getString(2);
                String matricula = rs.getString(3);
                int capacidade = rs.getInt(4);
                int a_id_armazem = rs.getInt(5);
                                
                v = new Veiculo(id_veiculo,marca,matricula,capacidade,a_id_armazem);
            }            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return v;
    }

    @Override
    public Veiculo put(Integer key, Veiculo value) {
        Veiculo v = null;
        CallableStatement cs = null;
        
        int id_veiculo=0;
        
        String id = "select id_veiculo.NEXTVAL from dual";
        try{
            String sql = "{call adiciona_veiculo(?,?,?,?,?)}";
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id);
            synchronized(this){
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                    id_veiculo=rs.getInt(1);
            }
                        
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id_veiculo);
            cs.setString(2, value.getMarca());
            cs.setString(3, value.getMatricula());
            cs.setInt(4, value.getCapacidade());
            cs.setInt(5, value.getArmazem_id_armazem());
            cs.executeQuery();
            
        }catch(SQLException e){ }
        
        return v;
    }

    @Override
    public Veiculo remove(Object key) {
        Veiculo v = null;
        CallableStatement cs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_veiculo(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id);
            cs.execute();
        } catch (SQLException e) {
        }
        return v;   
    }

    

    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT id_veiculo FROM veiculo";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next())
                res.add(rs.getInt(1));
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Collection<Veiculo> values() {
        Collection<Veiculo> res = new HashSet<>();
        try {
            String sql = "SELECT marca,matricula FROM veiculo";
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next())
                res.add(this.get(rs.getString(2)));
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return res;
    }

    @Override
    public Set<Entry<Integer, Veiculo>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void putAll(Map<? extends Integer, ? extends Veiculo> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
