/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDAO;

import Classes.Armazem;
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

public class ArmazemDAO implements Map<Integer, Armazem> {
    private static final int localidade=2;
    
    public ArmazemDAO() {
    }
    
    public int size() {
        int res = 0;
        try {
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM armazem";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                res++;
            }
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
            String sql = "SELECT * FROM armazem";
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
            Integer id = (Integer) key;
            String sql = "SELECT * FROM armazem WHERE id_armazem= " + id;
            Statement stm = ConexaoBD.getConexao().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            res = rs.next();

            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {}
        return res;
    }

    @Override
    public Armazem get(Object key) {
        Armazem a = null;
        int id_armazem=0;
        String localidade="";
        String coordenadas="";
        try {
            Integer id = (Integer) key;
            Statement stm = ConexaoBD.getConexao().createStatement();
            String sql = "SELECT * FROM armazem a WHERE a.id_armazem= "+id;
            ResultSet rs = stm.executeQuery(sql);
            
            if(rs.next()) {
                id_armazem = rs.getInt(1);
                localidade = rs.getString(2);
                coordenadas = rs.getString(3);
            }            
            a = new Armazem(id_armazem,localidade,coordenadas);
            
            ConexaoBD.fecharCursor(rs, stm);
        } catch (SQLException e) {
        }
        return a;    
    }

    @Override
    public Armazem put(Integer key, Armazem value) {
        Armazem a = null;
        CallableStatement cs = null;
        int id_armazem=0;
        String id = "select id_armazem.NEXTVAL from dual";
        try{
            String sql = "{call adiciona_armazem(?,?,?)}";
            PreparedStatement pst = ConexaoBD.getConexao().prepareStatement(id);
            synchronized(this){
                ResultSet rs = pst.executeQuery();
                if(rs.next())
                    id_armazem=rs.getInt(1);
            }
                      
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id_armazem);
            cs.setString(2, value.getLocalidade());
            cs.setString(3, value.getCoordenadas());
            cs.executeQuery();
            
        }catch(SQLException e){ }
        
        return a;
    }

    @Override
    public Armazem remove(Object key) {
        Armazem a = null;
        CallableStatement cs = null;
        try {
            Integer id = (Integer) key;
            String sql = "{call remove_encomenda(?)}";
            cs = ConexaoBD.getConexao().prepareCall(sql);
            cs.setInt(1,id);
            cs.execute();
        } catch (SQLException e) {
        }
        return a;   
    }
    
    @Override
    public Set<Integer> keySet() {
        Set<Integer> res = new TreeSet<>();
        try {
            String sql = "SELECT id_armazem FROM armazem";
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
    public Collection<Armazem> values() {
        Collection<Armazem> res = new HashSet<>();
        try {
            String sql = "SELECT localidade FROM armazem";
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
    public Set<Entry<Integer, Armazem>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void putAll(Map<? extends Integer, ? extends Armazem> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
